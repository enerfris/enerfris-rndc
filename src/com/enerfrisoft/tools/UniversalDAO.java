package com.enerfrisoft.tools;

import com.enerfrisoft.error.QueryError;
import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.modal.Modal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class UniversalDAO {

    public static Map<String,String> getRow(String query, Connection conn) {
        Map<String,String> out = new HashMap<String,String>() {}; 
        try{
            Statement sentencia = conn.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(query);
            
            consulta.next();
            int columns = consulta.getMetaData().getColumnCount();
            
            //Testing lines
//            System.out.println(columns + " columns");
//            int repeated = 0;
//            ArrayList<String> repeatedItems = new ArrayList<>();
//            System.out.println("The names are...");
//            for (int i = 1; i <= consulta.getMetaData().getColumnCount(); i++) {
//                System.out.println(consulta.getMetaData().getColumnLabel(i));
//            }
            
            for (int i = 1; i <= columns; i++) {
//                if(out.containsKey(consulta.getMetaData().getColumnName(i))){
//                    repeatedItems.add(consulta.getMetaData().getColumnName(i));
//                    repeated++;
//                }
                out.put(consulta.getMetaData().getColumnLabel(i), consulta.getString(i));
            }
//            System.out.println(repeated+" repeated");
//            System.out.println("these are... "+repeatedItems.toString());
            sentencia.close();
            consulta.close();
            
//            System.out.println(out.size());
            return out;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getRow");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getRow");
        }
        
        return out;
    }
    
    public static void execute(String query, Connection conexion) {
        try {
            Statement sentencia = conexion.createStatement();

            sentencia.execute(query);

            sentencia.close();

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static Boolean executeVerifing(String query, Connection conexion) {
        try {
            Statement sentencia = conexion.createStatement();

            sentencia.execute(query);

            sentencia.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static ArrayList<String> getArrayList(String sql, Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(sql);
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getArrayList");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getArrayList");
        }
        
        return tipo;
    }
    
    public static DefaultComboBoxModel getComboBoxModel(String sql, Connection conexion){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(sql);
        
            while(consulta.next()){
                tipo.add(consulta.getString(1));
            }
            
            sentencia.close();
            consulta.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getComboBoxModel");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getComboBoxModel");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    public static String getString(String sql, Connection conn){
        String out = "";
        try{
            Statement sentencia = conn.createStatement();
            ResultSet consulta = sentencia.executeQuery(sql);
            
            consulta.next();
            out = consulta.getString(1);
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getString");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getString");
        }
        
        return out;
    }
    
    public static String getStringPre(String preSql,String sql,Connection conn){
        String out = "";
        try{
            Statement sentencia = conn.createStatement();
            sentencia.execute(preSql);
            ResultSet consulta = sentencia.executeQuery(sql);
            
            consulta.next();
            out = consulta.getString(1);
            
            sentencia.close();
            consulta.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getString");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getString");
        }
        
        return out;
    }
    
    public static String getCODTIPOID(String tipoId, Connection conexion) {
        String out = "";
        try {
            Statement sentencia = conexion.createStatement();

            ResultSet consulta = sentencia.executeQuery(
                    "select cod from tipo_id where nombre='" + tipoId + "'"
            );
            consulta.next();
            out = consulta.getString(1);

            sentencia.close();
            consulta.close();
        } catch (Exception e) {
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.run();
        }
        return out;
    }
    
    public static String getCODMUNICIPIORNDC(String municipio, Connection conexion){
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
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.run();
        }catch(Exception e){
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.run();
        }
        
        return out;
    }
    
    public static void main(String[] args) {
        //Testing
//        Map<String,String> m = getRow("call get_remesa('CALI600100')", 2);
//        System.out.println(m.size());
//        System.out.println(m.toString());
    }
}
