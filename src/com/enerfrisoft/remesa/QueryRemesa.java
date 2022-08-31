package com.enerfrisoft.remesa;

import com.enerfrisoft.dao.DataSourceClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryRemesa implements Runnable {

    private Remesa remesa;
    private ArrayList<String> values;
    private ArrayList<String> attributes;
    
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    public QueryRemesa(Remesa remesa,DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        this.remesa = remesa;
        this.values = new ArrayList<>();
        this.attributes = new ArrayList<>();

        getAttributes();
    }

    private void getAttributes() {
//values.add(remesa.getNUMNITEMPRESATRANSPORTE());
        values.add(remesa.getCONSECUTIVOREMESA());
        values.add(remesa.getCONSECUTIVOINFORMACIONCARGA());
        values.add(remesa.getCONSECUTIVOCARGADIVIDIDA());
        values.add(remesa.getCODOPERACIONTRANSPORTE());
        values.add(remesa.getCODNATURALEZACARGA());
        values.add(remesa.getCANTIDADCARGADA());
        values.add(remesa.getUNIDADMEDIDACAPACIDAD());
        values.add(remesa.getCODTIPOEMPAQUE());
        values.add(remesa.getPESOCONTENEDORVACIO());
        values.add(remesa.getMERCANCIAREMESA());
        values.add(remesa.getDESCRIPCIONCORTAPRODUCTO());
        values.add(remesa.getCODTIPOIDREMITENTE());
        values.add(remesa.getNUMIDREMITENTE());
        values.add(remesa.getCODSEDEREMITENTE());
        values.add(remesa.getCODTIPOIDDESTINATARIO());
        values.add(remesa.getNUMIDDESTINATARIO());
        values.add(remesa.getCODSEDEDESTINATARIO());
        values.add(remesa.getDUENOPOLIZA());
        values.add(remesa.getNUMPOLIZATRANSPORTE());
        values.add(remesa.getCOMPANIASEGURO());
        values.add(remesa.getFECHAVENCIMIENTOPOLIZACARGA());
        values.add(remesa.getHORASPACTOCARGA());
        values.add(remesa.getMINUTOSPACTOCARGA());
        values.add(remesa.getHORASPACTODESCARGUE());
        values.add(remesa.getMINUTOSPACTODESCARGUE());
        values.add(remesa.getFECHALLEGADACARGUE());
        values.add(remesa.getHORALLEGADACARGUEREMESA());
        values.add(remesa.getFECHAENTRADACARGUE());
        values.add(remesa.getHORAENTRADACARGUEREMESA());
        values.add(remesa.getFECHASALIDACARGUE());
        values.add(remesa.getHORASALIDACARGUEREMESA());
        values.add(remesa.getCODTIPOIDPROPIETARIO());
        values.add(remesa.getNUMIDPROPIETARIO());
        values.add(remesa.getCODSEDEPROPIETARIO());
        values.add(remesa.getFECHACITAPACTADACARGUE());
        values.add(remesa.getHORACITAPACTADACARGUE());
        values.add(remesa.getFECHACITAPACTADADESCARGUE());
        values.add(remesa.getHORACITAPACTADADESCARGUEREMESA());
        values.add(remesa.getPERMISOCARGAEXTRA());
        values.add(remesa.getNUMIDGPS());
        values.add(remesa.getOBSERVACIONES());
        values.add(remesa.getUserLog());
        values.add(remesa.getEstado());
        values.add(remesa.getIngresoId());
    }

    private String buildQuery() {
        String query = "replace into remesa values(";

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

    private String buildQueryWithAtributes() {
//Espacio para agregar atributos al ArrayList<String> attributes
        attributes.add("CONSECUTIVOREMESA");
        attributes.add("CONSECUTIVOINFORMACIONCARGA");
        attributes.add("CONSECUTIVOCARGADIVIDIDA");
        attributes.add("CODOPERACIONTRANSPORTE");
        attributes.add("CODNATURALEZACARGA");
        attributes.add("CANTIDADCARGADA");
        attributes.add("UNIDADMEDIDACAPACIDAD");
        attributes.add("CODTIPOEMPAQUE");
        attributes.add("PESOCONTENEDORVACIO");
        attributes.add("MERCANCIAREMESA");
        attributes.add("DESCRIPCIONCORTAPRODUCTO");
        attributes.add("CODTIPOIDREMITENTE");
        attributes.add("NUMIDREMITENTE");
        attributes.add("CODSEDEREMITENTE");
        attributes.add("CODTIPOIDDESTINATARIO");
        attributes.add("NUMIDDESTINATARIO");
        attributes.add("CODSEDEDESTINATARIO");
        attributes.add("DUENOPOLIZA");
        attributes.add("NUMPOLIZATRANSPORTE");
        attributes.add("COMPANIASEGURO");
        attributes.add("FECHAVENCIMIENTOPOLIZACARGA");
        attributes.add("HORASPACTOCARGA");
        attributes.add("MINUTOSPACTOCARGA");
        attributes.add("HORASPACTODESCARGUE");
        attributes.add("MINUTOSPACTODESCARGUE");
        attributes.add("FECHALLEGADACARGUE");
        attributes.add("HORALLEGADACARGUEREMESA");
        attributes.add("FECHAENTRADACARGUE");
        attributes.add("HORAENTRADACARGUEREMESA");
        attributes.add("FECHASALIDACARGUE");
        attributes.add("HORASALIDACARGUEREMESA");
        attributes.add("CODTIPOIDPROPIETARIO");
        attributes.add("NUMIDPROPIETARIO");
        attributes.add("CODSEDEPROPIETARIO");
        attributes.add("FECHACITAPACTADACARGUE");
        attributes.add("HORACITAPACTADACARGUE");
        attributes.add("FECHACITAPACTADADESCARGUE");
        attributes.add("HORACITAPACTADADESCARGUEREMESA");
        attributes.add("PERMISOCARGAEXTRA");
        attributes.add("NUMIDGPS");
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

        String query = "replace into remesa(";

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
            System.out.println("- Mostrar query insert en DB");
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
