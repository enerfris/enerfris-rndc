package com.enerfrisoft.sede;

public class Sede {

    private String Usuario;
    private String Password;
    private String NUMNITEMPRESATRANSPORTE;
    private String NUMTELEFONOCONTACTO;
    private String NUMCELULARPERSONA;
    private String NOMENCLATURADIRECCION;
    private String CODMUNICIPIORNDC;
    private String CODSEDETERCERO;
    private String NOMSEDETERCERO;
    private String NUMIDTERCERO;
    private String userLog;

    public Sede() {

        Usuario = "";
        Password = "";
        NUMNITEMPRESATRANSPORTE = "";
        NUMTELEFONOCONTACTO = "";
        NUMCELULARPERSONA = "";
        NOMENCLATURADIRECCION = "";
        CODMUNICIPIORNDC = "";
        CODSEDETERCERO = "";
        NOMSEDETERCERO = "";
        NUMIDTERCERO = "";
        userLog = "";
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

    public String getNUMTELEFONOCONTACTO() {
        return NUMTELEFONOCONTACTO;
    }

    public void setNUMTELEFONOCONTACTO(String NUMTELEFONOCONTACTO) {
        this.NUMTELEFONOCONTACTO = NUMTELEFONOCONTACTO;
    }

    public String getNUMCELULARPERSONA() {
        return NUMCELULARPERSONA;
    }

    public void setNUMCELULARPERSONA(String NUMCELULARPERSONA) {
        this.NUMCELULARPERSONA = NUMCELULARPERSONA;
    }

    public String getNOMENCLATURADIRECCION() {
        return NOMENCLATURADIRECCION;
    }

    public void setNOMENCLATURADIRECCION(String NOMENCLATURADIRECCION) {
        this.NOMENCLATURADIRECCION = NOMENCLATURADIRECCION;
    }

    public String getCODMUNICIPIORNDC() {
        return CODMUNICIPIORNDC;
    }

    public void setCODMUNICIPIORNDC(String CODMUNICIPIORNDC) {
        this.CODMUNICIPIORNDC = CODMUNICIPIORNDC;
    }

    public String getCODSEDETERCERO() {
        return CODSEDETERCERO;
    }

    public void setCODSEDETERCERO(String CODSEDETERCERO) {
        this.CODSEDETERCERO = CODSEDETERCERO;
    }

    public String getNOMSEDETERCERO() {
        return NOMSEDETERCERO;
    }

    public void setNOMSEDETERCERO(String NOMSEDETERCERO) {
        this.NOMSEDETERCERO = NOMSEDETERCERO;
    }

    public String getNUMIDTERCERO() {
        return NUMIDTERCERO;
    }

    public void setNUMIDTERCERO(String NUMIDTERCERO) {
        this.NUMIDTERCERO = NUMIDTERCERO;
    }

    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

        
}
