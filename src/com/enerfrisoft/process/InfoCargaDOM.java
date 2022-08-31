/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.process;

import com.enerfrisoft.remesa.RemesaDAO;
import org.w3c.dom.Document;
import com.enerfrisoft.entity.InfoCarga;
import com.enerfrisoft.remesa.Remesa;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

/**
 *
 * @author Sebastianaf
 */
public class InfoCargaDOM{
    private Document request;
    private InfoCarga infoCarga;
    
    
    public InfoCargaDOM(InfoCarga infoCarga) throws ParserConfigurationException{
        this.infoCarga = infoCarga;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        request = builder.newDocument();
                
    }
    
    public String grabar(){
        try{
           DOMSource domSource = new DOMSource(request);
           StringWriter writer = new StringWriter();
           StreamResult result = new StreamResult(writer);
           TransformerFactory tf = TransformerFactory.newInstance();
           Transformer transformer = tf.newTransformer();
           transformer.transform(domSource, result);
           return writer.toString();
        }catch(TransformerException ex){
           ex.printStackTrace();
           return null;
        }
    }
    
    private static void generate(InfoCarga infoCarga) throws ParserConfigurationException{
        Document request;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        request = builder.newDocument();
        Element root = request.createElement("root");
        request.appendChild(root);
            Element acceso = request.createElement("acceso");
            root.appendChild(acceso);
                Element username = request.createElement("username");
                username.appendChild(request.createTextNode(infoCarga.getUsuario()));
                acceso.appendChild(username);
                Element password = request.createElement("password");
                password.appendChild(request.createTextNode(infoCarga.getPassword()));
                acceso.appendChild(password);
            Element solicitud = request.createElement("solicitud");
            root.appendChild(solicitud);
                Element tipo = request.createElement("tipo");
                tipo.appendChild(request.createTextNode("1"));
                solicitud.appendChild(tipo);
                Element procesoid = request.createElement("procesoid");
                procesoid.appendChild(request.createTextNode("1"));
                solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);
            
                Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
                NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(infoCarga.getNUMNITEMPRESATRANSPORTE()));
                variables.appendChild(NUMNITEMPRESATRANSPORTE);

                Element CONSECUTIVOINFORMACIONCARGA = request.createElement("CONSECUTIVOINFORMACIONCARGA");
                CONSECUTIVOINFORMACIONCARGA.appendChild(request.createTextNode(infoCarga.getCONSECUTIVOINFORMACIONCARGA()));
                variables.appendChild(CONSECUTIVOINFORMACIONCARGA);

                Element CODOPERACIONTRANSPORTE = request.createElement("CODOPERACIONTRANSPORTE");
                CODOPERACIONTRANSPORTE.appendChild(request.createTextNode(infoCarga.getCODOPERACIONTRANSPORTE()));
                variables.appendChild(CODOPERACIONTRANSPORTE);

                Element CODNATURALEZACARGA = request.createElement("CODNATURALEZACARGA");
                CODNATURALEZACARGA.appendChild(request.createTextNode(infoCarga.getCODNATURALEZACARGA()));
                variables.appendChild(CODNATURALEZACARGA);

                Element CANTIDADINFORMACIONCARGA = request.createElement("CANTIDADINFORMACIONCARGA");
                CANTIDADINFORMACIONCARGA.appendChild(request.createTextNode(infoCarga.getCANTIDADINFORMACIONCARGA()));
                variables.appendChild(CANTIDADINFORMACIONCARGA);

                Element UNIDADMEDIDACAPACIDAD = request.createElement("UNIDADMEDIDACAPACIDAD");
                UNIDADMEDIDACAPACIDAD.appendChild(request.createTextNode(infoCarga.getUNIDADMEDIDACAPACIDAD()));
                variables.appendChild(UNIDADMEDIDACAPACIDAD);

                Element CODTIPOEMPAQUE = request.createElement("CODTIPOEMPAQUE");
                CODTIPOEMPAQUE.appendChild(request.createTextNode(infoCarga.getCODTIPOEMPAQUE()));
                variables.appendChild(CODTIPOEMPAQUE);

                Element MERCANCIAINFORMACIONCARGA = request.createElement("MERCANCIAINFORMACIONCARGA");
                MERCANCIAINFORMACIONCARGA.appendChild(request.createTextNode(infoCarga.getMERCANCIAINFORMACIONCARGA()));
                variables.appendChild(MERCANCIAINFORMACIONCARGA);

                Element DESCRIPCIONCORTAPRODUCTO = request.createElement("DESCRIPCIONCORTAPRODUCTO");
                DESCRIPCIONCORTAPRODUCTO.appendChild(request.createTextNode(infoCarga.getDESCRIPCIONCORTAPRODUCTO()));
                variables.appendChild(DESCRIPCIONCORTAPRODUCTO);

                Element CODTIPOIDREMITENTE = request.createElement("CODTIPOIDREMITENTE");
                CODTIPOIDREMITENTE.appendChild(request.createTextNode(infoCarga.getCODTIPOIDREMITENTE()));
                variables.appendChild(CODTIPOIDREMITENTE);

                Element NUMIDREMITENTE = request.createElement("NUMIDREMITENTE");
                NUMIDREMITENTE.appendChild(request.createTextNode(infoCarga.getNUMIDREMITENTE()));
                variables.appendChild(NUMIDREMITENTE);

                Element CODSEDEREMITENTE = request.createElement("CODSEDEREMITENTE");
                CODSEDEREMITENTE.appendChild(request.createTextNode(infoCarga.getCODSEDEREMITENTE()));
                variables.appendChild(CODSEDEREMITENTE);

                Element PESOCONTENEDORVACIO = request.createElement("PESOCONTENEDORVACIO");
                PESOCONTENEDORVACIO.appendChild(request.createTextNode(infoCarga.getPESOCONTENEDORVACIO()));
                variables.appendChild(PESOCONTENEDORVACIO);

                Element CODTIPOIDDESTINATARIO = request.createElement("CODTIPOIDDESTINATARIO");
                CODTIPOIDDESTINATARIO.appendChild(request.createTextNode(infoCarga.getCODTIPOIDDESTINATARIO()));
                variables.appendChild(CODTIPOIDDESTINATARIO);

                Element NUMIDDESTINATARIO = request.createElement("NUMIDDESTINATARIO");
                NUMIDDESTINATARIO.appendChild(request.createTextNode(infoCarga.getNUMIDDESTINATARIO()));
                variables.appendChild(NUMIDDESTINATARIO);

                Element CODSEDEDESTINATARIO = request.createElement("CODSEDEDESTINATARIO");
                CODSEDEDESTINATARIO.appendChild(request.createTextNode(infoCarga.getCODSEDEDESTINATARIO()));
                variables.appendChild(CODSEDEDESTINATARIO);

                Element PACTOTIEMPOCARGUE = request.createElement("PACTOTIEMPOCARGUE");
                PACTOTIEMPOCARGUE.appendChild(request.createTextNode(infoCarga.getPACTOTIEMPOCARGUE()));
                variables.appendChild(PACTOTIEMPOCARGUE);

                Element HORASPACTOCARGA = request.createElement("HORASPACTOCARGA");
                HORASPACTOCARGA.appendChild(request.createTextNode(infoCarga.getHORASPACTOCARGA()));
                variables.appendChild(HORASPACTOCARGA);

                Element MINUTOSPACTOCARGA = request.createElement("MINUTOSPACTOCARGA");
                MINUTOSPACTOCARGA.appendChild(request.createTextNode(infoCarga.getMINUTOSPACTOCARGA()));
                variables.appendChild(MINUTOSPACTOCARGA);

                Element PACTOTIEMPODESCARGUE = request.createElement("PACTOTIEMPODESCARGUE");
                PACTOTIEMPODESCARGUE.appendChild(request.createTextNode(infoCarga.getPACTOTIEMPODESCARGUE()));
                variables.appendChild(PACTOTIEMPODESCARGUE);

                Element HORASPACTODESCARGUE = request.createElement("HORASPACTODESCARGUE");
                HORASPACTODESCARGUE.appendChild(request.createTextNode(infoCarga.getHORASPACTODESCARGUE()));
                variables.appendChild(HORASPACTODESCARGUE);

                Element MINUTOSPACTODESCARGUE = request.createElement("MINUTOSPACTODESCARGUE");
                MINUTOSPACTODESCARGUE.appendChild(request.createTextNode(infoCarga.getMINUTOSPACTODESCARGUE()));
                variables.appendChild(MINUTOSPACTODESCARGUE);

                Element OBSERVACIONES = request.createElement("OBSERVACIONES");
                OBSERVACIONES.appendChild(request.createTextNode(infoCarga.getOBSERVACIONES()));
                variables.appendChild(OBSERVACIONES);

                Element FECHACITAPACTADACARGUE = request.createElement("FECHACITAPACTADACARGUE");
                FECHACITAPACTADACARGUE.appendChild(request.createTextNode(infoCarga.getFECHACITAPACTADACARGUE()));
                variables.appendChild(FECHACITAPACTADACARGUE);

                Element HORACITAPACTADACARGUE = request.createElement("HORACITAPACTADACARGUE");
                HORACITAPACTADACARGUE.appendChild(request.createTextNode(infoCarga.getHORACITAPACTADACARGUE()));
                variables.appendChild(HORACITAPACTADACARGUE);

                Element FECHACITAPACTADADESCARGUE = request.createElement("FECHACITAPACTADADESCARGUE");
                FECHACITAPACTADADESCARGUE.appendChild(request.createTextNode(infoCarga.getFECHACITAPACTADADESCARGUE()));
                variables.appendChild(FECHACITAPACTADADESCARGUE);

                Element HORACITAPACTADADESCARGUEREMESA = request.createElement("HORACITAPACTADADESCARGUEREMESA");
                HORACITAPACTADADESCARGUEREMESA.appendChild(request.createTextNode(infoCarga.getHORACITAPACTADADESCARGUEREMESA()));
                variables.appendChild(HORACITAPACTADADESCARGUEREMESA);
    }
    
    
    public static void main(String[] args) throws ParserConfigurationException {
        //Testing
        InfoCargaDOM.generate(new InfoCarga());
        
    }
}
