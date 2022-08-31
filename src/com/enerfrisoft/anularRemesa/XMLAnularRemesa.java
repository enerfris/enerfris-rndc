package com.enerfrisoft.anularRemesa;

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

public class XMLAnularRemesa {

    public static String grabar(AnularRemesa entity) {
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
            procesoid.appendChild(request.createTextNode("9"));
            solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);

            Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
            NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(entity.getNUMNITEMPRESATRANSPORTE()));
            if (!entity.getNUMNITEMPRESATRANSPORTE().equals("")) {
                variables.appendChild(NUMNITEMPRESATRANSPORTE);
            }
            Element CONSECUTIVOREMESA = request.createElement("CONSECUTIVOREMESA");
            CONSECUTIVOREMESA.appendChild(request.createTextNode(entity.getCONSECUTIVOREMESA()));
            if (!entity.getCONSECUTIVOREMESA().equals("")) {
                variables.appendChild(CONSECUTIVOREMESA);
            }
            Element MOTIVOANULACIONREMESA = request.createElement("MOTIVOANULACIONREMESA");
            MOTIVOANULACIONREMESA.appendChild(request.createTextNode(entity.getMOTIVOANULACIONREMESA()));
            if (!entity.getMOTIVOANULACIONREMESA().equals("")) {
                variables.appendChild(MOTIVOANULACIONREMESA);
                }
            Element MOTIVOREVERSAREMESA = request.createElement("MOTIVOREVERSAREMESA");
            MOTIVOREVERSAREMESA.appendChild(request.createTextNode(entity.getMOTIVOREVERSAREMESA()));
            if (!entity.getMOTIVOREVERSAREMESA().equals("")) {
                variables.appendChild(MOTIVOREVERSAREMESA);
            }
            Element CODMUNICIPIOTRANSBORDO = request.createElement("CODMUNICIPIOTRANSBORDO");
            CODMUNICIPIOTRANSBORDO.appendChild(request.createTextNode(entity.getCODMUNICIPIOTRANSBORDO()));
            if (!entity.getCODMUNICIPIOTRANSBORDO().equals("")) {
                variables.appendChild(CODMUNICIPIOTRANSBORDO);
            }
            Element MOTIVOTRANSBORDOREMESA = request.createElement("MOTIVOTRANSBORDOREMESA");
            MOTIVOTRANSBORDOREMESA.appendChild(request.createTextNode(entity.getMOTIVOTRANSBORDOREMESA()));
            if (!entity.getMOTIVOTRANSBORDOREMESA().equals("")) {
                variables.appendChild(MOTIVOTRANSBORDOREMESA);
            }
            Element OBSERVACIONES = request.createElement("OBSERVACIONES");
            OBSERVACIONES.appendChild(request.createTextNode(entity.getOBSERVACIONES()));
            if (!entity.getOBSERVACIONES().equals("")) {
                variables.appendChild(OBSERVACIONES);
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
