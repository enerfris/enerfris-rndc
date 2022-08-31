/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.anularRemesa;

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

/**
 *
 * @author sebastianaf
 */
public class RecordAnularRemesa implements Runnable{
    private AnularRemesa remesa;
    private JFrame frame;
    private String consecutivo;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RecordAnularRemesa(AnularRemesa remesa, JFrame frame,String consecutivo,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.remesa = remesa;
        this.frame = frame;
        this.consecutivo = consecutivo;
    }
    
    private void registrarRemesa() {
       try {
            String request = XMLAnularRemesa.grabar(remesa);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                String ingresoId = Parser.verify(answer).get(0);
                if (!ingresoId.equals("error")) {
                    remesa.setIngresoId(ingresoId);
                    Thread query = new Thread(new QueryAnularRemesa(remesa,dataSource, conn));
                    query.start();

                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "La remesa " + consecutivo + " ha sido anulada", "Información", JOptionPane.ERROR_MESSAGE, icon);
                } else {
                    JTextArea textArea = new JTextArea(Parser.getErrors(answer));
                    textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    scrollPane.setPreferredSize(new Dimension(300, 180));
                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/error.png"));
                    JOptionPane.showMessageDialog(null, scrollPane, "Error",JOptionPane.ERROR_MESSAGE,icon);
                
                }
            } 
        } catch (Exception e) {
            System.out.println("anularRemesa" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarRemesa();
    }
}
