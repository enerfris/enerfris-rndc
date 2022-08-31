/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.anularManifiesto;

/**
 *
 * @author sebastianaf
 */
public class AnularManifiesto {

    private String usuario;
    private String password;
    private String NUMNITEMPRESATRANSPORTE;
    private String NUMMANIFIESTOCARGA;
    private String MOTIVOANULACIONMANIFIESTO;
    private String OBSERVACIONES;
    private String userLog;
    private String dateLog;
    private String estado;
    private String ingresoId;

    public AnularManifiesto() {
        usuario = "";
        password = "";
        NUMNITEMPRESATRANSPORTE = "";
        NUMMANIFIESTOCARGA = "";
        MOTIVOANULACIONMANIFIESTO = "";
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

    public String getNUMMANIFIESTOCARGA() {
        return NUMMANIFIESTOCARGA;
    }

    public void setNUMMANIFIESTOCARGA(String NUMMANIFIESTOCARGA) {
        this.NUMMANIFIESTOCARGA = NUMMANIFIESTOCARGA;
    }

    public String getMOTIVOANULACIONMANIFIESTO() {
        return MOTIVOANULACIONMANIFIESTO;
    }

    public void setMOTIVOANULACIONMANIFIESTO(String MOTIVOANULACIONMANIFIESTO) {
        this.MOTIVOANULACIONMANIFIESTO = MOTIVOANULACIONMANIFIESTO;
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
