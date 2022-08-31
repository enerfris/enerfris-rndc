/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.modal;

import com.enerfrisoft.tools.Parser;
import com.enerfrisoft.usuario.Tools;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sebastianaf
 */
public class Modal {

    public static void show(String titulo, String mensaje, JFrame frame, String type, String tipoMensaje) {
        switch (type) {
            case "large":
                JTextArea textArea = new JTextArea(mensaje);
                textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(300, 180));
                Icon icon = new ImageIcon(Modal.class.getResource("/com/enerfrisoft/gui/sources/" + tipoMensaje + ".png"));
                JOptionPane.showMessageDialog(frame, scrollPane, titulo, JOptionPane.ERROR_MESSAGE, icon);
                break;
            default:
                Icon icon2 = new ImageIcon(Modal.class.getResource("/com/enerfrisoft/gui/sources/" + tipoMensaje + ".png"));
                JOptionPane.showMessageDialog(frame, mensaje, titulo, JOptionPane.OK_OPTION, icon2);
        }
    }

    public static Boolean showComfirm(String titulo, String mensaje, JFrame frame, String tipoMensaje) {
        Icon icon = new ImageIcon(Modal.class.getResource("/com/enerfrisoft/gui/sources/" + tipoMensaje + ".png"));
        int t = JOptionPane.showConfirmDialog(frame, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if (t == 0) {
            return true;
        }
        return false;
    }
    
    public static String passModal(String titulo, JFrame frame) {
        JPasswordField pass1 = new JPasswordField();
        JPasswordField pass2 = new JPasswordField();
        Object[] inputFields = {"Escriba la contraseña", pass1,
                "Repita la contraseña", pass2};

        Icon icon = new ImageIcon(Modal.class.getResource("/com/enerfrisoft/gui/sources/" + "information" + ".png"));
        int option = JOptionPane.showConfirmDialog(frame,inputFields,titulo, JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,icon);

        if (option == JOptionPane.OK_OPTION) {
            if(String.valueOf(pass1.getPassword()).equals(String.valueOf(pass2.getPassword()))){
                if(String.valueOf(pass1.getPassword()).length()==0){
                    Modal.show("Error", "Las contraseña no pueden ser nulas", frame, "", "error");
                }
                return String.valueOf(pass1.getPassword());
            }else{
                Modal.show("Error", "Las contraseñas no coinciden", frame, "", "error");
                return null;
            }
        }
        return null;
    }
    
}
