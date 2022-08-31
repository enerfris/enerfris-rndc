/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.dao;
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
public class UsuarioDAO {
    
    
    
   
    public static void modificarUsuario(Usuario usuario, int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            sentencia.executeUpdate(
                    "update usuario "
                            + "set "
                            + "pass='"+usuario.getPass()+"',"
                            + " tipo_usuar \" tio='"+usuario.getTipo_usuario()+"'"
                            + " where username='"+usuario.getUsername()+"';"
                    
            );
        
            fachadaBD.closeConection(conexion);
            sentencia.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage() + ":modificarUsuario");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage() + ":modificarUsuario");
        }
    }
    
    public static void eliminarUsuario(String username, int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            sentencia.executeUpdate(
                    "update usuario\n" +
                    "set estado='inactivo'\n" +
                    "where username='"+ username +"';"
            );
        
            fachadaBD.closeConection(conexion);
            sentencia.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage() + ":eliminarUsuario");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage() + ":eliminarUsuario");
        }
    }
    
    public static DefaultComboBoxModel tipo_usuario(int server){
        ArrayList<String> tipo = new ArrayList<>();
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consultaTipoEventos = sentencia.executeQuery(
                "select nombre\n" +
                "from tipo_usuario;"
            );
        
            while(consultaTipoEventos.next()){
                tipo.add(consultaTipoEventos.getString(1));
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consultaTipoEventos.close();
            
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":tipo_usuario");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":tipo_usuario");
        }
        
        return new DefaultComboBoxModel(tipo.toArray());
    }
    
    
    
    public static int insertarUsuario(Usuario usuario,int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            int numFilas = sentencia.executeUpdate(
                    "insert into usuario values("
                            + "nextval('usuario_seq'::regclass),"
                            + "'"+usuario.getNombre()+"',"
                            + "'"+usuario.getUsername()+"',"
                            + "'"+usuario.getPass()+"',"
                            + "current_date,"
                            + "'activo',"
                            + "'"+usuario.getTipo_usuario()+"');");
            
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            
            return numFilas;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":insertarUsuario");
            return -1;
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":insertarUsuario");
            return -1;
        }
    }
    
    public static  void cambiarContraseña(String username, String pass,int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            sentencia.executeUpdate(
                    "update usuario"
                            + " set pass = '"+pass+"' "
                            + "where username = '"+username+"'");
           
            fachadaBD.closeConection(conexion);
            sentencia.close();
        
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":cambiarContraseña");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":cambiarContraseña");
        }
    }
    
    public static boolean validarContraseña(String username, String pass,int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select exists("
                            + "select username "
                            + "from usuario "
                            + "where "
                            + "username = '"+username+"' "
                            + "and pass='"+pass+"' )");
            resultado.next();
            if(resultado.getString(1).equals("t")){
                fachadaBD.closeConection(conexion);
                sentencia.close();
                return true;
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":validarContraseña");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":validarContraseña");
        }
        return false;
    }
    
    /**
     * Consulta si un usuario existe en la BD
     * @param usuario
     * @return booleano que indica la existencia
     */
    public static boolean Login(Usuario usuario,int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select exists(\n" +
                    "	select username \n" +
                    "	from usuario \n" +
                    "	where username = '" + usuario.getUsername() + "'\n" +
                    "	and pass = '" +  usuario.getPass() + "'\n" +
                    ");");
            resultado.next();
            if(resultado.getString(1).equals("t")){
                fachadaBD.closeConection(conexion);
                sentencia.close();
                return true;
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            return false;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":login");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":login");
        }
        return false;
    }
    
    public static String tipoUsuario(String username,int server){
        String out = "";
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select tipo_usuario "
                            + "from usuario "
                            + "where username = '"+username+"';");
           
            resultado.next();
            out = resultado.getString(1);
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage()+":tipoUsuario");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":tipoUsuario");
        }
        return out;
    }
    
    public static boolean validarUsuario(String username, int server){
        try{
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado = sentencia.executeQuery(
                    "select exists(\n" +
                    "	select username \n" +
                    "	from usuario \n" +
                    "	where username= '" + username + "');");
            resultado.next();
            if(resultado.getString(1).equals("t")){
                fachadaBD.closeConection(conexion);
                sentencia.close();
                return false;
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return true;
    }
    
    
    public static ArrayList<String[]> getUsuarios(int server){
        ArrayList<String[]> arreglo = new ArrayList<>();
        try {
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            
            ResultSet consulta = sentencia.executeQuery(
                    "select nombre, username,pass, fecha_creacion,tipo_usuario \n" +
                    "from usuario\n" +
                    "where estado='activo';"
            );
            
            while (consulta.next()) {
                String[] subArreglo = new String[5];
                for(int i=0;i<5;i++){
                    subArreglo[i] = consulta.getString(i+1);
                }
                arreglo.add(subArreglo);
            }
            
            fachadaBD.closeConection(conexion);
            sentencia.close();
            consulta.close();
            
        }catch (SQLException e) {
            System.out.println(e.getLocalizedMessage()+":getUsuarios");
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage()+":getUsuarios");
        }
        return arreglo;
    }
    
    
}
