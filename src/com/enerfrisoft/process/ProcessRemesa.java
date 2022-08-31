/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.process;

import com.enerfrisoft.remesa.Remesa;
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


/**
 *
 * @author Sebastianaf
 */
public class ProcessRemesa {
    
    public static String grabar(Remesa remesa){
        Document request;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            request = builder.newDocument();
            
            Element root = request.createElement("root");
        request.appendChild(root);
            Element acceso = request.createElement("acceso");
            root.appendChild(acceso);
                Element username = request.createElement("username");
                username.appendChild(request.createTextNode(remesa.getUsuario()));
                acceso.appendChild(username);
                Element password = request.createElement("password");
                password.appendChild(request.createTextNode(remesa.getPassword()));
                acceso.appendChild(password);
            Element solicitud = request.createElement("solicitud");
            root.appendChild(solicitud);
                Element tipo = request.createElement("tipo");
                tipo.appendChild(request.createTextNode("3"));
                solicitud.appendChild(tipo);
                Element procesoid = request.createElement("procesoid");
                procesoid.appendChild(request.createTextNode("1"));
                solicitud.appendChild(procesoid);
            Element variables = request.createElement("variables");
            root.appendChild(variables);
            
                Element NUMNITEMPRESATRANSPORTE = request.createElement("NUMNITEMPRESATRANSPORTE");
                NUMNITEMPRESATRANSPORTE.appendChild(request.createTextNode(remesa.getNUMNITEMPRESATRANSPORTE()));
                variables.appendChild(NUMNITEMPRESATRANSPORTE);
                
                Element CONSECUTIVOREMESA = request.createElement("CONSECUTIVOREMESA");
                CONSECUTIVOREMESA.appendChild(request.createTextNode(remesa.getCONSECUTIVOREMESA()));
                variables.appendChild(CONSECUTIVOREMESA);
                
                Element CONSECUTIVOINFORMACIONCARGA = request.createElement("CONSECUTIVOINFORMACIONCARGA");
                CONSECUTIVOINFORMACIONCARGA.appendChild(request.createTextNode(remesa.getCONSECUTIVOINFORMACIONCARGA()));
                variables.appendChild(CONSECUTIVOINFORMACIONCARGA);
                
                Element CONSECUTIVOCARGADIVIDIDA = request.createElement("CONSECUTIVOCARGADIVIDIDA");
                CONSECUTIVOCARGADIVIDIDA.appendChild(request.createTextNode(remesa.getCONSECUTIVOCARGADIVIDIDA()));
                variables.appendChild(CONSECUTIVOCARGADIVIDIDA);
    
                Element CODOPERACIONTRANSPORTE = request.createElement("CODOPERACIONTRANSPORTE");
                CODOPERACIONTRANSPORTE.appendChild(request.createTextNode(remesa.getCODOPERACIONTRANSPORTE()));
                variables.appendChild(CODOPERACIONTRANSPORTE);

                Element CODNATURALEZACARGA = request.createElement("CODNATURALEZACARGA");
                CODNATURALEZACARGA.appendChild(request.createTextNode(remesa.getCODNATURALEZACARGA()));
                variables.appendChild(CODNATURALEZACARGA);
    
                Element CANTIDADCARGADA = request.createElement("CANTIDADCARGADA");
                CANTIDADCARGADA.appendChild(request.createTextNode(remesa.getCANTIDADCARGADA()));
                variables.appendChild(CANTIDADCARGADA);
    
                Element UNIDADMEDIDACAPACIDAD = request.createElement("UNIDADMEDIDACAPACIDAD");
                UNIDADMEDIDACAPACIDAD.appendChild(request.createTextNode(remesa.getUNIDADMEDIDACAPACIDAD()));
                variables.appendChild(UNIDADMEDIDACAPACIDAD);
    
                Element CODTIPOEMPAQUE = request.createElement("CODTIPOEMPAQUE");
                CODTIPOEMPAQUE.appendChild(request.createTextNode(remesa.getCODTIPOEMPAQUE()));
                variables.appendChild(CODTIPOEMPAQUE);
                
                Element PESOCONTENEDORVACIO = request.createElement("PESOCONTENEDORVACIO");
                PESOCONTENEDORVACIO.appendChild(request.createTextNode(remesa.getPESOCONTENEDORVACIO()));
                variables.appendChild(PESOCONTENEDORVACIO);
    
                Element MERCANCIAREMESA = request.createElement("MERCANCIAREMESA");
                MERCANCIAREMESA.appendChild(request.createTextNode(remesa.getMERCANCIAREMESA()));
                variables.appendChild(MERCANCIAREMESA);
    
                Element DESCRIPCIONCORTAPRODUCTO = request.createElement("DESCRIPCIONCORTAPRODUCTO");
                DESCRIPCIONCORTAPRODUCTO.appendChild(request.createTextNode(remesa.getDESCRIPCIONCORTAPRODUCTO()));
                variables.appendChild(DESCRIPCIONCORTAPRODUCTO);
    
                Element CODTIPOIDREMITENTE = request.createElement("CODTIPOIDREMITENTE");
                CODTIPOIDREMITENTE.appendChild(request.createTextNode(remesa.getCODTIPOIDREMITENTE()));
                variables.appendChild(CODTIPOIDREMITENTE);
    
                Element NUMIDREMITENTE = request.createElement("NUMIDREMITENTE");
                NUMIDREMITENTE.appendChild(request.createTextNode(remesa.getNUMIDREMITENTE()));
                variables.appendChild(NUMIDREMITENTE);
    
                Element CODSEDEREMITENTE = request.createElement("CODSEDEREMITENTE");
                CODSEDEREMITENTE.appendChild(request.createTextNode(remesa.getCODSEDEREMITENTE()));
                variables.appendChild(CODSEDEREMITENTE);
                
                Element CODTIPOIDDESTINATARIO = request.createElement("CODTIPOIDDESTINATARIO");
                CODTIPOIDDESTINATARIO.appendChild(request.createTextNode(remesa.getCODTIPOIDDESTINATARIO()));
                variables.appendChild(CODTIPOIDDESTINATARIO);
                
                Element NUMIDDESTINATARIO = request.createElement("NUMIDDESTINATARIO");
                NUMIDDESTINATARIO.appendChild(request.createTextNode(remesa.getNUMIDDESTINATARIO()));
                variables.appendChild(NUMIDDESTINATARIO);
                
                Element CODSEDEDESTINATARIO = request.createElement("CODSEDEDESTINATARIO");
                CODSEDEDESTINATARIO.appendChild(request.createTextNode(remesa.getCODSEDEDESTINATARIO()));
                variables.appendChild(CODSEDEDESTINATARIO);
                
                Element DUENOPOLIZA = request.createElement("DUENOPOLIZA");
                DUENOPOLIZA.appendChild(request.createTextNode(remesa.getDUENOPOLIZA()));
                variables.appendChild(DUENOPOLIZA);
                
                Element NUMPOLIZATRANSPORTE = request.createElement("NUMPOLIZATRANSPORTE");
                NUMPOLIZATRANSPORTE.appendChild(request.createTextNode(remesa.getNUMPOLIZATRANSPORTE()));
                variables.appendChild(NUMPOLIZATRANSPORTE);
                
                Element COMPANIASEGURO = request.createElement("COMPANIASEGURO");
                COMPANIASEGURO.appendChild(request.createTextNode(remesa.getCOMPANIASEGURO()));
                variables.appendChild(COMPANIASEGURO);
                
                Element FECHAVENCIMIENTOPOLIZACARGA = request.createElement("FECHAVENCIMIENTOPOLIZACARGA");
                FECHAVENCIMIENTOPOLIZACARGA.appendChild(request.createTextNode(remesa.getFECHAVENCIMIENTOPOLIZACARGA()));
                variables.appendChild(FECHAVENCIMIENTOPOLIZACARGA);
                
                Element HORASPACTOCARGA = request.createElement("HORASPACTOCARGA");
                HORASPACTOCARGA.appendChild(request.createTextNode(remesa.getHORASPACTOCARGA()));
                variables.appendChild(HORASPACTOCARGA);
                
                Element MINUTOSPACTOCARGA = request.createElement("MINUTOSPACTOCARGA");
                MINUTOSPACTOCARGA.appendChild(request.createTextNode(remesa.getMINUTOSPACTOCARGA()));
                variables.appendChild(MINUTOSPACTOCARGA);
                
                Element HORASPACTODESCARGUE = request.createElement("HORASPACTODESCARGUE");
                HORASPACTODESCARGUE.appendChild(request.createTextNode(remesa.getHORASPACTODESCARGUE()));
                variables.appendChild(HORASPACTODESCARGUE);
                
                Element MINUTOSPACTODESCARGUE = request.createElement("MINUTOSPACTODESCARGUE");
                MINUTOSPACTODESCARGUE.appendChild(request.createTextNode(remesa.getMINUTOSPACTODESCARGUE()));
                variables.appendChild(MINUTOSPACTODESCARGUE);
                
                Element FECHALLEGADACARGUE = request.createElement("FECHALLEGADACARGUE");
                FECHALLEGADACARGUE.appendChild(request.createTextNode(remesa.getFECHALLEGADACARGUE()));
                variables.appendChild(FECHALLEGADACARGUE);
                
                Element HORALLEGADACARGUEREMESA = request.createElement("HORALLEGADACARGUEREMESA");
                HORALLEGADACARGUEREMESA.appendChild(request.createTextNode(remesa.getHORALLEGADACARGUEREMESA()));
                variables.appendChild(HORALLEGADACARGUEREMESA);
                
                Element FECHAENTRADACARGUE = request.createElement("FECHAENTRADACARGUE");
                FECHAENTRADACARGUE.appendChild(request.createTextNode(remesa.getFECHAENTRADACARGUE()));
                variables.appendChild(FECHAENTRADACARGUE);
               
                Element HORAENTRADACARGUEREMESA = request.createElement("HORAENTRADACARGUEREMESA");
                HORAENTRADACARGUEREMESA.appendChild(request.createTextNode(remesa.getHORAENTRADACARGUEREMESA()));
                variables.appendChild(HORAENTRADACARGUEREMESA);
                
                Element FECHASALIDACARGUE = request.createElement("FECHASALIDACARGUE");
                FECHASALIDACARGUE.appendChild(request.createTextNode(remesa.getFECHASALIDACARGUE()));
                variables.appendChild(FECHASALIDACARGUE);
                
                Element HORASALIDACARGUEREMESA = request.createElement("HORASALIDACARGUEREMESA");
                HORASALIDACARGUEREMESA.appendChild(request.createTextNode(remesa.getHORASALIDACARGUEREMESA()));
                variables.appendChild(HORASALIDACARGUEREMESA);
                
                Element CODTIPOIDPROPIETARIO = request.createElement("CODTIPOIDPROPIETARIO");
                CODTIPOIDPROPIETARIO.appendChild(request.createTextNode(remesa.getCODTIPOIDPROPIETARIO()));
                variables.appendChild(CODTIPOIDPROPIETARIO);
                
                Element NUMIDPROPIETARIO = request.createElement("NUMIDPROPIETARIO");
                NUMIDPROPIETARIO.appendChild(request.createTextNode(remesa.getNUMIDPROPIETARIO()));
                variables.appendChild(NUMIDPROPIETARIO);
                
                Element CODSEDEPROPIETARIO = request.createElement("CODSEDEPROPIETARIO");
                CODSEDEPROPIETARIO.appendChild(request.createTextNode(remesa.getCODSEDEPROPIETARIO()));
                variables.appendChild(CODSEDEPROPIETARIO);
                
                Element FECHACITAPACTADACARGUE = request.createElement("FECHACITAPACTADACARGUE");
                FECHACITAPACTADACARGUE.appendChild(request.createTextNode(remesa.getFECHACITAPACTADACARGUE()));
                variables.appendChild(FECHACITAPACTADACARGUE);
                
                Element HORACITAPACTADACARGUE = request.createElement("HORACITAPACTADACARGUE");
                HORACITAPACTADACARGUE.appendChild(request.createTextNode(remesa.getHORACITAPACTADACARGUE()));
                variables.appendChild(HORACITAPACTADACARGUE);
                
                Element FECHACITAPACTADADESCARGUE = request.createElement("FECHACITAPACTADADESCARGUE");
                FECHACITAPACTADADESCARGUE.appendChild(request.createTextNode(remesa.getFECHACITAPACTADADESCARGUE()));
                variables.appendChild(FECHACITAPACTADADESCARGUE);
                
                Element HORACITAPACTADADESCARGUEREMESA = request.createElement("HORACITAPACTADADESCARGUEREMESA");
                HORACITAPACTADADESCARGUEREMESA.appendChild(request.createTextNode(remesa.getHORACITAPACTADADESCARGUEREMESA()));
                variables.appendChild(HORACITAPACTADADESCARGUEREMESA);
                
                Element PERMISOCARGAEXTRA = request.createElement("PERMISOCARGAEXTRA");
                PERMISOCARGAEXTRA.appendChild(request.createTextNode(remesa.getPERMISOCARGAEXTRA()));
                variables.appendChild(PERMISOCARGAEXTRA);
                
                Element NUMIDGPS = request.createElement("NUMIDGPS");
                NUMIDGPS.appendChild(request.createTextNode(remesa.getNUMIDGPS()));
                variables.appendChild(NUMIDGPS);
                
                
            DOMSource domSource = new DOMSource(request);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
            
        }catch(ParserConfigurationException | TransformerException | DOMException ex){
            System.out.println(ex.getClass()+".consultar."+ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static void main(String[] args){
        System.out.println(ProcessRemesa.grabar(new Remesa()));
    }
}
