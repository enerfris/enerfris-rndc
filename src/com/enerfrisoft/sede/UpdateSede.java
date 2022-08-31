package com.enerfrisoft.sede;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdateSede implements Runnable {

    private Sede sede;
    private ArrayList<String> values;
    private ArrayList<String> attributes;

    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public UpdateSede(Sede sede, DataSourceClass dataSource, Connection conn) {

        this.conn = conn;
        this.dataSource = dataSource;

        this.sede = sede;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        values.add(sede.getNUMIDTERCERO());
        values.add(sede.getCODSEDETERCERO());
        values.add(sede.getNUMTELEFONOCONTACTO());
        values.add(sede.getNUMCELULARPERSONA());
        values.add(sede.getNOMENCLATURADIRECCION());
        values.add(sede.getCODMUNICIPIORNDC());
        values.add(sede.getNOMSEDETERCERO());
        values.add(sede.getUserLog());
    }

    private String buildQuery() {
        attributes.add("NUMIDTERCERO");
        attributes.add("CODSEDETERCERO");
        attributes.add("NUMTELEFONOCONTACTO");
        attributes.add("NUMCELULARPERSONA");
        attributes.add("NOMENCLATURADIRECCION");
        attributes.add("CODMUNICIPIORNDC");
        attributes.add("NOMSEDETERCERO");
        attributes.add("userLog");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 2; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "update sede set ";

        for (int i = 0; i < seleccion.size(); i++) {
            query += attributes.get(seleccion.get(i));
            query += " = '";
            query += values.get(seleccion.get(i));
            query += "', ";
        }

        query += " dateLog = current_timestamp ";
        query += "where NUMIDTERCERO = '";
        query += values.get(0);
        query += "' and CODSEDETERCERO = '";
        query += values.get(1);
        query += "';";

        return query;
    }

    public void query() {
        try {
            Statement sentencia = conn.createStatement();
            String query = "";

            query = buildQuery();
            System.out.println(query);
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
