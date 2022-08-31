/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.remesa;

/**
 *
 * @author Sebastianaf
 */
public class Remesa {

    private String Usuario;
    private String Password;
    private String NUMNITEMPRESATRANSPORTE;
    private String CONSECUTIVOREMESA;
    private String CONSECUTIVOINFORMACIONCARGA;
    private String CONSECUTIVOCARGADIVIDIDA;
    private String CODOPERACIONTRANSPORTE;
    private String CODNATURALEZACARGA;
    private String CANTIDADCARGADA;
    private String UNIDADMEDIDACAPACIDAD;
    private String CODTIPOEMPAQUE;
    private String PESOCONTENEDORVACIO;
    private String MERCANCIAREMESA;
    private String DESCRIPCIONCORTAPRODUCTO;
    private String CODTIPOIDREMITENTE;
    private String NUMIDREMITENTE;
    private String CODSEDEREMITENTE;
    private String CODTIPOIDDESTINATARIO;
    private String NUMIDDESTINATARIO;
    private String CODSEDEDESTINATARIO;
    private String DUENOPOLIZA;
    private String NUMPOLIZATRANSPORTE;
    private String COMPANIASEGURO;
    private String FECHAVENCIMIENTOPOLIZACARGA;
    private String HORASPACTOCARGA;
    private String MINUTOSPACTOCARGA;
    private String HORASPACTODESCARGUE;
    private String MINUTOSPACTODESCARGUE;
    private String FECHALLEGADACARGUE;
    private String HORALLEGADACARGUEREMESA;
    private String FECHAENTRADACARGUE;
    private String HORAENTRADACARGUEREMESA;
    private String FECHASALIDACARGUE;
    private String HORASALIDACARGUEREMESA;
    private String CODTIPOIDPROPIETARIO;
    private String NUMIDPROPIETARIO;
    private String CODSEDEPROPIETARIO;
    private String FECHACITAPACTADACARGUE;
    private String HORACITAPACTADACARGUE;
    private String FECHACITAPACTADADESCARGUE;
    private String HORACITAPACTADADESCARGUEREMESA;
    private String PERMISOCARGAEXTRA;
    private String NUMIDGPS;
    private String OBSERVACIONES;
    private String userLog;
    private String dateLog;
    private String estado;
    private String ingresoId;

    public Remesa() {
        Usuario = "";
        Password = "";
        NUMNITEMPRESATRANSPORTE = "";
        CONSECUTIVOREMESA = "";
        CONSECUTIVOINFORMACIONCARGA = "";
        CONSECUTIVOCARGADIVIDIDA = "";
        CODOPERACIONTRANSPORTE = "";
        CODNATURALEZACARGA = "";
        CANTIDADCARGADA = "";
        UNIDADMEDIDACAPACIDAD = "";
        CODTIPOEMPAQUE = "";
        PESOCONTENEDORVACIO = "";
        MERCANCIAREMESA = "";
        DESCRIPCIONCORTAPRODUCTO = "";
        CODTIPOIDREMITENTE = "";
        NUMIDREMITENTE = "";
        CODSEDEREMITENTE = "";
        CODTIPOIDDESTINATARIO = "";
        NUMIDDESTINATARIO = "";
        CODSEDEDESTINATARIO = "";
        DUENOPOLIZA = "";
        NUMPOLIZATRANSPORTE = "";
        COMPANIASEGURO = "";
        FECHAVENCIMIENTOPOLIZACARGA = "";
        HORASPACTOCARGA = "";
        MINUTOSPACTOCARGA = "";
        HORASPACTODESCARGUE = "";
        MINUTOSPACTODESCARGUE = "";
        FECHALLEGADACARGUE = "";
        HORALLEGADACARGUEREMESA = "";
        FECHAENTRADACARGUE = "";
        HORAENTRADACARGUEREMESA = "";
        FECHASALIDACARGUE = "";
        HORASALIDACARGUEREMESA = "";
        CODTIPOIDPROPIETARIO = "";
        NUMIDPROPIETARIO = "";
        CODSEDEPROPIETARIO = "";
        FECHACITAPACTADACARGUE = "";
        HORACITAPACTADACARGUE = "";
        FECHACITAPACTADADESCARGUE = "";
        HORACITAPACTADADESCARGUEREMESA = "";
        PERMISOCARGAEXTRA = "";
        NUMIDGPS = "";
        OBSERVACIONES = "";
        userLog = "";
        dateLog = "";
        estado = "";
        ingresoId = "";
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNUMNITEMPRESATRANSPORTE() {
        return NUMNITEMPRESATRANSPORTE;
    }

    public void setNUMNITEMPRESATRANSPORTE(String NUMNITEMPRESATRANSPORTE) {
        this.NUMNITEMPRESATRANSPORTE = NUMNITEMPRESATRANSPORTE;
    }

    public String getCONSECUTIVOREMESA() {
        return CONSECUTIVOREMESA;
    }

    public void setCONSECUTIVOREMESA(String CONSECUTIVOREMESA) {
        this.CONSECUTIVOREMESA = CONSECUTIVOREMESA;
    }

    public String getCONSECUTIVOINFORMACIONCARGA() {
        return CONSECUTIVOINFORMACIONCARGA;
    }

    public void setCONSECUTIVOINFORMACIONCARGA(String CONSECUTIVOINFORMACIONCARGA) {
        this.CONSECUTIVOINFORMACIONCARGA = CONSECUTIVOINFORMACIONCARGA;
    }

    public String getCONSECUTIVOCARGADIVIDIDA() {
        return CONSECUTIVOCARGADIVIDIDA;
    }

    public void setCONSECUTIVOCARGADIVIDIDA(String CONSECUTIVOCARGADIVIDIDA) {
        this.CONSECUTIVOCARGADIVIDIDA = CONSECUTIVOCARGADIVIDIDA;
    }

    public String getCODOPERACIONTRANSPORTE() {
        return CODOPERACIONTRANSPORTE;
    }

    public void setCODOPERACIONTRANSPORTE(String CODOPERACIONTRANSPORTE) {
        this.CODOPERACIONTRANSPORTE = CODOPERACIONTRANSPORTE;
    }

    public String getCODNATURALEZACARGA() {
        return CODNATURALEZACARGA;
    }

    public void setCODNATURALEZACARGA(String CODNATURALEZACARGA) {
        this.CODNATURALEZACARGA = CODNATURALEZACARGA;
    }

    public String getCANTIDADCARGADA() {
        return CANTIDADCARGADA;
    }

    public void setCANTIDADCARGADA(String CANTIDADCARGADA) {
        this.CANTIDADCARGADA = CANTIDADCARGADA;
    }

    public String getUNIDADMEDIDACAPACIDAD() {
        return UNIDADMEDIDACAPACIDAD;
    }

    public void setUNIDADMEDIDACAPACIDAD(String UNIDADMEDIDACAPACIDAD) {
        this.UNIDADMEDIDACAPACIDAD = UNIDADMEDIDACAPACIDAD;
    }

    public String getCODTIPOEMPAQUE() {
        return CODTIPOEMPAQUE;
    }

    public void setCODTIPOEMPAQUE(String CODTIPOEMPAQUE) {
        this.CODTIPOEMPAQUE = CODTIPOEMPAQUE;
    }

    public String getPESOCONTENEDORVACIO() {
        return PESOCONTENEDORVACIO;
    }

    public void setPESOCONTENEDORVACIO(String PESOCONTENEDORVACIO) {
        this.PESOCONTENEDORVACIO = PESOCONTENEDORVACIO;
    }

    public String getMERCANCIAREMESA() {
        return MERCANCIAREMESA;
    }

    public void setMERCANCIAREMESA(String MERCANCIAREMESA) {
        this.MERCANCIAREMESA = MERCANCIAREMESA;
    }

    public String getDESCRIPCIONCORTAPRODUCTO() {
        return DESCRIPCIONCORTAPRODUCTO;
    }

    public void setDESCRIPCIONCORTAPRODUCTO(String DESCRIPCIONCORTAPRODUCTO) {
        this.DESCRIPCIONCORTAPRODUCTO = DESCRIPCIONCORTAPRODUCTO;
    }

    public String getCODTIPOIDREMITENTE() {
        return CODTIPOIDREMITENTE;
    }

    public void setCODTIPOIDREMITENTE(String CODTIPOIDREMITENTE) {
        this.CODTIPOIDREMITENTE = CODTIPOIDREMITENTE;
    }

    public String getNUMIDREMITENTE() {
        return NUMIDREMITENTE;
    }

    public void setNUMIDREMITENTE(String NUMIDREMITENTE) {
        this.NUMIDREMITENTE = NUMIDREMITENTE;
    }

    public String getCODSEDEREMITENTE() {
        return CODSEDEREMITENTE;
    }

    public void setCODSEDEREMITENTE(String CODSEDEREMITENTE) {
        this.CODSEDEREMITENTE = CODSEDEREMITENTE;
    }

    public String getCODTIPOIDDESTINATARIO() {
        return CODTIPOIDDESTINATARIO;
    }

    public void setCODTIPOIDDESTINATARIO(String CODTIPOIDDESTINATARIO) {
        this.CODTIPOIDDESTINATARIO = CODTIPOIDDESTINATARIO;
    }

    public String getNUMIDDESTINATARIO() {
        return NUMIDDESTINATARIO;
    }

    public void setNUMIDDESTINATARIO(String NUMIDDESTINATARIO) {
        this.NUMIDDESTINATARIO = NUMIDDESTINATARIO;
    }

    public String getCODSEDEDESTINATARIO() {
        return CODSEDEDESTINATARIO;
    }

    public void setCODSEDEDESTINATARIO(String CODSEDEDESTINATARIO) {
        this.CODSEDEDESTINATARIO = CODSEDEDESTINATARIO;
    }

    public String getDUENOPOLIZA() {
        return DUENOPOLIZA;
    }

    public void setDUENOPOLIZA(String DUENOPOLIZA) {
        this.DUENOPOLIZA = DUENOPOLIZA;
    }

    public String getNUMPOLIZATRANSPORTE() {
        return NUMPOLIZATRANSPORTE;
    }

    public void setNUMPOLIZATRANSPORTE(String NUMPOLIZATRANSPORTE) {
        this.NUMPOLIZATRANSPORTE = NUMPOLIZATRANSPORTE;
    }

    public String getCOMPANIASEGURO() {
        return COMPANIASEGURO;
    }

    public void setCOMPANIASEGURO(String COMPANIASEGURO) {
        this.COMPANIASEGURO = COMPANIASEGURO;
    }

    public String getFECHAVENCIMIENTOPOLIZACARGA() {
        return FECHAVENCIMIENTOPOLIZACARGA;
    }

    public void setFECHAVENCIMIENTOPOLIZACARGA(String FECHAVENCIMIENTOPOLIZACARGA) {
        this.FECHAVENCIMIENTOPOLIZACARGA = FECHAVENCIMIENTOPOLIZACARGA;
    }

    public String getHORASPACTOCARGA() {
        return HORASPACTOCARGA;
    }

    public void setHORASPACTOCARGA(String HORASPACTOCARGA) {
        this.HORASPACTOCARGA = HORASPACTOCARGA;
    }

    public String getMINUTOSPACTOCARGA() {
        return MINUTOSPACTOCARGA;
    }

    public void setMINUTOSPACTOCARGA(String MINUTOSPACTOCARGA) {
        this.MINUTOSPACTOCARGA = MINUTOSPACTOCARGA;
    }

    public String getHORASPACTODESCARGUE() {
        return HORASPACTODESCARGUE;
    }

    public void setHORASPACTODESCARGUE(String HORASPACTODESCARGUE) {
        this.HORASPACTODESCARGUE = HORASPACTODESCARGUE;
    }

    public String getMINUTOSPACTODESCARGUE() {
        return MINUTOSPACTODESCARGUE;
    }

    public void setMINUTOSPACTODESCARGUE(String MINUTOSPACTODESCARGUE) {
        this.MINUTOSPACTODESCARGUE = MINUTOSPACTODESCARGUE;
    }

    public String getFECHALLEGADACARGUE() {
        return FECHALLEGADACARGUE;
    }

    public void setFECHALLEGADACARGUE(String FECHALLEGADACARGUE) {
        this.FECHALLEGADACARGUE = FECHALLEGADACARGUE;
    }

    public String getHORALLEGADACARGUEREMESA() {
        return HORALLEGADACARGUEREMESA;
    }

    public void setHORALLEGADACARGUEREMESA(String HORALLEGADACARGUEREMESA) {
        this.HORALLEGADACARGUEREMESA = HORALLEGADACARGUEREMESA;
    }

    public String getFECHAENTRADACARGUE() {
        return FECHAENTRADACARGUE;
    }

    public void setFECHAENTRADACARGUE(String FECHAENTRADACARGUE) {
        this.FECHAENTRADACARGUE = FECHAENTRADACARGUE;
    }

    public String getHORAENTRADACARGUEREMESA() {
        return HORAENTRADACARGUEREMESA;
    }

    public void setHORAENTRADACARGUEREMESA(String HORAENTRADACARGUEREMESA) {
        this.HORAENTRADACARGUEREMESA = HORAENTRADACARGUEREMESA;
    }

    public String getFECHASALIDACARGUE() {
        return FECHASALIDACARGUE;
    }

    public void setFECHASALIDACARGUE(String FECHASALIDACARGUE) {
        this.FECHASALIDACARGUE = FECHASALIDACARGUE;
    }

    public String getHORASALIDACARGUEREMESA() {
        return HORASALIDACARGUEREMESA;
    }

    public void setHORASALIDACARGUEREMESA(String HORASALIDACARGUEREMESA) {
        this.HORASALIDACARGUEREMESA = HORASALIDACARGUEREMESA;
    }

    public String getCODTIPOIDPROPIETARIO() {
        return CODTIPOIDPROPIETARIO;
    }

    public void setCODTIPOIDPROPIETARIO(String CODTIPOIDPROPIETARIO) {
        this.CODTIPOIDPROPIETARIO = CODTIPOIDPROPIETARIO;
    }

    public String getNUMIDPROPIETARIO() {
        return NUMIDPROPIETARIO;
    }

    public void setNUMIDPROPIETARIO(String NUMIDPROPIETARIO) {
        this.NUMIDPROPIETARIO = NUMIDPROPIETARIO;
    }

    public String getCODSEDEPROPIETARIO() {
        return CODSEDEPROPIETARIO;
    }

    public void setCODSEDEPROPIETARIO(String CODSEDEPROPIETARIO) {
        this.CODSEDEPROPIETARIO = CODSEDEPROPIETARIO;
    }

    public String getFECHACITAPACTADACARGUE() {
        return FECHACITAPACTADACARGUE;
    }

    public void setFECHACITAPACTADACARGUE(String FECHACITAPACTADACARGUE) {
        this.FECHACITAPACTADACARGUE = FECHACITAPACTADACARGUE;
    }

    public String getHORACITAPACTADACARGUE() {
        return HORACITAPACTADACARGUE;
    }

    public void setHORACITAPACTADACARGUE(String HORACITAPACTADACARGUE) {
        this.HORACITAPACTADACARGUE = HORACITAPACTADACARGUE;
    }

    public String getFECHACITAPACTADADESCARGUE() {
        return FECHACITAPACTADADESCARGUE;
    }

    public void setFECHACITAPACTADADESCARGUE(String FECHACITAPACTADADESCARGUE) {
        this.FECHACITAPACTADADESCARGUE = FECHACITAPACTADADESCARGUE;
    }

    public String getHORACITAPACTADADESCARGUEREMESA() {
        return HORACITAPACTADADESCARGUEREMESA;
    }

    public void setHORACITAPACTADADESCARGUEREMESA(String HORACITAPACTADADESCARGUEREMESA) {
        this.HORACITAPACTADADESCARGUEREMESA = HORACITAPACTADADESCARGUEREMESA;
    }

    public String getPERMISOCARGAEXTRA() {
        return PERMISOCARGAEXTRA;
    }

    public void setPERMISOCARGAEXTRA(String PERMISOCARGAEXTRA) {
        this.PERMISOCARGAEXTRA = PERMISOCARGAEXTRA;
    }

    public String getNUMIDGPS() {
        return NUMIDGPS;
    }

    public void setNUMIDGPS(String NUMIDGPS) {
        this.NUMIDGPS = NUMIDGPS;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    public String getDateLog() {
        return dateLog;
    }

    public void setDateLog(String dateLog) {
        this.dateLog = dateLog;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(String ingresoId) {
        this.ingresoId = ingresoId;
    }
    
    
    
}
