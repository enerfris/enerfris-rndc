/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.asyncRemesa;

import com.enerfrisoft.dao.DataSourceClass;
import static com.enerfrisoft.openForm.OpenForm.remesa;
import com.enerfrisoft.remesa.Remesa;
import com.enerfrisoft.tools.Data;
import com.enerfrisoft.tools.DateParsing;
import com.enerfrisoft.tools.WebServices;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import wsdl.IBPMServices;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.SAXException;

/**
 *
 * @author sebastianaf
 */
public class AsyncRemesa {

    private IBPMServices conn;
    private final int server;
    private String conRemesa;
    private Remesa remesa;
    private Connection connDB;
    private DataSourceClass dataSourceDB;
    private static final String ANSI_GREEN = "\033[1;34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private String userLog;

    public AsyncRemesa(String remesa, String userLog, int server) {
        System.out.println("--- Iniciando ---");
        this.userLog = userLog;
        this.conRemesa = remesa;
        this.conn = WebServices.getConnection();
        this.server = server;
        this.remesa = new Remesa();

        System.out.print("- Creando conexiones");
        //Conexión BD

        this.dataSourceDB = new DataSourceClass();
        this.connDB = dataSourceDB.conectar(server);
        System.out.println("... OK");
    }

    //Construye la consulta
    public String buildRemesaXML(String username, String passwd, String nit, String remesa) {
        String query = "<?xml version='1.0' encoding='ISO-8859-1' ?>\n"
                + "<root>\n"
                + " <acceso>\n"
                + "  <username>" + username + "</username>\n"
                + "  <password>" + passwd + "</password>\n"
                + " </acceso>\n"
                + " <solicitud>\n"
                + "  <tipo>3</tipo>\n"
                + "  <procesoid>3</procesoid>\n"
                + " </solicitud>\n"
                + " <variables>\n"
                + "INGRESOID,FECHAING,NUMNITEMPRESATRANSPORTE,CONSECUTIVOREMESA,CONSECUTIVOINFORMACIONCARGA,CONSECUTIVOCARGADIVIDIDA,CODOPERACIONTRANSPORTE,CODNATURALEZACARGA,CANTIDADCARGADA,UNIDADMEDIDACAPACIDAD,CODTIPOEMPAQUE,PESOCONTENEDORVACIO,MERCANCIAREMESA,DESCRIPCIONCORTAPRODUCTO,CODTIPOIDREMITENTE,NUMIDREMITENTE,CODSEDEREMITENTE,CODTIPOIDDESTINATARIO,NUMIDDESTINATARIO,CODSEDEDESTINATARIO,DUENOPOLIZA,NUMPOLIZATRANSPORTE,COMPANIASEGURO,FECHAVENCIMIENTOPOLIZACARGA,HORASPACTOCARGA,MINUTOSPACTOCARGA,HORASPACTODESCARGUE,MINUTOSPACTODESCARGUE,FECHALLEGADACARGUE,HORALLEGADACARGUEREMESA,FECHAENTRADACARGUE,HORAENTRADACARGUEREMESA,FECHASALIDACARGUE,HORASALIDACARGUEREMESA,CODTIPOIDPROPIETARIO,NUMIDPROPIETARIO,CODSEDEPROPIETARIO,FECHACITAPACTADACARGUE,HORACITAPACTADACARGUE,FECHACITAPACTADADESCARGUE,HORACITAPACTADADESCARGUEREMESA,PERMISOCARGAEXTRA,NUMIDGPS\n"
                + " </variables>\n"
                + " <documento>\n"
                + "  <NUMNITEMPRESATRANSPORTE>'" + nit + "'</NUMNITEMPRESATRANSPORTE>\n"
                + "  <CONSECUTIVOREMESA>'" + remesa + "'</CONSECUTIVOREMESA>\n"
                + " </documento>\n"
                + "</root>";
        return query;
    }

    //Consulta los datos de una remesa
    private String getRemesa() {
        System.out.print("- Preparando consulta");
        String user = Data.getCredentials(server).get("user");
        String pass = Data.getCredentials(server).get("pass");
        String nit = Data.getCredentials(server).get("nit");
        String out = "";
        System.out.println("... OK");
        try {
            System.out.print("- Enviar mensaje RNDC");
            out = conn.atenderMensajeRNDC(buildRemesaXML(user, pass, nit, conRemesa));
        } catch (RemoteException ex) {
            System.out.println(ex.getLocalizedMessage() + ":AsyncRemesa.getRemesa()");
        }
        System.out.println("... OK");
        return out;
    }

    private void buildRemesa() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringBuilder xmlStringBuilder = new StringBuilder();
            String remesa = getRemesa();
            System.out.print("- Iniciando parse XML");
            xmlStringBuilder.append(remesa);
            ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
            Document doc = builder.parse(input);
            NodeList documento = doc.getElementsByTagName("documento");
            System.out.println("... OK");
            //Mostrar los datos del xml
            System.out.println("- Mostrando datos obtenidos");
            for (int i = 1; i < documento.item(0).getChildNodes().getLength(); i+=2) {
                System.out.print(ANSI_GREEN + documento.item(0).getChildNodes().item(i).getNodeName().toUpperCase()+" - ");
                System.out.println(documento.item(0).getChildNodes().item(i).getTextContent() + ANSI_RESET);
            };

            Map<String, String> data = new HashMap<String, String>();
            for (int i = 1; i < documento.item(0).getChildNodes().getLength(); i += 2) {
                String key = documento.item(0).getChildNodes().item(i).getNodeName();
                String value = documento.item(0).getChildNodes().item(i).getTextContent();
                data.put(key.toUpperCase(), value);
            };

            this.remesa.setEstado("activo");
            this.remesa.setUserLog(this.userLog);

            String fecha = data.get("FECHAING");
            String sufijo = (fecha.subSequence(fecha.length() - 5, fecha.length()).equals("a. m."))?" AM":" PM"; 
            fecha = fecha.subSequence(0, fecha.length() - 6) + sufijo;
            Date parsed = DateParsing.timeStampFromRNDC(fecha);
            fecha = DateParsing.toMySQLTimeStap(parsed);
            this.remesa.setDateLog(fecha);

            String data0 = data.get("INGRESOID");
            this.remesa.setIngresoId(data0);

            String data2 = data.get("NUMNITEMPRESATRANSPORTE");
            this.remesa.setNUMNITEMPRESATRANSPORTE(data2);

            String data3 = data.get("CONSECUTIVOREMESA");
            System.out.println("Consecutivo Remesa: " + data3);
            this.remesa.setCONSECUTIVOREMESA(data3);

            String data4 = data.get("CONSECUTIVOINFORMACIONCARGA");
            this.remesa.setCONSECUTIVOINFORMACIONCARGA(data4);

            String data5 = data.get("CONSECUTIVOCARGADIVIDIDA");
            this.remesa.setCONSECUTIVOCARGADIVIDIDA(data5);

            String data6 = data.get("CODOPERACIONTRANSPORTE");
            this.remesa.setCODOPERACIONTRANSPORTE(data6);

            String data7 = data.get("CODNATURALEZACARGA");
            this.remesa.setCODNATURALEZACARGA(data7);

            String data8 = data.get("CANTIDADCARGADA");
            this.remesa.setCANTIDADCARGADA(data8);

            String data9 = data.get("UNIDADMEDIDACAPACIDAD");
            this.remesa.setUNIDADMEDIDACAPACIDAD(data9);

            String data10 = data.get("CODTIPOEMPAQUE");
            this.remesa.setCODTIPOEMPAQUE(data10);

            String data11 = data.get("PESOCONTENEDORVACIO");
            this.remesa.setPESOCONTENEDORVACIO(data11);

            String data12 = data.get("MERCANCIAREMESA");
            this.remesa.setMERCANCIAREMESA(data12);

            String data13 = data.get("DESCRIPCIONCORTAPRODUCTO");
            this.remesa.setDESCRIPCIONCORTAPRODUCTO(data13);

            String data14 = data.get("CODTIPOIDREMITENTE");
            this.remesa.setCODTIPOIDREMITENTE(data14);

            String data15 = data.get("NUMIDREMITENTE");
            this.remesa.setNUMIDREMITENTE(data15);

            String data16 = data.get("CODSEDEREMITENTE");
            this.remesa.setCODSEDEREMITENTE(data16);

            String data17 = data.get("CODTIPOIDDESTINATARIO");
            this.remesa.setCODTIPOIDDESTINATARIO(data17);

            String data18 = data.get("NUMIDDESTINATARIO");
            this.remesa.setNUMIDDESTINATARIO(data18);

            String data19 = data.get("CODSEDEDESTINATARIO");
            this.remesa.setCODSEDEDESTINATARIO(data19);

            String data20 = data.get("DUENOPOLIZA");
            this.remesa.setDUENOPOLIZA(data20);

            String data21 = data.get("NUMPOLIZATRANSPORTE");
            this.remesa.setNUMPOLIZATRANSPORTE(data21);

            String data22 = data.get("COMPANIASEGURO");
            this.remesa.setCOMPANIASEGURO(data22);

            String data23 = data.get("FECHAVENCIMIENTOPOLIZACARGA");
            this.remesa.setFECHAVENCIMIENTOPOLIZACARGA(data23);

            String data24 = data.get("HORASPACTOCARGA");
            this.remesa.setHORASPACTOCARGA(data24);

            String data25 = data.get("MINUTOSPACTOCARGA");
            this.remesa.setMINUTOSPACTOCARGA(data25);

            String data26 = data.get("HORASPACTODESCARGUE");
            this.remesa.setHORASPACTODESCARGUE(data26);

            String data27 = data.get("MINUTOSPACTODESCARGUE");
            this.remesa.setMINUTOSPACTODESCARGUE(data27);

            String data28 = data.get("FECHALLEGADACARGUE");
            this.remesa.setFECHALLEGADACARGUE(data28);

            String data29 = data.get("HORALLEGADACARGUEREMESA");
            this.remesa.setHORALLEGADACARGUEREMESA(data29);

            String data30 = data.get("FECHAENTRADACARGUE");
            this.remesa.setFECHAENTRADACARGUE(data30);

            String data31 = data.get("HORAENTRADACARGUEREMESA");
            this.remesa.setHORAENTRADACARGUEREMESA(data31);

            String data32 = data.get("FECHASALIDACARGUE");
            this.remesa.setFECHASALIDACARGUE(data32);

            String data33 = data.get("HORASALIDACARGUEREMESA");
            this.remesa.setHORASALIDACARGUEREMESA(data33);

            String data34 = data.get("CODTIPOIDPROPIETARIO");
            this.remesa.setCODTIPOIDPROPIETARIO(data34);

            String data35 = data.get("NUMIDPROPIETARIO");
            this.remesa.setNUMIDPROPIETARIO(data35);

            String data36 = data.get("CODSEDEPROPIETARIO");
            this.remesa.setCODSEDEPROPIETARIO(data36);

            String data37 = data.get("FECHACITAPACTADACARGUE");
            this.remesa.setFECHACITAPACTADACARGUE(data37);

            String data38 = data.get("HORACITAPACTADACARGUE");
            this.remesa.setHORACITAPACTADACARGUE(data38);

            String data39 = data.get("FECHACITAPACTADADESCARGUE");
            this.remesa.setFECHACITAPACTADADESCARGUE(data39);

            String data40 = data.get("HORACITAPACTADADESCARGUEREMESA");
            this.remesa.setHORACITAPACTADADESCARGUEREMESA(data40);

            String data41 = data.get("PERMISOCARGAEXTRA");
            this.remesa.setPERMISOCARGAEXTRA(data41);

            String data42 = data.get("NUMIDGPS");
            this.remesa.setNUMIDGPS(data42);

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage() + ":AsyncRemesa.buildRemesa()");
        }
    }

    public String buildQuery() {
        String out = "";
        buildRemesa();
        Thread query = new Thread(new QueryRemesa(remesa, dataSourceDB, connDB));
        query.start();
        return out;
    }

    public static void main(String[] args) {
        for (int i = 38; i <= 95; i++) {
            AsyncRemesa obj = new AsyncRemesa("TT1011"+i, "zmendezTT", 1);
            obj.buildQuery();
        }
        
        
    }
}
