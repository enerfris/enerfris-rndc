package com.enerfrisoft.remolque;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryRemolque implements Runnable {

    private Remolque remolque;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;
    
    
    public QueryRemolque(Remolque remolque,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        
        this.remolque = remolque;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();
        
        getAttributes();
    }

    private void getAttributes() {
        values.add(remolque.getNUMPLACA());
        values.add(remolque.getCODCONFIGURACIONUNIDADCARGA());
        values.add(remolque.getCODMARCAVEHICULOCARGA());
        values.add(remolque.getNUMEJES());
        values.add(remolque.getANOFABRICACIONVEHICULOCARGA());
        values.add(remolque.getPESOVEHICULOVACIO());
        values.add(remolque.getCAPACIDADUNIDADCARGA());
        values.add(remolque.getUNIDADMEDIDACAPACIDAD());
        values.add(remolque.getCODTIPOCARROCERIA());
        values.add(remolque.getNUMCHASIS());
        values.add(remolque.getNUMSEGUROSOAT());
        values.add(remolque.getNUMNITASEGURADORASOAT());
        values.add(DateParsing.toMySQL(remolque.getFECHAVENCIMIENTOSOAT()));
        values.add(remolque.getCODTIPOIDPROPIETARIO());
        values.add(remolque.getNUMIDPROPIETARIO());
        values.add(remolque.getCODTIPOIDTENEDOR());
        values.add(remolque.getNUMIDTENEDOR());
        values.add(remolque.getIngresoId());
        values.add(remolque.getAfiliacion());
        values.add(remolque.getEmpresa());
        values.add(remolque.getRntc());
        values.add(remolque.getPbc());
        values.add(remolque.getTarjeta_propiedad());
        values.add(remolque.getEstado());
        values.add(remolque.getUserLog());
        values.add(remolque.getObservaciones());
    }

    private String buildQueryWithAtributes() {
        attributes.add("NUMPLACA");
        attributes.add("CODCONFIGURACIONUNIDADCARGA");
        attributes.add("CODMARCAVEHICULOCARGA");
        attributes.add("NUMEJES");
        attributes.add("ANOFABRICACIONVEHICULOCARGA");
        attributes.add("PESOVEHICULOVACIO");
        attributes.add("CAPACIDADUNIDADCARGA");
        attributes.add("UNIDADMEDIDACAPACIDAD");
        attributes.add("CODTIPOCARROCERIA");
        attributes.add("NUMCHASIS");
        attributes.add("NUMSEGUROSOAT");
        attributes.add("NUMNITASEGURADORASOAT");
        attributes.add("FECHAVENCIMIENTOSOAT");
        attributes.add("CODTIPOIDPROPIETARIO");
        attributes.add("NUMIDPROPIETARIO");
        attributes.add("CODTIPOIDTENEDOR");
        attributes.add("NUMIDTENEDOR");
        attributes.add("ingresoId");
        attributes.add("afiliacion");
        attributes.add("empresa");
        attributes.add("rntc");
        attributes.add("pbc");
        attributes.add("tarjeta_propiedad");
        attributes.add("estado");
        attributes.add("userLog");
        attributes.add("observaciones");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "replace into remolque(";

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
