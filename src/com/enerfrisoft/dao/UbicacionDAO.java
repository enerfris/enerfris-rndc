/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebastianaf
 */
public class UbicacionDAO {
    public static String getMunicipio(String direccion, int server){
        String out = "";
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select municipio from ubicacion where direccion = '"+direccion+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNombreTercero");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNombreTercero");
        }
        
        return out;
    }
    
    public static String getTelefono(String direccion, int server){
        String out = "";
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select telefono from sede natural join ubicacion where direccion='"+direccion+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNombreTercero");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNombreTercero");
        }
        
        return out;
    }
    
    public static void main(String[] args) {
        System.out.println(getTelefono("AVENIDA IDEMA",1));
    }
}
