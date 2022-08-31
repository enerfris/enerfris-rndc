package com.enerfrisoft.error;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryError implements Runnable {

    private Error error;
    private ArrayList<String> values;

    public QueryError(Error error) {
        this.error = error;
        this.values = new ArrayList<>();
        getAttributes();
    }

    private void getAttributes() {
        values.add(error.getCLASE());
        values.add(error.getMETODO());
        values.add(error.getLINEA());
        values.add(error.getMENSAJE());
    }

    private String buildQuery() {
        ArrayList<String> attributes = new ArrayList<>();

        attributes.add("CLASE");
        attributes.add("METODO");
        attributes.add("LINEA");
        attributes.add("MENSAJE");

        String query = "replace into error(";

        for (int i = 0; i < attributes.size(); i++) {
            query += attributes.get(i);
            if (i == attributes.size() - 1) {
                query += ") ";
            } else {
                query += ",";
            }
        }

        query += "values(";

        for (int i = 0; i < values.size(); i++) {
            query += "'";
            query += values.get(i);
            if (i == values.size() - 1) {
                query += "');";
            } else {
                query += "',";
            }
        }
        return query;
    }

    public void query(int server) {
        try {
            com.enerfrisoft.dao.DataSourceClass fachadaBD = new com.enerfrisoft.dao.DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();
            String query = "";

            query = buildQuery();
            //System.out.println(query);
            sentencia.execute(query);

            fachadaBD.closeConection(conexion);
            sentencia.close();

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        query(0);
    }
}
