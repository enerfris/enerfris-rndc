package com.enerfrisoft.anularManifiesto;

import com.enerfrisoft.dao.DataSourceClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryAnularManifiesto implements Runnable {

    private AnularManifiesto anularManifiesto;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public QueryAnularManifiesto(AnularManifiesto anularManifiesto, DataSourceClass dataSource,Connection conn) {
        this.conn = conn;        
        this.dataSource = dataSource;
        this.anularManifiesto = anularManifiesto;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();
        

        getAttributes();
    }

    private void getAttributes() {
        values.add(anularManifiesto.getNUMMANIFIESTOCARGA());
        values.add(anularManifiesto.getMOTIVOANULACIONMANIFIESTO());
        values.add(anularManifiesto.getOBSERVACIONES());
        values.add(anularManifiesto.getUserLog());
        values.add(anularManifiesto.getDateLog());
        values.add(anularManifiesto.getEstado());
        values.add(anularManifiesto.getIngresoId());
    }

    private String buildQueryWithAtributes() {
        attributes.add("NUMMANIFIESTOCARGA ");
        attributes.add("MOTIVOANULACIONMANIFIESTO ");
        attributes.add("OBSERVACIONES ");
        attributes.add("userLog ");
        attributes.add("dateLog ");
        attributes.add("estado ");
        attributes.add("ingresoId ");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "replace into anular_manifiesto(";

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
            sentencia.execute(query);

            sentencia.execute(""
                    + "update remesa "
                    + "set remesa.manifiestoConsecutivo = NULL "
                    + "where manifiestoConsecutivo = '"+anularManifiesto.getNUMMANIFIESTOCARGA()+"'");
            
            sentencia.execute(""
                    + "update manifiesto "
                    + "set manifiesto.estado = 'inactivo' "
                    + "where manifiesto.NUMMANIFIESTOCARGA = '"+anularManifiesto.getNUMMANIFIESTOCARGA()+"'");
            
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
