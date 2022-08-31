/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tercero;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.sede.QuerySede;
import com.enerfrisoft.sede.Sede;
import com.enerfrisoft.sede.UpdateSede;
import com.enerfrisoft.tools.Parser;
import com.enerfrisoft.tools.VerifyConnection;
import com.enerfrisoft.tools.WebServices;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author sebastianaf
 */
public class RegistrarTercero implements Runnable {

    private Tercero tercero;
    private Sede sede;
    private JFrame frame;
    private JButton boton;
    private String terceroNombre;
    
    private Boolean updateTercero;
    private Boolean updateSede;
    
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RegistrarTercero(Tercero tercero, Boolean updateTercero, Sede sede,Boolean updateSede, JFrame frame, JButton boton, String terceroNombre, DataSourceClass dataSource,Connection conn) {
        this.updateTercero = updateTercero;
        this.updateSede = updateSede;
        this.tercero = tercero;
        this.frame = frame;
        this.boton = boton;
        this.terceroNombre = terceroNombre;
        this.sede = sede;
        
        this.conn = conn;
        this.dataSource = dataSource;
        

    }

    private void registrarTercero() {
        try {
            String request = XMLTercero.grabar(tercero);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
                String ingresoId = Parser.verify(answer).get(0);
                if (!ingresoId.equals("error")) {

                    tercero.setIngresoId(ingresoId);

                    Thread query1 = null;
                    Runnable run1 = null;
                    if (updateTercero) {
                        run1 = new UpdateTercero(tercero,dataSource, conn);
                    } else {
                        run1 = new QueryTercero(tercero, dataSource, conn);
                    }
                    
                    Thread query2 = null;
                    Runnable run2 = null;
                    if (updateSede) {
                        run2 = new UpdateSede(sede,dataSource, conn);
                    } else {
                        run2 = new QuerySede(sede, dataSource, conn);
                    }
                    
                    query1 = new Thread(run1);
                    query2 = new Thread(run2);
                    
                    query1.start();
                    query1.join();
                    query2.start();
                    query2.join();

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "El tercero \"" + terceroNombre + "\" se ha registrado", "Información", JOptionPane.ERROR_MESSAGE, icon);
                    frame.dispose();
                } else {
                    JTextArea textArea = new JTextArea(Parser.getErrors(answer));
                    textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    scrollPane.setPreferredSize(new Dimension(300, 180));
                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/error.png"));
                    JOptionPane.showMessageDialog(null, scrollPane, "Error", JOptionPane.ERROR_MESSAGE, icon);
                }
            } else {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
            }

        } catch (Exception e) {
            System.out.println("registrarTercero" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarTercero();
    }
}
