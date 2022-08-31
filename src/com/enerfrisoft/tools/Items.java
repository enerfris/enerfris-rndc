/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sebastianaf
 */
public class Items {

    public static void setValue(Object o,String value) {
        try {
            if (o.getClass().getName().equals(new javax.swing.JTextField().getClass().getName())) {
                JTextField obj = (JTextField) o;
                obj.setText(value);
            }
            if (o.getClass().getName().equals(new javax.swing.JComboBox().getClass().getName())) {
                JComboBox obj = (JComboBox) o;
                obj.setSelectedItem(value);
            }
            if (o.getClass().getName().equals(new com.toedter.calendar.JDateChooser().getClass().getName())) {
                JDateChooser obj = (JDateChooser) o;
                obj.setDate(DateParsing.fromRNDC_record(value));
            }
            if (o.getClass().getName().equals(new com.github.lgooddatepicker.components.TimePicker().getClass().getName())) {
                TimePicker obj = (TimePicker) o;
                obj.setText(value);
            }
            if (o.getClass().getName().equals(new javax.swing.JTextArea().getClass().getName())) {
                JTextArea obj = (JTextArea) o;
                obj.setText(value);
            }
            if (o.getClass().getName().equals(new javax.swing.JFormattedTextField().getClass().getName())) {
                JFormattedTextField obj = (JFormattedTextField) o;
                obj.setText(value);
            }
            if (o.getClass().getName().equals(new javax.swing.JSpinner().getClass().getName())) {
                JSpinner obj = (JSpinner) o;
                obj.setValue(value);
            }
        } catch (Exception ex) {
        }

    }

    public static String getValue(Object o) {
        String out = "";
        if (o.getClass().toString().equals("class javax.swing.JComboBox")) {
            try {
                JComboBox j = (JComboBox) o;
                out = j.getSelectedItem().toString();
            } catch (Exception e) {
                return out;
            }
        }
        return out;
    }

    //Hermanos por nombre y/o clase
    //Elementos por nombre y/o clase
    public static Object[] siblingsbyName(Component c, String siblingName) {
        ArrayList<Component> s = new ArrayList<>();
        for (Component c1 : c.getParent().getComponents()) {
            if (c1.getName() != null) {
                if (c1.getName().equals(siblingName)) {
                    s.add(c1);
                }
            }
        }
        return s.toArray();
    }

    /**
     * Por revisar
     */
    public static ArrayList<Component> byName(Container c, String nameItem) {
        Component[] cs = c.getComponents();
//        System.out.println(cs.length + " components Found");
        ArrayList<Component> csa = new ArrayList<>();
        for (Component c1 : cs) {
//            System.out.println("component name: " + c1.getName());
            if (c1.getName() != null) {
                if (c1.getName().equals(nameItem)) {
//                System.out.println(nameItem + " item found");
                    csa.add(c1);
                }
                //caso recursivo
                if (validateContainer(c1)) {
                    csa.addAll(byName((Container) c1, nameItem));
                }
                //para tomar los elementos sin nombre
            } else {
                csa.add(c1);
                //caso recursivo
                if (validateContainer(c1)) {
                    csa.addAll(byName((Container) c1, nameItem));
                }
            }
        }
        return csa;
    }

    public static ArrayList<Component> byClass(Container c, String classItem) {
        Component[] cs = c.getComponents();
//        System.out.println(cs.length + " components Found");
        ArrayList<Component> csa = new ArrayList<>();
        for (Component c1 : cs) {
//          System.out.println("component name: " + c1.getName());
            if (c1.getClass().toString().contains(classItem)) {
//                System.out.println(nameItem + " item found");
                csa.add(c1);
            }
            //caso recursivo
            if (validateContainer(c1)) {
                csa.addAll(byClass((Container) c1, classItem));
            }
        }
        return csa;
    }

    //pendiente poner privada
    public static Boolean validateContainer(Component c) {
        if (c.getClass().toString().contains("JPanel")
                || c.getClass().toString().contains("JLayeredPane")
                || c.getClass().toString().contains("JMenuBar")
                || c.getClass().toString().contains("JRootPane")
                || c.getClass().toString().contains("JScrollPane")
                || c.getClass().toString().contains("JMenu")
                || c.getClass().toString().contains("JMenuItem")) {
            return true;
        }
        return false;
        /**
         * por considerar para mejora
         */
//        try{
//            return c instanceof Container;
//        }catch(Exception e){
//            System.out.println(e.getLocalizedMessage()+":validateContainer");
//        }
//        return false;
    }

    
}
