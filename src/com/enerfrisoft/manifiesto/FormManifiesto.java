/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.manifiesto;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.error.QueryError;
import com.enerfrisoft.modal.Modal;
import com.enerfrisoft.openForm.OpenForm;
import com.enerfrisoft.remesa.RecordRemesa;
import com.enerfrisoft.tools.Data;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.Visual;
import com.enerfrisoft.tools.n2t;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Usuario
 */
public class FormManifiesto extends javax.swing.JFrame {

    private String username;
    private Boolean allowRecords;
    private TextAutoCompleter acCODMUNICIPIOORIGENMANIFIESTO;
    private TextAutoCompleter acCODMUNICIPIODESTINOMANIFIESTO;
    private TextAutoCompleter acNUMIDTITULARMANIFIESTO;
    private TextAutoCompleter acNUMPLACA;
    private TextAutoCompleter acNUMPLACAREMOLQUE1;
    private TextAutoCompleter acNUMPLACAREMOLQUE2;
    private TextAutoCompleter acNUMIDCONDUCTOR;
    private TextAutoCompleter acNUMIDCONDUCTOR2;
    private TextAutoCompleter acMUNICIPIOPAGOSALDO;
    private ArrayList<String> consecutivos;
    private Connection conn;
    private Connection defaultServerConn;
    private DataSourceClass dataSource;
    

    /**
     * Creates new form FormManifiesto
     */
    public FormManifiesto(String username, DataSourceClass dataSource,Connection conn) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa",conn));
        consecutivos = new ArrayList<>();
        this.username = username;
        this.allowRecords = true;
        initComponents();
        buildAutoCompleterNUMIDTITULARMANIFIESTO();
        buildAutoCompleterCODMUNICIPIOMANIFIESTO();
        buildAutoCompleterNUMPLACAVEHICULOCARGA();
        buildAutoCompleterNUMPLACAREMOLQUE1();
        buildAutoCompleterNUMPLACAREMOLQUE2();
        buildAutoCompleterNUMIDCONDUCTOR();
        buildAutoCompleterNUMIDCONDUCTOR2();
        buildAutoCompleterMUNICIPIOPAGOSALDO();
        NUMMANIFIESTOCARGA.setText(UniversalDAO.getString(""
                + "select concat(conPalMan,conNumMan) "
                + "from server "
                + "where server.id='" + String.valueOf(server) + "'", this.defaultServerConn));
        NUMORDENCARGUE.setText(UniversalDAO.getString(""
                + "select concat(conPalOC,conNumOC) "
                + "from server "
                + "where server.id='" + String.valueOf(server) + "'", this.defaultServerConn));
        Visual.windowIcon(this);
        verifyConsecutivos();
    }
    
    private void verifyConsecutivos(){
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa "
                + "where id = '1'",conn));
        int valueConNumMan = Integer.valueOf(UniversalDAO.getString(""
                + "select conNumMan "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        int valueMaxConNumMan = Integer.valueOf(UniversalDAO.getString(""
                + "select maxconNumMan "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        int valueAvisoConNumMan = Integer.valueOf(UniversalDAO.getString(""
                + "select avisoconNumMan "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        
        if(valueConNumMan < valueMaxConNumMan){
            if(valueMaxConNumMan-valueAvisoConNumMan <= valueConNumMan){
                Modal.show("Alerta", "Queda(n) " + String.valueOf(valueMaxConNumMan - valueConNumMan) + " consecutivo(s) disponible(s) para manifiestos", this, "", "warning");
            }
        }else{
            Modal.show("Error", "No hay consecutivos disponibles para manifiestos", this, "", "error");
            this.allowRecords = false;
        }
        
        int valueConNumOC = Integer.valueOf(UniversalDAO.getString(""
                + "select conNumOC "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        int valueMaxConNumOC = Integer.valueOf(UniversalDAO.getString(""
                + "select maxconNumOC "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        int valueAvisoConNumOC = Integer.valueOf(UniversalDAO.getString(""
                + "select avisoconNumOC "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn));
        
        if(valueConNumOC < valueMaxConNumOC){
            if(valueMaxConNumOC-valueAvisoConNumOC <= valueConNumOC){
                Modal.show("Alerta", "Queda(n) " + String.valueOf(valueMaxConNumOC - valueConNumOC) + " consecutivo(s) disponible(s) para ordenes de carga", this, "", "warning");
            }
        }else{
            Modal.show("Error", "No hay consecutivos disponibles para ordenes de carga", this, "", "error");
            this.allowRecords = false;
        }
    }

    private ComboBoxModel getRemesas() {
        DefaultComboBoxModel combo = UniversalDAO.getComboBoxModel(""
                + "select concat (remesa.CONSECUTIVOREMESA,' - ',remesa.DESCRIPCIONCORTAPRODUCTO,' - ',remesa.CANTIDADCARGADA,' ',unidad_medida.nombre)\n"
                + "from remesa inner join unidad_medida on remesa.UNIDADMEDIDACAPACIDAD = unidad_medida.cod\n"
                + "where remesa.estado = 'activo' and remesa.manifiestoConsecutivo is NULL", conn);
        return combo;
    }

    private Manifiesto getValues() {
        Manifiesto manifiesto = new Manifiesto();
        Data.auth(manifiesto, conn);
        manifiesto.setNUMMANIFIESTOCARGA(NUMMANIFIESTOCARGA.getText());
        manifiesto.setNUMORDENCARGUE(NUMORDENCARGUE.getText());
        manifiesto.setCODOPERACIONTRANSPORTE(CODOPERACIONTRANSPORTE.getText());
        try {
            manifiesto.setFECHAEXPEDICIONMANIFIESTO(FECHAEXPEDICIONMANIFIESTO.getDate());
        } catch (Exception e) {
            manifiesto.setFECHAEXPEDICIONMANIFIESTO(null);
        }
        manifiesto.setCODMUNICIPIOORIGENMANIFIESTO(CODMUNICIPIOORIGENMANIFIESTO.getText());
        manifiesto.setCODMUNICIPIODESTINOMANIFIESTO(CODMUNICIPIODESTINOMANIFIESTO.getText());
        manifiesto.setCODIDTITULARMANIFIESTO(CODIDTITULARMANIFIESTO.getText());
        manifiesto.setNUMIDTITULARMANIFIESTO(NUMIDTITULARMANIFIESTO.getText());
        manifiesto.setCODSEDETITULARMANIFIESTO(CODSEDETITULARMANIFIESTO.getText());

        manifiesto.setNUMPLACA(NUMPLACA.getText());
        manifiesto.setCODCONFIGURACIONRESULTANTE(CODCONFIGURACIONRESULTANTE.getText());
        manifiesto.setNUMPLACAREMOLQUE1(NUMPLACAREMOLQUE1.getText());
        manifiesto.setPESOVEHICULOVACIOREMOLQUE1(PESOVEHICULOVACIOREMOLQUE1.getText());
        manifiesto.setNUMPLACAREMOLQUE2(NUMPLACAREMOLQUE2.getText());
        manifiesto.setPESOVEHICULOVACIOREMOLQUE2(PESOVEHICULOVACIOREMOLQUE2.getText());
        manifiesto.setCODIDCONDUCTOR(CODIDCONDUCTOR.getText());
        manifiesto.setNUMIDCONDUCTOR(NUMIDCONDUCTOR.getText());
        manifiesto.setCODIDCONDUCTOR2(CODIDCONDUCTOR2.getText());
        manifiesto.setNUMIDCONDUCTOR2(NUMIDCONDUCTOR2.getText());
        manifiesto.setVALORFLETEPACTADOVIAJE(VALORFLETEPACTADOVIAJE.getText());
        n2t converter = new n2t();
        manifiesto.setVALORFLETEPACTADOVIAJELETRAS(
                converter.convertirLetras(Integer.valueOf(VALORFLETEPACTADOVIAJE.getText()))
        );
        manifiesto.setRETENCIONICAMANIFIESTOCARGA(
                RETENCIONICAMANIFIESTOCARGA_U.getValue().toString()
                + "."
                + RETENCIONICAMANIFIESTOCARGA_D.getValue().toString()
        );
        manifiesto.setRETENCIONICAMANIFIESTOCARGAVALOR(retencionICA.getText());
        manifiesto.setORETENCIONFUENTEMANIFIEST(retencionEnLaFuente.getText());
        manifiesto.setVALORANTICIPOMANIFIESTO(VALORANTICIPOMANIFIESTO.getText());
        manifiesto.setNETOAPAGAR(netoPagar.getText());
        manifiesto.setSALDOAPAGAR(saldoPorPagar.getText());
        try {
            manifiesto.setFECHAPAGOSALDOMANIFIESTO(FECHAPAGOSALDOMANIFIESTO.getDate());
        } catch (Exception e) {
            manifiesto.setFECHAPAGOSALDOMANIFIESTO(null);
        }
        manifiesto.setCODRESPONSABLEPAGOCARGUE(CODRESPONSABLEPAGOCARGUE.getText());
        manifiesto.setCODRESPONSABLEPAGODESCARGUE(CODRESPONSABLEPAGODESCARGUE.getText());
        manifiesto.setOBSERVACIONES(OBSERVACIONES.getText());
        manifiesto.setREMESASMAN(consecutivos);
        manifiesto.setUserLog(username);
        manifiesto.setEstado("activo");
        manifiesto.setCODMUNICIPIOPAGOSALDO(CODMUNICIPIOPAGOSALDO.getText());

        return manifiesto;
    }

    private void buildAutoCompleterMUNICIPIOPAGOSALDO() {
        acMUNICIPIOPAGOSALDO = new TextAutoCompleter(MUNICIPIOPAGOSALDO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String codMUNICIPIOPAGOSALDO = UniversalDAO.getString(""
                        + "select id "
                        + "from municipio "
                        + "where nombre='" + o.toString() + "'", conn);
                CODMUNICIPIOPAGOSALDO.setText(codMUNICIPIOPAGOSALDO);
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select nombre "
                + "from municipio", conn);
        for (int i = 0; i < data.size(); i++) {
            acMUNICIPIOPAGOSALDO.addItem(data.get(i));
        }
    }

    private void buildAutoCompleterNUMIDCONDUCTOR() {
        acNUMIDCONDUCTOR = new TextAutoCompleter(NUMIDCONDUCTOR, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String nombreCompleto = UniversalDAO.getString(""
                        + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                        + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
                String sNOMENCLATURADIRECCIONCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.NOMENCLATURADIRECCION "
                        + "from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sTELEFONOCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.NUMTELEFONOCONTACTO "
                        + " from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sCODMUNICIPIOCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.CODMUNICIPIORNDC "
                        + "from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sMUNICIPIOCONDUCTOR = UniversalDAO.getString(""
                        + "select municipio.nombre "
                        + "from municipio "
                        + "where municipio.id ='" + sCODMUNICIPIOCONDUCTOR + "'", conn);
                String sCATEGORIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.CODCATEGORIALICENCIACONDUCCION "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sNUMLICENCIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.NUMLICENCIACONDUCCION "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sVENCIMIENTOLICENCIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.FECHAVENCIMIENTOLICENCIA "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                try {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formato.parse(sVENCIMIENTOLICENCIACONDUCTOR);
                    SimpleDateFormat formato2 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                    sVENCIMIENTOLICENCIACONDUCTOR = formato2.format(date);
                } catch (Exception e) {
                    System.out.println("date parsing exception");
                }

                NOMCONDUCTOR.setText(nombreCompleto);
                NOMENCLATURADIRECCIONCONDUCTOR.setText(sNOMENCLATURADIRECCIONCONDUCTOR);
                TELEFONOCONDUCTOR.setText(sTELEFONOCONDUCTOR);
                CODMUNICIPIOCONDUCTOR.setText(sCODMUNICIPIOCONDUCTOR);
                MUNICIPIOCONDUCTOR.setText(sMUNICIPIOCONDUCTOR);
                CATEGORIACONDUCTOR.setText(sCATEGORIACONDUCTOR);
                NUMLICENCIACONDUCTOR.setText(sNUMLICENCIACONDUCTOR);
                VENCIMIENTOLICENCIACONDUCTOR.setText(sVENCIMIENTOLICENCIACONDUCTOR);

            }
        });

    }

    private void buildAutoCompleterNUMIDCONDUCTOR2() {
        acNUMIDCONDUCTOR2 = new TextAutoCompleter(NUMIDCONDUCTOR2, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String nombreCompleto = UniversalDAO.getString(""
                        + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                        + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
                String sNOMENCLATURADIRECCIONCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.NOMENCLATURADIRECCION "
                        + "from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sTELEFONOCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.NUMTELEFONOCONTACTO "
                        + " from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sCODMUNICIPIOCONDUCTOR = UniversalDAO.getString(""
                        + "select sede.CODMUNICIPIORNDC "
                        + "from sede inner join tercero "
                        + "on sede.NUMIDTERCERO = tercero.NUMIDTERCERO "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sMUNICIPIOCONDUCTOR = UniversalDAO.getString(""
                        + "select municipio.nombre "
                        + "from municipio "
                        + "where municipio.id ='" + sCODMUNICIPIOCONDUCTOR + "'", conn);
                String sCATEGORIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.CODCATEGORIALICENCIACONDUCCION "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sNUMLICENCIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.NUMLICENCIACONDUCCION "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                String sVENCIMIENTOLICENCIACONDUCTOR = UniversalDAO.getString(""
                        + "select tercero.FECHAVENCIMIENTOLICENCIA "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO='" + o.toString() + "'", conn);
                try {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = formato.parse(sVENCIMIENTOLICENCIACONDUCTOR);
                    formato = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                    sVENCIMIENTOLICENCIACONDUCTOR = formato.format(date);
                } catch (Exception e) {
                }

                NOMCONDUCTOR2.setText(nombreCompleto);
                NOMENCLATURADIRECCIONCONDUCTOR2.setText(sNOMENCLATURADIRECCIONCONDUCTOR);
                TELEFONOCONDUCTOR2.setText(sTELEFONOCONDUCTOR);
                CODMUNICIPIOCONDUCTOR2.setText(sCODMUNICIPIOCONDUCTOR);
                MUNICIPIOCONDUCTOR2.setText(sMUNICIPIOCONDUCTOR);
                CATEGORIACONDUCTOR2.setText(sCATEGORIACONDUCTOR);
                NUMLICENCIACONDUCTOR2.setText(sNUMLICENCIACONDUCTOR);
                VENCIMIENTOLICENCIACONDUCTOR2.setText(sVENCIMIENTOLICENCIACONDUCTOR);

            }
        });

    }

    private void buildAutoCompleterNUMPLACAREMOLQUE1() {
        acNUMPLACAREMOLQUE1 = new TextAutoCompleter(NUMPLACAREMOLQUE1, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                fillREMOLQUE(MARCAREMOLQUE1,
                        CODMARCAREMOLQUE1,
                        CONFIGURACIONREMOLQUE1,
                        CODCONFIGURACIONREMOLQUE1,
                        PESOVEHICULOVACIOREMOLQUE1,
                        UNIDADCAPACIDADREMOLQUE1,
                        o.toString());
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select remolque.NUMPLACA "
                + "from remolque", conn);
        for (int i = 0; i < data.size(); i++) {
            acNUMPLACAREMOLQUE1.addItem(data.get(i));
        }
    }

    private void buildAutoCompleterNUMPLACAREMOLQUE2() {
        acNUMPLACAREMOLQUE2 = new TextAutoCompleter(NUMPLACAREMOLQUE2, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                fillREMOLQUE(MARCAREMOLQUE2,
                        CODMARCAREMOLQUE2,
                        CONFIGURACIONREMOLQUE2,
                        CODCONFIGURACIONREMOLQUE2,
                        PESOVEHICULOVACIOREMOLQUE2,
                        UNIDADCAPACIDADREMOLQUE2,
                        o.toString());
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select remolque.NUMPLACA "
                + "from remolque", conn);
        for (int i = 0; i < data.size(); i++) {
            acNUMPLACAREMOLQUE2.addItem(data.get(i));
        }
    }

    private void buildAutoCompleterNUMPLACAVEHICULOCARGA() {
        acNUMPLACA = new TextAutoCompleter(NUMPLACA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String marca = UniversalDAO.getString(""
                        + "select marca_vehiculo.nombre "
                        + "from vehiculo inner join marca_vehiculo "
                        + "on vehiculo.CODMARCAVEHICULOCARGA = marca_vehiculo.cod "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String codMarca = UniversalDAO.getString(""
                        + "select CODMARCAVEHICULOCARGA "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "'", conn);
                String configuracionVehiculoCarga = UniversalDAO.getString(""
                        + "select configuracion_vehiculo.descripcion "
                        + "from configuracion_vehiculo inner join vehiculo "
                        + "on configuracion_vehiculo.cod = vehiculo.CODCONFIGURACIONUNIDADCARGA "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String sPESOVACIOVEHICULOCARGA = UniversalDAO.getString(""
                        + "select vehiculo.PESOVEHICULOVACIO "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "'", conn);
                String sUNIDADMEDIDACAPACIDAD = "Kilogramos";
                String sANOFABRICACIONVEHICULOCARGA = UniversalDAO.getString(""
                        + "select vehiculo.ANOFABRICACIONVEHICULOCARGA "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "'", conn);
                String sTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select concat "
                        + "(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                        + "from tercero "
                        + "where tercero.NUMIDTERCERO = ("
                        + "select vehiculo.NUMIDPROPIETARIO "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "')", conn);
                String sNUMIDTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select vehiculo.NUMIDTENEDOR "
                        + "from vehiculo "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String sCODTIPOIDTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select vehiculo.CODTIPOIDTENEDOR "
                        + "from vehiculo "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String sNOMENCLATURADIRECCIONTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select sede.NOMENCLATURADIRECCION "
                        + "from vehiculo inner join sede "
                        + "on vehiculo.NUMIDTENEDOR = sede.NUMIDTERCERO "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String sMUNICIPIOTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select municipio.nombre "
                        + "from municipio "
                        + "where municipio.id = ("
                        + "select sede.CODMUNICIPIORNDC "
                        + "from sede inner join vehiculo "
                        + "on sede.NUMIDTERCERO = vehiculo.NUMIDTENEDOR "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "')", conn);
                String sCODMUNICIPIOTENEDORVEHICULOCARGA = UniversalDAO.getString(""
                        + "select sede.CODMUNICIPIORNDC "
                        + "from sede inner join vehiculo "
                        + "on sede.NUMIDTERCERO = vehiculo.NUMIDTENEDOR "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "'", conn);
                String sNUMSEGUROSOAT = UniversalDAO.getString(""
                        + "select vehiculo.NUMSEGUROSOAT "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "'", conn);
                String sFECHAVENCIMIENTOSOATVEHICULOCARGA = UniversalDAO.getString(""
                        + "select vehiculo.FECHAVENCIMIENTOSOAT "
                        + "from vehiculo "
                        + "where NUMPLACA='" + o.toString() + "'", conn);
                String sNOMASEGURADORASOAT = UniversalDAO.getString(""
                        + "select aseguradora.nombre "
                        + "from aseguradora "
                        + "where id=("
                        + "select vehiculo.NUMNITASEGURADORASOAT "
                        + "from vehiculo "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "')", conn);
                String sCODCONFIGURACIONRESULTANTE = UniversalDAO.getString(""
                        + "select configuracion_vehiculo.nombre "
                        + "from configuracion_vehiculo "
                        + "where configuracion_vehiculo.cod = ("
                        + "select vehiculo.CODCONFIGURACIONUNIDADCARGA "
                        + "from vehiculo "
                        + "where vehiculo.NUMPLACA='" + o.toString() + "')", conn);

                MARCAVEHICULOCARGA.setText(marca);
                CODMARCAVEHICULOCARGA.setText(codMarca);
                CONFIGURACIONVEHICULOCARGA.setText(configuracionVehiculoCarga);
                PESOVACIOVEHICULOCARGA.setText(sPESOVACIOVEHICULOCARGA);
                UNIDADCAPACIDADVEHICULOCARGA.setText(sUNIDADMEDIDACAPACIDAD);
                ANOFABRICACIONVEHICULOCARGA.setText(sANOFABRICACIONVEHICULOCARGA);
                TENEDORVEHICULOCARGA.setText(sTENEDORVEHICULOCARGA);
                NUMIDTENEDORVEHICULOCARGA.setText(sNUMIDTENEDORVEHICULOCARGA);
                CODTIPOIDTENEDORVEHICULOCARGA.setText(sCODTIPOIDTENEDORVEHICULOCARGA);
                NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA.setText(sNOMENCLATURADIRECCIONTENEDORVEHICULOCARGA);
                MUNICIPIOTENEDORVEHICULOCARGA.setText(sMUNICIPIOTENEDORVEHICULOCARGA);
                CODMUNICIPIOTENEDORVEHICULOCARGA.setText(sCODMUNICIPIOTENEDORVEHICULOCARGA);
                NUMSEGUROSOAT.setText(sNUMSEGUROSOAT);
                FECHAVENCIMIENTOSOATVEHICULOCARGA.setText(sFECHAVENCIMIENTOSOATVEHICULOCARGA);
                NOMASEGURADORASOAT.setText(sNOMASEGURADORASOAT);
                CLEARDATOSREMOLQUE1();
                CLEARDATOSREMOLQUE2();
                CODCONFIGURACIONRESULTANTE.setText(sCODCONFIGURACIONRESULTANTE);
            }
        });
        fillNUMPLACAVEHICULOCARGA();
    }

    private void fillNUMPLACAVEHICULOCARGA() {
        acNUMPLACA.removeAllItems();
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select vehiculo.NUMPLACA "
                + "from vehiculo", conn);
        for (int i = 0; i < data.size(); i++) {
            acNUMPLACA.addItem(data.get(i));
        }
    }

    private void buildAutoCompleterNUMIDTITULARMANIFIESTO() {
        acNUMIDTITULARMANIFIESTO = new TextAutoCompleter(NUMIDTITULARMANIFIESTO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String nombreCompleto = UniversalDAO.getString(""
                        + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                        + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
                NOMBREIDTITULARMANIFIESTO.setText(nombreCompleto);
                ArrayList<String> data = UniversalDAO.getArrayList(""
                        + "select concat(CODSEDETERCERO,' - ',NOMSEDETERCERO,' - ',NOMENCLATURADIRECCION) "
                        + "from sede where NUMIDTERCERO='" + o.toString() + "'", conn);
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                for (int i = 0; i < data.size(); i++) {
                    model.addElement(data.get(i));
                }
                SEDETITULARMANIFIESTO.setEnabled(true);
                SEDETITULARMANIFIESTO.setModel(model);
                SEDETITULARMANIFIESTO.setSelectedItem(null);
            }
        });
    }

    private void buildAutoCompleterCODMUNICIPIOMANIFIESTO() {
        acCODMUNICIPIOORIGENMANIFIESTO = new TextAutoCompleter(MUNICIPIOORIGENMANIFIESTO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                CODMUNICIPIOORIGENMANIFIESTO.setText(UniversalDAO.getString(""
                        + "select id "
                        + "from municipio "
                        + "where nombre='" + o.toString() + "'", conn));
            }
        });

        acCODMUNICIPIODESTINOMANIFIESTO = new TextAutoCompleter(MUNICIPIODESTINOMANIFIESTO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                CODMUNICIPIODESTINOMANIFIESTO.setText(UniversalDAO.getString(""
                        + "select id "
                        + "from municipio "
                        + "where nombre='" + o.toString() + "'", conn));
            }
        });

        ArrayList<String> municipios = ManifiestoDAO.getMunicipios(conn);
        for (int i = 0; i < municipios.size(); i++) {
            acCODMUNICIPIODESTINOMANIFIESTO.addItem(municipios.get(i));
            acCODMUNICIPIOORIGENMANIFIESTO.addItem(municipios.get(i));
        }
    }

    private void fillREMOLQUE(JTextField MARCAREMOLQUE,
            JTextField CODMARCAREMOLQUE1,
            JTextField CONFIGURACIONREMOLQUE,
            JTextField CODCONFIGURACIONREMOLQUE,
            JTextField PESOVEHICULOVACIOREMOLQUE,
            JTextField UNIDADCAPACIDADREMOLQUE,
            String NUMPLACA) {
        String sCODMARCAREMOLQUE = UniversalDAO.getString(""
                + "select remolque.CODMARCAVEHICULOCARGA "
                + "from remolque "
                + "where NUMPLACA='" + NUMPLACA + "'", conn);
        String sMARCAREMOLQUE = UniversalDAO.getString(""
                + "select marca_semiremolque.nombre "
                + "from marca_semiremolque "
                + "where marca_semiremolque.cod='" + sCODMARCAREMOLQUE + "'", conn);
        String sPESOVACIO = UniversalDAO.getString(""
                + "select remolque.PESOVEHICULOVACIO "
                + "from remolque "
                + "where NUMPLACA='" + NUMPLACA + "'", conn);
        String sUNIDADCAPACIDADREMOLQUE1 = "Kilogramos";
        String sCODCONFIGURACIONREMOLQUE = UniversalDAO.getString(""
                + "select remolque.CODCONFIGURACIONUNIDADCARGA "
                + "from remolque "
                + "where NUMPLACA='" + NUMPLACA + "'", conn);
        String sCONFIGURACIONREMOLQUE = UniversalDAO.getString(""
                + "select configuracion_vehiculo.descripcion "
                + "from configuracion_vehiculo "
                + "where configuracion_vehiculo.cod='" + sCODCONFIGURACIONREMOLQUE + "'", conn);
        String sNOMBRECONFIGURACIONREMOLQUE1 = UniversalDAO.getString(""
                + "select configuracion_vehiculo.nombre "
                + "from configuracion_vehiculo "
                + "where configuracion_vehiculo.cod='" + sCODCONFIGURACIONREMOLQUE + "'", conn);

        CODMARCAREMOLQUE1.setText(sCODMARCAREMOLQUE);
        MARCAREMOLQUE.setText(sMARCAREMOLQUE);
        PESOVEHICULOVACIOREMOLQUE.setText(sPESOVACIO);
        UNIDADCAPACIDADREMOLQUE.setText(sUNIDADCAPACIDADREMOLQUE1);
        CODCONFIGURACIONREMOLQUE.setText(sCODCONFIGURACIONREMOLQUE);
        CONFIGURACIONREMOLQUE.setText(sCONFIGURACIONREMOLQUE);

        String before = CODCONFIGURACIONRESULTANTE.getText();
        CODCONFIGURACIONRESULTANTE.setText(before + sNOMBRECONFIGURACIONREMOLQUE1);

    }

    private void FillNUMIDTERCERO(JComboBox TIPOIDTERCERO,
            TextAutoCompleter acNUMIDTERCERO,
            JTextField NUMIDTERCERO,
            JTextField NOMIDTERCERO,
            JTextField CODTIPOIDTERCERO
    ) {
        if (TIPOIDTERCERO.getSelectedItem() != null) {
            NUMIDTERCERO.setEditable(true);
            NUMIDTERCERO.setText("");
            NOMIDTERCERO.setText("");
            String TIPOIDTERCERO_value = TIPOIDTERCERO.getSelectedItem().toString();
            String COD = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_id "
                    + "where nombre='" + TIPOIDTERCERO_value + "';", conn);
            CODTIPOIDTERCERO.setText(COD);
            ArrayList<String> ids = UniversalDAO.getArrayList(""
                    + "select NUMIDTERCERO "
                    + "from tercero "
                    + "where CODTIPOIDTERCERO='" + COD + "'", conn);
            acNUMIDTERCERO.removeAllItems();
            for (int i = 0; i < ids.size(); i++) {
                acNUMIDTERCERO.addItem(ids.get(i));
            }
        }
    }

    private void FillNUMIDCONDUCTOR(JComboBox TIPOIDTERCERO,
            TextAutoCompleter acNUMIDTERCERO,
            JTextField NUMIDTERCERO,
            JTextField NOMIDTERCERO,
            JTextField CODTIPOIDTERCERO) {
        if (TIPOIDTERCERO.getSelectedItem() != null) {
            NUMIDTERCERO.setEditable(true);
            NUMIDTERCERO.setText("");
            NOMIDTERCERO.setText("");
            String TIPOIDTERCERO_value = TIPOIDTERCERO.getSelectedItem().toString();
            String COD = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_id "
                    + "where nombre='" + TIPOIDTERCERO_value + "';", conn);
            CODTIPOIDTERCERO.setText(COD);
            ArrayList<String> ids = UniversalDAO.getArrayList(""
                    + "select NUMIDTERCERO "
                    + "from tercero "
                    + "where CODTIPOIDTERCERO='" + COD + "' "
                    + "and tercero.NUMLICENCIACONDUCCION != ''", conn);
            acNUMIDTERCERO.removeAllItems();
            for (int i = 0; i < ids.size(); i++) {
                acNUMIDTERCERO.addItem(ids.get(i));
            }
            NUMIDTERCERO.setEnabled(true);
        }
    }

    private void fillSEDETERCERO(JTextField NUMIDTERCERO,
            JComboBox CODSEDETERCERO,
            JTextField NOMENCLATURADIRECCION,
            JTextField MUNICIPIORNDC,
            JTextField fieldCODSEDE,
            JTextField TELEFONOSEDE,
            JTextField fieldMUNICIPIO) {
        if (CODSEDETERCERO.getSelectedItem() != null) {
            String CODSEDE = CODSEDETERCERO.getSelectedItem().toString().split(" - ")[0];

            fieldCODSEDE.setText(CODSEDE);
            String direccion = UniversalDAO.getString(""
                    + "select sede.NOMENCLATURADIRECCION\n"
                    + "from sede, tercero\n"
                    + "where\n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + CODSEDE + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO", conn);
            String municipio = UniversalDAO.getString(""
                    + "select municipio.nombre\n"
                    + "from sede, tercero, municipio\n"
                    + "where\n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + CODSEDE + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO and\n"
                    + "municipio.id = sede.CODMUNICIPIORNDC", conn);
            String telefono = UniversalDAO.getString(""
                    + "select concat(sede.NUMTELEFONOCONTACTO,' ',sede.NUMCELULARPERSONA)\n"
                    + "from sede,tercero\n"
                    + "where \n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + CODSEDE + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO", conn);
            String codMunicipio = UniversalDAO.getString(""
                    + "select sede.CODMUNICIPIORNDC\n"
                    + "from sede,tercero\n"
                    + "where \n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + CODSEDE + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO", conn);

            NOMENCLATURADIRECCION.setText(direccion);
            MUNICIPIORNDC.setText(municipio);
            TELEFONOSEDE.setText(telefono);
            fieldMUNICIPIO.setText(codMunicipio);
        }
    }

//    private void buildAutoCompleterNumeroIdentificacionPrimerConductor() {
//        acNumeroIdentificacionPrimerConductor = new TextAutoCompleter(NUMIDCONDUCTOR, new AutoCompleterCallback() {
//            @Override
//            public void callback(Object o) {
//
//            }
//        });
//        acNumeroIdentificacionPrimerConductor.removeAllItems();
//        ArrayList<String> ids = TerceroDAO.getIds(CODIDTITULARMANIFIESTO.getSelectedItem().toString());
//        for (int i = 0; i < ids.size(); i++) {
//            acNumeroIdentificacionPrimerConductor.addItem(ids.get(i));
//        }
//    }
//
//    private void buildAutoCompleterNumeroIdentificacionSegundoConductor() {
//        acNumeroIdentificacionSegundoConductor = new TextAutoCompleter(NUMIDCONDUCTOR2, new AutoCompleterCallback() {
//            @Override
//            public void callback(Object o) {
//
//            }
//        });
//        acNumeroIdentificacionSegundoConductor.removeAllItems();
//        ArrayList<String> ids = TerceroDAO.getIds(CODIDTITULARMANIFIESTO.getSelectedItem().toString());
//        for (int i = 0; i < ids.size(); i++) {
//            acNumeroIdentificacionSegundoConductor.addItem(ids.get(i));
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        OPERACIONTRANSPORTE = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        MUNICIPIOORIGENMANIFIESTO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        MUNICIPIODESTINOMANIFIESTO = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FECHAEXPEDICIONMANIFIESTO = new com.toedter.calendar.JDateChooser();
        CLEARCARACTERISTICASGENERALESMANIFIESTO = new javax.swing.JButton();
        CODMUNICIPIOORIGENMANIFIESTO = new javax.swing.JTextField();
        CODMUNICIPIODESTINOMANIFIESTO = new javax.swing.JTextField();
        CODOPERACIONTRANSPORTE = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IDTITULARMANIFIESTO = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        NUMIDTITULARMANIFIESTO = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        SEDETITULARMANIFIESTO = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        NOMBREIDTITULARMANIFIESTO = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        NOMENCLATURADIRECCIONTITULARMANIFIESTO = new javax.swing.JTextField();
        TELEFONOSEDETITULARMANIFIESTO = new javax.swing.JTextField();
        MUNICIPIOTITULARMANIFIESTO = new javax.swing.JTextField();
        CLEARTITULARMANIFIESTO = new javax.swing.JButton();
        CODIDTITULARMANIFIESTO = new javax.swing.JTextField();
        fieldMUNICIPIOTITULARMANIFIESTO = new javax.swing.JTextField();
        CODSEDETITULARMANIFIESTO = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        NUMPLACA = new javax.swing.JTextField();
        MARCAVEHICULOCARGA = new javax.swing.JTextField();
        CONFIGURACIONVEHICULOCARGA = new javax.swing.JTextField();
        PESOVACIOVEHICULOCARGA = new javax.swing.JTextField();
        ANOFABRICACIONVEHICULOCARGA = new javax.swing.JTextField();
        TENEDORVEHICULOCARGA = new javax.swing.JTextField();
        NUMIDTENEDORVEHICULOCARGA = new javax.swing.JTextField();
        NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA = new javax.swing.JTextField();
        MUNICIPIOTENEDORVEHICULOCARGA = new javax.swing.JTextField();
        NUMSEGUROSOAT = new javax.swing.JTextField();
        FECHAVENCIMIENTOSOATVEHICULOCARGA = new javax.swing.JTextField();
        NOMASEGURADORASOAT = new javax.swing.JTextField();
        CODCONFIGURACIONRESULTANTE = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        CODMARCAVEHICULOCARGA = new javax.swing.JTextField();
        CODTIPOIDTENEDORVEHICULOCARGA = new javax.swing.JTextField();
        CODMUNICIPIOTENEDORVEHICULOCARGA = new javax.swing.JTextField();
        UNIDADCAPACIDADVEHICULOCARGA = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        NUMMANIFIESTOCARGA = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        NUMORDENCARGUE = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        IDCONDUCTOR = new javax.swing.JComboBox<>();
        NUMIDCONDUCTOR = new javax.swing.JTextField();
        NOMCONDUCTOR = new javax.swing.JTextField();
        NOMENCLATURADIRECCIONCONDUCTOR = new javax.swing.JTextField();
        TELEFONOCONDUCTOR = new javax.swing.JTextField();
        MUNICIPIOCONDUCTOR = new javax.swing.JTextField();
        CATEGORIACONDUCTOR = new javax.swing.JTextField();
        NUMLICENCIACONDUCTOR = new javax.swing.JTextField();
        VENCIMIENTOLICENCIACONDUCTOR = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        CODIDCONDUCTOR = new javax.swing.JTextField();
        CODMUNICIPIOCONDUCTOR = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        IDCONDUCTOR2 = new javax.swing.JComboBox<>();
        NUMIDCONDUCTOR2 = new javax.swing.JTextField();
        NOMCONDUCTOR2 = new javax.swing.JTextField();
        NOMENCLATURADIRECCIONCONDUCTOR2 = new javax.swing.JTextField();
        TELEFONOCONDUCTOR2 = new javax.swing.JTextField();
        MUNICIPIOCONDUCTOR2 = new javax.swing.JTextField();
        CATEGORIACONDUCTOR2 = new javax.swing.JTextField();
        NUMLICENCIACONDUCTOR2 = new javax.swing.JTextField();
        VENCIMIENTOLICENCIACONDUCTOR2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        CODIDCONDUCTOR2 = new javax.swing.JTextField();
        CODMUNICIPIOCONDUCTOR2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        VALORFLETEPACTADOVIAJE = new javax.swing.JTextField();
        retencionEnLaFuente = new javax.swing.JTextField();
        retencionICA = new javax.swing.JTextField();
        netoPagar = new javax.swing.JTextField();
        VALORANTICIPOMANIFIESTO = new javax.swing.JTextField();
        saldoPorPagar = new javax.swing.JTextField();
        MUNICIPIOPAGOSALDO = new javax.swing.JTextField();
        RESPONSABLEPAGOCARGUE = new javax.swing.JComboBox<>();
        RESPONSABLEPAGODESCARGUE = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        OBSERVACIONES = new javax.swing.JTextArea();
        FECHAPAGOSALDOMANIFIESTO = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        CODMUNICIPIOPAGOSALDO = new javax.swing.JTextField();
        CODRESPONSABLEPAGODESCARGUE = new javax.swing.JTextField();
        CODRESPONSABLEPAGOCARGUE = new javax.swing.JTextField();
        RETENCIONICAMANIFIESTOCARGA_U = new javax.swing.JSpinner();
        jLabel82 = new javax.swing.JLabel();
        RETENCIONICAMANIFIESTOCARGA_D = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableRemesas = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        horasTotalesCargue = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        minutosTotalesCargue = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        horasTotalesDescargue = new javax.swing.JTextField();
        minutosTotalesDescargue = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        cantidadRemesas = new javax.swing.JTextField();
        kilogramosRemesas = new javax.swing.JTextField();
        galonesCargados = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        remesaAdicionar = new javax.swing.JComboBox<>();
        adicionarRemesa = new javax.swing.JButton();
        cleanRemesas = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        NUMPLACAREMOLQUE1 = new javax.swing.JTextField();
        MARCAREMOLQUE1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        CONFIGURACIONREMOLQUE1 = new javax.swing.JTextField();
        PESOVEHICULOVACIOREMOLQUE1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        CODMARCAREMOLQUE1 = new javax.swing.JTextField();
        CLEARDATOSREMOLQUE1 = new javax.swing.JButton();
        CODCONFIGURACIONREMOLQUE1 = new javax.swing.JTextField();
        UNIDADCAPACIDADREMOLQUE1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        NUMPLACAREMOLQUE2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        MARCAREMOLQUE2 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        CONFIGURACIONREMOLQUE2 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        PESOVEHICULOVACIOREMOLQUE2 = new javax.swing.JTextField();
        CODMARCAREMOLQUE2 = new javax.swing.JTextField();
        CLEARDATOSREMOLQUE2 = new javax.swing.JButton();
        UNIDADCAPACIDADREMOLQUE2 = new javax.swing.JTextField();
        CODCONFIGURACIONREMOLQUE2 = new javax.swing.JTextField();
        viewManifiestos = new javax.swing.JButton();
        connectionStatus = new javax.swing.JButton();
        limpiarCaracteristicas = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        registrar = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        remesa = new javax.swing.JMenuItem();
        manifiesto = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        registrarTerceros = new javax.swing.JMenuItem();
        registrarVehiculos = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        acercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro manifiesto");

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));
        jLayeredPane1.setOpaque(true);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Registro de Manifiesto");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Caracteristicas Generales del Manifiesto");

        jLabel2.setText("Tipo manifiesto");

        OPERACIONTRANSPORTE.setModel(ManifiestoDAO.tipo_manifiesto(conn));
        OPERACIONTRANSPORTE.setSelectedItem(null);
        OPERACIONTRANSPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OPERACIONTRANSPORTEActionPerformed(evt);
            }
        });

        jLabel3.setText("Origen");

        jLabel4.setText("Destino");

        jLabel5.setText("Fecha expedición");

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        FECHAEXPEDICIONMANIFIESTO.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHAEXPEDICIONMANIFIESTO.setFocusCycleRoot(true);
        FECHAEXPEDICIONMANIFIESTO.setMinSelectableDate(new Date());

        CLEARCARACTERISTICASGENERALESMANIFIESTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARCARACTERISTICASGENERALESMANIFIESTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARCARACTERISTICASGENERALESMANIFIESTOActionPerformed(evt);
            }
        });

        CODMUNICIPIOORIGENMANIFIESTO.setEditable(false);

        CODMUNICIPIODESTINOMANIFIESTO.setEditable(false);

        CODOPERACIONTRANSPORTE.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARCARACTERISTICASGENERALESMANIFIESTO))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(MUNICIPIOORIGENMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODMUNICIPIOORIGENMANIFIESTO, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(OPERACIONTRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODOPERACIONTRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel4))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FECHAEXPEDICIONMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MUNICIPIODESTINOMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CODMUNICIPIODESTINOMANIFIESTO, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CLEARCARACTERISTICASGENERALESMANIFIESTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(OPERACIONTRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CODOPERACIONTRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(MUNICIPIOORIGENMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CODMUNICIPIOORIGENMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(FECHAEXPEDICIONMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(MUNICIPIODESTINOMANIFIESTO)
                            .addComponent(CODMUNICIPIODESTINOMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Titular Manifiesto");

        jLabel7.setText("Tipo identificación");

        IDTITULARMANIFIESTO.setModel(ManifiestoDAO.tipo_id(conn));
        IDTITULARMANIFIESTO.setSelectedItem(null);
        IDTITULARMANIFIESTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTITULARMANIFIESTOActionPerformed(evt);
            }
        });

        jLabel8.setText("Numero identificación");

        NUMIDTITULARMANIFIESTO.setEditable(false);

        jLabel9.setText("Sede");

        SEDETITULARMANIFIESTO.setEnabled(false);
        SEDETITULARMANIFIESTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEDETITULARMANIFIESTOActionPerformed(evt);
            }
        });

        jLabel10.setText("Nombre");

        NOMBREIDTITULARMANIFIESTO.setEditable(false);

        jLabel11.setText("Dirección");

        jLabel12.setText("Teléfono");

        jLabel13.setText("Municipio");

        NOMENCLATURADIRECCIONTITULARMANIFIESTO.setEditable(false);

        TELEFONOSEDETITULARMANIFIESTO.setEditable(false);

        MUNICIPIOTITULARMANIFIESTO.setEditable(false);

        CLEARTITULARMANIFIESTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARTITULARMANIFIESTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARTITULARMANIFIESTOActionPerformed(evt);
            }
        });

        CODIDTITULARMANIFIESTO.setEditable(false);

        fieldMUNICIPIOTITULARMANIFIESTO.setEditable(false);

        CODSEDETITULARMANIFIESTO.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NOMBREIDTITULARMANIFIESTO)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(MUNICIPIOTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldMUNICIPIOTITULARMANIFIESTO))
                            .addComponent(NOMENCLATURADIRECCIONTITULARMANIFIESTO)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(SEDETITULARMANIFIESTO, javax.swing.GroupLayout.Alignment.LEADING, 0, 350, Short.MAX_VALUE)
                                    .addComponent(IDTITULARMANIFIESTO, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CODIDTITULARMANIFIESTO)
                                    .addComponent(CODSEDETITULARMANIFIESTO)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NUMIDTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TELEFONOSEDETITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARTITULARMANIFIESTO)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CLEARTITULARMANIFIESTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(IDTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODIDTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(NUMIDTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(SEDETITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODSEDETITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(NOMBREIDTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NOMENCLATURADIRECCIONTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TELEFONOSEDETITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(MUNICIPIOTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldMUNICIPIOTITULARMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Vehículo");

        jLabel16.setText("Placa vehículo");

        jLabel17.setText("Marca");

        jLabel18.setText("Configuración");

        jLabel19.setText("Peso vacío");

        jLabel20.setText("Año modelo");

        jLabel21.setText("Tenedor vehículo");

        jLabel22.setText("Identificación");

        jLabel23.setText("Dirección");

        jLabel24.setText("Municipio");

        jLabel25.setText("Poliza SOAT");

        jLabel26.setText("Vencimiento");

        jLabel27.setText("Aseguradora");

        jLabel37.setText("Configuración resultante");

        MARCAVEHICULOCARGA.setEditable(false);

        CONFIGURACIONVEHICULOCARGA.setEditable(false);

        PESOVACIOVEHICULOCARGA.setEditable(false);

        ANOFABRICACIONVEHICULOCARGA.setEditable(false);

        TENEDORVEHICULOCARGA.setEditable(false);

        NUMIDTENEDORVEHICULOCARGA.setEditable(false);

        NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA.setEditable(false);

        MUNICIPIOTENEDORVEHICULOCARGA.setEditable(false);

        NUMSEGUROSOAT.setEditable(false);

        FECHAVENCIMIENTOSOATVEHICULOCARGA.setEditable(false);

        NOMASEGURADORASOAT.setEditable(false);

        CODCONFIGURACIONRESULTANTE.setEditable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        CODMARCAVEHICULOCARGA.setEditable(false);

        CODTIPOIDTENEDORVEHICULOCARGA.setEditable(false);

        CODMUNICIPIOTENEDORVEHICULOCARGA.setEditable(false);

        UNIDADCAPACIDADVEHICULOCARGA.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CODMARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CODMUNICIPIOTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel24)))
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(NOMASEGURADORASOAT, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MUNICIPIOTENEDORVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TENEDORVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CONFIGURACIONVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MARCAVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(NUMIDTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(CODTIPOIDTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(NUMPLACA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ANOFABRICACIONVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(PESOVACIOVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(UNIDADCAPACIDADVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(FECHAVENCIMIENTOSOATVEHICULOCARGA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(NUMSEGUROSOAT, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addComponent(CODCONFIGURACIONRESULTANTE, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 81, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(NUMPLACA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(MARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(CONFIGURACIONVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(PESOVACIOVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UNIDADCAPACIDADVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(ANOFABRICACIONVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(NUMIDTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODTIPOIDTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(MUNICIPIOTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMUNICIPIOTENEDORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(NUMSEGUROSOAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(FECHAVENCIMIENTOSOATVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(NOMASEGURADORASOAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(CODCONFIGURACIONRESULTANTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Consecutivo del Manifiesto");

        NUMMANIFIESTOCARGA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NUMMANIFIESTOCARGA.setForeground(new java.awt.Color(255, 0, 0));
        NUMMANIFIESTOCARGA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NUMMANIFIESTOCARGA.setText("CALI600000");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel83.setText("Orden de Cargue No.");

        NUMORDENCARGUE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NUMORDENCARGUE.setForeground(new java.awt.Color(255, 0, 0));
        NUMORDENCARGUE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NUMORDENCARGUE.setText("600000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NUMMANIFIESTOCARGA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NUMORDENCARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NUMMANIFIESTOCARGA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NUMORDENCARGUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Primer Conductor");

        jLabel39.setText("Tipo identificación");

        jLabel40.setText("Numero Identificación");

        jLabel41.setText("Nombre");

        jLabel44.setText("Municipio");

        jLabel42.setText("Dirección");

        jLabel43.setText("Teléfono");

        jLabel45.setText("Categoría Licencia");

        jLabel46.setText("Licencia");

        jLabel47.setText("Vencimiento");

        IDCONDUCTOR.setModel(UniversalDAO.getComboBoxModel("select nombre from tipo_id_conductor", conn)
        );
        IDCONDUCTOR.setSelectedItem(null);
        IDCONDUCTOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDCONDUCTORActionPerformed(evt);
            }
        });

        NUMIDCONDUCTOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUMIDCONDUCTORActionPerformed(evt);
            }
        });

        NOMCONDUCTOR.setEditable(false);

        NOMENCLATURADIRECCIONCONDUCTOR.setEditable(false);

        TELEFONOCONDUCTOR.setEditable(false);

        MUNICIPIOCONDUCTOR.setEditable(false);

        CATEGORIACONDUCTOR.setEditable(false);

        NUMLICENCIACONDUCTOR.setEditable(false);

        VENCIMIENTOLICENCIACONDUCTOR.setEditable(false);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        CODIDCONDUCTOR.setEditable(false);

        CODMUNICIPIOCONDUCTOR.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(MUNICIPIOCONDUCTOR, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NUMIDCONDUCTOR, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                            .addComponent(IDCONDUCTOR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CODIDCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(TELEFONOCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODMUNICIPIOCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(VENCIMIENTOLICENCIACONDUCTOR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                    .addComponent(NUMLICENCIACONDUCTOR, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CATEGORIACONDUCTOR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(NOMCONDUCTOR)
                            .addComponent(NOMENCLATURADIRECCIONCONDUCTOR))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(IDCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODIDCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(NUMIDCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(NOMCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(NOMENCLATURADIRECCIONCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(TELEFONOCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(MUNICIPIOCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMUNICIPIOCONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(CATEGORIACONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(NUMLICENCIACONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(VENCIMIENTOLICENCIACONDUCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Segundo Conductor");

        jLabel59.setText("Tipo identificación");

        jLabel60.setText("Numero Identificación");

        jLabel61.setText("Nombre");

        jLabel62.setText("Municipio");

        jLabel63.setText("Dirección");

        jLabel64.setText("Teléfono");

        jLabel65.setText("Categoría Licencia");

        jLabel66.setText("Licencia");

        jLabel67.setText("Vencimiento");

        IDCONDUCTOR2.setModel(UniversalDAO.getComboBoxModel("select nombre from tipo_id_conductor", conn));
        IDCONDUCTOR2.setSelectedItem(null);
        IDCONDUCTOR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDCONDUCTOR2ActionPerformed(evt);
            }
        });

        NOMCONDUCTOR2.setEditable(false);

        NOMENCLATURADIRECCIONCONDUCTOR2.setEditable(false);

        TELEFONOCONDUCTOR2.setEditable(false);

        MUNICIPIOCONDUCTOR2.setEditable(false);

        CATEGORIACONDUCTOR2.setEditable(false);

        NUMLICENCIACONDUCTOR2.setEditable(false);

        VENCIMIENTOLICENCIACONDUCTOR2.setEditable(false);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        CODIDCONDUCTOR2.setEditable(false);

        CODMUNICIPIOCONDUCTOR2.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59)
                            .addComponent(jLabel61)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel62)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66)
                            .addComponent(jLabel67))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(MUNICIPIOCONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(VENCIMIENTOLICENCIACONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                            .addComponent(CATEGORIACONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(NUMLICENCIACONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TELEFONOCONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 82, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODMUNICIPIOCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(NUMIDCONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IDCONDUCTOR2, javax.swing.GroupLayout.Alignment.LEADING, 0, 265, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODIDCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(NOMENCLATURADIRECCIONCONDUCTOR2)
                            .addComponent(NOMCONDUCTOR2))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(IDCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODIDCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(NUMIDCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(NOMCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(NOMENCLATURADIRECCIONCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(TELEFONOCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(MUNICIPIOCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMUNICIPIOCONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(CATEGORIACONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(NUMLICENCIACONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(VENCIMIENTOLICENCIACONDUCTOR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Valor del Viaje");

        jLabel49.setText("Valor a pagar por el viaje");

        jLabel50.setText("Retención en la fuente");

        jLabel51.setText("Retencion ICA (% mil)");

        jLabel52.setText("Neto a pagar");

        jLabel53.setText("Valor anticipo");

        jLabel54.setText("Saldo por pagar");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Pago del saldo");

        jLabel56.setText("Lugar");

        jLabel57.setText("Fecha");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("Responsable del Pago");

        jLabel69.setText("Cargue");

        jLabel70.setText("Descargue");

        jLabel71.setText("Recomendaciones");

        VALORFLETEPACTADOVIAJE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                VALORFLETEPACTADOVIAJEFocusLost(evt);
            }
        });
        VALORFLETEPACTADOVIAJE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VALORFLETEPACTADOVIAJEActionPerformed(evt);
            }
        });
        VALORFLETEPACTADOVIAJE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VALORFLETEPACTADOVIAJEKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VALORFLETEPACTADOVIAJEKeyReleased(evt);
            }
        });

        retencionEnLaFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                retencionEnLaFuenteKeyReleased(evt);
            }
        });

        retencionICA.setEditable(false);

        netoPagar.setEditable(false);

        VALORANTICIPOMANIFIESTO.setText("0");
        VALORANTICIPOMANIFIESTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VALORANTICIPOMANIFIESTOKeyReleased(evt);
            }
        });

        saldoPorPagar.setEditable(false);

        RESPONSABLEPAGOCARGUE.setModel(UniversalDAO.getComboBoxModel("select nombre from responsable_pago", conn));
        RESPONSABLEPAGOCARGUE.setSelectedItem(null);
        RESPONSABLEPAGOCARGUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESPONSABLEPAGOCARGUEActionPerformed(evt);
            }
        });

        RESPONSABLEPAGODESCARGUE.setModel(UniversalDAO.getComboBoxModel("select nombre from responsable_pago", conn));
        RESPONSABLEPAGODESCARGUE.setSelectedItem(null);
        RESPONSABLEPAGODESCARGUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESPONSABLEPAGODESCARGUEActionPerformed(evt);
            }
        });

        OBSERVACIONES.setColumns(20);
        OBSERVACIONES.setLineWrap(true);
        OBSERVACIONES.setWrapStyleWord(true);
        jScrollPane2.setViewportView(OBSERVACIONES);

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        FECHAPAGOSALDOMANIFIESTO.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHAPAGOSALDOMANIFIESTO.setMinSelectableDate(new Date());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        CODMUNICIPIOPAGOSALDO.setEditable(false);

        CODRESPONSABLEPAGODESCARGUE.setEditable(false);

        CODRESPONSABLEPAGOCARGUE.setEditable(false);

        RETENCIONICAMANIFIESTOCARGA_U.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        RETENCIONICAMANIFIESTOCARGA_U.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RETENCIONICAMANIFIESTOCARGA_UStateChanged(evt);
            }
        });

        jLabel82.setText(".");

        RETENCIONICAMANIFIESTOCARGA_D.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        RETENCIONICAMANIFIESTOCARGA_D.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RETENCIONICAMANIFIESTOCARGA_DStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel53)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(RETENCIONICAMANIFIESTOCARGA_U, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel82)
                                .addGap(1, 1, 1)
                                .addComponent(RETENCIONICAMANIFIESTOCARGA_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(retencionICA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(saldoPorPagar, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(VALORANTICIPOMANIFIESTO, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(netoPagar, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(retencionEnLaFuente, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(VALORFLETEPACTADOVIAJE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69)
                            .addComponent(jLabel68)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(RESPONSABLEPAGODESCARGUE, javax.swing.GroupLayout.Alignment.LEADING, 0, 235, Short.MAX_VALUE)
                                    .addComponent(RESPONSABLEPAGOCARGUE, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CODRESPONSABLEPAGODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CODRESPONSABLEPAGOCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 67, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel56)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addGap(22, 22, 22))
                            .addComponent(jLabel55))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(FECHAPAGOSALDOMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(MUNICIPIOPAGOSALDO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CODMUNICIPIOPAGOSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(VALORFLETEPACTADOVIAJE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(retencionEnLaFuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(retencionICA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RETENCIONICAMANIFIESTOCARGA_U, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82)
                    .addComponent(RETENCIONICAMANIFIESTOCARGA_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(netoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(VALORANTICIPOMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(saldoPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(MUNICIPIOPAGOSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMUNICIPIOPAGOSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(FECHAPAGOSALDOMANIFIESTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(RESPONSABLEPAGOCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODRESPONSABLEPAGOCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(RESPONSABLEPAGODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODRESPONSABLEPAGODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Remesas");

        tableRemesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Remesa", "Codigo", "Producto", "Cantidad", "Unidad", "Naturaleza", "Empaque", "Remitente", "Destinatario"
            }
        ));
        jScrollPane3.setViewportView(tableRemesas);

        jLabel73.setText("Tiempo Total Cargue Pactado(Incluye Espera + Cargue)");

        horasTotalesCargue.setEditable(false);
        horasTotalesCargue.setText("0");

        jLabel74.setText("Horas");

        minutosTotalesCargue.setEditable(false);
        minutosTotalesCargue.setText("0");

        jLabel75.setText("Minutos");

        jLabel76.setText("Tiempo Total Descargue Pactado(Incluye Espera + Descargue)");

        horasTotalesDescargue.setEditable(false);
        horasTotalesDescargue.setText("0");

        minutosTotalesDescargue.setEditable(false);
        minutosTotalesDescargue.setText("0");

        jLabel77.setText("Cantidad de Remesas");

        jLabel78.setText("Kilogramos remesas");

        jLabel79.setText("Galones remesas");

        cantidadRemesas.setEditable(false);
        cantidadRemesas.setText("0");

        kilogramosRemesas.setEditable(false);
        kilogramosRemesas.setText("0");

        galonesCargados.setEditable(false);
        galonesCargados.setText("0");

        jLabel81.setText("Adicionar remesa");

        remesaAdicionar.setModel(getRemesas());
        remesaAdicionar.setSelectedItem(null);

        adicionarRemesa.setText("Adicionar");
        adicionarRemesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarRemesaActionPerformed(evt);
            }
        });

        cleanRemesas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        cleanRemesas.setText(" ");
        cleanRemesas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanRemesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanRemesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel73))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel74)
                                            .addComponent(horasTotalesCargue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(minutosTotalesCargue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel78))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel75)
                                                .addGap(106, 106, 106)
                                                .addComponent(jLabel77))))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(horasTotalesDescargue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(minutosTotalesDescargue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel79)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cantidadRemesas, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(kilogramosRemesas)
                                    .addComponent(galonesCargados)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel72)))
                        .addGap(0, 297, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cleanRemesas)))))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remesaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adicionarRemesa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cleanRemesas)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(remesaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adicionarRemesa))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minutosTotalesCargue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(cantidadRemesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel73)
                                .addComponent(horasTotalesCargue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel78)
                                .addComponent(kilogramosRemesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(minutosTotalesDescargue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(horasTotalesDescargue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel79)
                        .addComponent(galonesCargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setText("Placa Remolque");

        MARCAREMOLQUE1.setEditable(false);

        jLabel29.setText("Marca");

        jLabel30.setText("Configuración");

        CONFIGURACIONREMOLQUE1.setEditable(false);

        jLabel31.setText("Peso Vacio");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Datos de Remolque 1");

        CODMARCAREMOLQUE1.setEditable(false);

        CLEARDATOSREMOLQUE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARDATOSREMOLQUE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARDATOSREMOLQUE1ActionPerformed(evt);
            }
        });

        CODCONFIGURACIONREMOLQUE1.setEditable(false);

        UNIDADCAPACIDADREMOLQUE1.setEditable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARDATOSREMOLQUE1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CONFIGURACIONREMOLQUE1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MARCAREMOLQUE1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CODMARCAREMOLQUE1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(CODCONFIGURACIONREMOLQUE1)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NUMPLACAREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(PESOVEHICULOVACIOREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(UNIDADCAPACIDADREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARDATOSREMOLQUE1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NUMPLACAREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(MARCAREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMARCAREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(CONFIGURACIONREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODCONFIGURACIONREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(PESOVEHICULOVACIOREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UNIDADCAPACIDADREMOLQUE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setText("Datos de Remolque 2");

        jLabel33.setText("Placa Remolque");

        jLabel34.setText("Marca");

        MARCAREMOLQUE2.setEditable(false);

        jLabel35.setText("Configuración");

        CONFIGURACIONREMOLQUE2.setEditable(false);

        jLabel36.setText("Peso vacío");

        CODMARCAREMOLQUE2.setEditable(false);

        CLEARDATOSREMOLQUE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARDATOSREMOLQUE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARDATOSREMOLQUE2ActionPerformed(evt);
            }
        });

        UNIDADCAPACIDADREMOLQUE2.setEditable(false);

        CODCONFIGURACIONREMOLQUE2.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARDATOSREMOLQUE2))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NUMPLACAREMOLQUE2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addComponent(PESOVEHICULOVACIOREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UNIDADCAPACIDADREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(CONFIGURACIONREMOLQUE2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MARCAREMOLQUE2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CODMARCAREMOLQUE2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(CODCONFIGURACIONREMOLQUE2))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARDATOSREMOLQUE2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(NUMPLACAREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(MARCAREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMARCAREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(CONFIGURACIONREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODCONFIGURACIONREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(PESOVEHICULOVACIOREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UNIDADCAPACIDADREMOLQUE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        viewManifiestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/tabla.png"))); // NOI18N
        viewManifiestos.setToolTipText("Mostrar registros");
        viewManifiestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewManifiestosActionPerformed(evt);
            }
        });

        connectionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/connection.png"))); // NOI18N

        limpiarCaracteristicas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        limpiarCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCaracteristicasActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(viewManifiestos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(connectionStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(limpiarCaracteristicas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(320, 320, 320)
                            .addComponent(viewManifiestos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(limpiarCaracteristicas)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(connectionStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(connectionStatus)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(limpiarCaracteristicas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewManifiestos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jLayeredPane1);

        jMenu3.setText("Archivo");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        registrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        registrar.setMnemonic('G');
        registrar.setText("Registrar manifiesto");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jMenu3.add(registrar);
        jMenu3.add(jSeparator4);

        salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu3.add(salir);

        jMenuBar1.add(jMenu3);

        jMenu1.setMnemonic('F');
        jMenu1.setText("Formularios");

        remesa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        remesa.setMnemonic('R');
        remesa.setText("Remesa");
        remesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remesaActionPerformed(evt);
            }
        });
        jMenu1.add(remesa);

        manifiesto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        manifiesto.setMnemonic('M');
        manifiesto.setText("Manifiesto");
        manifiesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manifiestoActionPerformed(evt);
            }
        });
        jMenu1.add(manifiesto);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Herramientas");

        registrarTerceros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        registrarTerceros.setText("Registrar Terceros y Conductores");
        registrarTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarTercerosActionPerformed(evt);
            }
        });
        jMenu4.add(registrarTerceros);

        registrarVehiculos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        registrarVehiculos.setText("Registrar Vehiculos y Remolques");
        registrarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarVehiculosActionPerformed(evt);
            }
        });
        jMenu4.add(registrarVehiculos);

        jMenuBar1.add(jMenu4);

        ayuda.setText("Opciones");

        acercaDe.setMnemonic('A');
        acercaDe.setText("Acerca de");
        acercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaDeActionPerformed(evt);
            }
        });
        ayuda.add(acercaDe);

        jMenuBar1.add(ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1240, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void IDTITULARMANIFIESTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTITULARMANIFIESTOActionPerformed
        if (IDTITULARMANIFIESTO.getSelectedItem() != null) {
            FillNUMIDTERCERO(IDTITULARMANIFIESTO, acNUMIDTITULARMANIFIESTO, NUMIDTITULARMANIFIESTO, NOMBREIDTITULARMANIFIESTO, CODIDTITULARMANIFIESTO);

//            buildAutoCompleterNumeroIdentificacionTitular();
//            String tipoID = CODIDTITULARMANIFIESTO.getSelectedItem().toString();
//            String COD = UniversalDAO.getString("select cod from tipo_id where nombre='" + tipoID + "';");
//            fieldCODIDTITULARMANIFIESTO.setText(COD);
//            NUMIDTITULARMANIFIESTO.setEnabled(true);
        }
    }//GEN-LAST:event_IDTITULARMANIFIESTOActionPerformed

    private void SEDETITULARMANIFIESTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEDETITULARMANIFIESTOActionPerformed
        if (SEDETITULARMANIFIESTO.getSelectedItem() != null) {
            fillSEDETERCERO(NUMIDTITULARMANIFIESTO,
                    SEDETITULARMANIFIESTO,
                    NOMENCLATURADIRECCIONTITULARMANIFIESTO,
                    MUNICIPIOTITULARMANIFIESTO,
                    CODSEDETITULARMANIFIESTO,
                    TELEFONOSEDETITULARMANIFIESTO,
                    fieldMUNICIPIOTITULARMANIFIESTO);
        }
    }//GEN-LAST:event_SEDETITULARMANIFIESTOActionPerformed


    private void CLEARCARACTERISTICASGENERALESMANIFIESTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARCARACTERISTICASGENERALESMANIFIESTOActionPerformed
        CLEARCARACTERISTICASGENERALESMANIFIESTO();
    }//GEN-LAST:event_CLEARCARACTERISTICASGENERALESMANIFIESTOActionPerformed

    private void CLEARCARACTERISTICASGENERALESMANIFIESTO() {
        CODOPERACIONTRANSPORTE.setText("");
        OPERACIONTRANSPORTE.removeAllItems();
        OPERACIONTRANSPORTE.setModel(ManifiestoDAO.tipo_manifiesto(conn));
        FECHAEXPEDICIONMANIFIESTO.setDate(null);
        MUNICIPIOORIGENMANIFIESTO.setText("");
        MUNICIPIODESTINOMANIFIESTO.setText("");
        OPERACIONTRANSPORTE.setSelectedItem(null);
        CODMUNICIPIOORIGENMANIFIESTO.setText("");
        CODMUNICIPIODESTINOMANIFIESTO.setText("");
    }

    private void CLEARTITULARMANIFIESTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARTITULARMANIFIESTOActionPerformed
        CLEARTITULARMANIFIESTO();
    }//GEN-LAST:event_CLEARTITULARMANIFIESTOActionPerformed

    private void CLEARTITULARMANIFIESTO() {
        IDTITULARMANIFIESTO.setSelectedItem(null);
        NUMIDTITULARMANIFIESTO.setText("");
        NUMIDTITULARMANIFIESTO.setEditable(false);
        acNUMIDTITULARMANIFIESTO.removeAllItems();
        NOMBREIDTITULARMANIFIESTO.setText("");
        NOMENCLATURADIRECCIONTITULARMANIFIESTO.setText("");
        TELEFONOSEDETITULARMANIFIESTO.setText("");
        MUNICIPIOTITULARMANIFIESTO.setText("");
        CODIDTITULARMANIFIESTO.setText("");
        CODSEDETITULARMANIFIESTO.setText("");
        SEDETITULARMANIFIESTO.setEnabled(false);
        SEDETITULARMANIFIESTO.removeAllItems();
        fieldMUNICIPIOTITULARMANIFIESTO.setText("");
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CLEARCONDUCTOR();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CLEARCONDUCTOR() {
        acNUMIDCONDUCTOR.removeAllItems();
        NOMCONDUCTOR.setText("");
        NOMENCLATURADIRECCIONCONDUCTOR.setText("");
        TELEFONOCONDUCTOR.setText("");
        MUNICIPIOCONDUCTOR.setText("");
        CATEGORIACONDUCTOR.setText("");
        NUMLICENCIACONDUCTOR.setText("");
        VENCIMIENTOLICENCIACONDUCTOR.setText("");
        CODMUNICIPIOCONDUCTOR.setText("");
        NUMIDCONDUCTOR.setText("");
        CODIDCONDUCTOR.setText("");
        IDCONDUCTOR.setSelectedItem(null);
        NUMIDCONDUCTOR.setEnabled(false);
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CLEARCONDUCTOR2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CLEARCONDUCTOR2() {
        acNUMIDCONDUCTOR2.removeAllItems();
        NOMCONDUCTOR2.setText("");
        NOMENCLATURADIRECCIONCONDUCTOR2.setText("");
        TELEFONOCONDUCTOR2.setText("");
        MUNICIPIOCONDUCTOR2.setText("");
        CATEGORIACONDUCTOR2.setText("");
        NUMLICENCIACONDUCTOR2.setText("");
        VENCIMIENTOLICENCIACONDUCTOR2.setText("");
        CODMUNICIPIOCONDUCTOR2.setText("");
        NUMIDCONDUCTOR2.setText("");
        CODIDCONDUCTOR2.setText("");
        IDCONDUCTOR2.setSelectedItem(null);
        NUMIDCONDUCTOR2.setEnabled(false);
    }

    private void CLEARDATOSREMOLQUE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARDATOSREMOLQUE1ActionPerformed
        CLEARDATOSREMOLQUE1();
    }//GEN-LAST:event_CLEARDATOSREMOLQUE1ActionPerformed

    private void CLEARDATOSREMOLQUE1() {
        NUMPLACAREMOLQUE1.setText("");
        MARCAREMOLQUE1.setText("");
        CODMARCAREMOLQUE1.setText("");
        CONFIGURACIONREMOLQUE1.setText("");
        CODCONFIGURACIONREMOLQUE1.setText("");
        PESOVEHICULOVACIOREMOLQUE1.setText("");
        UNIDADCAPACIDADREMOLQUE1.setText("");
        if (CODCONFIGURACIONRESULTANTE.getText().length() >= 2) {
            String pre = CODCONFIGURACIONRESULTANTE.getText();
            pre = pre.substring(0, pre.length() - 2);
            CODCONFIGURACIONRESULTANTE.setText(pre);
        }
    }

    private void CLEARDATOSREMOLQUE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARDATOSREMOLQUE2ActionPerformed
        CLEARDATOSREMOLQUE2();
    }//GEN-LAST:event_CLEARDATOSREMOLQUE2ActionPerformed

    private void IDCONDUCTORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDCONDUCTORActionPerformed
        FillNUMIDCONDUCTOR(IDCONDUCTOR, acNUMIDCONDUCTOR, NUMIDCONDUCTOR, NOMCONDUCTOR, CODIDCONDUCTOR);
    }//GEN-LAST:event_IDCONDUCTORActionPerformed

    private void IDCONDUCTOR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDCONDUCTOR2ActionPerformed
        FillNUMIDCONDUCTOR(IDCONDUCTOR2, acNUMIDCONDUCTOR2, NUMIDCONDUCTOR2, NOMCONDUCTOR2, CODIDCONDUCTOR2);
    }//GEN-LAST:event_IDCONDUCTOR2ActionPerformed

    private void NUMIDCONDUCTORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUMIDCONDUCTORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUMIDCONDUCTORActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if(allowRecords){
            if(!CODSEDETITULARMANIFIESTO.getText().equals("")){
                if(!CODRESPONSABLEPAGOCARGUE.getText().equals("") && !CODRESPONSABLEPAGODESCARGUE.getText().equals("")){
                    registrarManifiesto();
                }else{
                    Modal.show("Error", "Seleccione los responsables de pago del cargue y descargue", this, "", "error");
                }
            }else{
                Modal.show("Error", "Seleccione una sede para el titular del manifiesto", this, "", "error");
            }
        }else{
            Modal.show("Error", "No hay consecutivos disponibles para manifiestos y/o ordenes de carga", this, "", "error");
        }
    }//GEN-LAST:event_registrarActionPerformed

    public void registrarManifiesto(){
        try {
            Thread registro = new Thread(new RecordManifiesto(getValues(), this, connectionStatus, NUMMANIFIESTOCARGA.getText(),  dataSource,  conn));
            registro.start();
        } catch (Exception e) {
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.start();
        }
    }
    
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void remesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remesaActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.remesa.FormRemesa(dataSource, conn, username).setVisible(true);
            }
        });
    }//GEN-LAST:event_remesaActionPerformed

    private void manifiestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manifiestoActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.manifiesto.FormManifiesto(username, dataSource, conn).setVisible(true);
            }
        });
    }//GEN-LAST:event_manifiestoActionPerformed

    private void registrarTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarTercerosActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.tercero.FormTerceros(dataSource, conn, username).setVisible(true);
            }
        });
    }//GEN-LAST:event_registrarTercerosActionPerformed

    private void registrarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarVehiculosActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.vehiculo.FormVehiculos(dataSource, conn, username).setVisible(true);
            }
        });
    }//GEN-LAST:event_registrarVehiculosActionPerformed

    private void acercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.acercaDe.FormAcercaDe().setVisible(true);
            }
        });
    }//GEN-LAST:event_acercaDeActionPerformed

    private void VALORFLETEPACTADOVIAJEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VALORFLETEPACTADOVIAJEFocusLost

    }//GEN-LAST:event_VALORFLETEPACTADOVIAJEFocusLost

    private void VALORFLETEPACTADOVIAJEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VALORFLETEPACTADOVIAJEActionPerformed

    }//GEN-LAST:event_VALORFLETEPACTADOVIAJEActionPerformed

    private void VALORFLETEPACTADOVIAJEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VALORFLETEPACTADOVIAJEKeyPressed

    }//GEN-LAST:event_VALORFLETEPACTADOVIAJEKeyPressed

    private void VALORFLETEPACTADOVIAJEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VALORFLETEPACTADOVIAJEKeyReleased
        calcularValorDeViaje(false);
    }//GEN-LAST:event_VALORFLETEPACTADOVIAJEKeyReleased

    private void retencionEnLaFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retencionEnLaFuenteKeyReleased
        calcularValorDeViaje(true);
    }//GEN-LAST:event_retencionEnLaFuenteKeyReleased

    private void VALORANTICIPOMANIFIESTOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VALORANTICIPOMANIFIESTOKeyReleased
        calcularValorDeViaje(false);
    }//GEN-LAST:event_VALORANTICIPOMANIFIESTOKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearValorDeViaje();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void RESPONSABLEPAGOCARGUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESPONSABLEPAGOCARGUEActionPerformed
        if (RESPONSABLEPAGOCARGUE.getSelectedItem() != null) {
            String codRESPONSABLEPAGOCARGUE = UniversalDAO.getString(""
                    + "select cod "
                    + "from responsable_pago "
                    + "where nombre='" + RESPONSABLEPAGOCARGUE.getSelectedItem().toString() + "'", conn);
            CODRESPONSABLEPAGOCARGUE.setText(codRESPONSABLEPAGOCARGUE);
        }
    }//GEN-LAST:event_RESPONSABLEPAGOCARGUEActionPerformed

    private void RESPONSABLEPAGODESCARGUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESPONSABLEPAGODESCARGUEActionPerformed
        if (RESPONSABLEPAGODESCARGUE.getSelectedItem() != null) {
            String codRESPONSABLEPAGODESCARGUE = UniversalDAO.getString(""
                    + "select cod "
                    + "from responsable_pago "
                    + "where nombre='" + RESPONSABLEPAGODESCARGUE.getSelectedItem().toString() + "'", conn);
            CODRESPONSABLEPAGODESCARGUE.setText(codRESPONSABLEPAGODESCARGUE);
        }
    }//GEN-LAST:event_RESPONSABLEPAGODESCARGUEActionPerformed

    private void buildTableRemesas(ArrayList<String> consecutivos) {
        try {
            Statement sentencia = conn.createStatement();

            String query = "select * from remesas_agregar where Remesa='";

            for (int i = 0; i < consecutivos.size(); i++) {
                if (i == consecutivos.size() - 1) {
                    query += consecutivos.get(i);
                } else {
                    query += consecutivos.get(i);
                    query += "' or Remesa='";
                }
            }
            query += "'";

            ResultSet result = sentencia.executeQuery(query);

            int columns = result.getMetaData().getColumnCount();

            //Table's data
            ArrayList<ArrayList<String>> data = new ArrayList<>();

            int rows = 0;
            for (int i = 0; i < columns; i++) {
                data.add(new ArrayList<>());
                while (result.next()) {
                    data.get(i).add(result.getString(i + 1));
                    if (i == 0) {
                        rows++;
                    }
                }
                result.beforeFirst();
            }

            ArrayList<String> columnNames = new ArrayList<>();

            for (int i = 1; i <= columns; i++) {
                columnNames.add(result.getMetaData().getColumnName(i));
            }

            DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };

            Vector column;
            for (int i = 0; i < columns; i++) {
                column = new Vector();
                for (int j = 0; j < rows; j++) {
                    column.add(data.get(i).get(j));
                }
                tableModel.addColumn(columnNames.get(i), column);
            }

            tableRemesas.getTableHeader().setReorderingAllowed(false);

            tableRemesas.setModel(tableModel);
//            jScrollPane1.setViewportView(table);

            sentencia.close();
            result.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
//            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
    }

    private Boolean isRemesa(String consecutivo) {
        for (int i = 0; i < consecutivos.size(); i++) {
            if (consecutivos.get(i).equals(consecutivo)) {
                return true;
            }
        }
        return false;
    }

    private Boolean addRemesa(String consecutivo) {
        if (!isRemesa(consecutivo)) {
            consecutivos.add(consecutivo);
            return true;
        }
        return false;
    }

    private void adicionarRemesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarRemesaActionPerformed
        if (remesaAdicionar.getSelectedItem() != null) {
            String remesa = remesaAdicionar.getSelectedItem().toString().split(" - ")[0];
            if (!addRemesa(remesa)) {
                Modal.show("Aviso", "La remesa ya ha sido agregada", this, "", "warning");
            } else {
                buildTableRemesas(consecutivos);
                fillValuesAdicionarRemesas();
                remesaAdicionar.setSelectedItem(null);
            }
        } else {
            Modal.show("Aviso", "Por favor seleccione una remesa", this, "", "warning");
        }
    }//GEN-LAST:event_adicionarRemesaActionPerformed

    private void cleanRemesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanRemesasActionPerformed
        consecutivos.clear();
        horasTotalesCargue.setText("0");
        minutosTotalesCargue.setText("0");
        horasTotalesDescargue.setText("0");
        minutosTotalesDescargue.setText("0");

        cantidadRemesas.setText("0");
        kilogramosRemesas.setText("0");
        galonesCargados.setText("0");

        remesaAdicionar.setSelectedItem(null);

        buildTableRemesas(consecutivos);
    }//GEN-LAST:event_cleanRemesasActionPerformed

    private void OPERACIONTRANSPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OPERACIONTRANSPORTEActionPerformed
        if (OPERACIONTRANSPORTE.getSelectedItem() != null) {
            String vCODOPERACIONTRANSPORTE = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_manifiesto "
                    + "where nombre='" + OPERACIONTRANSPORTE.getSelectedItem().toString() + "'", conn);
            CODOPERACIONTRANSPORTE.setText(vCODOPERACIONTRANSPORTE);
        }
    }//GEN-LAST:event_OPERACIONTRANSPORTEActionPerformed

    private void viewManifiestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewManifiestosActionPerformed
        openFromViewManifiestos(dataSource,conn);
    }//GEN-LAST:event_viewManifiestosActionPerformed

    public static void openFromViewManifiestos(DataSourceClass dataSource,Connection conn) {
        String[] querys = {
            "select * from manifiestos"
        };
        OpenForm.view("Manifiestos", querys, dataSource, conn);
    }

    private void limpiarCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCaracteristicasActionPerformed
        cleanAll();
    }//GEN-LAST:event_limpiarCaracteristicasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CLEARVEHICULO();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void RETENCIONICAMANIFIESTOCARGA_UStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RETENCIONICAMANIFIESTOCARGA_UStateChanged
        calcularValorDeViaje(false);
    }//GEN-LAST:event_RETENCIONICAMANIFIESTOCARGA_UStateChanged

    private void RETENCIONICAMANIFIESTOCARGA_DStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RETENCIONICAMANIFIESTOCARGA_DStateChanged
        calcularValorDeViaje(false);
    }//GEN-LAST:event_RETENCIONICAMANIFIESTOCARGA_DStateChanged

    private void CLEARVEHICULO() {
        NUMPLACA.setText("");
        MARCAVEHICULOCARGA.setText("");
        CODMARCAVEHICULOCARGA.setText("");
        CONFIGURACIONVEHICULOCARGA.setText("");
        PESOVACIOVEHICULOCARGA.setText("");
        UNIDADCAPACIDADVEHICULOCARGA.setText("");
        ANOFABRICACIONVEHICULOCARGA.setText("");

        TENEDORVEHICULOCARGA.setText("");
        NUMIDTENEDORVEHICULOCARGA.setText("");
        CODTIPOIDTENEDORVEHICULOCARGA.setText("");
        NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA.setText("");
        MUNICIPIOTENEDORVEHICULOCARGA.setText("");
        CODMUNICIPIOTENEDORVEHICULOCARGA.setText("");
        NUMSEGUROSOAT.setText("");
        FECHAVENCIMIENTOSOATVEHICULOCARGA.setText("");
        NOMASEGURADORASOAT.setText("");
        CODCONFIGURACIONRESULTANTE.setText("");

        fillNUMPLACAVEHICULOCARGA();

    }

    private void cleanAll() {
        CLEARCARACTERISTICASGENERALESMANIFIESTO();
        CLEARCONDUCTOR();
        CLEARCONDUCTOR2();
        CLEARDATOSREMOLQUE1();
        CLEARDATOSREMOLQUE2();
        CLEARTITULARMANIFIESTO();
        clearValorDeViaje();
        CLEARVEHICULO();
    }

    private void fillValuesAdicionarRemesas() {
        int iCantidadRemesas = consecutivos.size();
        cantidadRemesas.setText(String.valueOf(iCantidadRemesas));

        int sumaConsecutivos = consecutivos.size();
        cantidadRemesas.setText(String.valueOf(sumaConsecutivos));

        int sumaKilosCargados = 0;
        try {
            for (int i = 0; i < consecutivos.size(); i++) {
                String data = UniversalDAO.getString(""
                        + "select remesa.CANTIDADCARGADA "
                        + "from remesa "
                        + "where remesa.CONSECUTIVOREMESA = '" + consecutivos.get(i) + "' "
                        + "and remesa.UNIDADMEDIDACAPACIDAD='1'", conn);
                int iCantidadCargada = Integer.valueOf(data);
                sumaKilosCargados += iCantidadCargada;
            }
            kilogramosRemesas.setText(String.valueOf(sumaKilosCargados));
        } catch (Exception e) {
            kilogramosRemesas.setText("0");
        }

        int sumaGalonesCargados = 0;
        try {
            for (int i = 0; i < consecutivos.size(); i++) {
                String data = UniversalDAO.getString(""
                        + "select remesa.CANTIDADCARGADA "
                        + "from remesa "
                        + "where remesa.CONSECUTIVOREMESA = '" + consecutivos.get(i) + "' "
                        + "and remesa.UNIDADMEDIDACAPACIDAD='2'", conn);
                int iCantidadCargada = Integer.valueOf(data);
                sumaGalonesCargados += iCantidadCargada;
            }
            galonesCargados.setText(String.valueOf(sumaGalonesCargados));
        } catch (Exception e) {
            galonesCargados.setText("0");
        }

        int horasTotalCargue = 0;
        int minutosTotalCargue = 0;
        try {
            for (int i = 0; i < consecutivos.size(); i++) {
                String[] values = {"remesa.HORASPACTOCARGA", "remesa.MINUTOSPACTOCARGA"};
                for (int j = 0; j < 2; j++) {
                    String value = UniversalDAO.getString(""
                            + "select " + values[j] + " "
                            + "from remesa "
                            + "where remesa.CONSECUTIVOREMESA='" + consecutivos.get(i) + "'", conn);
                    if (j == 0) {
                        int iValue = Integer.valueOf(value);
                        horasTotalCargue += iValue;
                    } else {
                        int iValue = Integer.valueOf(value);
                        minutosTotalCargue += iValue;
                    }
                }
            }
            horasTotalesCargue.setText(String.valueOf(horasTotalCargue));
            minutosTotalesCargue.setText(String.valueOf(minutosTotalCargue));
        } catch (Exception e) {
            horasTotalesCargue.setText("0");
            minutosTotalesCargue.setText("0");
        }

        int horasTotalDescargue = 0;
        int minutosTotalDescargue = 0;
        try {
            for (int i = 0; i < consecutivos.size(); i++) {
                String[] values = {"remesa.HORASPACTODESCARGUE", "remesa.MINUTOSPACTODESCARGUE"};
                for (int j = 0; j < 2; j++) {
                    String value = UniversalDAO.getString(""
                            + "select " + values[j] + " "
                            + "from remesa "
                            + "where remesa.CONSECUTIVOREMESA='" + consecutivos.get(i) + "'", conn);
                    if (j == 0) {
                        int iValue = Integer.valueOf(value);
                        horasTotalDescargue += iValue;
                    } else {
                        int iValue = Integer.valueOf(value);
                        minutosTotalDescargue += iValue;
                    }
                }
            }
            horasTotalesDescargue.setText(String.valueOf(horasTotalDescargue));
            minutosTotalesDescargue.setText(String.valueOf(minutosTotalDescargue));
        } catch (Exception e) {
            horasTotalesDescargue.setText("0");
            minutosTotalesDescargue.setText("0");
        }

    }

    private void clearValorDeViaje() {
        VALORFLETEPACTADOVIAJE.setText("");
        retencionEnLaFuente.setText("");
        retencionICA.setText("");
        netoPagar.setText("");
        VALORANTICIPOMANIFIESTO.setText("");
        saldoPorPagar.setText("");
        RETENCIONICAMANIFIESTOCARGA_U.setValue(0);
        RETENCIONICAMANIFIESTOCARGA_D.setValue(0);

        MUNICIPIOPAGOSALDO.setText("");
        CODMUNICIPIOPAGOSALDO.setText("");
        FECHAPAGOSALDOMANIFIESTO.setDate(null);
        RESPONSABLEPAGOCARGUE.setSelectedItem(null);
        RESPONSABLEPAGODESCARGUE.setSelectedItem(null);
        CODRESPONSABLEPAGOCARGUE.setText("");
        CODRESPONSABLEPAGODESCARGUE.setText("");
        OBSERVACIONES.setText("");

    }

    private void calcularValorDeViaje(Boolean retefuente) {
        try {
            int valorViaje = Integer.valueOf(VALORFLETEPACTADOVIAJE.getText());

            int anticipo = 0;
            if (!VALORANTICIPOMANIFIESTO.getText().equals("")) {
                anticipo = Integer.valueOf(VALORANTICIPOMANIFIESTO.getText());
            }

            float porReteICA = 0;

            String v = RETENCIONICAMANIFIESTOCARGA_U.getValue().toString()
                    + "."
                    + RETENCIONICAMANIFIESTOCARGA_D.getValue().toString();
            porReteICA = Float.valueOf(v);

            int reteICA = (int) (valorViaje * (porReteICA * 0.001));
            retencionICA.setText(String.valueOf(reteICA));

            int vRetefuente = (int) (valorViaje * (0.01));
//            int vRetefuenteField = Integer.valueOf(retencionEnLaFuente.getText());
            if (retefuente) {
                vRetefuente = Integer.valueOf(retencionEnLaFuente.getText());
            }
            retencionEnLaFuente.setText(String.valueOf(vRetefuente));

            int netoPagar = valorViaje - (vRetefuente + reteICA);
            this.netoPagar.setText(String.valueOf(netoPagar));

            int saldoPorPagar = netoPagar - anticipo;
            this.saldoPorPagar.setText(String.valueOf(saldoPorPagar));

        } catch (Exception e) {
            clearValorDeViaje();
        }
    }

    private void CLEARDATOSREMOLQUE2() {
        NUMPLACAREMOLQUE2.setText("");
        MARCAREMOLQUE2.setText("");
        CODMARCAREMOLQUE2.setText("");
        CONFIGURACIONREMOLQUE2.setText("");
        CODCONFIGURACIONREMOLQUE2.setText("");
        PESOVEHICULOVACIOREMOLQUE2.setText("");
        UNIDADCAPACIDADREMOLQUE2.setText("");
        if (CODCONFIGURACIONRESULTANTE.getText().length() >= 2) {
            String pre = CODCONFIGURACIONRESULTANTE.getText();
            pre = pre.substring(0, pre.length() - 2);
            CODCONFIGURACIONRESULTANTE.setText(pre);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormManifiesto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManifiesto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManifiesto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManifiesto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormManifiesto("testingTT", new DataSourceClass(), new DataSourceClass().conectar(1) ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ANOFABRICACIONVEHICULOCARGA;
    private javax.swing.JTextField CATEGORIACONDUCTOR;
    private javax.swing.JTextField CATEGORIACONDUCTOR2;
    private javax.swing.JButton CLEARCARACTERISTICASGENERALESMANIFIESTO;
    private javax.swing.JButton CLEARDATOSREMOLQUE1;
    private javax.swing.JButton CLEARDATOSREMOLQUE2;
    private javax.swing.JButton CLEARTITULARMANIFIESTO;
    private javax.swing.JTextField CODCONFIGURACIONREMOLQUE1;
    private javax.swing.JTextField CODCONFIGURACIONREMOLQUE2;
    private javax.swing.JTextField CODCONFIGURACIONRESULTANTE;
    private javax.swing.JTextField CODIDCONDUCTOR;
    private javax.swing.JTextField CODIDCONDUCTOR2;
    private javax.swing.JTextField CODIDTITULARMANIFIESTO;
    private javax.swing.JTextField CODMARCAREMOLQUE1;
    private javax.swing.JTextField CODMARCAREMOLQUE2;
    private javax.swing.JTextField CODMARCAVEHICULOCARGA;
    private javax.swing.JTextField CODMUNICIPIOCONDUCTOR;
    private javax.swing.JTextField CODMUNICIPIOCONDUCTOR2;
    private javax.swing.JTextField CODMUNICIPIODESTINOMANIFIESTO;
    private javax.swing.JTextField CODMUNICIPIOORIGENMANIFIESTO;
    private javax.swing.JTextField CODMUNICIPIOPAGOSALDO;
    private javax.swing.JTextField CODMUNICIPIOTENEDORVEHICULOCARGA;
    private javax.swing.JTextField CODOPERACIONTRANSPORTE;
    private javax.swing.JTextField CODRESPONSABLEPAGOCARGUE;
    private javax.swing.JTextField CODRESPONSABLEPAGODESCARGUE;
    private javax.swing.JTextField CODSEDETITULARMANIFIESTO;
    private javax.swing.JTextField CODTIPOIDTENEDORVEHICULOCARGA;
    private javax.swing.JTextField CONFIGURACIONREMOLQUE1;
    private javax.swing.JTextField CONFIGURACIONREMOLQUE2;
    private javax.swing.JTextField CONFIGURACIONVEHICULOCARGA;
    private com.toedter.calendar.JDateChooser FECHAEXPEDICIONMANIFIESTO;
    private com.toedter.calendar.JDateChooser FECHAPAGOSALDOMANIFIESTO;
    private javax.swing.JTextField FECHAVENCIMIENTOSOATVEHICULOCARGA;
    private javax.swing.JComboBox<String> IDCONDUCTOR;
    private javax.swing.JComboBox<String> IDCONDUCTOR2;
    private javax.swing.JComboBox<String> IDTITULARMANIFIESTO;
    private javax.swing.JTextField MARCAREMOLQUE1;
    private javax.swing.JTextField MARCAREMOLQUE2;
    private javax.swing.JTextField MARCAVEHICULOCARGA;
    private javax.swing.JTextField MUNICIPIOCONDUCTOR;
    private javax.swing.JTextField MUNICIPIOCONDUCTOR2;
    private javax.swing.JTextField MUNICIPIODESTINOMANIFIESTO;
    private javax.swing.JTextField MUNICIPIOORIGENMANIFIESTO;
    private javax.swing.JTextField MUNICIPIOPAGOSALDO;
    private javax.swing.JTextField MUNICIPIOTENEDORVEHICULOCARGA;
    private javax.swing.JTextField MUNICIPIOTITULARMANIFIESTO;
    private javax.swing.JTextField NOMASEGURADORASOAT;
    private javax.swing.JTextField NOMBREIDTITULARMANIFIESTO;
    private javax.swing.JTextField NOMCONDUCTOR;
    private javax.swing.JTextField NOMCONDUCTOR2;
    private javax.swing.JTextField NOMENCLATURADIRECCIONCONDUCTOR;
    private javax.swing.JTextField NOMENCLATURADIRECCIONCONDUCTOR2;
    private javax.swing.JTextField NOMENCLATURADIRECCIONTENEDORVEHICULOCARGA;
    private javax.swing.JTextField NOMENCLATURADIRECCIONTITULARMANIFIESTO;
    private javax.swing.JTextField NUMIDCONDUCTOR;
    private javax.swing.JTextField NUMIDCONDUCTOR2;
    private javax.swing.JTextField NUMIDTENEDORVEHICULOCARGA;
    private javax.swing.JFormattedTextField NUMIDTITULARMANIFIESTO;
    private javax.swing.JTextField NUMLICENCIACONDUCTOR;
    private javax.swing.JTextField NUMLICENCIACONDUCTOR2;
    private javax.swing.JLabel NUMMANIFIESTOCARGA;
    private javax.swing.JLabel NUMORDENCARGUE;
    private javax.swing.JTextField NUMPLACA;
    private javax.swing.JTextField NUMPLACAREMOLQUE1;
    private javax.swing.JTextField NUMPLACAREMOLQUE2;
    private javax.swing.JTextField NUMSEGUROSOAT;
    private javax.swing.JTextArea OBSERVACIONES;
    private javax.swing.JComboBox<String> OPERACIONTRANSPORTE;
    private javax.swing.JTextField PESOVACIOVEHICULOCARGA;
    private javax.swing.JTextField PESOVEHICULOVACIOREMOLQUE1;
    private javax.swing.JTextField PESOVEHICULOVACIOREMOLQUE2;
    private javax.swing.JComboBox<String> RESPONSABLEPAGOCARGUE;
    private javax.swing.JComboBox<String> RESPONSABLEPAGODESCARGUE;
    private javax.swing.JSpinner RETENCIONICAMANIFIESTOCARGA_D;
    private javax.swing.JSpinner RETENCIONICAMANIFIESTOCARGA_U;
    private javax.swing.JComboBox<String> SEDETITULARMANIFIESTO;
    private javax.swing.JTextField TELEFONOCONDUCTOR;
    private javax.swing.JTextField TELEFONOCONDUCTOR2;
    private javax.swing.JTextField TELEFONOSEDETITULARMANIFIESTO;
    private javax.swing.JTextField TENEDORVEHICULOCARGA;
    private javax.swing.JTextField UNIDADCAPACIDADREMOLQUE1;
    private javax.swing.JTextField UNIDADCAPACIDADREMOLQUE2;
    private javax.swing.JTextField UNIDADCAPACIDADVEHICULOCARGA;
    private javax.swing.JTextField VALORANTICIPOMANIFIESTO;
    private javax.swing.JTextField VALORFLETEPACTADOVIAJE;
    private javax.swing.JTextField VENCIMIENTOLICENCIACONDUCTOR;
    private javax.swing.JTextField VENCIMIENTOLICENCIACONDUCTOR2;
    private javax.swing.JMenuItem acercaDe;
    private javax.swing.JButton adicionarRemesa;
    private javax.swing.JMenu ayuda;
    private javax.swing.JTextField cantidadRemesas;
    private javax.swing.JButton cleanRemesas;
    private javax.swing.JButton connectionStatus;
    private javax.swing.JTextField fieldMUNICIPIOTITULARMANIFIESTO;
    private javax.swing.JTextField galonesCargados;
    private javax.swing.JTextField horasTotalesCargue;
    private javax.swing.JTextField horasTotalesDescargue;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTextField kilogramosRemesas;
    private javax.swing.JButton limpiarCaracteristicas;
    private javax.swing.JMenuItem manifiesto;
    private javax.swing.JTextField minutosTotalesCargue;
    private javax.swing.JTextField minutosTotalesDescargue;
    private javax.swing.JTextField netoPagar;
    private javax.swing.JMenuItem registrar;
    private javax.swing.JMenuItem registrarTerceros;
    private javax.swing.JMenuItem registrarVehiculos;
    private javax.swing.JMenuItem remesa;
    private javax.swing.JComboBox<String> remesaAdicionar;
    private javax.swing.JTextField retencionEnLaFuente;
    private javax.swing.JTextField retencionICA;
    private javax.swing.JTextField saldoPorPagar;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTable tableRemesas;
    private javax.swing.JButton viewManifiestos;
    // End of variables declaration//GEN-END:variables
}
