package com.enerfrisoft.error;

public class Error {

    private String CLASE;
    private String METODO;
    private String LINEA;
    private String MENSAJE;
    
    public Error(Exception e) {
        CLASE = e.getStackTrace()[0].getClassName();
        METODO = e.getStackTrace()[0].getMethodName();
        LINEA = String.valueOf(e.getStackTrace()[0].getLineNumber());
        MENSAJE = String.valueOf(e.getLocalizedMessage());
    }

    public String getCLASE() {
        return CLASE;
    }

    public void setCLASE(String CLASE) {
        this.CLASE = CLASE;
    }

    public String getMETODO() {
        return METODO;
    }

    public void setMETODO(String METODO) {
        this.METODO = METODO;
    }

    public String getLINEA() {
        return LINEA;
    }

    public void setLINEA(String LINEA) {
        this.LINEA = LINEA;
    }

    public String getMENSAJE() {
        return MENSAJE;
    }

    public void setMENSAJE(String MENSAJE) {
        this.MENSAJE = MENSAJE;
    }

}
