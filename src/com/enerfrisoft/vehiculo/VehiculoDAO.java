/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.vehiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.UniversalDAO;

public class VehiculoDAO {
    
    public static String uniqueValue(String sql, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(sql);
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":uniqueValue");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":uniqueValue");
        }
        
        return out;
    }
    
    
    public static DefaultComboBoxModel getTipoCarroceria(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tipo_carroceria"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getConfiguracionVehiculo");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getConfiguracionVehiculo");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static String getTipoVehiculo(String descripcion, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select tipo_vehiculo from configuracion_vehiculo "
                    + "where descripcion = '"+descripcion+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }
        
        return out;
    }
    
    public static String getTipoId(String nombreId, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from tipo_id where nombre='"+nombreId+"'");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }
        
        return out;
    }
    
    public static String getCodTipoCombustible(String nombre, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from tipo_combustible "
                    + "where nombre = '"+nombre+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCodTipoCombustible");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCodTipoCombustible");
        }
        
        return out;
    }
    
    public static String getNitAseguradora(String nombre, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select id from aseguradora where nombre = '"+nombre+"'");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoVehiculo");
        }
        
        return out;
    }
    
    public static String getCodTipoCarroceria(String nombre, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from tipo_carroceria "
                    + "where nombre = '"+nombre+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCodTipoCarroceria");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCodTipoCarroceria");
        }
        
        return out;
    }
    
    public static String getCODCONFIGURACIONUNIDADCARGA(String CONFIGURACIONUNIDADCARGA, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from configuracion_vehiculo "
                    + "where descripcion = '"+CONFIGURACIONUNIDADCARGA+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }
        
        return out;
    }
    
    
    
    public static String getCODLINEAVEHICULOCARGA(String LINEAVEHICULOCARGA, String MARCAVEHICULOCARGA, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery("select cod_linea from linea_vehiculo "
                + "where nombre_marca = '"+MARCAVEHICULOCARGA+"' " +
                    "and nombre_linea = '"+LINEAVEHICULOCARGA+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }
        
        return out;
    }
    
    public static String getCodColor(String nombreColor, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from vehiculo_color "
                    + "where nombre = '"+nombreColor+"'");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }
        
        return out;
    }
    
    public static String getCODMARCAVEHICULOCARGA(String MARCAVEHICULOCARGA, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery("select cod from marca_vehiculo where nombre = '"+MARCAVEHICULOCARGA+"'");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }
        
        return out;
    }
    
    public static String getCodMarcaSemiremolque(String marcaSemirremolque, Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(""
                    + "select cod from marca_semiremolque "
                    + "where nombre = '"+marcaSemirremolque+"';");
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODLINEAVEHICULOCARGA");
        }
        
        return out;
    }
    
    public static ArrayList<String> getMARCAVEHICULOCARGA(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre from marca_vehiculo"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getMARCAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getMARCAVEHICULOCARGA");
        }
        
        return tipo;
    }
    
    public static ArrayList<String> getMarcasSemiremolques(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre from marca_semiremolque;"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getMARCAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getMARCAVEHICULOCARGA");
        }
        
        return tipo;
    }
    
    public static ArrayList<String> getLINEAVEHICULOCARGA(String CODMARCAVEHICULOCARGA, Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre_linea from linea_vehiculo where cod_marca = '"+CODMARCAVEHICULOCARGA+"';"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getLINEAVEHICULOCARGA");
        }
        
        return tipo;
    }
    
    public static ArrayList<String> getColores(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre from vehiculo_color;"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getLINEAVEHICULOCARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getLINEAVEHICULOCARGA");
        }
        
        return tipo;
    }
    
    public static DefaultComboBoxModel getConfiguracionVehiculo(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select descripcion from configuracion_vehiculo "
                        + "order by descripcion ASC"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getConfiguracionVehiculo");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getConfiguracionVehiculo");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static  DefaultComboBoxModel getTipoCombustible(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tipo_combustible"
            );
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoCombustible");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoCombustible");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
}
