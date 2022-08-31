package com.enerfrisoft.remolque;

import java.io.StringWriter;
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

public class XMLRemolque {

    public static String grabar(Remolque entity) {
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
            Element NUMPLACA = request.createElement("NUMPLACA");
            NUMPLACA.appendChild(request.createTextNode(entity.getNUMPLACA()));
            if (!entity.getNUMPLACA().equals("")) {
                variables.appendChild(NUMPLACA);
            }
            Element CODCONFIGURACIONUNIDADCARGA = request.createElement("CODCONFIGURACIONUNIDADCARGA");
            CODCONFIGURACIONUNIDADCARGA.appendChild(request.createTextNode(entity.getCODCONFIGURACIONUNIDADCARGA()));
            if (!entity.getCODCONFIGURACIONUNIDADCARGA().equals("")) {
                variables.appendChild(CODCONFIGURACIONUNIDADCARGA);
            }
            Element CODMARCAVEHICULOCARGA = request.createElement("CODMARCAVEHICULOCARGA");
            CODMARCAVEHICULOCARGA.appendChild(request.createTextNode(entity.getCODMARCAVEHICULOCARGA()));
            if (!entity.getCODMARCAVEHICULOCARGA().equals("")) {
                variables.appendChild(CODMARCAVEHICULOCARGA);
            }
            Element NUMEJES = request.createElement("NUMEJES");
            NUMEJES.appendChild(request.createTextNode(entity.getNUMEJES()));
            if (!entity.getNUMEJES().equals("")) {
                variables.appendChild(NUMEJES);
            }
            Element ANOFABRICACIONVEHICULOCARGA = request.createElement("ANOFABRICACIONVEHICULOCARGA");
            ANOFABRICACIONVEHICULOCARGA.appendChild(request.createTextNode(entity.getANOFABRICACIONVEHICULOCARGA()));
            if (!entity.getANOFABRICACIONVEHICULOCARGA().equals("")) {
                variables.appendChild(ANOFABRICACIONVEHICULOCARGA);
            }
            Element PESOVEHICULOVACIO = request.createElement("PESOVEHICULOVACIO");
            PESOVEHICULOVACIO.appendChild(request.createTextNode(entity.getPESOVEHICULOVACIO()));
            if (!entity.getPESOVEHICULOVACIO().equals("")) {
                variables.appendChild(PESOVEHICULOVACIO);
            }
            Element CAPACIDADUNIDADCARGA = request.createElement("CAPACIDADUNIDADCARGA");
            CAPACIDADUNIDADCARGA.appendChild(request.createTextNode(entity.getCAPACIDADUNIDADCARGA()));
            if (!entity.getCAPACIDADUNIDADCARGA().equals("")) {
                variables.appendChild(CAPACIDADUNIDADCARGA);
            }
            Element UNIDADMEDIDACAPACIDAD = request.createElement("UNIDADMEDIDACAPACIDAD");
            UNIDADMEDIDACAPACIDAD.appendChild(request.createTextNode(entity.getUNIDADMEDIDACAPACIDAD()));
            if (!entity.getUNIDADMEDIDACAPACIDAD().equals("")) {
                variables.appendChild(UNIDADMEDIDACAPACIDAD);
            }
            Element CODTIPOCARROCERIA = request.createElement("CODTIPOCARROCERIA");
            CODTIPOCARROCERIA.appendChild(request.createTextNode(entity.getCODTIPOCARROCERIA()));
            if (!entity.getCODTIPOCARROCERIA().equals("")) {
                variables.appendChild(CODTIPOCARROCERIA);
            }
            Element NUMCHASIS = request.createElement("NUMCHASIS");
            NUMCHASIS.appendChild(request.createTextNode(entity.getNUMCHASIS()));
            if (!entity.getNUMCHASIS().equals("")) {
                variables.appendChild(NUMCHASIS);
            }
            Element NUMSEGUROSOAT = request.createElement("NUMSEGUROSOAT");
            NUMSEGUROSOAT.appendChild(request.createTextNode(entity.getNUMSEGUROSOAT()));
            if (!entity.getNUMSEGUROSOAT().equals("")) {
                variables.appendChild(NUMSEGUROSOAT);
            }
            Element CODTIPOIDPROPIETARIO = request.createElement("CODTIPOIDPROPIETARIO");
            CODTIPOIDPROPIETARIO.appendChild(request.createTextNode(entity.getCODTIPOIDPROPIETARIO()));
            if (!entity.getCODTIPOIDPROPIETARIO().equals("")) {
                variables.appendChild(CODTIPOIDPROPIETARIO);
            }
            Element NUMIDPROPIETARIO = request.createElement("NUMIDPROPIETARIO");
            NUMIDPROPIETARIO.appendChild(request.createTextNode(entity.getNUMIDPROPIETARIO()));
            if (!entity.getNUMIDPROPIETARIO().equals("")) {
                variables.appendChild(NUMIDPROPIETARIO);
            }
            Element CODTIPOIDTENEDOR = request.createElement("CODTIPOIDTENEDOR");
            CODTIPOIDTENEDOR.appendChild(request.createTextNode(entity.getCODTIPOIDTENEDOR()));
            if (!entity.getCODTIPOIDTENEDOR().equals("")) {
                variables.appendChild(CODTIPOIDTENEDOR);
            }
            Element NUMIDTENEDOR = request.createElement("NUMIDTENEDOR");
            NUMIDTENEDOR.appendChild(request.createTextNode(entity.getNUMIDTENEDOR()));
            if (!entity.getNUMIDTENEDOR().equals("")) {
                variables.appendChild(NUMIDTENEDOR);
            }
            
            DOMSource domSource = new DOMSource(request);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();

        } catch (ParserConfigurationException | TransformerException | DOMException ex) {
            System.out.println(ex.getLocalizedMessage()+":grabar");
        }

        return null;
    }
    
    public static void main(String[] args) {
        Remolque r = new Remolque();
        System.out.println(grabar(r));
    }
}
