/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import java.awt.Window;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastianaf
 */
public class VerifyConnection implements Runnable {

    private JButton connectionStatus;
    private Window window;

    public VerifyConnection(JButton connectionStatus, Window window) {
        this.connectionStatus = connectionStatus;
        this.window = window;
    }
    
    
    
    private Boolean auxVerify() {
        String path = "";
        if (WebServices.testRndcConnection()) {
            path = "/com/enerfrisoft/gui/sources/connection.png";
            Icon icon = new ImageIcon(this.getClass().getResource(path));
            connectionStatus.setIcon(icon);
            return true;
        } else {
            path = "/com/enerfrisoft/gui/sources/disconnection.png";
            Icon icon = new ImageIcon(this.getClass().getResource(path));
            connectionStatus.setIcon(icon);

        }
        return false;
    }

    private void verify() {
        if (auxVerify()) {
//            Icon icon = new ImageIcon(window.getClass().getResource("/com/enerfrisoft/gui/sources/ok.png"));
//            JOptionPane.showMessageDialog(window, "Conexión exitosa", "Información", JOptionPane.INFORMATION_MESSAGE, icon);
        } else {
            Icon icon = new ImageIcon(window.getClass().getResource("/com/enerfrisoft/gui/sources/error.png"));
            JOptionPane.showMessageDialog(window, "Conexión Interrumpida", "Error", JOptionPane.ERROR_MESSAGE, icon);
        }
    }

    @Override
    public void run() {
        verify();
    }

}
