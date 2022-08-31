/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.dao;

import com.enerfrisoft.entity.InfoEmpresa;
import com.enerfrisoft.entity.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Sebastianaf
 */
public class ParametrosDAO {
    
    
    public static InfoEmpresa getEmpresa(Connection conexion){
        InfoEmpresa out = new InfoEmpresa();
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select nit,usuario,nombre from info_empresa where id=1;");
            resultado.next();
            out.setNit(resultado.getString(1));
            out.setUsuario(resultado.getString(2));
            out.setNombre(resultado.getString(3));
            
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getUsuarioEmpresa");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUsuarioEmpresa");
        }
        return out;
    }
    
    public static int getUltimoConsecutivoRemesa(Connection conexion){
        int out = 0;
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select ultimo_consecutivo_remesa "
                            + "from parametro_configuracion "
                            + "where id=1;");
            resultado.next();
            out = resultado.getInt(1);
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getUltimoConsecutivoRemesa");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUltimoConsecutivoRemesa");
        }
        return out;
    }
    
    public static int getUltimoConsecutivoOrdenCarga(Connection conexion){
        int out = 0;
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select ultimo_consecutivo_orden_carga "
                            + "from parametro_configuracion "
                            + "where id=1;");
            resultado.next();
            out = resultado.getInt(1);
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getUltimoConsecutivoOrdenCarga");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUltimoConsecutivoOrdenCarga");
        }
        return out;
    }
    
    public static int getConsecutivoRemesa(Connection conexion){
        int out = 0;
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select consecutivo_remesa "
                            + "from parametro_configuracion "
                            + "where id=1;");
            resultado.next();
            out = resultado.getInt(1);
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getConsecutivoRemesa");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getConsecutivoRemesa");
        }
        return out;
    }
    
    public static int getConsecutivoOrden_Carga(Connection conexion){
        int out = 0;
        try{
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select consecutivo_orden_carga "
                            + "from parametro_configuracion "
                            + "where id=1;");
            resultado.next();
            out = resultado.getInt(1);
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":getConsecutivoOrden_Carga");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getConsecutivoOrden_Carga");
        }
        return out;
    }
    
    public static void setConsecutivoOrden_Carga(String valor, int  server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            sentencia.executeUpdate(
                    "update parametro_configuracion "
                            + "set consecutivo_orden_carga="+valor
                            + "where id=1;");
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":setConsecutivoOrden_Carga");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":setConsecutivoOrden_Carga");
        }
    }
    
    public static void setConsecutivoRemesa(String valor, Connection conexion){
        try{
            Statement sentencia = conexion.createStatement();
            
            sentencia.executeUpdate(
                    "update parametro_configuracion "
                            + "set consecutivo_remesa="+valor
                            + "where id=1;");
            
            sentencia.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":setConsecutivoRemesa");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":setConsecutivoRemesa");
        }
    }
}
