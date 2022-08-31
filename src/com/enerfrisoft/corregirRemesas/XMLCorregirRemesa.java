package com.enerfrisoft.corregirRemesas;

import com.enerfrisoft.tools.Data;
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


public class XMLCorregirRemesa {

    public static String grabar(Remesa entity) {
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
            procesoid.appendChild(request.createTextNode("38"));
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
            Element CONSECUTIVOINFORMACIONCARGA = request.createElement("CONSECUTIVOINFORMACIONCARGA");
            CONSECUTIVOINFORMACIONCARGA.appendChild(request.createTextNode(entity.getCONSECUTIVOINFORMACIONCARGA()));
            if (!entity.getCONSECUTIVOINFORMACIONCARGA().equals("")) {
                variables.appendChild(CONSECUTIVOINFORMACIONCARGA);
            }
            Element CONSECUTIVOCARGADIVIDIDA = request.createElement("CONSECUTIVOCARGADIVIDIDA");
            CONSECUTIVOCARGADIVIDIDA.appendChild(request.createTextNode(entity.getCONSECUTIVOCARGADIVIDIDA()));
            if (!entity.getCONSECUTIVOCARGADIVIDIDA().equals("")) {
                variables.appendChild(CONSECUTIVOCARGADIVIDIDA);
            }
            Element CODOPERACIONTRANSPORTE = request.createElement("CODOPERACIONTRANSPORTE");
            CODOPERACIONTRANSPORTE.appendChild(request.createTextNode(entity.getCODOPERACIONTRANSPORTE()));
            if (!entity.getCODOPERACIONTRANSPORTE().equals("")) {
                variables.appendChild(CODOPERACIONTRANSPORTE);
            }
            Element CODNATURALEZACARGA = request.createElement("CODNATURALEZACARGA");
            CODNATURALEZACARGA.appendChild(request.createTextNode(entity.getCODNATURALEZACARGA()));
            if (!entity.getCODNATURALEZACARGA().equals("")) {
                variables.appendChild(CODNATURALEZACARGA);
            }
            Element CANTIDADCARGADA = request.createElement("CANTIDADCARGADA");
            CANTIDADCARGADA.appendChild(request.createTextNode(entity.getCANTIDADCARGADA()));
            if (!entity.getCANTIDADCARGADA().equals("")) {
                variables.appendChild(CANTIDADCARGADA);
            }
            Element UNIDADMEDIDACAPACIDAD = request.createElement("UNIDADMEDIDACAPACIDAD");
            UNIDADMEDIDACAPACIDAD.appendChild(request.createTextNode(entity.getUNIDADMEDIDACAPACIDAD()));
            if (!entity.getUNIDADMEDIDACAPACIDAD().equals("")) {
                variables.appendChild(UNIDADMEDIDACAPACIDAD);
            }
            Element CODTIPOEMPAQUE = request.createElement("CODTIPOEMPAQUE");
            CODTIPOEMPAQUE.appendChild(request.createTextNode(entity.getCODTIPOEMPAQUE()));
            if (!entity.getCODTIPOEMPAQUE().equals("")) {
                variables.appendChild(CODTIPOEMPAQUE);
            }
            Element PESOCONTENEDORVACIO = request.createElement("PESOCONTENEDORVACIO");
            PESOCONTENEDORVACIO.appendChild(request.createTextNode(entity.getPESOCONTENEDORVACIO()));
            if (!entity.getPESOCONTENEDORVACIO().equals("")) {
                variables.appendChild(PESOCONTENEDORVACIO);
            }
            Element MERCANCIAREMESA = request.createElement("MERCANCIAREMESA");
            MERCANCIAREMESA.appendChild(request.createTextNode(entity.getMERCANCIAREMESA()));
            if (!entity.getMERCANCIAREMESA().equals("")) {
                variables.appendChild(MERCANCIAREMESA);
            }
            Element DESCRIPCIONCORTAPRODUCTO = request.createElement("DESCRIPCIONCORTAPRODUCTO");
            DESCRIPCIONCORTAPRODUCTO.appendChild(request.createTextNode(entity.getDESCRIPCIONCORTAPRODUCTO()));
            if (!entity.getDESCRIPCIONCORTAPRODUCTO().equals("")) {
                variables.appendChild(DESCRIPCIONCORTAPRODUCTO);
            }
            Element CODTIPOIDREMITENTE = request.createElement("CODTIPOIDREMITENTE");
            CODTIPOIDREMITENTE.appendChild(request.createTextNode(entity.getCODTIPOIDREMITENTE()));
            if (!entity.getCODTIPOIDREMITENTE().equals("")) {
                variables.appendChild(CODTIPOIDREMITENTE);
            }
            Element NUMIDREMITENTE = request.createElement("NUMIDREMITENTE");
            NUMIDREMITENTE.appendChild(request.createTextNode(entity.getNUMIDREMITENTE()));
            if (!entity.getNUMIDREMITENTE().equals("")) {
                variables.appendChild(NUMIDREMITENTE);
            }
            Element CODSEDEREMITENTE = request.createElement("CODSEDEREMITENTE");
            CODSEDEREMITENTE.appendChild(request.createTextNode(entity.getCODSEDEREMITENTE()));
            if (!entity.getCODSEDEREMITENTE().equals("")) {
                variables.appendChild(CODSEDEREMITENTE);
            }
            Element CODTIPOIDDESTINATARIO = request.createElement("CODTIPOIDDESTINATARIO");
            CODTIPOIDDESTINATARIO.appendChild(request.createTextNode(entity.getCODTIPOIDDESTINATARIO()));
            if (!entity.getCODTIPOIDDESTINATARIO().equals("")) {
                variables.appendChild(CODTIPOIDDESTINATARIO);
            }
            Element NUMIDDESTINATARIO = request.createElement("NUMIDDESTINATARIO");
            NUMIDDESTINATARIO.appendChild(request.createTextNode(entity.getNUMIDDESTINATARIO()));
            if (!entity.getNUMIDDESTINATARIO().equals("")) {
                variables.appendChild(NUMIDDESTINATARIO);
            }
            Element CODSEDEDESTINATARIO = request.createElement("CODSEDEDESTINATARIO");
            CODSEDEDESTINATARIO.appendChild(request.createTextNode(entity.getCODSEDEDESTINATARIO()));
            if (!entity.getCODSEDEDESTINATARIO().equals("")) {
                variables.appendChild(CODSEDEDESTINATARIO);
            }
            Element DUENOPOLIZA = request.createElement("DUENOPOLIZA");
            DUENOPOLIZA.appendChild(request.createTextNode(entity.getDUENOPOLIZA()));
            if (!entity.getDUENOPOLIZA().equals("")) {
                variables.appendChild(DUENOPOLIZA);
            }
            Element NUMPOLIZATRANSPORTE = request.createElement("NUMPOLIZATRANSPORTE");
            NUMPOLIZATRANSPORTE.appendChild(request.createTextNode(entity.getNUMPOLIZATRANSPORTE()));
            if (!entity.getNUMPOLIZATRANSPORTE().equals("")) {
                variables.appendChild(NUMPOLIZATRANSPORTE);
            }
            Element COMPANIASEGURO = request.createElement("COMPANIASEGURO");
            COMPANIASEGURO.appendChild(request.createTextNode(entity.getCOMPANIASEGURO()));
            if (!entity.getCOMPANIASEGURO().equals("")) {
                variables.appendChild(COMPANIASEGURO);
            }
            Element FECHAVENCIMIENTOPOLIZACARGA = request.createElement("FECHAVENCIMIENTOPOLIZACARGA");
            FECHAVENCIMIENTOPOLIZACARGA.appendChild(request.createTextNode(entity.getFECHAVENCIMIENTOPOLIZACARGA()));
            if (!entity.getFECHAVENCIMIENTOPOLIZACARGA().equals("")) {
                variables.appendChild(FECHAVENCIMIENTOPOLIZACARGA);
            }
            Element HORASPACTOCARGA = request.createElement("HORASPACTOCARGA");
            HORASPACTOCARGA.appendChild(request.createTextNode(entity.getHORASPACTOCARGA()));
            if (!entity.getHORASPACTOCARGA().equals("")) {
                variables.appendChild(HORASPACTOCARGA);
            }
            Element MINUTOSPACTOCARGA = request.createElement("MINUTOSPACTOCARGA");
            MINUTOSPACTOCARGA.appendChild(request.createTextNode(entity.getMINUTOSPACTOCARGA()));
            if (!entity.getMINUTOSPACTOCARGA().equals("")) {
                variables.appendChild(MINUTOSPACTOCARGA);
            }
            Element HORASPACTODESCARGUE = request.createElement("HORASPACTODESCARGUE");
            HORASPACTODESCARGUE.appendChild(request.createTextNode(entity.getHORASPACTODESCARGUE()));
            if (!entity.getHORASPACTODESCARGUE().equals("")) {
                variables.appendChild(HORASPACTODESCARGUE);
            }
            Element MINUTOSPACTODESCARGUE = request.createElement("MINUTOSPACTODESCARGUE");
            MINUTOSPACTODESCARGUE.appendChild(request.createTextNode(entity.getMINUTOSPACTODESCARGUE()));
            if (!entity.getMINUTOSPACTODESCARGUE().equals("")) {
                variables.appendChild(MINUTOSPACTODESCARGUE);
            }
            Element FECHALLEGADACARGUE = request.createElement("FECHALLEGADACARGUE");
            FECHALLEGADACARGUE.appendChild(request.createTextNode(entity.getFECHALLEGADACARGUE()));
            if (!entity.getFECHALLEGADACARGUE().equals("")) {
                variables.appendChild(FECHALLEGADACARGUE);
            }
            Element HORALLEGADACARGUEREMESA = request.createElement("HORALLEGADACARGUEREMESA");
            HORALLEGADACARGUEREMESA.appendChild(request.createTextNode(entity.getHORALLEGADACARGUEREMESA()));
            if (!entity.getHORALLEGADACARGUEREMESA().equals("")) {
                variables.appendChild(HORALLEGADACARGUEREMESA);
            }
            Element FECHAENTRADACARGUE = request.createElement("FECHAENTRADACARGUE");
            FECHAENTRADACARGUE.appendChild(request.createTextNode(entity.getFECHAENTRADACARGUE()));
            if (!entity.getFECHAENTRADACARGUE().equals("")) {
                variables.appendChild(FECHAENTRADACARGUE);
            }
            Element HORAENTRADACARGUEREMESA = request.createElement("HORAENTRADACARGUEREMESA");
            HORAENTRADACARGUEREMESA.appendChild(request.createTextNode(entity.getHORAENTRADACARGUEREMESA()));
            if (!entity.getHORAENTRADACARGUEREMESA().equals("")) {
                variables.appendChild(HORAENTRADACARGUEREMESA);
            }
            Element FECHASALIDACARGUE = request.createElement("FECHASALIDACARGUE");
            FECHASALIDACARGUE.appendChild(request.createTextNode(entity.getFECHASALIDACARGUE()));
            if (!entity.getFECHASALIDACARGUE().equals("")) {
                variables.appendChild(FECHASALIDACARGUE);
            }
            Element HORASALIDACARGUEREMESA = request.createElement("HORASALIDACARGUEREMESA");
            HORASALIDACARGUEREMESA.appendChild(request.createTextNode(entity.getHORASALIDACARGUEREMESA()));
            if (!entity.getHORASALIDACARGUEREMESA().equals("")) {
                variables.appendChild(HORASALIDACARGUEREMESA);
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
            Element CODSEDEPROPIETARIO = request.createElement("CODSEDEPROPIETARIO");
            CODSEDEPROPIETARIO.appendChild(request.createTextNode(entity.getCODSEDEPROPIETARIO()));
            if (!entity.getCODSEDEPROPIETARIO().equals("")) {
                variables.appendChild(CODSEDEPROPIETARIO);
            }
            Element FECHACITAPACTADACARGUE = request.createElement("FECHACITAPACTADACARGUE");
            FECHACITAPACTADACARGUE.appendChild(request.createTextNode(entity.getFECHACITAPACTADACARGUE()));
            if (!entity.getFECHACITAPACTADACARGUE().equals("")) {
                variables.appendChild(FECHACITAPACTADACARGUE);
            }
            Element HORACITAPACTADACARGUE = request.createElement("HORACITAPACTADACARGUE");
            HORACITAPACTADACARGUE.appendChild(request.createTextNode(entity.getHORACITAPACTADACARGUE()));
            if (!entity.getHORACITAPACTADACARGUE().equals("")) {
                variables.appendChild(HORACITAPACTADACARGUE);
            }
            Element FECHACITAPACTADADESCARGUE = request.createElement("FECHACITAPACTADADESCARGUE");
            FECHACITAPACTADADESCARGUE.appendChild(request.createTextNode(entity.getFECHACITAPACTADADESCARGUE()));
            if (!entity.getFECHACITAPACTADADESCARGUE().equals("")) {
                variables.appendChild(FECHACITAPACTADADESCARGUE);
            }
            Element HORACITAPACTADADESCARGUEREMESA = request.createElement("HORACITAPACTADADESCARGUEREMESA");
            HORACITAPACTADADESCARGUEREMESA.appendChild(request.createTextNode(entity.getHORACITAPACTADADESCARGUEREMESA()));
            if (!entity.getHORACITAPACTADADESCARGUEREMESA().equals("")) {
                variables.appendChild(HORACITAPACTADADESCARGUEREMESA);
            }
            Element PERMISOCARGAEXTRA = request.createElement("PERMISOCARGAEXTRA");
            PERMISOCARGAEXTRA.appendChild(request.createTextNode(entity.getPERMISOCARGAEXTRA()));
            if (!entity.getPERMISOCARGAEXTRA().equals("")) {
                variables.appendChild(PERMISOCARGAEXTRA);
            }
            Element NUMIDGPS = request.createElement("NUMIDGPS");
            NUMIDGPS.appendChild(request.createTextNode(entity.getNUMIDGPS()));
            if (!entity.getNUMIDGPS().equals("")) {
                variables.appendChild(NUMIDGPS);
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
            return writer.toString();
        } catch (ParserConfigurationException | TransformerException | DOMException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
}
