package com.enerfrisoft.sede;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.error.QueryError;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuerySede implements Runnable {

    private Sede sede;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public QuerySede(Sede sede,DataSourceClass dataSource,Connection conn) {
        
        this.conn = conn;
        this.dataSource = dataSource;
                
        this.sede = sede;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();
        getAttributes();
    }

    private void getAttributes() {
        values.add(sede.getNUMTELEFONOCONTACTO());
        values.add(sede.getNUMCELULARPERSONA());
        values.add(sede.getNOMENCLATURADIRECCION());
        values.add(sede.getCODMUNICIPIORNDC());
        values.add(sede.getCODSEDETERCERO());
        values.add(sede.getNOMSEDETERCERO());
        values.add(sede.getNUMIDTERCERO());
        values.add(sede.getUserLog());
        
    }

    private String buildQueryWithAtributes() {
        String query = "replace into sede(";

        attributes.add("NUMTELEFONOCONTACTO");
        attributes.add("NUMCELULARPERSONA");
        attributes.add("NOMENCLATURADIRECCION");
        attributes.add("CODMUNICIPIORNDC");
        attributes.add("CODSEDETERCERO");
        attributes.add("NOMSEDETERCERO");
        attributes.add("NUMIDTERCERO");
        attributes.add("userLog");

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

    public void query() {
        try {
            
            Statement sentencia = conn.createStatement();
            String query = "";

            query = buildQueryWithAtributes();
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
