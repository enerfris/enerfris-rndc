package com.enerfrisoft.manifiesto;

import com.enerfrisoft.tools.DateParsing;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLManifiesto {

    public static String grabar(Manifiesto entity) {
        Document request;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            request = builder.newDocument();
            Element root = request.createElement("root");
            request.appendChild(root);
            Element acceso = request.createElement("acceso");
            root.appendChild(acceso);
            Element username = request.createElement("username");
            username.appendChild(request.createTextNode(entity.getUsuario()));
            acceso.appendChild(username);
            Element password = request.createElement("password");
            password.appendChild(request.createTextNode(entity.getPassword()));
            acceso.appendChild(password);
            Element solicitud = request.createElement("solicitud");
            root.appendChild(solicitud);
            Element tipo = request.createElement("tipo");
            tipo.appendChild(request.createTextNode("1"));
            solicitud.appendChild(tipo);
            Element procesoid = request.createElement("procesoid");
            procesoid.appendChild(request.createTextNode("4"));
            solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);

            Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
            NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(entity.getNUMNITEMPRESATRANSPORTE()));
            if (!entity.getNUMNITEMPRESATRANSPORTE().equals("")) {
                variables.appendChild(NUMNITEMPRESATRANSPORTE);
            }
            Element NUMMANIFIESTOCARGA = request.createElement("NUMMANIFIESTOCARGA");
            NUMMANIFIESTOCARGA.appendChild(request.createTextNode(entity.getNUMMANIFIESTOCARGA()));
            if (!entity.getNUMMANIFIESTOCARGA().equals("")) {
                variables.appendChild(NUMMANIFIESTOCARGA);
            }
            Element CONSECUTIVOINFORMACIONVIAJE = request.createElement("CONSECUTIVOINFORMACIONVIAJE");
            CONSECUTIVOINFORMACIONVIAJE.appendChild(request.createTextNode(entity.getCONSECUTIVOINFORMACIONVIAJE()));
            if (!entity.getCONSECUTIVOINFORMACIONVIAJE().equals("")) {
                variables.appendChild(CONSECUTIVOINFORMACIONVIAJE);
            }
            Element MANNROMANIFIESTOTRANSBORDO = request.createElement("MANNROMANIFIESTOTRANSBORDO");
            MANNROMANIFIESTOTRANSBORDO.appendChild(request.createTextNode(entity.getMANNROMANIFIESTOTRANSBORDO()));
            if (!entity.getMANNROMANIFIESTOTRANSBORDO().equals("")) {
                variables.appendChild(MANNROMANIFIESTOTRANSBORDO);
            }
            Element CODOPERACIONTRANSPORTE = request.createElement("CODOPERACIONTRANSPORTE");
            CODOPERACIONTRANSPORTE.appendChild(request.createTextNode(entity.getCODOPERACIONTRANSPORTE()));
            if (!entity.getCODOPERACIONTRANSPORTE().equals("")) {
                variables.appendChild(CODOPERACIONTRANSPORTE);
            }
            Element FECHAEXPEDICIONMANIFIESTO = request.createElement("FECHAEXPEDICIONMANIFIESTO");
            FECHAEXPEDICIONMANIFIESTO.appendChild(request.createTextNode(DateParsing.toXML(entity.getFECHAEXPEDICIONMANIFIESTO())));
            if (!DateParsing.toXML(entity.getFECHAEXPEDICIONMANIFIESTO()).equals("")) {
                variables.appendChild(FECHAEXPEDICIONMANIFIESTO);
            }
            Element CODMUNICIPIOORIGENMANIFIESTO = request.createElement("CODMUNICIPIOORIGENMANIFIESTO");
            CODMUNICIPIOORIGENMANIFIESTO.appendChild(request.createTextNode(entity.getCODMUNICIPIOORIGENMANIFIESTO()));
            if (!entity.getCODMUNICIPIOORIGENMANIFIESTO().equals("")) {
                variables.appendChild(CODMUNICIPIOORIGENMANIFIESTO);
            }
            Element CODMUNICIPIODESTINOMANIFIESTO = request.createElement("CODMUNICIPIODESTINOMANIFIESTO");
            CODMUNICIPIODESTINOMANIFIESTO.appendChild(request.createTextNode(entity.getCODMUNICIPIODESTINOMANIFIESTO()));
            if (!entity.getCODMUNICIPIODESTINOMANIFIESTO().equals("")) {
                variables.appendChild(CODMUNICIPIODESTINOMANIFIESTO);
            }
            Element CODIDTITULARMANIFIESTO = request.createElement("CODIDTITULARMANIFIESTO");
            CODIDTITULARMANIFIESTO.appendChild(request.createTextNode(entity.getCODIDTITULARMANIFIESTO()));
            if (!entity.getCODIDTITULARMANIFIESTO().equals("")) {
                variables.appendChild(CODIDTITULARMANIFIESTO);
            }
            Element NUMIDTITULARMANIFIESTO = request.createElement("NUMIDTITULARMANIFIESTO");
            NUMIDTITULARMANIFIESTO.appendChild(request.createTextNode(entity.getNUMIDTITULARMANIFIESTO()));
            if (!entity.getNUMIDTITULARMANIFIESTO().equals("")) {
                variables.appendChild(NUMIDTITULARMANIFIESTO);
            }
            Element NUMPLACA = request.createElement("NUMPLACA");
            NUMPLACA.appendChild(request.createTextNode(entity.getNUMPLACA()));
            if (!entity.getNUMPLACA().equals("")) {
                variables.appendChild(NUMPLACA);
            }
            Element NUMPLACAREMOLQUE = request.createElement("NUMPLACAREMOLQUE");
            NUMPLACAREMOLQUE.appendChild(request.createTextNode(entity.getNUMPLACAREMOLQUE1()));
            if (!entity.getNUMPLACAREMOLQUE1().equals("")) {
                variables.appendChild(NUMPLACAREMOLQUE);
            }
            Element CODIDCONDUCTOR = request.createElement("CODIDCONDUCTOR");
            CODIDCONDUCTOR.appendChild(request.createTextNode(entity.getCODIDCONDUCTOR()));
            if (!entity.getCODIDCONDUCTOR().equals("")) {
                variables.appendChild(CODIDCONDUCTOR);
            }
            Element NUMIDCONDUCTOR = request.createElement("NUMIDCONDUCTOR");
            NUMIDCONDUCTOR.appendChild(request.createTextNode(entity.getNUMIDCONDUCTOR()));
            if (!entity.getNUMIDCONDUCTOR().equals("")) {
                variables.appendChild(NUMIDCONDUCTOR);
            }
            Element CODIDCONDUCTOR2 = request.createElement("CODIDCONDUCTOR2");
            CODIDCONDUCTOR2.appendChild(request.createTextNode(entity.getCODIDCONDUCTOR2()));
            if (!entity.getCODIDCONDUCTOR2().equals("")) {
                variables.appendChild(CODIDCONDUCTOR2);
            }
            Element NUMIDCONDUCTOR2 = request.createElement("NUMIDCONDUCTOR2");
            NUMIDCONDUCTOR2.appendChild(request.createTextNode(entity.getNUMIDCONDUCTOR2()));
            if (!entity.getNUMIDCONDUCTOR2().equals("")) {
                variables.appendChild(NUMIDCONDUCTOR2);
            }
            Element VALORFLETEPACTADOVIAJE = request.createElement("VALORFLETEPACTADOVIAJE");
            VALORFLETEPACTADOVIAJE.appendChild(request.createTextNode(entity.getVALORFLETEPACTADOVIAJE()));
            if (!entity.getVALORFLETEPACTADOVIAJE().equals("")) {
                variables.appendChild(VALORFLETEPACTADOVIAJE);
            }
            //no se encuentra en el diccionario de datos
//            Element ORETENCIONFUENTEMANIFIEST = request.createElement("ORETENCIONFUENTEMANIFIEST");
//            ORETENCIONFUENTEMANIFIEST.appendChild(request.createTextNode(entity.getORETENCIONFUENTEMANIFIEST()));
//            if (!entity.getORETENCIONFUENTEMANIFIEST().equals("")) {
//                variables.appendChild(ORETENCIONFUENTEMANIFIEST);
//            }
            Element RETENCIONICAMANIFIESTOCARGA = request.createElement("RETENCIONICAMANIFIESTOCARGA");
            RETENCIONICAMANIFIESTOCARGA.appendChild(request.createTextNode(entity.getRETENCIONICAMANIFIESTOCARGA()));
            if (!entity.getRETENCIONICAMANIFIESTOCARGA().equals("")) {
                variables.appendChild(RETENCIONICAMANIFIESTOCARGA);
            }
            Element VALORANTICIPOMANIFIESTO = request.createElement("VALORANTICIPOMANIFIESTO");
            VALORANTICIPOMANIFIESTO.appendChild(request.createTextNode(entity.getVALORANTICIPOMANIFIESTO()));
            if (!entity.getVALORANTICIPOMANIFIESTO().equals("")) {
                variables.appendChild(VALORANTICIPOMANIFIESTO);
            }
            Element CODMUNICIPIOPAGOSALDO = request.createElement("CODMUNICIPIOPAGOSALDO");
            CODMUNICIPIOPAGOSALDO.appendChild(request.createTextNode(entity.getCODMUNICIPIOPAGOSALDO()));
            if (!entity.getCODMUNICIPIOPAGOSALDO().equals("")) {
                variables.appendChild(CODMUNICIPIOPAGOSALDO);
            }
            Element CODRESPONSABLEPAGOCARGUE = request.createElement("CODRESPONSABLEPAGOCARGUE");
            CODRESPONSABLEPAGOCARGUE.appendChild(request.createTextNode(entity.getCODRESPONSABLEPAGOCARGUE()));
            if (!entity.getCODRESPONSABLEPAGOCARGUE().equals("")) {
                variables.appendChild(CODRESPONSABLEPAGOCARGUE);
            }
            Element CODRESPONSABLEPAGODESCARGUE = request.createElement("CODRESPONSABLEPAGODESCARGUE");
            CODRESPONSABLEPAGODESCARGUE.appendChild(request.createTextNode(entity.getCODRESPONSABLEPAGODESCARGUE()));
            if (!entity.getCODRESPONSABLEPAGODESCARGUE().equals("")) {
                variables.appendChild(CODRESPONSABLEPAGODESCARGUE);
            }
            Element FECHAPAGOSALDOMANIFIESTO = request.createElement("FECHAPAGOSALDOMANIFIESTO");
            FECHAPAGOSALDOMANIFIESTO.appendChild(request.createTextNode(DateParsing.toXML(entity.getFECHAPAGOSALDOMANIFIESTO())));
            if (!DateParsing.toXML(entity.getFECHAPAGOSALDOMANIFIESTO()).equals("")) {
                variables.appendChild(FECHAPAGOSALDOMANIFIESTO);
            }
            Element OBSERVACIONES = request.createElement("OBSERVACIONES");
            OBSERVACIONES.appendChild(request.createTextNode(entity.getOBSERVACIONES()));
            if (!entity.getOBSERVACIONES().equals("")) {
                variables.appendChild(OBSERVACIONES);
            }
            Element SEGURIDADQR = request.createElement("SEGURIDADQR");
            SEGURIDADQR.appendChild(request.createTextNode(entity.getSEGURIDADQR()));
            if (!entity.getSEGURIDADQR().equals("")) {
                variables.appendChild(SEGURIDADQR);
            }
            
            Element REMESASMAN = request.createElement("REMESASMAN");
            REMESASMAN.setAttribute("procesoid", "43");
            for (int i = 0; i < entity.getREMESASMAN().size(); i++) {
                Element REMESA = request.createElement("REMESA");
                Element CONSECUTIVOREMESA = request.createElement("CONSECUTIVOREMESA");
                CONSECUTIVOREMESA.appendChild(request.createTextNode(entity.getREMESASMAN().get(i)));
                REMESA.appendChild(CONSECUTIVOREMESA);
                REMESASMAN.appendChild(REMESA);
            }
            variables.appendChild(REMESASMAN);
            
            DOMSource domSource = new DOMSource(request);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            //System.out.println(writer.toString());
            return writer.toString();
        } catch (ParserConfigurationException | TransformerException | DOMException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
