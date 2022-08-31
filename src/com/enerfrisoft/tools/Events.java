/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.tools;


import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sebastianaf
 */
public class Events {
    
    private static void naturalezaCarga(Container c){
        for(Component c1 : Items.byName(c, "naturalezaCarga")){
            
            for(Object c2 : Items.siblingsbyName(c1, "clasificacion")){
                JComboBox cb = (JComboBox)c2;
                //liniea continuada
            }
        }
    }
    
    private static void eventTipoId(Component [] cs,Component c){
        JComboBox comboBox = (JComboBox)c;
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < cs.length; i++) {
                    if(cs[i].getName()!=null){
                        if(cs[i].getName().equals("etiqueta")){
                            JLabel label = (JLabel)cs[i];
                            label.setText(comboBox.getSelectedItem().toString());
                        }
                    }
                }
            }}
        );
        
    }
    
   
   public static void listenersTipoId(JPanel p){
       Component[] componentes = p.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if(componentes[i].getName()!=null){
                if(componentes[i].getName().equals("tipoId")){
                    eventTipoId(componentes,componentes[i]);
                }
            }
            if(componentes[i].getClass().toString().contains("JPanel")){
                listenersTipoId((JPanel)componentes[i]);
            }
            
        }
    }
}
