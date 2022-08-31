package com.enerfrisoft.asyncManifiesto;

import com.enerfrisoft.manifiesto.*;
import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QueryManifiesto implements Runnable {

    private Manifiesto manifiesto;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;
    

    public QueryManifiesto(Manifiesto manifiesto,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.manifiesto = manifiesto;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
        //values.add(manifiesto.getNUMNITEMPRESATRANSPORTE());
        values.add(manifiesto.getNUMMANIFIESTOCARGA());
        values.add(manifiesto.getNUMORDENCARGUE());
        values.add(manifiesto.getCONSECUTIVOINFORMACIONVIAJE());
        values.add(manifiesto.getMANNROMANIFIESTOTRANSBORDO());
        values.add(manifiesto.getCODOPERACIONTRANSPORTE());
        values.add(DateParsing.toMySQL(manifiesto.getFECHAEXPEDICIONMANIFIESTO()));
        values.add(manifiesto.getCODMUNICIPIOORIGENMANIFIESTO());
        values.add(manifiesto.getCODMUNICIPIODESTINOMANIFIESTO());
        values.add(manifiesto.getCODIDTITULARMANIFIESTO());
        values.add(manifiesto.getNUMIDTITULARMANIFIESTO());
        values.add(manifiesto.getCODSEDETITULARMANIFIESTO());
        values.add(manifiesto.getNUMPLACA());
        values.add(manifiesto.getCODCONFIGURACIONRESULTANTE());
        values.add(manifiesto.getNUMPLACAREMOLQUE1());
        values.add(manifiesto.getPESOVEHICULOVACIOREMOLQUE1());
        values.add(manifiesto.getNUMPLACAREMOLQUE2());
        values.add(manifiesto.getPESOVEHICULOVACIOREMOLQUE2());
        values.add(manifiesto.getCODIDCONDUCTOR());
        values.add(manifiesto.getNUMIDCONDUCTOR());
        values.add(manifiesto.getCODIDCONDUCTOR2());
        values.add(manifiesto.getNUMIDCONDUCTOR2());
        values.add(manifiesto.getVALORFLETEPACTADOVIAJE());
        values.add(manifiesto.getVALORFLETEPACTADOVIAJELETRAS());
        values.add(manifiesto.getORETENCIONFUENTEMANIFIEST());
        values.add(manifiesto.getRETENCIONICAMANIFIESTOCARGA());
        values.add(manifiesto.getRETENCIONICAMANIFIESTOCARGAVALOR());
        values.add(manifiesto.getVALORANTICIPOMANIFIESTO());
        values.add(manifiesto.getSALDOAPAGAR());
        values.add(manifiesto.getNETOAPAGAR());
        values.add(manifiesto.getCODMUNICIPIOPAGOSALDO());
        values.add(manifiesto.getCODRESPONSABLEPAGOCARGUE());
        values.add(manifiesto.getCODRESPONSABLEPAGODESCARGUE());
        values.add(DateParsing.toMySQL(manifiesto.getFECHAPAGOSALDOMANIFIESTO()));
        values.add(manifiesto.getOBSERVACIONES());
        values.add(manifiesto.getSEGURIDADQR());
        values.add(manifiesto.getIngresoId());
        values.add(manifiesto.getUserLog());
        values.add(manifiesto.getEstado());
        values.add(manifiesto.getDateLog());
        
    }

    private String buildQueryWithAtributes() {
        //attributes.add("NUMNITEMPRESATRANSPORTE");
        attributes.add("NUMMANIFIESTOCARGA");
        attributes.add("NUMORDENCARGUE");
        attributes.add("CONSECUTIVOINFORMACIONVIAJE");
        attributes.add("MANNROMANIFIESTOTRANSBORDO");
        attributes.add("CODOPERACIONTRANSPORTE");
        attributes.add("FECHAEXPEDICIONMANIFIESTO");
        attributes.add("CODMUNICIPIOORIGENMANIFIESTO");
        attributes.add("CODMUNICIPIODESTINOMANIFIESTO");
        attributes.add("CODIDTITULARMANIFIESTO");
        attributes.add("NUMIDTITULARMANIFIESTO");
        attributes.add("CODSEDETITULARMANIFIESTO");
        attributes.add("NUMPLACA");
        attributes.add("CODCONFIGURACIONRESULTANTE");
        attributes.add("NUMPLACAREMOLQUE1");
        attributes.add("PESOVEHICULOVACIOREMOLQUE1");
        attributes.add("NUMPLACAREMOLQUE2");
        attributes.add("PESOVEHICULOVACIOREMOLQUE2");
        attributes.add("CODIDCONDUCTOR");
        attributes.add("NUMIDCONDUCTOR");
        attributes.add("CODIDCONDUCTOR2");
        attributes.add("NUMIDCONDUCTOR2");
        attributes.add("VALORFLETEPACTADOVIAJE");
        attributes.add("VALORFLETEPACTADOVIAJELETRAS");
        attributes.add("ORETENCIONFUENTEMANIFIEST");
        attributes.add("RETENCIONICAMANIFIESTOCARGA");
        attributes.add("RETENCIONICAMANIFIESTOCARGAVALOR");
        attributes.add("VALORANTICIPOMANIFIESTO");
        attributes.add("SALDOAPAGAR");
        attributes.add("NETOAPAGAR");
        attributes.add("CODMUNICIPIOPAGOSALDO");
        attributes.add("CODRESPONSABLEPAGOCARGUE");
        attributes.add("CODRESPONSABLEPAGODESCARGUE");
        attributes.add("FECHAPAGOSALDOMANIFIESTO");
        attributes.add("OBSERVACIONES");
        attributes.add("SEGURIDADQR");
        attributes.add("ingresoId");
        attributes.add("userLog");
        attributes.add("estado");
        attributes.add("dateLog");

        ArrayList<Integer> seleccion = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!values.get(i).equals("")) {
                seleccion.add(i);
            }
        }

        String query = "replace into manifiesto(";

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
            System.out.println(query);
            sentencia.execute(query);
            
            for (int i = 0; i < manifiesto.getREMESASMAN().size(); i++) {
                String subQuery = ""
                        + "update remesa "
                        + "set manifiestoConsecutivo='"+manifiesto.getNUMMANIFIESTOCARGA()+"' "
                        + "where remesa.CONSECUTIVOREMESA='"+manifiesto.getREMESASMAN().get(i)+"'";
                sentencia.execute(subQuery);
            }
            
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
    
    public static void main(String[] args) {
        Manifiesto m = new Manifiesto();
        
    }
}
