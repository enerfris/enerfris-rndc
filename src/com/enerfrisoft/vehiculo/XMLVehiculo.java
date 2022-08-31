package com.enerfrisoft.vehiculo;

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

public class XMLVehiculo {

    public static String grabar(Vehiculo entity) {
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
            procesoid.appendChild(request.createTextNode("12"));
            solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);
            Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
            NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(entity.getNUMNITEMPRESATRANSPORTE()));
            if (!entity.getNUMNITEMPRESATRANSPORTE().equals("")) {
                variables.appendChild(NUMNITEMPRESATRANSPORTE);
            }
            Element CONFIGURACIONUNIDADCARGA = request.createElement("CONFIGURACIONUNIDADCARGA");
            CONFIGURACIONUNIDADCARGA.appendChild(request.createTextNode(entity.getCODCONFIGURACIONUNIDADCARGA()));
            if (!entity.getCODCONFIGURACIONUNIDADCARGA().equals("")) {
                variables.appendChild(CONFIGURACIONUNIDADCARGA);
            }
            Element CODCOLORVEHICULOCARGA = request.createElement("CODCOLORVEHICULOCARGA");
            CODCOLORVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODCOLORVEHICULOCARGA()));
            if (!entity.getCODCOLORVEHICULOCARGA().equals("")) {
                variables.appendChild(CODCOLORVEHICULOCARGA);
            }
            Element ANOFABRICACIONVEHICULOCARGA = request.createElement("ANOFABRICACIONVEHICULOCARGA");
            ANOFABRICACIONVEHICULOCARGA.appendChild(request.createTextNode(entity.getANOFABRICACIONVEHICULOCARGA()));
            if (!entity.getANOFABRICACIONVEHICULOCARGA().equals("")) {
                variables.appendChild(ANOFABRICACIONVEHICULOCARGA);
            }
            Element CAPACIDADUNIDADCARGA = request.createElement("CAPACIDADUNIDADCARGA");
            CAPACIDADUNIDADCARGA.appendChild(request.createTextNode(entity.getCAPACIDADUNIDADCARGA()));
            if (!entity.getCAPACIDADUNIDADCARGA().equals("")) {
                variables.appendChild(CAPACIDADUNIDADCARGA);
            }
            Element COLORVEHICULOCARGA = request.createElement("COLORVEHICULOCARGA");
            COLORVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODCOLORVEHICULOCARGA()));
            if (!entity.getCODCOLORVEHICULOCARGA().equals("")) {
                variables.appendChild(COLORVEHICULOCARGA);
            }
            Element CODCONFIGURACIONUNIDADCARGA = request.createElement("CODCONFIGURACIONUNIDADCARGA");
            CODCONFIGURACIONUNIDADCARGA.appendChild(request.createTextNode(entity.getCODCONFIGURACIONUNIDADCARGA()));
            if (!entity.getCODCONFIGURACIONUNIDADCARGA().equals("")) {
                variables.appendChild(CODCONFIGURACIONUNIDADCARGA);
            }
            Element CODLINEAVEHICULOCARGA = request.createElement("CODLINEAVEHICULOCARGA");
            CODLINEAVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODLINEAVEHICULOCARGA()));
            if (!entity.getCODLINEAVEHICULOCARGA().equals("")) {
                variables.appendChild(CODLINEAVEHICULOCARGA);
            }
            Element CODMARCAVEHICULOCARGA = request.createElement("CODMARCAVEHICULOCARGA");
            CODMARCAVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODMARCAVEHICULOCARGA()));
            if (!entity.getCODMARCAVEHICULOCARGA().equals("")) {
                variables.appendChild(CODMARCAVEHICULOCARGA);
            }
            Element TIPOCARROCERIA = request.createElement("TIPOCARROCERIA");
            TIPOCARROCERIA.appendChild(request.createTextNode(entity.getCODTIPOCARROCERIA()));
            if (!entity.getCODTIPOCARROCERIA().equals("")) {
                variables.appendChild(TIPOCARROCERIA);
            }
            Element CODTIPOCARROCERIA = request.createElement("CODTIPOCARROCERIA");
            CODTIPOCARROCERIA.appendChild(request.createTextNode(entity.getCODTIPOCARROCERIA()));
            if (!entity.getCODTIPOCARROCERIA().equals("")) {
                variables.appendChild(CODTIPOCARROCERIA);
            }
            Element CODTIPOCOMBUSTIBLE = request.createElement("CODTIPOCOMBUSTIBLE");
            CODTIPOCOMBUSTIBLE.appendChild(request.createTextNode(entity.getCODTIPOCOMBUSTIBLE()));
            if (!entity.getCODTIPOCOMBUSTIBLE().equals("")) {
                variables.appendChild(CODTIPOCOMBUSTIBLE);
            }
            Element CODTIPOIDPROPIETARIO = request.createElement("CODTIPOIDPROPIETARIO");
            CODTIPOIDPROPIETARIO.appendChild(request.createTextNode(entity.getCODTIPOIDPROPIETARIO()));
            if (!entity.getCODTIPOIDPROPIETARIO().equals("")) {
                variables.appendChild(CODTIPOIDPROPIETARIO);
            }
            Element CODTIPOIDTENEDOR = request.createElement("CODTIPOIDTENEDOR");
            CODTIPOIDTENEDOR.appendChild(request.createTextNode(entity.getCODTIPOIDTENEDOR()));
            if (!entity.getCODTIPOIDTENEDOR().equals("")) {
                variables.appendChild(CODTIPOIDTENEDOR);
            }
            
            Element FECHAVENCIMIENTOSOAT = request.createElement("FECHAVENCIMIENTOSOAT");
            FECHAVENCIMIENTOSOAT.appendChild(request.createTextNode(DateParsing.toXML(entity.getFECHAVENCIMIENTOSOAT())));
            if (!entity.getFECHAVENCIMIENTOSOAT().equals("")) {
                variables.appendChild(FECHAVENCIMIENTOSOAT);
            }
            
            Element LINEAVEHICULOCARGA = request.createElement("LINEAVEHICULOCARGA");
            LINEAVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODLINEAVEHICULOCARGA()));
            if (!entity.getCODLINEAVEHICULOCARGA().equals("")) {
                variables.appendChild(LINEAVEHICULOCARGA);
            }
            Element MARCAVEHICULOCARGA = request.createElement("MARCAVEHICULOCARGA");
            MARCAVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODMARCAVEHICULOCARGA()));
            if (!entity.getCODMARCAVEHICULOCARGA().equals("")) {
                variables.appendChild(MARCAVEHICULOCARGA);
            }
            Element NUMCHASIS = request.createElement("NUMCHASIS");
            NUMCHASIS.appendChild(request.createTextNode(entity.getNUMCHASIS()));
            if (!entity.getNUMCHASIS().equals("")) {
                variables.appendChild(NUMCHASIS);
            }
            Element NUMIDPROPIETARIO = request.createElement("NUMIDPROPIETARIO");
            NUMIDPROPIETARIO.appendChild(request.createTextNode(entity.getNUMIDPROPIETARIO()));
            if (!entity.getNUMIDPROPIETARIO().equals("")) {
                variables.appendChild(NUMIDPROPIETARIO);
            }
            Element NUMIDTENEDOR = request.createElement("NUMIDTENEDOR");
            NUMIDTENEDOR.appendChild(request.createTextNode(entity.getNUMIDTENEDOR()));
            if (!entity.getNUMIDTENEDOR().equals("")) {
                variables.appendChild(NUMIDTENEDOR);
            }
            Element NUMNITASEGURADORASOAT = request.createElement("NUMNITASEGURADORASOAT");
            NUMNITASEGURADORASOAT.appendChild(request.createTextNode(entity.getNUMNITASEGURADORASOAT()));
            if (!entity.getNUMNITASEGURADORASOAT().equals("")) {
                variables.appendChild(NUMNITASEGURADORASOAT);
            }
            Element NUMPLACA = request.createElement("NUMPLACA");
            NUMPLACA.appendChild(request.createTextNode(entity.getNUMPLACA()));
            if (!entity.getNUMPLACA().equals("")) {
                variables.appendChild(NUMPLACA);
            }
            Element NUMSEGUROSOAT = request.createElement("NUMSEGUROSOAT");
            NUMSEGUROSOAT.appendChild(request.createTextNode(entity.getNUMSEGUROSOAT()));
            if (!entity.getNUMSEGUROSOAT().equals("")) {
                variables.appendChild(NUMSEGUROSOAT);
            }
            Element PESOVEHICULOVACIO = request.createElement("PESOVEHICULOVACIO");
            PESOVEHICULOVACIO.appendChild(request.createTextNode(entity.getPESOVEHICULOVACIO()));
            if (!entity.getPESOVEHICULOVACIO().equals("")) {
                variables.appendChild(PESOVEHICULOVACIO);
            }
            Element NUMEJES = request.createElement("NUMEJES");
            NUMEJES.appendChild(request.createTextNode(entity.getNUMEJES()));
            if (!entity.getNUMEJES().equals("")) {
                variables.appendChild(NUMEJES);
            }
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
