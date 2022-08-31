/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sebastianaf
 */
public class DateParsing {

    public static String toXML(Date date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toMySQL(Date date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static String toMySQLTimeStap(Date date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formato.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static Date fromMySQL(String date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date out = formato.parse(date);
            return out;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date timeStampFromRNDC(String date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
            Date out = formato.parse(date);
            return out;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date fromRNDC_record(String date) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date out = formato.parse(date);
            return out;
        } catch (Exception e) {
            return null;
        }
    }

    public static String genModels(String name, ArrayList<String> arr) {
        String out = ""
                + "const " + name + "= sequelize.define('" + name.toLowerCase() + "',{\n";
        for (int i = 0; i < arr.size(); i++) {
            out += arr.get(i) + ":{\n"
                    + "type: Sequelize.STRING(50),\n"
                    + "notNull: true,\n"
                    + "}, \n";
        }

        out += "\n"
                + "},{\n"
                + "freezeTableName: true,\n"
                + "timestamps:false\n"
                + "});";
        return out;
    }

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        System.out.println(genModels("nombre",arr));
    }
}
