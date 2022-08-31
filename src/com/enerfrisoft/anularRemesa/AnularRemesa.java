/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.anularRemesa;

/**
 *
 * @author sebastianaf
 */
public class AnularRemesa {

    private String usuario;
    private String password;
    private String NUMNITEMPRESATRANSPORTE;
    private String CONSECUTIVOREMESA;
    private String MOTIVOANULACIONREMESA;
    private String MOTIVOREVERSAREMESA;
    private String CODMUNICIPIOTRANSBORDO;
    private String MOTIVOTRANSBORDOREMESA;
    private String OBSERVACIONES;
    private String userLog;
    private String dateLog;
    private String estado;
    private String ingresoId;

    public AnularRemesa() {
        usuario = "";
        password = "";
        NUMNITEMPRESATRANSPORTE = "";
        CONSECUTIVOREMESA = "";
        MOTIVOANULACIONREMESA = "";
        MOTIVOREVERSAREMESA = "";
        CODMUNICIPIOTRANSBORDO = "";
        MOTIVOTRANSBORDOREMESA = "";
        OBSERVACIONES = "";
        userLog = "";
        dateLog = "";
        estado = "";
        ingresoId = "";
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

    public String getCONSECUTIVOREMESA() {
        return CONSECUTIVOREMESA;
    }

    public void setCONSECUTIVOREMESA(String CONSECUTIVOREMESA) {
        this.CONSECUTIVOREMESA = CONSECUTIVOREMESA;
    }

    public String getMOTIVOANULACIONREMESA() {
        return MOTIVOANULACIONREMESA;
    }

    public void setMOTIVOANULACIONREMESA(String MOTIVOANULACIONREMESA) {
        this.MOTIVOANULACIONREMESA = MOTIVOANULACIONREMESA;
    }

    public String getMOTIVOREVERSAREMESA() {
        return MOTIVOREVERSAREMESA;
    }

    public void setMOTIVOREVERSAREMESA(String MOTIVOREVERSAREMESA) {
        this.MOTIVOREVERSAREMESA = MOTIVOREVERSAREMESA;
    }

    public String getCODMUNICIPIOTRANSBORDO() {
        return CODMUNICIPIOTRANSBORDO;
    }

    public void setCODMUNICIPIOTRANSBORDO(String CODMUNICIPIOTRANSBORDO) {
        this.CODMUNICIPIOTRANSBORDO = CODMUNICIPIOTRANSBORDO;
    }

    public String getMOTIVOTRANSBORDOREMESA() {
        return MOTIVOTRANSBORDOREMESA;
    }

    public void setMOTIVOTRANSBORDOREMESA(String MOTIVOTRANSBORDOREMESA) {
        this.MOTIVOTRANSBORDOREMESA = MOTIVOTRANSBORDOREMESA;
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
