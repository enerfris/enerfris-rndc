package com.enerfrisoft.dao;

import com.enerfrisoft.modal.Modal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceClass {

    private String url, usuario, psw;
    private Connection conexion = null;

    public DataSourceClass() {
        
//        url = "jdbc:mysql://localhost:3307/Empresa1"
//                + "?useLegacyDatetimeCode=false"
//                + "&serverTimezone=UTC";
//        usuario = "root";
//        psw = "";
    }

    public Connection conectar(int connection) {
        try {
            switch (connection) {
                case 0:
//                    System.out.println("case 0");
                    conexion = DriverManager.getConnection(InitData.getDefaultServer(),
                            InitData.getUser(),
                            InitData.getPsw());
                    break;
                case 1:
//                    System.out.println("case 1");
                    conexion = DriverManager.getConnection(InitData.getEmpresa1Server(),
                            InitData.getUser(),
                            InitData.getPsw());
                    break;
                case 2:
//                    System.out.println("case 2");
                    conexion = DriverManager.getConnection(InitData.getEmpresa2Server(),
                            InitData.getUser(),
                            InitData.getPsw());
                    break;
            }
            return conexion;

        } catch (SQLException e) {
            //System.out.println(e.getLocalizedMessage());
            Modal.show("Error", "No se ha podido establecer conexión con el servidor", null, "", "error");
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public void closeConection(Connection c) {
        try {
            if (conexion != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        try {
            DataSourceClass d = new DataSourceClass();
            Connection c = d.conectar(0);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select username from usuario");
            r.next();
            String out = r.getString(1);
            System.out.println(out);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
