/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author Sebastianaf
 */
public class Parameters {
    public static final String aplicationName = "Empresa1 Software";
    
    //Panels
    public static final Border  pl1b = new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED);
    public static final Color plc = new Color(244,247,253);
    public static final Color panelTitleColor = new Color(201,222,244);
    public static final Border  pl2b =javax.swing.BorderFactory.createTitledBorder("");
    public static final Dimension panelPreferredSize = new Dimension(100,100);
    public static final Dimension maxPanelSize = new Dimension(10000,100);
    public static final Dimension minPanelSize = new Dimension(0,50);
    public static final Dimension preferredTitlePanelSize = new Dimension(0,30);
    
    public static final Dimension minWindowSize = new Dimension(1024,600);
    
    
    public static final Font mainFont = new Font("Harlow Solid Italic", Font.PLAIN, 14);
    public static final Font titleFont = new Font("Harlow Solid Italic", Font.BOLD, 15);
    public static final Font panelTitleFont = new Font("Harlow Solid Italic", Font.BOLD, 15);
    
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public static ArrayList<Integer> shuffle(ArrayList<Integer> arr){
        java.util.Collections.shuffle(arr);
        System.out.println(arr);
        return arr;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> as = new ArrayList<>();
        as.add(1);
        as.add(2);
        as.add(3);
        as.add(4);
        shuffle(as);
        
    }
}
