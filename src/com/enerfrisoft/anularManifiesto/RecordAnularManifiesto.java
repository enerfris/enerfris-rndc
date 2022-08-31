/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.anularManifiesto;


import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.Parser;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.WebServices;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author sebastianaf
 */
public class RecordAnularManifiesto implements Runnable{
    private  AnularManifiesto manifiesto;
    private JFrame frame;
    private String consecutivo;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public RecordAnularManifiesto( AnularManifiesto manifiesto, JFrame frame,String consecutivo,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;        
        this.dataSource = dataSource;
        this.manifiesto = manifiesto;
        this.frame = frame;
        this.consecutivo = consecutivo;
    }
    
    private void liberarRemesas(){
        UniversalDAO.execute(""
                + "update remesa "
                + "set remesa.manifiestoConsecutivo = NULL "
                + "where remesa.manifiestoConsecutivo = '"+consecutivo+"'", conn);
    }
    
    private void registrarManifiesto() {
       try {
            String request = XMLAnularManifiesto.grabar(manifiesto);
            String answer = WebServices.getAnswer(request);

            if (WebServices.testRndcConnection()) {
                String ingresoId = Parser.verify(answer).get(0);
                if (!ingresoId.equals("error")) {
                    manifiesto.setIngresoId(ingresoId);
                    Thread query = new Thread(new QueryAnularManifiesto(manifiesto,dataSource, conn));
                    query.start();

                    liberarRemesas();
                    
                    Icon icon = new ImageIcon(frame.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
                    JOptionPane.showMessageDialog(frame, "El manifiesto " + consecutivo + " ha sido anulado", "Información", JOptionPane.ERROR_MESSAGE, icon);
                   
                    
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
            System.out.println("anularManifiesto" + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        registrarManifiesto();
    }
}
