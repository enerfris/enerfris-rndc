package com.enerfrisoft.anularRemesa;

import com.enerfrisoft.dao.DataSourceClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryAnularRemesa implements Runnable {

    private AnularRemesa anularRemesa;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    

    public QueryAnularRemesa(AnularRemesa anularRemesa,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.anularRemesa = anularRemesa;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        values.add(anularRemesa.getCONSECUTIVOREMESA());
        values.add(anularRemesa.getMOTIVOANULACIONREMESA());
        values.add(anularRemesa.getMOTIVOREVERSAREMESA());
        values.add(anularRemesa.getCODMUNICIPIOTRANSBORDO());
        values.add(anularRemesa.getMOTIVOTRANSBORDOREMESA());
        values.add(anularRemesa.getOBSERVACIONES());
        values.add(anularRemesa.getUserLog());
        values.add(anularRemesa.getEstado());
        values.add(anularRemesa.getIngresoId());
    }

    private String buildQueryWithAtributes() {
        attributes.add("CONSECUTIVOREMESA");
        attributes.add("MOTIVOANULACIONREMESA");
        attributes.add("MOTIVOREVERSAREMESA");
        attributes.add("CODMUNICIPIOTRANSBORDO");
        attributes.add("MOTIVOTRANSBORDOREMESA");
        attributes.add("OBSERVACIONES");
        attributes.add("userLog");
        attributes.add("estado");
        attributes.add("ingresoId");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "replace into anular_remesa(";

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
            query += "'";
            query += values.get(seleccion.get(i));
            if (i == seleccion.size() - 1) {
                query += "');";
            } else {
                query += "',";
            }
        }
        return query;
    }

    public void query() {
        try {
            
            Statement sentencia = conn.createStatement();
            String query = "";

            query = buildQueryWithAtributes();
            //System.out.println(query);
            sentencia.execute(""
                    + "update remesa "
                    + "set remesa.estado = 'inactivo' "
                    + "where remesa.CONSECUTIVOREMESA = '"+anularRemesa.getCONSECUTIVOREMESA()+"'");
            sentencia.execute(query);

            sentencia.close();

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        query();
    }
}
