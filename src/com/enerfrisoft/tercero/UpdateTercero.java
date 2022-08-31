package com.enerfrisoft.tercero;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class UpdateTercero implements Runnable {

    private Tercero tercero;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public UpdateTercero(Tercero tercero,DataSourceClass dataSource,Connection conn) {
        
        this.conn = conn;
        this.dataSource = dataSource;
        

        this.tercero = tercero;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        //values.add(tercero.getCODTIPOIDTERCERO());
        values.add(tercero.getNUMIDTERCERO());
        values.add(tercero.getEXPEDICION());
        values.add(tercero.getNOMIDTERCERO());
        values.add(tercero.getPRIMERAPELLIDOIDTERCERO());
        values.add(tercero.getSEGUNDOAPELLIDOIDTERCERO());
        values.add(tercero.getNUMLICENCIACONDUCCION());
        values.add(tercero.getCODCATEGORIALICENCIACONDUCCION());
        values.add(DateParsing.toMySQL(tercero.getFECHAVENCIMIENTOLICENCIA()));
        values.add(tercero.getEMAIL());
        values.add(tercero.getIngresoId());
        values.add(tercero.getEstado());
        values.add(tercero.getUserLog());
        values.add(tercero.getInfoAdicional());
    }

    private String buildQuery() {
        //attributes.add("CODTIPOIDTERCERO");
        attributes.add("NUMIDTERCERO");
        attributes.add("EXPEDICION");
        attributes.add("NOMIDTERCERO");
        attributes.add("PRIMERAPELLIDOIDTERCERO");
        attributes.add("SEGUNDOAPELLIDOIDTERCERO");
        attributes.add("NUMLICENCIACONDUCCION");
        attributes.add("CODCATEGORIALICENCIACONDUCCION");
        attributes.add("FECHAVENCIMIENTOLICENCIA");
        attributes.add("EMAIL");
        attributes.add("ingresoId");
        attributes.add("estado");
        attributes.add("userLog");
        attributes.add("infoAdicional");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 1; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "update tercero set ";
                    
        for (int i = 0; i < seleccion.size(); i++) {
            query += attributes.get(seleccion.get(i));
            query += " = '";
            query += values.get(seleccion.get(i));
            query += "', ";
        }
        
        query += " dateLog = current_timestamp ";
        query += "where NUMIDTERCERO = '";
        query += values.get(0);
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
