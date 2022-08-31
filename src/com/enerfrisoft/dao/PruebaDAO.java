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
import java.util.ArrayList;

/**
 *
 * @author Sebastianaf
 */
public class PruebaDAO {
    public static ArrayList<ArrayList<String>> getSedes(String id, int server){
        ArrayList<ArrayList<String>> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select sede.nombre, ubicacion.direccion, ubicacion.municipio\n" +
                "from sede inner join ubicacion \n" +
                "on sede.ubicacion = ubicacion.id\n" +
                "inner join tercero\n" +
                "on tercero.nombre = sede.tercero\n" +
                "where tercero.id='"+id+"'"
            );
            ArrayList<String> inicio = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                inicio.add("");
            }
            tipo.add(inicio);
            
            while(consulta.next()){
                ArrayList<String> sede = new ArrayList<>();
                for(int i=1;i<4;i++){
                    sede.add(consulta.getString(i));
                }
                tipo.add(sede);
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }
        
        return tipo;
    }
    
    public static void main(String[] args) {
       String primero = "";
       primero += " hola";
       primero += " saludos.";
        System.out.println(primero);
        
    }
    
    
    
}


