/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.remesa;

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
public class RemesaDAO {
    
    public static  ArrayList<String> getNUMIDTERCERO(String tipo_id,Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select NUMIDTERCERO from tercero where CODTIPOIDTERCERO='"+tipo_id+"';"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIdProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIdProducto");
        }
        
        return tipo;
    }
    
    
    public static ArrayList<ArrayList<String>> getSedes(String id,Connection conexion){
        ArrayList<ArrayList<String>> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select sede.nombre, ubicacion.direccion, ubicacion.municipio\n" +
                "from sede inner join ubicacion \n" +
                "on sede.ubicacion = ubicacion.id\n" +
                "inner join tercero\n" +
                "on tercero.nombre = sede.tercero\n" +
                "where tercero.id='"+id+"'"
            );
            
            ArrayList<String> sedeVacia = new ArrayList<>();
            
            tipo.add(sedeVacia);
            while(consulta.next()){
                ArrayList<String> sede = new ArrayList<>();
                for(int i=1;i<4;i++){
                    sede.add(consulta.getString(i));
                }
                tipo.add(sede);
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getSedes");
        }
        
        return tipo;
    }
    
    public static String getNaturalezaCarga(String id,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select naturaleza_carga.nombre \n" +
                "from naturaleza_carga inner join clasificacion_producto \n" +
                "on clasificacion_producto.naturaleza=naturaleza_carga.nombre\n" +
                "inner join nombre_producto on nombre_producto.clasificacion=clasificacion_producto.nombre\n" +
                "where id='"+id+"'"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCarga");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCarga");
        }
        
        return out;
    }
    
    
    
    
    public static  ArrayList<String> getIdProducto(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select id from nombre_producto"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIdProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIdProducto");
        }
        
        return tipo;
    }
    
    public static String getNombreProductoId(String id,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from nombre_producto where id='"+id+"';"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNombreProductoId");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNombreProductoId");
        }
        
        return out;
    }
    
    public static String getIdNombreProducto(String nombre,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select id from nombre_producto where nombre='"+nombre+"';"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getIdNombreProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getIdNombreProducto");
        }
        
        return out;
    }
    
    public static String getClasificacionProducto(String id,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select clasificacion from nombre_producto where id='"+id+"';"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto");
        }
        
        return out;
    }
    
    public static String clasificacionFromNombre(String nombre,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select clasificacion_producto.nombre\n" +
                "from naturaleza_carga inner join clasificacion_producto\n" +
                "on clasificacion_producto.naturaleza=naturaleza_carga.nombre\n" +
                "inner join nombre_producto on nombre_producto.clasificacion=clasificacion_producto.nombre\n" +
                "where nombre_producto.nombre='"+nombre+"';"
            );
            
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":clasificacionFromNombre");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":clasificacionFromNombre");
        }
        
        return out;
    }
    
    
    public static ArrayList<String> getClasificacionProducto2(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from clasificacion_producto"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto2");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto2");
        }
        
        return tipo;
    }
    
    public static DefaultComboBoxModel getClasificacionProducto3(String naturaleza,Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from clasificacion_producto where naturaleza='"+naturaleza+"'"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto3");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getClasificacionProducto3");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
    
    public static  DefaultComboBoxModel getNombreProducto(String clasificacion,Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from nombre_producto where clasificacion='"+clasificacion+"';"
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNombreProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNombreProducto");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
    
    public static ArrayList<String> getNombreProducto2(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from nombre_producto;"
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNombreProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNombreProducto");
        }
        
        return tipo;
    }
    
    public static boolean isProducto(String id,Connection conexion){
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select exists(select nombre from nombre_producto where id='"+id+"');");
            resultado.next();
            if(resultado.getString(1).equals("t")){
                
                sentencia.close();
                return true;
            }
            
            
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":isProducto");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":isProducto");
        }
        return false;
    }
    
    public static boolean isCargaNormalaux(String naturaleza){
        if(naturaleza.equals("Carga Normal")
                    ||naturaleza.equals("Carga Extradimensionada")
                    ||naturaleza.equals("Carga Extrapesada")
                    ||naturaleza.equals("Semovientes")
                    ||naturaleza.equals("Refrigerada"))return true;
        return false;
    }
    
    public static boolean isCargaNormal(String nombre,Connection conexion){
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select naturaleza_carga.nombre\n" +
                    "from naturaleza_carga inner join clasificacion_producto\n" +
                    "on clasificacion_producto.naturaleza=naturaleza_carga.nombre\n" +
                    "inner join nombre_producto on nombre_producto.clasificacion=clasificacion_producto.nombre\n" +
                    "where nombre_producto.nombre='"+nombre+"'");
            resultado.next();
            String resultadoString = resultado.getString(1);
            if(isCargaNormalaux(resultadoString)){
                
                sentencia.close();
                return true;
            }
            
            
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":isCargaNormal");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":isCargaNormal");
        }
        return false;
    }
    
    public static boolean isINVIAS(String nombre,Connection conexion){
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select naturaleza_carga.nombre\n" +
                    "from naturaleza_carga inner join clasificacion_producto\n" +
                    "on clasificacion_producto.naturaleza=naturaleza_carga.nombre\n" +
                    "inner join nombre_producto on nombre_producto.clasificacion=clasificacion_producto.nombre\n" +
                    "where nombre_producto.nombre='"+nombre+"'");
            resultado.next();
            if(resultado.getString(1).equals("Carga Extradimensionada")
                    ||resultado.getString(1).equals("Carga Extrapesada")){
                
                sentencia.close();
                return true;
            }
            
            
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":isINVIAS");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":isINVIAS");
        }
        return false;
    }
    
    public static boolean isProductoNombre(String nombre,Connection conexion){
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select exists(select nombre from nombre_producto where nombre='"+nombre+"');");
            resultado.next();
            if(resultado.getString(1).equals("t")){
                
                sentencia.close();
                return true;
            }
            
            
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":isProductoNombre");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":isProductoNombre");
        }
        return false;
    }
    
    public static DefaultComboBoxModel getNaturalezaCarga(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from naturaleza_carga"
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCarga");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCarga");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
     public static String getNaturalezaCargaNombre(String nombre,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select naturaleza_carga.nombre\n" +
                "from naturaleza_carga inner join clasificacion_producto\n" +
                "on clasificacion_producto.naturaleza=naturaleza_carga.nombre\n" +
                "inner join nombre_producto on nombre_producto.clasificacion=clasificacion_producto.nombre\n" +
                "where nombre_producto.nombre='"+nombre+"';"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCargaNombre");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCargaNombre");
        }
        
        return out;
    }
    
    public static String getNaturalezaCargabyNombre(String nombre,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod \n" +
                "from naturaleza_carga \n" +
                "where nombre= '"+nombre+"';"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCargabyNombre");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getNaturalezaCargabyNombre");
        }
        
        return out;
    }
    
    public static DefaultComboBoxModel getUnidadMedida(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from unidad_medida"
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getUnidadMedida");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUnidadMedida");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
    public static DefaultComboBoxModel getTipoEmpaque(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tipo_empaque"
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoEmpaque");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoEmpaque");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static DefaultComboBoxModel getTipoOperacion(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tipo_operacion "
            );
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTipoOperacion");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTipoOperacion");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
        
    }
    
    public static DefaultComboBoxModel getAseguradoras(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from aseguradora"
            );
        
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getAseguradoras");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getAseguradoras");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
    
    public static String getCODOPERACIONTRANSPORTE(String operacion,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from tipo_operacion where nombre = '"+operacion+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODOPERACIONTRANSPORTE");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODOPERACIONTRANSPORTE");
        }
        
        return out;
    }
    
    public static String getCODTIPOEMPAQUE(String tipoEmpaque,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from tipo_empaque where nombre = '"+tipoEmpaque+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODTIPOEMPAQUE");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODTIPOEMPAQUE");
        }
        
        return out;
    }
    
    public static String getCODNATURALEZACARGA(String naturalezaCarga,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from naturaleza_carga where nombre = '"+naturalezaCarga+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODNATURALEZACARGA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODNATURALEZACARGA");
        }
        
        return out;
    }
    
    public static String getUnidadMedida(String unidad,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from unidad_medida where nombre='"+unidad+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getUnidadMedida");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUnidadMedida");
        }
        
        return out;
    }
    
    public static String getCODTIPOID(String tipoId,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from tipo_id where nombre='"+tipoId+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODTIPOID");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODTIPOID");
        }
        
        return out;
    }
    
    
    public static DefaultComboBoxModel getCODCATEGORIALICENCIACONDUCCION(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from categoria_licencia"
            );
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODCATEGORIALICENCIACONDUCCION");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODCATEGORIALICENCIACONDUCCION");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static String getCODSEDE(String direccionSede,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from sede natural join ubicacion where ubicacion.direccion = '"+direccionSede+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCODSEDE");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCODSEDE");
        }
        
        return out;
    }
    
    public static String getCOMPANIASEGURO(String nombre,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select id from aseguradora where nombre = '"+nombre+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getCOMPANIASEGURO");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getCOMPANIASEGURO");
        }
        
        return out;
    }
    
    public static DefaultComboBoxModel getTomadorPoliza(Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select nombre from tomador_poliza"
            );
        
            
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getTomadorPoliza");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getTomadorPoliza");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static String getDUENOPOLIZA(String dueno,Connection conexion){
        String out = "";
        try{
            
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                "select cod from tomador_poliza where nombre = '"+dueno+"'"
            );
            consulta.next();
            out = consulta.getString(1);
            
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getDUENOPOLIZA");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getDUENOPOLIZA");
        }
        
        return out;
    }
    
    
}
