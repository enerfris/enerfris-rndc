/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.dao;

/**
 *
 * @author sebastianaf
 */
public class InitData {

    private static String ipServer = "localhost";
    private static String ipPort= "3306";
    private static String user  = "root";
    private static String psw  = "";
    private static String parameters = "?useLegacyDatetimeCode=false&serverTimezone=UTC";   
    
    private static String Empresa1Server = ""
            + "jdbc:mysql://"+ipServer+":"+ipPort+"/empresa_1"+parameters;
    private static String Empresa2Server  = ""
            + "jdbc:mysql://"+ipServer+":"+ipPort+"/empresa_2"+parameters;
    private static String defaultServer = ""
            + "jdbc:mysql://"+ipServer+":"+ipPort+"/default_server"+parameters;  
    
    public static String getEmpresa1Server() {
        return Empresa1Server;
    }

    public static String getEmpresa2Server() {
        return Empresa2Server;
    }

    public static String getDefaultServer() {
        return defaultServer;
    }

    public static String getUser() {
        return user;
    }

    public static String getPsw() {
        return psw;
    }
    

    

    

}
