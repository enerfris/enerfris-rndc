/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.vehiculo;

import com.enerfrisoft.dao.DataSourceClass;
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

public class RegistrarVehiculo implements Runnable {

    private Vehiculo vehiculo;
    private String tipoVehiculo;
    private JFrame frame;
    private JButton boton;
    
    private Boolean updateVehiculo;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RegistrarVehiculo(Vehiculo vehiculo, Boolean updateVehiculo, String tipoVehiculo, JFrame frame, JButton boton, DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        
        
        this.updateVehiculo = updateVehiculo;
        
        this.vehiculo = vehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.frame = frame;
        this.boton = boton;
    }

    private void registrarVehiculo() {
        try {
            String request = XMLVehiculo.grabar(vehiculo);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
                String ingresoId = Parser.verify(answer).get(0);
                if (!ingresoId.equals("error")) {
                    vehiculo.setIngresoId(ingresoId);

                    Thread query1 = null;
                    
                    if (updateVehiculo) {
                        query1 = new Thread(new UpdateVehiculo(vehiculo, dataSource, conn));
                        query1.start();
                    } else {
                        query1 = new Thread(new QueryVehiculo(vehiculo, dataSource, conn));
                        query1.start();
                    }

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "El " + tipoVehiculo + " se ha registrado", "Información", JOptionPane.ERROR_MESSAGE, icon);

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
            System.out.println("registrarVehiculo" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarVehiculo();
    }

}
