package com.enerfrisoft.vehiculo;

import java.util.Date;

public class Vehiculo {

    private String NUMNITEMPRESATRANSPORTE;
    private String NUMPLACA;
    private String CODCONFIGURACIONUNIDADCARGA;
    private String CODMARCAVEHICULOCARGA;
    private String CODLINEAVEHICULOCARGA;
    private String NUMEJES;
    private String ANOFABRICACIONVEHICULOCARGA;
    private String ANOREPOTENCIACION;
    private String CODCOLORVEHICULOCARGA;
    private String PESOVEHICULOVACIO;
    private String CAPACIDADUNIDADCARGA;
    private String UNIDADMEDIDACAPACIDAD;
    private String CODTIPOCARROCERIA;
    private String NUMCHASIS;
    private String CODTIPOCOMBUSTIBLE;
    private String NUMSEGUROSOAT;
    private Date FECHAVENCIMIENTOSOAT;
    private String NUMNITASEGURADORASOAT;
    private String CODTIPOIDPROPIETARIO;
    private String NUMIDPROPIETARIO;
    private String CODTIPOIDTENEDOR;
    private String NUMIDTENEDOR;
    private String ingresoId;
    private String afiliacion;
    private String empresa;
    private String rntc;
    private String pvb;
    private String tarjeta_propiedad;
    private String serie_motor;
    private String usuario;
    private String password;
    private String estado;
    private String userLog;
    private String observaciones;

    public Vehiculo() {
        NUMNITEMPRESATRANSPORTE = "";
        NUMPLACA = "";
        CODCONFIGURACIONUNIDADCARGA = "";
        CODMARCAVEHICULOCARGA = "";
        CODLINEAVEHICULOCARGA = "";
        NUMEJES = "";
        ANOFABRICACIONVEHICULOCARGA = "";
        ANOREPOTENCIACION = "";
        CODCOLORVEHICULOCARGA = "";
        PESOVEHICULOVACIO = "";
        CAPACIDADUNIDADCARGA = "";
        UNIDADMEDIDACAPACIDAD = "";
        CODTIPOCARROCERIA = "";
        NUMCHASIS = "";
        CODTIPOCOMBUSTIBLE = "";
        NUMSEGUROSOAT = "";
        FECHAVENCIMIENTOSOAT = null;
        NUMNITASEGURADORASOAT = "";
        CODTIPOIDPROPIETARIO = "";
        NUMIDPROPIETARIO = "";
        CODTIPOIDTENEDOR = "";
        NUMIDTENEDOR = "";
        ingresoId = "";
        afiliacion = "";
        empresa = "";
        rntc = "";
        pvb = "";
        serie_motor = "";
        usuario = "";
        password = "";
        estado = "";
        userLog = "";
        observaciones = "";
    }

    public String getNUMNITEMPRESATRANSPORTE() {
        return NUMNITEMPRESATRANSPORTE;
    }

    public void setNUMNITEMPRESATRANSPORTE(String NUMNITEMPRESATRANSPORTE) {
        this.NUMNITEMPRESATRANSPORTE = NUMNITEMPRESATRANSPORTE;
    }

    public String getNUMPLACA() {
        return NUMPLACA;
    }

    public void setNUMPLACA(String NUMPLACA) {
        this.NUMPLACA = NUMPLACA;
    }

    public String getCODCONFIGURACIONUNIDADCARGA() {
        return CODCONFIGURACIONUNIDADCARGA;
    }

    public void setCODCONFIGURACIONUNIDADCARGA(String CODCONFIGURACIONUNIDADCARGA) {
        this.CODCONFIGURACIONUNIDADCARGA = CODCONFIGURACIONUNIDADCARGA;
    }

    public String getCODMARCAVEHICULOCARGA() {
        return CODMARCAVEHICULOCARGA;
    }

    public void setCODMARCAVEHICULOCARGA(String CODMARCAVEHICULOCARGA) {
        this.CODMARCAVEHICULOCARGA = CODMARCAVEHICULOCARGA;
    }

    public String getCODLINEAVEHICULOCARGA() {
        return CODLINEAVEHICULOCARGA;
    }

    public void setCODLINEAVEHICULOCARGA(String CODLINEAVEHICULOCARGA) {
        this.CODLINEAVEHICULOCARGA = CODLINEAVEHICULOCARGA;
    }

    public String getNUMEJES() {
        return NUMEJES;
    }

    public void setNUMEJES(String NUMEJES) {
        this.NUMEJES = NUMEJES;
    }

    public String getANOFABRICACIONVEHICULOCARGA() {
        return ANOFABRICACIONVEHICULOCARGA;
    }

    public void setANOFABRICACIONVEHICULOCARGA(String ANOFABRICACIONVEHICULOCARGA) {
        this.ANOFABRICACIONVEHICULOCARGA = ANOFABRICACIONVEHICULOCARGA;
    }

    public String getANOREPOTENCIACION() {
        return ANOREPOTENCIACION;
    }

    public void setANOREPOTENCIACION(String ANOREPOTENCIACION) {
        this.ANOREPOTENCIACION = ANOREPOTENCIACION;
    }

    public String getCODCOLORVEHICULOCARGA() {
        return CODCOLORVEHICULOCARGA;
    }

    public void setCODCOLORVEHICULOCARGA(String CODCOLORVEHICULOCARGA) {
        this.CODCOLORVEHICULOCARGA = CODCOLORVEHICULOCARGA;
    }

    public String getPESOVEHICULOVACIO() {
        return PESOVEHICULOVACIO;
    }

    public void setPESOVEHICULOVACIO(String PESOVEHICULOVACIO) {
        this.PESOVEHICULOVACIO = PESOVEHICULOVACIO;
    }

    public String getCAPACIDADUNIDADCARGA() {
        return CAPACIDADUNIDADCARGA;
    }

    public void setCAPACIDADUNIDADCARGA(String CAPACIDADUNIDADCARGA) {
        this.CAPACIDADUNIDADCARGA = CAPACIDADUNIDADCARGA;
    }

    public String getUNIDADMEDIDACAPACIDAD() {
        return UNIDADMEDIDACAPACIDAD;
    }

    public void setUNIDADMEDIDACAPACIDAD(String UNIDADMEDIDACAPACIDAD) {
        this.UNIDADMEDIDACAPACIDAD = UNIDADMEDIDACAPACIDAD;
    }

    public String getCODTIPOCARROCERIA() {
        return CODTIPOCARROCERIA;
    }

    public void setCODTIPOCARROCERIA(String CODTIPOCARROCERIA) {
        this.CODTIPOCARROCERIA = CODTIPOCARROCERIA;
    }

    public String getNUMCHASIS() {
        return NUMCHASIS;
    }

    public void setNUMCHASIS(String NUMCHASIS) {
        this.NUMCHASIS = NUMCHASIS;
    }

    public String getCODTIPOCOMBUSTIBLE() {
        return CODTIPOCOMBUSTIBLE;
    }

    public void setCODTIPOCOMBUSTIBLE(String CODTIPOCOMBUSTIBLE) {
        this.CODTIPOCOMBUSTIBLE = CODTIPOCOMBUSTIBLE;
    }

    public String getNUMSEGUROSOAT() {
        return NUMSEGUROSOAT;
    }

    public void setNUMSEGUROSOAT(String NUMSEGUROSOAT) {
        this.NUMSEGUROSOAT = NUMSEGUROSOAT;
    }

    public Date getFECHAVENCIMIENTOSOAT() {
        return FECHAVENCIMIENTOSOAT;
    }

    public void setFECHAVENCIMIENTOSOAT(Date FECHAVENCIMIENTOSOAT) {
        this.FECHAVENCIMIENTOSOAT = FECHAVENCIMIENTOSOAT;
    }

    public String getNUMNITASEGURADORASOAT() {
        return NUMNITASEGURADORASOAT;
    }

    public void setNUMNITASEGURADORASOAT(String NUMNITASEGURADORASOAT) {
        this.NUMNITASEGURADORASOAT = NUMNITASEGURADORASOAT;
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

    public String getCODTIPOIDTENEDOR() {
        return CODTIPOIDTENEDOR;
    }

    public void setCODTIPOIDTENEDOR(String CODTIPOIDTENEDOR) {
        this.CODTIPOIDTENEDOR = CODTIPOIDTENEDOR;
    }

    public String getNUMIDTENEDOR() {
        return NUMIDTENEDOR;
    }

    public void setNUMIDTENEDOR(String NUMIDTENEDOR) {
        this.NUMIDTENEDOR = NUMIDTENEDOR;
    }

    public String getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(String ingresoId) {
        this.ingresoId = ingresoId;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rntc;
    }

    public String getPvb() {
        return pvb;
    }

    public void setPvb(String pvb) {
        this.pvb = pvb;
    }

    public String getTarjeta_propiedad() {
        return tarjeta_propiedad;
    }

    public void setTarjeta_propiedad(String tarjeta_propiedad) {
        this.tarjeta_propiedad = tarjeta_propiedad;
    }

    public String getSerie_motor() {
        return serie_motor;
    }

    public void setSerie_motor(String serie_motor) {
        this.serie_motor = serie_motor;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
