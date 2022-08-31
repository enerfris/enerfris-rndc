/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.manifiesto;

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
public class ManifiestoDAO {
    
    public static ArrayList<String> getIds(String query, Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                query
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIds");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIds");
        }
        
        return tipo;
    }

    public static DefaultComboBoxModel tipo_id(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre\n" +
                "from tipo_id;"
            );
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":tipo_id");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":tipo_id");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static String getIdMunicipio(String municipio, Connection conexion){
        String out = "";
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select id from municipio where nombre='"+municipio+"';"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIdMunicipio");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIdMunicipio");
        }
        
        return out;
    }
    
    
    
    public static ArrayList<String> getMunicipios(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre from municipio;"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getMunicipios");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getMunicipios");
        }
        
        return tipo;
    }
    
    public static DefaultComboBoxModel tipo_manifiesto(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre from tipo_manifiesto;"
            );
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":tipo_manifiesto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":tipo_manifiesto");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
}
