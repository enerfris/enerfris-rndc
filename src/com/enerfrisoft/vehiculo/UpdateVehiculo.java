package com.enerfrisoft.vehiculo;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class UpdateVehiculo implements Runnable {

    private Vehiculo vehiculo;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;
    

    public UpdateVehiculo(Vehiculo vehiculo,DataSourceClass dataSource,Connection conn) {
        
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        
        this.vehiculo = vehiculo;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        values.add(vehiculo.getNUMPLACA());
        values.add(vehiculo.getCODCONFIGURACIONUNIDADCARGA());
        values.add(vehiculo.getCODMARCAVEHICULOCARGA());
        values.add(vehiculo.getCODLINEAVEHICULOCARGA());
        values.add(vehiculo.getNUMEJES());
        values.add(vehiculo.getANOFABRICACIONVEHICULOCARGA());
        values.add(vehiculo.getANOREPOTENCIACION());
        values.add(vehiculo.getCODCOLORVEHICULOCARGA());
        values.add(vehiculo.getPESOVEHICULOVACIO());
        values.add(vehiculo.getCAPACIDADUNIDADCARGA());
        values.add(vehiculo.getUNIDADMEDIDACAPACIDAD());
        values.add(vehiculo.getCODTIPOCARROCERIA());
        values.add(vehiculo.getNUMCHASIS());
        values.add(vehiculo.getCODTIPOCOMBUSTIBLE());
        values.add(vehiculo.getNUMSEGUROSOAT());
        values.add(DateParsing.toMySQL(vehiculo.getFECHAVENCIMIENTOSOAT()));
        values.add(vehiculo.getNUMNITASEGURADORASOAT());
        values.add(vehiculo.getCODTIPOIDPROPIETARIO());
        values.add(vehiculo.getNUMIDPROPIETARIO());
        values.add(vehiculo.getCODTIPOIDTENEDOR());
        values.add(vehiculo.getNUMIDTENEDOR());
        values.add(vehiculo.getIngresoId());
        values.add(vehiculo.getAfiliacion());
        values.add(vehiculo.getEmpresa());
        values.add(vehiculo.getRntc());
        values.add(vehiculo.getPvb());
        values.add(vehiculo.getTarjeta_propiedad());
        values.add(vehiculo.getSerie_motor());
        values.add(vehiculo.getEstado());
        values.add(vehiculo.getUserLog());
        values.add(vehiculo.getObservaciones());
    }

    private String buildQuery() {
        attributes.add("NUMPLACA");
        attributes.add("CODCONFIGURACIONUNIDADCARGA");
        attributes.add("CODMARCAVEHICULOCARGA");
        attributes.add("CODLINEAVEHICULOCARGA");
        attributes.add("NUMEJES");
        attributes.add("ANOFABRICACIONVEHICULOCARGA");
        attributes.add("ANOREPOTENCIACION");
        attributes.add("CODCOLORVEHICULOCARGA");
        attributes.add("PESOVEHICULOVACIO");
        attributes.add("CAPACIDADUNIDADCARGA");
        attributes.add("UNIDADMEDIDACAPACIDAD");
        attributes.add("CODTIPOCARROCERIA");
        attributes.add("NUMCHASIS");
        attributes.add("CODTIPOCOMBUSTIBLE");
        attributes.add("NUMSEGUROSOAT");
        attributes.add("FECHAVENCIMIENTOSOAT");
        attributes.add("NUMNITASEGURADORASOAT");
        attributes.add("CODTIPOIDPROPIETARIO");
        attributes.add("NUMIDPROPIETARIO");
        attributes.add("CODTIPOIDTENEDOR");
        attributes.add("NUMIDTENEDOR");
        attributes.add("ingresoId");
        attributes.add("afiliacion");
        attributes.add("empresa");
        attributes.add("rntc");
        attributes.add("pvb");
        attributes.add("tarjeta_propiedad");
        attributes.add("serie_motor");
        attributes.add("estado");
        attributes.add("userLog");
        attributes.add("observaciones");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 1; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "update vehiculo set ";
                    
        for (int i = 0; i < seleccion.size(); i++) {
            query += attributes.get(seleccion.get(i));
            query += " = '";
            query += values.get(seleccion.get(i));
            query += "', ";
        }
        
        query += " dateLog = current_timestamp ";
        query += "where NUMPLACA = '";
        query += values.get(0);
        query += "';";

        return query;
    }

    public void query() {
        try {
            
            Statement sentencia = conn.createStatement();
            String query = "";

            query = buildQuery();
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
