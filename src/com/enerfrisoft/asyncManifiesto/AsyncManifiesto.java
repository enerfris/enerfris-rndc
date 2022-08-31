/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.asyncManifiesto;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.manifiesto.Manifiesto;
import com.enerfrisoft.tools.Data;
import com.enerfrisoft.tools.DateParsing;
import com.enerfrisoft.tools.WebServices;
import com.enerfrisoft.tools.n2t;
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
public class AsyncManifiesto {

    private IBPMServices conn;
    private final int server;
    private String conManifiesto;
    private Manifiesto manifiesto;
    private Connection connDB;
    private DataSourceClass dataSourceDB;
    private static final String ANSI_GREEN = "\033[1;34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private String userLog;

    public AsyncManifiesto(String conManifiesto, String userLog, int server) {
        System.out.println("--- Iniciando ---");
        this.userLog = userLog;
        this.conManifiesto = conManifiesto;
        this.conn = WebServices.getConnection();
        this.server = server;
        this.manifiesto = new Manifiesto();

        System.out.print("- Creando conexiones");
        //Conexión BD

        this.dataSourceDB = new DataSourceClass();
        this.connDB = dataSourceDB.conectar(server);
        System.out.println("... OK");
    }

    //Construye la consulta
    public String buildManifiestoXML(String username, String passwd, String nit) {
        String query = ""
                + "<?xml version='1.0' encoding='ISO-8859-1' ?>\n"
                + "<root>\n"
                + " <acceso>\n"
                + "  <username>" + username + "</username>\n"
                + "  <password>" + passwd + "</password>\n"
                + " </acceso>\n"
                + " <solicitud>\n"
                + "  <tipo>3</tipo>\n"
                + "  <procesoid>4</procesoid>\n"
                + " </solicitud>\n"
                + " <variables>\n"
                + "INGRESOID,FECHAING,NUMNITEMPRESATRANSPORTE,NUMMANIFIESTOCARGA,CONSECUTIVOINFORMACIONVIAJE,MANNROMANIFIESTOTRANSBORDO,CODOPERACIONTRANSPORTE,FECHAEXPEDICIONMANIFIESTO,CODMUNICIPIOORIGENMANIFIESTO,CODMUNICIPIODESTINOMANIFIESTO,CODIDTITULARMANIFIESTO,NUMIDTITULARMANIFIESTO,NUMPLACA,NUMPLACAREMOLQUE,CODIDCONDUCTOR,NUMIDCONDUCTOR,CODIDCONDUCTOR2,NUMIDCONDUCTOR2,VALORFLETEPACTADOVIAJE,RETENCIONFUENTEMANIFIESTO,RETENCIONICAMANIFIESTOCARGA,VALORANTICIPOMANIFIESTO,CODMUNICIPIOPAGOSALDO,CODRESPONSABLEPAGOCARGUE,CODRESPONSABLEPAGODESCARGUE,FECHAPAGOSALDOMANIFIESTO,OBSERVACIONES,SEGURIDADQR\n"
                + " </variables>\n"
                + " <documento>\n"
                + "  <NUMNITEMPRESATRANSPORTE>'" + nit + "'</NUMNITEMPRESATRANSPORTE>\n"
                + "  <NUMMANIFIESTOCARGA>'" + this.conManifiesto + "'</NUMMANIFIESTOCARGA>\n"
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
            out = conn.atenderMensajeRNDC(buildManifiestoXML(user, pass, nit));
        } catch (RemoteException ex) {
            System.out.println(ex.getLocalizedMessage() + ":AsyncManifiesto.getManifiesto()");
        }
        System.out.println("... OK");
        return out;
    }

    private void buildManifiesto() {
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
            System.out.println("- " + documento.item(0).getChildNodes().getLength() + " datos obtenidos mostrando");
            for (int i = 1; i < documento.item(0).getChildNodes().getLength(); i += 2) {
                System.out.print(" - " + documento.item(0).getChildNodes().item(i).getNodeName().toUpperCase() + " ");
                System.out.print(ANSI_GREEN + documento.item(0).getChildNodes().item(i).getTextContent() + ANSI_RESET);
            };
            System.out.println(" - ");
            
            Map<String, String> data = new HashMap<String, String>();
            for (int i = 1; i < documento.item(0).getChildNodes().getLength(); i += 2) {
                String key = documento.item(0).getChildNodes().item(i).getNodeName();
                String value = documento.item(0).getChildNodes().item(i).getTextContent();
                data.put(key.toUpperCase(), value);
            };

            this.manifiesto.setEstado("activo");
            this.manifiesto.setUserLog(this.userLog);

            String fecha = data.get("FECHAING");
            String sufijo = (fecha.subSequence(fecha.length() - 5, fecha.length()).equals("a. m.")) ? " AM" : " PM";
            fecha = fecha.subSequence(0, fecha.length() - 6) + sufijo;
            Date parsed = DateParsing.timeStampFromRNDC(fecha);
            fecha = DateParsing.toMySQLTimeStap(parsed);
            this.manifiesto.setDateLog(fecha);
            
            String oc = data.get("NUMMANIFIESTOCARGA");
            this.manifiesto.setNUMORDENCARGUE(oc);
            
            this.manifiesto.setCODCONFIGURACIONRESULTANTE("2");
            
            this.manifiesto.setCODSEDETITULARMANIFIESTO("1");
            
            n2t obj = new n2t();
            this.manifiesto.setVALORFLETEPACTADOVIAJELETRAS(obj.convertirLetras(Integer.valueOf(data.get("VALORFLETEPACTADOVIAJE"))));
            
            int valorViaje = Integer.valueOf(data.get("VALORFLETEPACTADOVIAJE"));
            float reteica = Float.valueOf(data.get("RETENCIONICAMANIFIESTOCARGA"));
            int valorReteica = (int)(valorViaje*(reteica/1000));
            this.manifiesto.setRETENCIONICAMANIFIESTOCARGAVALOR(String.valueOf(valorReteica));
            
            int retencion = Integer.valueOf(data.get("RETENCIONFUENTEMANIFIESTO"));
            int neto = valorViaje - valorReteica - retencion;
            this.manifiesto.setNETOAPAGAR(String.valueOf(neto));
            
            int anticipo = Integer.valueOf(data.get("VALORANTICIPOMANIFIESTO"));
            this.manifiesto.setSALDOAPAGAR(String.valueOf(neto - anticipo));
            
            String data0 = data.get("INGRESOID");
            this.manifiesto.setIngresoId(data0);

            String data1 = data.get("NUMNITEMPRESATRANSPORTE");
            this.manifiesto.setNUMNITEMPRESATRANSPORTE(data1);

            String data2 = data.get("NUMMANIFIESTOCARGA");
            this.manifiesto.setNUMMANIFIESTOCARGA(data2);

            String data3 = data.get("CONSECUTIVOINFORMACIONVIAJE");
            this.manifiesto.setCONSECUTIVOINFORMACIONVIAJE(data3);

            String data4 = data.get("MANNROMANIFIESTOTRANSBORDO");
            this.manifiesto.setMANNROMANIFIESTOTRANSBORDO(data4);

            String data5 = data.get("CODOPERACIONTRANSPORTE");
            this.manifiesto.setCODOPERACIONTRANSPORTE(data5);

            String data6 = data.get("FECHAEXPEDICIONMANIFIESTO");
            Date date1 = DateParsing.fromRNDC_record(data6);
            this.manifiesto.setFECHAEXPEDICIONMANIFIESTO(date1);

            String data7 = data.get("CODMUNICIPIOORIGENMANIFIESTO");
            this.manifiesto.setCODMUNICIPIOORIGENMANIFIESTO(data7);

            String data8 = data.get("CODMUNICIPIODESTINOMANIFIESTO");
            this.manifiesto.setCODMUNICIPIODESTINOMANIFIESTO(data8);

            String data9 = data.get("CODIDTITULARMANIFIESTO");
            this.manifiesto.setCODIDTITULARMANIFIESTO(data9);

            String data10 = data.get("NUMIDTITULARMANIFIESTO");
            this.manifiesto.setNUMIDTITULARMANIFIESTO(data10);

            String data11 = data.get("NUMPLACA");
            this.manifiesto.setNUMPLACA(data11);

            String data12 = data.get("NUMPLACAREMOLQUE");
            this.manifiesto.setNUMPLACAREMOLQUE1(data12);

            String data13 = data.get("CODIDCONDUCTOR");
            this.manifiesto.setCODIDCONDUCTOR(data13);

            String data14 = data.get("NUMIDCONDUCTOR");
            this.manifiesto.setNUMIDCONDUCTOR(data14);

            String data15 = data.get("CODIDCONDUCTOR2");
            this.manifiesto.setCODIDCONDUCTOR2(data15);

            String data16 = data.get("NUMIDCONDUCTOR2");
            this.manifiesto.setNUMIDCONDUCTOR2(data16);

            String data17 = data.get("VALORFLETEPACTADOVIAJE");
            this.manifiesto.setVALORFLETEPACTADOVIAJE(data17);

            String data18 = data.get("RETENCIONFUENTEMANIFIESTO");
            this.manifiesto.setORETENCIONFUENTEMANIFIEST(data18);

            String data19 = data.get("RETENCIONICAMANIFIESTOCARGA");
            this.manifiesto.setRETENCIONICAMANIFIESTOCARGA(data19);

            String data20 = data.get("VALORANTICIPOMANIFIESTO");
            this.manifiesto.setVALORANTICIPOMANIFIESTO(data20);

            String data21 = data.get("CODMUNICIPIOPAGOSALDO");
            this.manifiesto.setCODMUNICIPIOPAGOSALDO(data21);

            String data22 = data.get("CODRESPONSABLEPAGOCARGUE");
            this.manifiesto.setCODRESPONSABLEPAGOCARGUE(data22);

            String data23 = data.get("CODRESPONSABLEPAGODESCARGUE");
            this.manifiesto.setCODRESPONSABLEPAGODESCARGUE(data23);

            String data24 = data.get("FECHAPAGOSALDOMANIFIESTO");
            Date date2 = DateParsing.fromRNDC_record(data24);
            this.manifiesto.setFECHAPAGOSALDOMANIFIESTO(date2);

            String data25 = data.get("OBSERVACIONES");
            this.manifiesto.setOBSERVACIONES(data25);

            String data26 = data.get("SEGURIDADQR");
            this.manifiesto.setSEGURIDADQR(data26);

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage() + ":AsyncManifiesto.buildManifiesto()");
        }
    }

    public String buildQuery() {
        String out = "";
        buildManifiesto();
        Thread query = new Thread(new QueryManifiesto(manifiesto, dataSourceDB, connDB));
        query.start();
        return out;
    }

    public static void main(String[] args) {
        for (int i = 80; i <= 99; i++) {
            AsyncManifiesto obj = new AsyncManifiesto("TT1003" + i, "zmendezTT", 1);
            obj.buildQuery();
        }
    }
}
