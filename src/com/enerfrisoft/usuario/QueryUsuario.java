package com.enerfrisoft.usuario;

import com.enerfrisoft.modal.Modal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;

public class QueryUsuario implements Runnable {

    private Usuario usuario;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private JFrame frame;

    public QueryUsuario(Usuario usuario, JFrame frame) {
        this.frame = frame;
        this.usuario = usuario;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        values.add(usuario.getUsername());
        values.add(usuario.getNombre());
        values.add(usuario.getPass());
        values.add(usuario.getIdentidad());
        values.add(usuario.getTipo_usuario());
        values.add(usuario.getEstado());
        values.add(usuario.getServer());
    }

    private String buildQueryWithAtributes() {
        attributes.add("username");
        attributes.add("nombre");
        attributes.add("pass");
        attributes.add("identidad");
        attributes.add("tipo_usuario");
        attributes.add("estado");
        attributes.add("server");  

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "replace into usuario(";

        for (int i = 0; i < seleccion.size(); i++) {
            query += attributes.get(seleccion.get(i));
            if (i == seleccion.size() - 1) {
                query += ") ";
            } else {
                query += ",";
            }
        }

        query += "values(";

        for (int i = 0; i < seleccion.size(); i++) {
            if (!values.get(seleccion.get(i)).equals("current_date")) {
                query += "'";
            }
            query += values.get(seleccion.get(i));
            if (i == seleccion.size() - 1) {
                query += "');";
            } else {
                if (!values.get(seleccion.get(i)).equals("current_date")) {
                    query += "',";
                } else {
                    query += ",";
                }

            }
        }
        return query;
    }

    public void query() {
        try {
            com.enerfrisoft.dao.DataSourceClass fachadaBD = new com.enerfrisoft.dao.DataSourceClass();
            Connection conexion = fachadaBD.conectar(0);
            Statement sentencia = conexion.createStatement();
            String query = "";

            query = buildQueryWithAtributes();
            //System.out.println("query: " + query);
            sentencia.execute(query);

            fachadaBD.closeConection(conexion);
            sentencia.close();

            Modal.show("Registro", "El registro se completo exitosamente", frame, "", "ok");

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            Modal.show("Error", "El registro no se completo correctamente", frame, "", "error");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            Modal.show("Error", "El registro no se completo correctamente", frame, "", "error");
        }
    }

    @Override
    public void run() {
        query();
    }
}
