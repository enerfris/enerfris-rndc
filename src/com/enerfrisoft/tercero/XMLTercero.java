package com.enerfrisoft.tercero;

import com.enerfrisoft.tercero.Tercero;
import com.enerfrisoft.tools.DateParsing;
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




public class XMLTercero {

    public static String grabar(Tercero entity) {
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
            procesoid.appendChild(request.createTextNode("11"));
            solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);
            
            Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
            NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(entity.getNUMNITEMPRESATRANSPORTE()));
            if (!entity.getNUMNITEMPRESATRANSPORTE().equals("")) {
                variables.appendChild(NUMNITEMPRESATRANSPORTE);
            }
            Element CODTIPOIDTERCERO = request.createElement("CODTIPOIDTERCERO");
            CODTIPOIDTERCERO.appendChild(request.createTextNode(entity.getCODTIPOIDTERCERO()));
            if (!entity.getCODTIPOIDTERCERO().equals("")) {
                variables.appendChild(CODTIPOIDTERCERO);
            }
            Element NUMIDTERCERO = request.createElement("NUMIDTERCERO");
            NUMIDTERCERO.appendChild(request.createTextNode(entity.getNUMIDTERCERO()));
            if (!entity.getNUMIDTERCERO().equals("")) {
                variables.appendChild(NUMIDTERCERO);
            }
            Element NOMIDTERCERO = request.createElement("NOMIDTERCERO");
            NOMIDTERCERO.appendChild(request.createTextNode(entity.getNOMIDTERCERO()));
            if (!entity.getNOMIDTERCERO().equals("")) {
                variables.appendChild(NOMIDTERCERO);
            }
            Element PRIMERAPELLIDOIDTERCERO = request.createElement("PRIMERAPELLIDOIDTERCERO");
            PRIMERAPELLIDOIDTERCERO.appendChild(request.createTextNode(entity.getPRIMERAPELLIDOIDTERCERO()));
            if (!entity.getPRIMERAPELLIDOIDTERCERO().equals("")) {
                variables.appendChild(PRIMERAPELLIDOIDTERCERO);
            }
            Element SEGUNDOAPELLIDOIDTERCERO = request.createElement("SEGUNDOAPELLIDOIDTERCERO");
            SEGUNDOAPELLIDOIDTERCERO.appendChild(request.createTextNode(entity.getSEGUNDOAPELLIDOIDTERCERO()));
            if (!entity.getSEGUNDOAPELLIDOIDTERCERO().equals("")) {
                variables.appendChild(SEGUNDOAPELLIDOIDTERCERO);
            }
            Element NUMTELEFONOCONTACTO = request.createElement("NUMTELEFONOCONTACTO");
            NUMTELEFONOCONTACTO.appendChild(request.createTextNode(entity.getNUMTELEFONOCONTACTO()));
            if (!entity.getNUMTELEFONOCONTACTO().equals("")) {
                variables.appendChild(NUMTELEFONOCONTACTO);
            }
            Element NUMCELULARPERSONA = request.createElement("NUMCELULARPERSONA");
            NUMCELULARPERSONA.appendChild(request.createTextNode(entity.getNUMCELULARPERSONA()));
            if (!entity.getNUMCELULARPERSONA().equals("")) {
                variables.appendChild(NUMCELULARPERSONA);
            }
            Element NOMENCLATURADIRECCION = request.createElement("NOMENCLATURADIRECCION");
            NOMENCLATURADIRECCION.appendChild(request.createTextNode(entity.getNOMENCLATURADIRECCION()));
            if (!entity.getNOMENCLATURADIRECCION().equals("")) {
                variables.appendChild(NOMENCLATURADIRECCION);
            }
            Element CODMUNICIPIORNDC = request.createElement("CODMUNICIPIORNDC");
            CODMUNICIPIORNDC.appendChild(request.createTextNode(entity.getCODMUNICIPIORNDC()));
            if (!entity.getCODMUNICIPIORNDC().equals("")) {
                variables.appendChild(CODMUNICIPIORNDC);
            }
            Element CODSEDETERCERO = request.createElement("CODSEDETERCERO");
            CODSEDETERCERO.appendChild(request.createTextNode(entity.getCODSEDETERCERO()));
            if (!entity.getCODSEDETERCERO().equals("")) {
                variables.appendChild(CODSEDETERCERO);
            }
            Element NOMSEDETERCERO = request.createElement("NOMSEDETERCERO");
            NOMSEDETERCERO.appendChild(request.createTextNode(entity.getNOMSEDETERCERO()));
            if (!entity.getNOMSEDETERCERO().equals("")) {
                variables.appendChild(NOMSEDETERCERO);
            }
            Element NUMLICENCIACONDUCCION = request.createElement("NUMLICENCIACONDUCCION");
            NUMLICENCIACONDUCCION.appendChild(request.createTextNode(entity.getNUMLICENCIACONDUCCION()));
            if (!entity.getNUMLICENCIACONDUCCION().equals("")) {
                variables.appendChild(NUMLICENCIACONDUCCION);
            }
            Element CODCATEGORIALICENCIACONDUCCION = request.createElement("CODCATEGORIALICENCIACONDUCCION");
            CODCATEGORIALICENCIACONDUCCION.appendChild(request.createTextNode(entity.getCODCATEGORIALICENCIACONDUCCION()));
            if (!entity.getCODCATEGORIALICENCIACONDUCCION().equals("")) {
                variables.appendChild(CODCATEGORIALICENCIACONDUCCION);
            }
            Element FECHAVENCIMIENTOLICENCIA = request.createElement("FECHAVENCIMIENTOLICENCIA");
            FECHAVENCIMIENTOLICENCIA.appendChild(request.createTextNode(DateParsing.toXML(entity.getFECHAVENCIMIENTOLICENCIA())));
            if (!DateParsing.toXML(entity.getFECHAVENCIMIENTOLICENCIA()).equals("")) {
                variables.appendChild(FECHAVENCIMIENTOLICENCIA);
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
