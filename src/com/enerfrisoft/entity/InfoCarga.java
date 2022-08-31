/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.enerfrisoft.entity;

/**
 *
 * @author Sebastianaf
 */
public class InfoCarga {
    
    private String  usuario;
    private String  password;
    private String  NUMNITEMPRESATRANSPORTE;
    private String  CONSECUTIVOINFORMACIONCARGA;
    private String  CODOPERACIONTRANSPORTE;
    private String  CODNATURALEZACARGA;
    private String  CANTIDADINFORMACIONCARGA;
    private String  UNIDADMEDIDACAPACIDAD;
    private String  CODTIPOEMPAQUE;
    private String  MERCANCIAINFORMACIONCARGA;
    private String  DESCRIPCIONCORTAPRODUCTO;
    private String  CODTIPOIDREMITENTE;
    private String  NUMIDREMITENTE;
    private String  CODSEDEREMITENTE;
    private String  PESOCONTENEDORVACIO;
    private String  CODTIPOIDDESTINATARIO;
    private String  NUMIDDESTINATARIO;
    private String  CODSEDEDESTINATARIO;
    private String  PACTOTIEMPOCARGUE;
    private String  HORASPACTOCARGA;
    private String  MINUTOSPACTOCARGA;
    private String  PACTOTIEMPODESCARGUE;
    private String  HORASPACTODESCARGUE;
    private String  MINUTOSPACTODESCARGUE;
    private String  OBSERVACIONES;
    private String  FECHACITAPACTADACARGUE;
    private String  HORACITAPACTADACARGUE;
    private String  FECHACITAPACTADADESCARGUE;
    private String  HORACITAPACTADADESCARGUEREMESA;
    
    public InfoCarga(){
        usuario = "";
        password = "";
        NUMNITEMPRESATRANSPORTE = "";
        CONSECUTIVOINFORMACIONCARGA = "";
        CODOPERACIONTRANSPORTE = "";
        CODNATURALEZACARGA = "";
        CANTIDADINFORMACIONCARGA = "";
        UNIDADMEDIDACAPACIDAD = "";
        CODTIPOEMPAQUE = "";
        MERCANCIAINFORMACIONCARGA = "";
        DESCRIPCIONCORTAPRODUCTO = "";
        CODTIPOIDREMITENTE = "";
        NUMIDREMITENTE = "";
        CODSEDEREMITENTE = "";
        PESOCONTENEDORVACIO = "";
        CODTIPOIDDESTINATARIO = "";
        NUMIDDESTINATARIO = "";
        CODSEDEDESTINATARIO = "";
        PACTOTIEMPOCARGUE = "";
        HORASPACTOCARGA = "";
        MINUTOSPACTOCARGA = "";
        PACTOTIEMPODESCARGUE = "";
        HORASPACTODESCARGUE = "";
        MINUTOSPACTODESCARGUE = "";
        OBSERVACIONES = "";
        FECHACITAPACTADACARGUE = "";
        HORACITAPACTADACARGUE = "";
        FECHACITAPACTADADESCARGUE = "";
        HORACITAPACTADADESCARGUEREMESA = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    public String getNUMNITEMPRESATRANSPORTE() {
        return NUMNITEMPRESATRANSPORTE;
    }

    public void setNUMNITEMPRESATRANSPORTE(String NUMNITEMPRESATRANSPORTE) {
        this.NUMNITEMPRESATRANSPORTE = NUMNITEMPRESATRANSPORTE;
    }

    public String getCONSECUTIVOINFORMACIONCARGA() {
        return CONSECUTIVOINFORMACIONCARGA;
    }

    public void setCONSECUTIVOINFORMACIONCARGA(String CONSECUTIVOINFORMACIONCARGA) {
        this.CONSECUTIVOINFORMACIONCARGA = CONSECUTIVOINFORMACIONCARGA;
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

    public String getCANTIDADINFORMACIONCARGA() {
        return CANTIDADINFORMACIONCARGA;
    }

    public void setCANTIDADINFORMACIONCARGA(String CANTIDADINFORMACIONCARGA) {
        this.CANTIDADINFORMACIONCARGA = CANTIDADINFORMACIONCARGA;
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

    public String getMERCANCIAINFORMACIONCARGA() {
        return MERCANCIAINFORMACIONCARGA;
    }

    public void setMERCANCIAINFORMACIONCARGA(String MERCANCIAINFORMACIONCARGA) {
        this.MERCANCIAINFORMACIONCARGA = MERCANCIAINFORMACIONCARGA;
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

    public String getPESOCONTENEDORVACIO() {
        return PESOCONTENEDORVACIO;
    }

    public void setPESOCONTENEDORVACIO(String PESOCONTENEDORVACIO) {
        this.PESOCONTENEDORVACIO = PESOCONTENEDORVACIO;
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

    public String getPACTOTIEMPOCARGUE() {
        return PACTOTIEMPOCARGUE;
    }

    public void setPACTOTIEMPOCARGUE(String PACTOTIEMPOCARGUE) {
        this.PACTOTIEMPOCARGUE = PACTOTIEMPOCARGUE;
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

    public String getPACTOTIEMPODESCARGUE() {
        return PACTOTIEMPODESCARGUE;
    }

    public void setPACTOTIEMPODESCARGUE(String PACTOTIEMPODESCARGUE) {
        this.PACTOTIEMPODESCARGUE = PACTOTIEMPODESCARGUE;
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

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
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
    
                
                                            
}
