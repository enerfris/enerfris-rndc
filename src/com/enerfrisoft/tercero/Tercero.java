/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tercero;

import java.util.Date;

/**
 *
 * @author Sebastianaf
 */
public class Tercero {

    private String Usuario;
    private String Password;
    private String NUMNITEMPRESATRANSPORTE;
    private String CODTIPOIDTERCERO;
    private String NUMIDTERCERO;
    private String EXPEDICION;
    private String NOMIDTERCERO;
    private String PRIMERAPELLIDOIDTERCERO;
    private String SEGUNDOAPELLIDOIDTERCERO;
    private String NUMTELEFONOCONTACTO;
    private String NUMCELULARPERSONA;
    private String NOMENCLATURADIRECCION;
    private String CODMUNICIPIORNDC;
    private String CODSEDETERCERO;
    private String NOMSEDETERCERO;
    private String NUMLICENCIACONDUCCION;
    private String CODCATEGORIALICENCIACONDUCCION;
    private Date FECHAVENCIMIENTOLICENCIA;
    private String EMAIL;
    private String ingresoId;
    private String estado;
    private String userLog;
    private String infoAdicional;

    public Tercero() {
        Usuario = "";
        Password = "";
        NUMNITEMPRESATRANSPORTE = "";
        CODTIPOIDTERCERO = "";
        NUMIDTERCERO = "";
        EXPEDICION = "";
        NOMIDTERCERO = "";
        PRIMERAPELLIDOIDTERCERO = "";
        SEGUNDOAPELLIDOIDTERCERO = "";
        NUMTELEFONOCONTACTO = "";
        NUMCELULARPERSONA = "";
        NOMENCLATURADIRECCION = "";
        CODMUNICIPIORNDC = "";
        CODSEDETERCERO = "";
        NOMSEDETERCERO = "";
        NUMLICENCIACONDUCCION = "";
        CODCATEGORIALICENCIACONDUCCION = "";
        FECHAVENCIMIENTOLICENCIA = null;
        EMAIL = "";
        ingresoId = "";
        estado = "";
        userLog = "";
        infoAdicional = "";

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

    public String getCODTIPOIDTERCERO() {
        return CODTIPOIDTERCERO;
    }

    public void setCODTIPOIDTERCERO(String CODTIPOIDTERCERO) {
        this.CODTIPOIDTERCERO = CODTIPOIDTERCERO;
    }

    public String getNUMIDTERCERO() {
        return NUMIDTERCERO;
    }

    public void setNUMIDTERCERO(String NUMIDTERCERO) {
        this.NUMIDTERCERO = NUMIDTERCERO;
    }

    public String getEXPEDICION() {
        return EXPEDICION;
    }

    public void setEXPEDICION(String EXPEDICION) {
        this.EXPEDICION = EXPEDICION;
    }

    public String getNOMIDTERCERO() {
        return NOMIDTERCERO;
    }

    public void setNOMIDTERCERO(String NOMIDTERCERO) {
        this.NOMIDTERCERO = NOMIDTERCERO;
    }

    public String getPRIMERAPELLIDOIDTERCERO() {
        return PRIMERAPELLIDOIDTERCERO;
    }

    public void setPRIMERAPELLIDOIDTERCERO(String PRIMERAPELLIDOIDTERCERO) {
        this.PRIMERAPELLIDOIDTERCERO = PRIMERAPELLIDOIDTERCERO;
    }

    public String getSEGUNDOAPELLIDOIDTERCERO() {
        return SEGUNDOAPELLIDOIDTERCERO;
    }

    public void setSEGUNDOAPELLIDOIDTERCERO(String SEGUNDOAPELLIDOIDTERCERO) {
        this.SEGUNDOAPELLIDOIDTERCERO = SEGUNDOAPELLIDOIDTERCERO;
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

    public String getNUMLICENCIACONDUCCION() {
        return NUMLICENCIACONDUCCION;
    }

    public void setNUMLICENCIACONDUCCION(String NUMLICENCIACONDUCCION) {
        this.NUMLICENCIACONDUCCION = NUMLICENCIACONDUCCION;
    }

    public String getCODCATEGORIALICENCIACONDUCCION() {
        return CODCATEGORIALICENCIACONDUCCION;
    }

    public void setCODCATEGORIALICENCIACONDUCCION(String CODCATEGORIALICENCIACONDUCCION) {
        this.CODCATEGORIALICENCIACONDUCCION = CODCATEGORIALICENCIACONDUCCION;
    }

    public Date getFECHAVENCIMIENTOLICENCIA() {
        return FECHAVENCIMIENTOLICENCIA;
    }

    public void setFECHAVENCIMIENTOLICENCIA(Date FECHAVENCIMIENTOLICENCIA) {
        this.FECHAVENCIMIENTOLICENCIA = FECHAVENCIMIENTOLICENCIA;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(String ingresoId) {
        this.ingresoId = ingresoId;
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

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    
}
