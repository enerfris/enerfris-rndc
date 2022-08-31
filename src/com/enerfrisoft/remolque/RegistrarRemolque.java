package com.enerfrisoft.remolque;

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

public class RegistrarRemolque implements Runnable {

    private String tipoRemolque;
    private Remolque remolque;
    private JFrame frame;
    private JButton boton;
    
    private Boolean updateRemolque;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;
    

    public RegistrarRemolque(Remolque remolque, Boolean updateRemolque, JFrame frame, JButton boton, String tipoRemolque, DataSourceClass dataSource,Connection conn) {
        
        this.conn = conn;
        this.dataSource = dataSource;
        
        this.updateRemolque = updateRemolque;
        this.remolque = remolque;
        this.frame = frame;
        this.boton = boton;
        this.tipoRemolque = tipoRemolque;
    }

    private void registrarRemolque() {
        try {
            String request = XMLRemolque.grabar(remolque);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
                String ingresoId = Parser.verify(answer).get(0);
                if (!ingresoId.equals("error")) {
                    remolque.setIngresoId(ingresoId);
                    if (updateRemolque) {
                        Thread query = new Thread(new UpdateRemolque(remolque, dataSource, conn));
                        query.start();
                    } else {
                        Thread query = new Thread(new QueryRemolque(remolque, dataSource, conn));
                        query.start();
                    }

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "El " + tipoRemolque + " se ha registrado", "Información", JOptionPane.ERROR_MESSAGE, icon);

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
            System.out.println("registrarRemolque" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarRemolque();
    }

}
