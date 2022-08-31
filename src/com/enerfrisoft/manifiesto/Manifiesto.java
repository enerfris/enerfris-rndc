/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.manifiesto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sebastianaf
 */
public class Manifiesto {

    private String Usuario;
    private String Password;
    private String NUMNITEMPRESATRANSPORTE;
    private String NUMMANIFIESTOCARGA;
    private String NUMORDENCARGUE;
    private String CONSECUTIVOINFORMACIONVIAJE;
    private String MANNROMANIFIESTOTRANSBORDO;
    private String CODOPERACIONTRANSPORTE;
    private Date FECHAEXPEDICIONMANIFIESTO;
    private String CODMUNICIPIOORIGENMANIFIESTO;
    private String CODMUNICIPIODESTINOMANIFIESTO;
    private String CODIDTITULARMANIFIESTO;
    private String NUMIDTITULARMANIFIESTO;
    private String CODSEDETITULARMANIFIESTO;
    private String NUMPLACA;
    private String CODCONFIGURACIONRESULTANTE;
    private String NUMPLACAREMOLQUE1;
    private String PESOVEHICULOVACIOREMOLQUE1;
    private String NUMPLACAREMOLQUE2;
    private String PESOVEHICULOVACIOREMOLQUE2;
    private String CODIDCONDUCTOR;
    private String NUMIDCONDUCTOR;
    private String CODIDCONDUCTOR2;
    private String NUMIDCONDUCTOR2;
    private String VALORFLETEPACTADOVIAJE;
    private String VALORFLETEPACTADOVIAJELETRAS;
    private String ORETENCIONFUENTEMANIFIEST;
    private String RETENCIONICAMANIFIESTOCARGA;
    private String RETENCIONICAMANIFIESTOCARGAVALOR;
    private String VALORANTICIPOMANIFIESTO;
    private String SALDOAPAGAR;
    private String NETOAPAGAR;
    private String CODMUNICIPIOPAGOSALDO;
    private String CODRESPONSABLEPAGOCARGUE;
    private String CODRESPONSABLEPAGODESCARGUE;
    private Date FECHAPAGOSALDOMANIFIESTO;
    private String OBSERVACIONES;
    private ArrayList<String> REMESASMAN;
    private String SEGURIDADQR;
    private String ingresoId;
    private String userLog;
    private String dateLog;
    private String estado;

    public Manifiesto() {
        Usuario = "";
        Password = "";
        NUMNITEMPRESATRANSPORTE = "";
        NUMMANIFIESTOCARGA = "";
        NUMORDENCARGUE = "";
        CONSECUTIVOINFORMACIONVIAJE = "";
        MANNROMANIFIESTOTRANSBORDO = "";
        CODOPERACIONTRANSPORTE = "";
        FECHAEXPEDICIONMANIFIESTO = null;
        CODMUNICIPIOORIGENMANIFIESTO = "";
        CODMUNICIPIODESTINOMANIFIESTO = "";
        CODIDTITULARMANIFIESTO = "";
        NUMIDTITULARMANIFIESTO = "";
        CODSEDETITULARMANIFIESTO = "";
        NUMPLACA = "";
        CODCONFIGURACIONRESULTANTE = "";
        NUMPLACAREMOLQUE1 = "";
        PESOVEHICULOVACIOREMOLQUE1 = "";
        NUMPLACAREMOLQUE2 = "";
        PESOVEHICULOVACIOREMOLQUE2 = "";
        CODIDCONDUCTOR = "";
        NUMIDCONDUCTOR = "";
        CODIDCONDUCTOR2 = "";
        NUMIDCONDUCTOR2 = "";
        VALORFLETEPACTADOVIAJE = "";
        VALORFLETEPACTADOVIAJELETRAS = "";
        ORETENCIONFUENTEMANIFIEST = "";
        RETENCIONICAMANIFIESTOCARGA = "";
        RETENCIONICAMANIFIESTOCARGAVALOR = "";
        VALORANTICIPOMANIFIESTO = "";
        SALDOAPAGAR = "";
        NETOAPAGAR = "";
        CODMUNICIPIOPAGOSALDO = "";
        CODRESPONSABLEPAGOCARGUE = "";
        CODRESPONSABLEPAGODESCARGUE = "";
        FECHAPAGOSALDOMANIFIESTO = null;
        OBSERVACIONES = "";
        SEGURIDADQR = "";
        ingresoId = "";
        REMESASMAN = new ArrayList<>();
        userLog = "";
        dateLog = "";
        estado = "";
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

    public String getNUMMANIFIESTOCARGA() {
        return NUMMANIFIESTOCARGA;
    }

    public void setNUMMANIFIESTOCARGA(String NUMMANIFIESTOCARGA) {
        this.NUMMANIFIESTOCARGA = NUMMANIFIESTOCARGA;
    }

    public String getNUMORDENCARGUE() {
        return NUMORDENCARGUE;
    }

    public void setNUMORDENCARGUE(String NUMORDENCARGUE) {
        this.NUMORDENCARGUE = NUMORDENCARGUE;
    }

    public String getCONSECUTIVOINFORMACIONVIAJE() {
        return CONSECUTIVOINFORMACIONVIAJE;
    }

    public void setCONSECUTIVOINFORMACIONVIAJE(String CONSECUTIVOINFORMACIONVIAJE) {
        this.CONSECUTIVOINFORMACIONVIAJE = CONSECUTIVOINFORMACIONVIAJE;
    }

    public String getMANNROMANIFIESTOTRANSBORDO() {
        return MANNROMANIFIESTOTRANSBORDO;
    }

    public void setMANNROMANIFIESTOTRANSBORDO(String MANNROMANIFIESTOTRANSBORDO) {
        this.MANNROMANIFIESTOTRANSBORDO = MANNROMANIFIESTOTRANSBORDO;
    }

    public String getCODOPERACIONTRANSPORTE() {
        return CODOPERACIONTRANSPORTE;
    }

    public void setCODOPERACIONTRANSPORTE(String CODOPERACIONTRANSPORTE) {
        this.CODOPERACIONTRANSPORTE = CODOPERACIONTRANSPORTE;
    }

    public Date getFECHAEXPEDICIONMANIFIESTO() {
        return FECHAEXPEDICIONMANIFIESTO;
    }

    public void setFECHAEXPEDICIONMANIFIESTO(Date FECHAEXPEDICIONMANIFIESTO) {
        this.FECHAEXPEDICIONMANIFIESTO = FECHAEXPEDICIONMANIFIESTO;
    }

    public String getCODMUNICIPIOORIGENMANIFIESTO() {
        return CODMUNICIPIOORIGENMANIFIESTO;
    }

    public void setCODMUNICIPIOORIGENMANIFIESTO(String CODMUNICIPIOORIGENMANIFIESTO) {
        this.CODMUNICIPIOORIGENMANIFIESTO = CODMUNICIPIOORIGENMANIFIESTO;
    }

    public String getCODMUNICIPIODESTINOMANIFIESTO() {
        return CODMUNICIPIODESTINOMANIFIESTO;
    }

    public void setCODMUNICIPIODESTINOMANIFIESTO(String CODMUNICIPIODESTINOMANIFIESTO) {
        this.CODMUNICIPIODESTINOMANIFIESTO = CODMUNICIPIODESTINOMANIFIESTO;
    }

    public String getCODIDTITULARMANIFIESTO() {
        return CODIDTITULARMANIFIESTO;
    }

    public void setCODIDTITULARMANIFIESTO(String CODIDTITULARMANIFIESTO) {
        this.CODIDTITULARMANIFIESTO = CODIDTITULARMANIFIESTO;
    }

    public String getNUMIDTITULARMANIFIESTO() {
        return NUMIDTITULARMANIFIESTO;
    }

    public void setNUMIDTITULARMANIFIESTO(String NUMIDTITULARMANIFIESTO) {
        this.NUMIDTITULARMANIFIESTO = NUMIDTITULARMANIFIESTO;
    }

    public String getCODSEDETITULARMANIFIESTO() {
        return CODSEDETITULARMANIFIESTO;
    }

    public void setCODSEDETITULARMANIFIESTO(String CODSEDETITULARMANIFIESTO) {
        this.CODSEDETITULARMANIFIESTO = CODSEDETITULARMANIFIESTO;
    }

    public String getNUMPLACA() {
        return NUMPLACA;
    }

    public void setNUMPLACA(String NUMPLACA) {
        this.NUMPLACA = NUMPLACA;
    }

    public String getCODCONFIGURACIONRESULTANTE() {
        return CODCONFIGURACIONRESULTANTE;
    }

    public void setCODCONFIGURACIONRESULTANTE(String CODCONFIGURACIONRESULTANTE) {
        this.CODCONFIGURACIONRESULTANTE = CODCONFIGURACIONRESULTANTE;
    }

    public String getNUMPLACAREMOLQUE1() {
        return NUMPLACAREMOLQUE1;
    }

    public void setNUMPLACAREMOLQUE1(String NUMPLACAREMOLQUE1) {
        this.NUMPLACAREMOLQUE1 = NUMPLACAREMOLQUE1;
    }

    public String getPESOVEHICULOVACIOREMOLQUE1() {
        return PESOVEHICULOVACIOREMOLQUE1;
    }

    public void setPESOVEHICULOVACIOREMOLQUE1(String PESOVEHICULOVACIOREMOLQUE1) {
        this.PESOVEHICULOVACIOREMOLQUE1 = PESOVEHICULOVACIOREMOLQUE1;
    }

    public String getNUMPLACAREMOLQUE2() {
        return NUMPLACAREMOLQUE2;
    }

    public void setNUMPLACAREMOLQUE2(String NUMPLACAREMOLQUE2) {
        this.NUMPLACAREMOLQUE2 = NUMPLACAREMOLQUE2;
    }

    public String getPESOVEHICULOVACIOREMOLQUE2() {
        return PESOVEHICULOVACIOREMOLQUE2;
    }

    public void setPESOVEHICULOVACIOREMOLQUE2(String PESOVEHICULOVACIOREMOLQUE2) {
        this.PESOVEHICULOVACIOREMOLQUE2 = PESOVEHICULOVACIOREMOLQUE2;
    }

    public String getCODIDCONDUCTOR() {
        return CODIDCONDUCTOR;
    }

    public void setCODIDCONDUCTOR(String CODIDCONDUCTOR) {
        this.CODIDCONDUCTOR = CODIDCONDUCTOR;
    }

    public String getNUMIDCONDUCTOR() {
        return NUMIDCONDUCTOR;
    }

    public void setNUMIDCONDUCTOR(String NUMIDCONDUCTOR) {
        this.NUMIDCONDUCTOR = NUMIDCONDUCTOR;
    }

    public String getCODIDCONDUCTOR2() {
        return CODIDCONDUCTOR2;
    }

    public void setCODIDCONDUCTOR2(String CODIDCONDUCTOR2) {
        this.CODIDCONDUCTOR2 = CODIDCONDUCTOR2;
    }

    public String getNUMIDCONDUCTOR2() {
        return NUMIDCONDUCTOR2;
    }

    public void setNUMIDCONDUCTOR2(String NUMIDCONDUCTOR2) {
        this.NUMIDCONDUCTOR2 = NUMIDCONDUCTOR2;
    }

    public String getVALORFLETEPACTADOVIAJE() {
        return VALORFLETEPACTADOVIAJE;
    }

    public void setVALORFLETEPACTADOVIAJE(String VALORFLETEPACTADOVIAJE) {
        this.VALORFLETEPACTADOVIAJE = VALORFLETEPACTADOVIAJE;
    }

    public String getVALORFLETEPACTADOVIAJELETRAS() {
        return VALORFLETEPACTADOVIAJELETRAS;
    }

    public void setVALORFLETEPACTADOVIAJELETRAS(String VALORFLETEPACTADOVIAJELETRAS) {
        this.VALORFLETEPACTADOVIAJELETRAS = VALORFLETEPACTADOVIAJELETRAS;
    }

    public String getORETENCIONFUENTEMANIFIEST() {
        return ORETENCIONFUENTEMANIFIEST;
    }

    public void setORETENCIONFUENTEMANIFIEST(String ORETENCIONFUENTEMANIFIEST) {
        this.ORETENCIONFUENTEMANIFIEST = ORETENCIONFUENTEMANIFIEST;
    }

    public String getRETENCIONICAMANIFIESTOCARGA() {
        return RETENCIONICAMANIFIESTOCARGA;
    }

    public void setRETENCIONICAMANIFIESTOCARGA(String RETENCIONICAMANIFIESTOCARGA) {
        this.RETENCIONICAMANIFIESTOCARGA = RETENCIONICAMANIFIESTOCARGA;
    }

    public String getRETENCIONICAMANIFIESTOCARGAVALOR() {
        return RETENCIONICAMANIFIESTOCARGAVALOR;
    }

    public void setRETENCIONICAMANIFIESTOCARGAVALOR(String RETENCIONICAMANIFIESTOCARGAVALOR) {
        this.RETENCIONICAMANIFIESTOCARGAVALOR = RETENCIONICAMANIFIESTOCARGAVALOR;
    }

    public String getVALORANTICIPOMANIFIESTO() {
        return VALORANTICIPOMANIFIESTO;
    }

    public void setVALORANTICIPOMANIFIESTO(String VALORANTICIPOMANIFIESTO) {
        this.VALORANTICIPOMANIFIESTO = VALORANTICIPOMANIFIESTO;
    }

    public String getSALDOAPAGAR() {
        return SALDOAPAGAR;
    }

    public void setSALDOAPAGAR(String SALDOAPAGAR) {
        this.SALDOAPAGAR = SALDOAPAGAR;
    }

    public String getNETOAPAGAR() {
        return NETOAPAGAR;
    }

    public void setNETOAPAGAR(String NETOAPAGAR) {
        this.NETOAPAGAR = NETOAPAGAR;
    }

    public String getCODMUNICIPIOPAGOSALDO() {
        return CODMUNICIPIOPAGOSALDO;
    }

    public void setCODMUNICIPIOPAGOSALDO(String CODMUNICIPIOPAGOSALDO) {
        this.CODMUNICIPIOPAGOSALDO = CODMUNICIPIOPAGOSALDO;
    }

    public String getCODRESPONSABLEPAGOCARGUE() {
        return CODRESPONSABLEPAGOCARGUE;
    }

    public void setCODRESPONSABLEPAGOCARGUE(String CODRESPONSABLEPAGOCARGUE) {
        this.CODRESPONSABLEPAGOCARGUE = CODRESPONSABLEPAGOCARGUE;
    }

    public String getCODRESPONSABLEPAGODESCARGUE() {
        return CODRESPONSABLEPAGODESCARGUE;
    }

    public void setCODRESPONSABLEPAGODESCARGUE(String CODRESPONSABLEPAGODESCARGUE) {
        this.CODRESPONSABLEPAGODESCARGUE = CODRESPONSABLEPAGODESCARGUE;
    }

    public Date getFECHAPAGOSALDOMANIFIESTO() {
        return FECHAPAGOSALDOMANIFIESTO;
    }

    public void setFECHAPAGOSALDOMANIFIESTO(Date FECHAPAGOSALDOMANIFIESTO) {
        this.FECHAPAGOSALDOMANIFIESTO = FECHAPAGOSALDOMANIFIESTO;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public ArrayList<String> getREMESASMAN() {
        return REMESASMAN;
    }

    public void setREMESASMAN(ArrayList<String> REMESASMAN) {
        this.REMESASMAN = REMESASMAN;
    }

    public String getSEGURIDADQR() {
        return SEGURIDADQR;
    }

    public void setSEGURIDADQR(String SEGURIDADQR) {
        this.SEGURIDADQR = SEGURIDADQR;
    }

    public String getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(String ingresoId) {
        this.ingresoId = ingresoId;
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
    
    
}
