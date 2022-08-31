/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.manifiesto;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.Parser;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.VerifyConnection;
import com.enerfrisoft.tools.WebServices;
import com.enerfrisoft.usuario.Tools;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class RecordManifiesto implements Runnable {

    private Manifiesto manifiesto;
    private JFrame frame;
    private JButton boton;
    private String consecutivo;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RecordManifiesto(Manifiesto manifiesto, JFrame frame, JButton boton, String consecutivo, DataSourceClass dataSource, Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;   
        this.defaultServerConn = this.dataSource.conectar(0);
        this.manifiesto = manifiesto;
        this.frame = frame;
        this.boton = boton;
        this.consecutivo = consecutivo;
    }

    private String buildSecure() {
        SimpleDateFormat formato = new SimpleDateFormat("@EEE MMMM HH:mm:ss.S@");
        return formato.format(new java.util.Date());
    }

    private void nextConsecutivoManifiesto() {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        int lastInt = Integer.valueOf(UniversalDAO.getString(""
                + "select server.conNumMan "
                + "from server "
                + "where server.id='" + server + "'", defaultServerConn));
        lastInt += 1;
        UniversalDAO.execute(""
                + "update server "
                + "set server.conNumMan = '" + String.valueOf(lastInt) + "' "
                + "where server.id='" + server + "'", defaultServerConn);
    }
    
    private void nextConsecutivoOC() {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        int lastInt = Integer.valueOf(UniversalDAO.getString(""
                + "select server.conNumOC "
                + "from server "
                + "where server.id='" + server + "'", defaultServerConn));
        lastInt += 1;
        UniversalDAO.execute(""
                + "update server "
                + "set server.conNumOC = '" + String.valueOf(lastInt) + "' "
                + "where server.id='" + server + "'", defaultServerConn);
    }

    private void registrarRemesa() {
        try {
            String request = XMLManifiesto.grabar(manifiesto);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                Thread verificar = new Thread(new VerifyConnection(boton, frame));
                verificar.start();
                ArrayList<String> res = Parser.verify(answer);
                if (!res.get(0).equals("error")) {
                    manifiesto.setIngresoId(res.get(0));
                    manifiesto.setSEGURIDADQR(res.get(1));
                    Thread query = new Thread(new QueryManifiesto(manifiesto, dataSource, conn));
                    query.start();

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "El manifiesto " + consecutivo + " se ha registrado", "Información", JOptionPane.ERROR_MESSAGE, icon);
                    
                    nextConsecutivoManifiesto();
                    nextConsecutivoOC();
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
            System.out.println("recordManifiesto" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarRemesa();
    }

    public static void main(String[] args) {
        SimpleDateFormat formato = new SimpleDateFormat("@EEE MMMM HH:mm:ss.S@");

        System.out.println(formato.format(new java.util.Date()));
        System.out.println(Tools.encrypt(formato.format(new java.util.Date()), null));
    }
}
