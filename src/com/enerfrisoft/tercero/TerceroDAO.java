/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.tercero;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import com.enerfrisoft.dao.DataSourceClass;

/**
 *
 * @author Sebastianaf
 */
public class TerceroDAO {
    
    public static String getCodSede(String terceroNombre, String terceroDireccion, int server){
        String out = "";
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select sede.cod from sede \n" +
                "inner join tercero on sede.tercero = tercero.nombre \n" +
                "inner join ubicacion on ubicacion.id = sede.ubicacion\n" +
                "where ubicacion.direccion = '"+terceroDireccion+"' and tercero.nombre = '"+terceroNombre+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCodSede");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCodSede");
        }
        
        return out;
    }
    
    
    public static String getNombreTercero(String id, int server){
        String out = "";
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tercero where id='"+id+"'"
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
    
    public static DefaultComboBoxModel getSedes(String terceroNombre, int server){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select ubicacion.direccion "
                        + "from tercero "
                        + "inner join sede on tercero.nombre = sede.tercero "
                        + "inner join ubicacion on sede.ubicacion = ubicacion.id\n" 
                        + "where tercero.nombre = '"+terceroNombre+"'"
            );
            tipo.add("");
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static ArrayList<String> getIds(String tipoId, int server){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select id from tercero where tipo_id='"+tipoId+"'"
            );
            tipo.add("");
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIds");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIds");
        }
        
        return tipo;
    }
    
    
    
}
