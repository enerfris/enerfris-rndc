/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.remesa;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.UniversalDAO;
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
public class RecordRemesa implements Runnable {

    private Remesa remesa;
    private JFrame frame;
    private JButton boton;
    private String remesaNombre;
    
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RecordRemesa(Remesa remesa, JFrame frame, JButton boton, String remesaNombre, DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        this.remesa = remesa;
        this.frame = frame;
        this.boton = boton;
        this.remesaNombre = remesaNombre;
    }

    private void nextConsecutivoRemesa() {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        int lastInt = Integer.valueOf(UniversalDAO.getString(""
                + "select server.conNumRem "
                + "from server "
                + "where server.id='" + server + "'", defaultServerConn));
        lastInt += 1;
        UniversalDAO.execute(""
                + "update server "
                + "set server.conNumRem = '" + String.valueOf(lastInt) + "' "
                + "where server.id='" + server + "'", defaultServerConn);
    }

    private void registrarRemesa() {
        try {
            String request = XMLRemesa.grabar(remesa);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
                String ingresoId = Parser.verify(answer).get(0); //por mejorar
                if (!ingresoId.equals("error")) {
                    remesa.setIngresoId(ingresoId);
                    Thread query = new Thread(new QueryRemesa(remesa, dataSource, conn));
                    query.start();

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "La remesa \"" + remesaNombre + "\" se ha registrado", "Información", JOptionPane.ERROR_MESSAGE, icon);

                    nextConsecutivoRemesa();
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
            System.out.println("registrarRemesa" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarRemesa();
    }
}
