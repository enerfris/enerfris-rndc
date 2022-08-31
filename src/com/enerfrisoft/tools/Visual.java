/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Visual | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.tools;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Sebastianaf
 */
public class Visual {
    
    
    
    public static void panelsSettings(Container c){
        ArrayList<Component> pl1 = Items.byName(c,"pl1");
//        System.out.println(pl1.size() + " pl1 items");
        for (Component c1 : pl1) {
            JPanel jp = (JPanel)c1;
            jp.setBackground(Parameters.plc);
            jp.setBorder(Parameters.pl1b);
        }
        ArrayList<Component> pl2 = Items.byName(c,"pl2");
//        System.out.println(pl2.size() + " pl2 items");
        for (Component c1 : pl2) {
            JPanel jp = (JPanel)c1;
            jp.setBackground(Parameters.plc);
            jp.setBorder(Parameters.pl2b);
            jp.setMinimumSize(Parameters.minPanelSize);
        }
    }
    
    public static void windowSettings(JFrame f){
        f.setLocationRelativeTo(null);
        f.setTitle(Parameters.aplicationName);
        f.setMinimumSize(Parameters.minWindowSize);
    }
    
    public static void windowIcon(JFrame f){
        Image icon = new ImageIcon(f.getClass().getResource(Path.windowIcon)).getImage();
        f.setIconImage(icon); 
    }
    
    public static void setLookAndFeel(){
        try {
           UIManager.LookAndFeelInfo [] iaf = UIManager.getInstalledLookAndFeels();
           UIManager.setLookAndFeel(iaf[3].getClassName());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setJSpinner(Container c){
        Component[] componentes = c.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if(componentes[i].getClass().toString().contains("JSpinner")){
                JSpinner obj = (JSpinner)componentes[i];
                obj.setModel(new SpinnerDateModel(new Date(),null,null,Calendar.HOUR_OF_DAY));
                JSpinner.DateEditor se = new JSpinner.DateEditor(obj,"hh:mm a");
                obj.setEditor(se);
            }
            if(Items.validateContainer(componentes[i]))setJSpinner((Container) componentes[i]);
        }
    }
    
    public static void setJDateChooser(Container c){
        Component[] componentes = c.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if(componentes[i].getClass().toString().contains("JDateChooser")){
                JDateChooser dc = (JDateChooser)componentes[i];
                dc.setDateFormatString("EEEE, dd MMMM yyyy");
                dc.setMinSelectableDate(new Date());
            }
            if(Items.validateContainer(componentes[i]))setJDateChooser((Container) componentes[i]);
        }
    }
    
    public static void fuente(Container c){
        for(Component c1 : Items.byName(c, "")){
            c1.setFont(Parameters.mainFont);
        }
//        System.out.println(Items.byName(c, "").size());
        for(Component c1 : Items.byName(c, "title")){
            c1.setFont(Parameters.titleFont);
        }
        for(Component c1 : Items.byName(c, "panelTitle")){
            JLabel l = (JLabel)c1;
            l.setPreferredSize(Parameters.preferredTitlePanelSize);
            l.setOpaque(true);
            l.setFont(Parameters.panelTitleFont);
            l.setBackground(Parameters.panelTitleColor);
            
        }
    }
}
