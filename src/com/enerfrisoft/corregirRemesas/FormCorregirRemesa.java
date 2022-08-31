/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.corregirRemesas;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.remesa.*;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.enerfrisoft.tools.Visual;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.enerfrisoft.error.QueryError;
import com.enerfrisoft.manifiesto.ManifiestoDAO;
import com.enerfrisoft.modal.Modal;
import com.enerfrisoft.openForm.OpenForm;
import com.enerfrisoft.tools.Data;
import com.enerfrisoft.tools.DateParsing;
import com.enerfrisoft.tools.Items;
import com.enerfrisoft.tools.UniversalDAO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class FormCorregirRemesa extends javax.swing.JFrame {

    private TextAutoCompleter acNUMIDREMITENTE;
    private TextAutoCompleter acNUMIDPROPIETARIO;
    private TextAutoCompleter acNUMIDDESTINATARIO;
    private TextAutoCompleter acMERCANCIAREMESA;
    private TextAutoCompleter acBUSCARNOMBRECARGA;
    private TextAutoCompleter acBUSCARASEGURADORA;
    private TextAutoCompleter acCONSECUTIVOREMESA;
    
    private String username;
    private Boolean allowRecords;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    /**
     * Creates new form FormRemesa
     */
    public FormCorregirRemesa(DataSourceClass dataSource,Connection conn, String username) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        this.username = username;
        this.allowRecords = true;
        initComponents();
        buildAutoCompleterNUMIDREMITENTE();
        buildAutoCompleterNUMIDPROPIETARIO();
        buildAutoCompleterNUMIDDESTINATARIO();
        buildAutoCompleterMERCANCIAREMESA();
        buildAutoCompleterBUSCARNOMBRECARGA();
        buildAutoCompleterBUSCARASEGURADORA();
        Visual.windowIcon(this);
        connectionStatus.doClick();
//        CONSECUTIVOREMESA.setText(UniversalDAO.getString(""
//                + "select concat(conPalRem,conNumRem) "
//                + "from server "
//                + "where server.id='"+String.valueOf(conn)+"'", defaultServerConn));
        //verifyConsecutivo();
        setEnabledAllFields(false);
        buildAutoCompleterCONSECUTIVOREMESA();
    }

    private void fillFields(String CONSECUTIVOREMESA) {
        Map<String, String> data = new HashMap<String, String>() {
        };
        data = UniversalDAO.getRow(""
                + "call get_remesa('" + CONSECUTIVOREMESA + "')", conn);

        try {
            Items.setValue(CODOPERACIONTRANSPORTE, data.get("CODOPERACIONTRANSPORTE"));
            Items.setValue(CODTIPOEMPAQUE, data.get("CODTIPOEMPAQUE"));
            Items.setValue(CONSECUTIVOCARGADIVIDIDA, data.get("CONSECUTIVOCARGADIVIDIDA"));
            Items.setValue(CODNATURALEZACARGA, data.get("CODNATURALEZACARGA"));
            Items.setValue(PERMISOCARGAEXTRA, data.get("PERMISOCARGAEXTRA"));
            Items.setValue(DESCRIPCIONCORTAPRODUCTO, data.get("DESCRIPCIONCORTAPRODUCTO"));
            Items.setValue(CLASIFICACIONCARGA, data.get("CLASIFICACIONCARGA"));
            Items.setValue(NOMBRECARGA, data.get("NOMBRECARGA"));
            Items.setValue(BUSCARNOMBRECARGA, data.get("BUSCARNOMBRECARGA"));
            Items.setValue(MERCANCIAREMESA, data.get("MERCANCIAREMESA"));
            callbackMERCANCIAREMESA(MERCANCIAREMESA.getText());
            Items.setValue(CANTIDADCARGADA, data.get("CANTIDADCARGADA"));
            Items.setValue(PESOCONTENEDORVACIO, data.get("PESOCONTENEDORVACIO"));
            Items.setValue(UNIDADMEDIDACAPACIDAD, data.get("UNIDADMEDIDACAPACIDAD"));
            
            //Remitente
            Items.setValue(TIPOIDREMITENTE, data.get("CODTIPOIDREMITENTE"));
            Items.setValue(NUMIDREMITENTE, data.get("NUMIDREMITENTE"));
            callbackNUMIDREMITENTE(data.get("NUMIDREMITENTE"));
            Items.setValue(CODSEDEREMITENTE, data.get("CODSEDEREMITENTE"));

            Items.setValue(FECHACITAPACTADADESCARGUE, data.get("FECHACITAPACTADADESCARGUE"));
            Items.setValue(HORACITAPACTADADESCARGUEREMESA, data.get("HORACITAPACTADADESCARGUEREMESA"));
            Items.setValue(NUMIDGPS, data.get("NUMIDGPS"));
            Items.setValue(OBSERVACIONES, data.get("OBSERVACIONES"));
            
            //Propietario
            Items.setValue(TIPOIDPROPIETARIO, data.get("CODTIPOIDPROPIETARIO"));
            Items.setValue(NUMIDPROPIETARIO, data.get("NUMIDPROPIETARIO"));
            callbackNUMIDPROPIETARIO(data.get("NUMIDPROPIETARIO"));
            Items.setValue(CODSEDEPROPIETARIO, data.get("CODSEDEPROPIETARIO"));
            
            Items.setValue(DUENOPOLIZA, data.get("DUENOPOLIZA"));
            Items.setValue(NUMPOLIZATRANSPORTE, data.get("NUMPOLIZATRANSPORTE"));
            Items.setValue(COMPANIASEGURO, data.get("COMPANIASEGURO"));
            Items.setValue(FECHAVENCIMIENTOPOLIZACARGA, data.get("FECHAVENCIMIENTOPOLIZACARGA"));
            Items.setValue(BUSCARASEGURADORA, data.get("BUSCARASEGURADORA"));
            
            //Destinatario
            Items.setValue(TIPOIDDESTINATARIO, data.get("CODTIPOIDDESTINATARIO"));
            Items.setValue(NUMIDDESTINATARIO, data.get("NUMIDDESTINATARIO"));
            callbackNUMIDDESTINATARIO(data.get("NUMIDDESTINATARIO"));
            Items.setValue(CODSEDEDESTINATARIO, data.get("CODSEDEDESTINATARIO"));
            
            Items.setValue(FECHACITAPACTADACARGUE, data.get("FECHACITAPACTADACARGUE"));
            Items.setValue(HORACITAPACTADACARGUE, data.get("HORACITAPACTADACARGUE"));
            Items.setValue(FECHALLEGADACARGUE, data.get("FECHALLEGADACARGUE"));
            Items.setValue(HORALLEGADACARGUEREMESA, data.get("HORALLEGADACARGUEREMESA"));
            Items.setValue(FECHAENTRADACARGUE, data.get("FECHAENTRADACARGUE"));
            Items.setValue(HORAENTRADACARGUEREMESA, data.get("HORAENTRADACARGUEREMESA"));
            Items.setValue(FECHASALIDACARGUE, data.get("FECHASALIDACARGUE"));
            Items.setValue(HORASALIDACARGUEREMESA, data.get("HORASALIDACARGUEREMESA"));
            Items.setValue(HORASPACTOCARGA, data.get("HORASPACTOCARGA"));
            Items.setValue(MINUTOSPACTOCARGA, data.get("MINUTOSPACTOCARGA"));
            Items.setValue(HORASPACTODESCARGUE, data.get("HORASPACTODESCARGUE"));
            Items.setValue(MINUTOSPACTODESCARGUE, data.get("MINUTOSPACTODESCARGUE"));
        } catch (Exception e) {
            System.out.println("something went wrong");
        }

    }

    private void buildAutoCompleterCONSECUTIVOREMESA() {
        acCONSECUTIVOREMESA = new TextAutoCompleter(CONSECUTIVOREMESA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                setEnabledAllFields(true);
                fillFields(o.toString());
            }

        });

        ArrayList<String> data = new ArrayList<>();
        data = com.enerfrisoft.tools.UniversalDAO.getArrayList(""
                + "select remesa.CONSECUTIVOREMESA "
                + "from remesa "
                + "where remesa.cumplida = false "
                + "and remesa.estado = 'activo' "
                + "order by remesa.dateLog DESC", conn);

        for (int i = 0; i < data.size(); i++) {
            acCONSECUTIVOREMESA.addItem(data.get(i));
        }

    }

    private void setEnabledAllFields(Boolean flag) {
        CLEARCARACTERISTICASMERCANCIA.setEnabled(flag);
        CODOPERACIONTRANSPORTE.setEnabled(flag);
        CODTIPOEMPAQUE.setEnabled(flag);
        CONSECUTIVOCARGADIVIDIDA.setEnabled(flag);

        CLEARPRODUCTO.setEnabled(flag);
        CODNATURALEZACARGA.setEnabled(flag);
        PERMISOCARGAEXTRA.setEnabled(flag);
        DESCRIPCIONCORTAPRODUCTO.setEnabled(flag);
        CLASIFICACIONCARGA.setEnabled(flag);
        NOMBRECARGA.setEnabled(flag);
        BUSCARNOMBRECARGA.setEnabled(flag);
        jButton1.setEnabled(flag);
        MERCANCIAREMESA.setEnabled(flag);
        CANTIDADCARGADA.setEnabled(flag);
        PESOCONTENEDORVACIO.setEnabled(flag);
        UNIDADMEDIDACAPACIDAD.setEnabled(flag);

        CLEARREMITENTE.setEnabled(flag);
        TIPOIDREMITENTE.setEnabled(flag);
        NUMIDREMITENTE.setEnabled(flag);

        CLEARTIEMPOSDESTINO.setEnabled(flag);
        FECHACITAPACTADADESCARGUE.setEnabled(flag);
        HORACITAPACTADADESCARGUEREMESA.setEnabled(flag);
        NUMIDGPS.setEnabled(flag);

        CLEAROBSERVACIONES.setEnabled(flag);
        OBSERVACIONES.setEnabled(flag);

        CLEARPROPIETARIO.setEnabled(flag);
        TIPOIDPROPIETARIO.setEnabled(flag);
        NUMIDPROPIETARIO.setEnabled(flag);

        CLEARSEGURO.setEnabled(flag);
        DUENOPOLIZA.setEnabled(flag);
        NUMPOLIZATRANSPORTE.setEnabled(flag);
        COMPANIASEGURO.setEnabled(flag);
        FECHAVENCIMIENTOPOLIZACARGA.setEnabled(flag);
        BUSCARASEGURADORA.setEnabled(flag);
        CLEARASEGURADORA.setEnabled(flag);

        CLEARDESTINATARIO.setEnabled(flag);
        TIPOIDDESTINATARIO.setEnabled(flag);
        NUMIDDESTINATARIO.setEnabled(flag);

        CLEARTIEMPOSORIGEN.setEnabled(flag);
        FECHACITAPACTADACARGUE.setEnabled(flag);
        HORACITAPACTADACARGUE.setEnabled(flag);
        FECHALLEGADACARGUE.setEnabled(flag);
        HORALLEGADACARGUEREMESA.setEnabled(flag);
        FECHAENTRADACARGUE.setEnabled(flag);
        HORAENTRADACARGUEREMESA.setEnabled(flag);
        FECHASALIDACARGUE.setEnabled(flag);
        HORASALIDACARGUEREMESA.setEnabled(flag);
        HORASPACTOCARGA.setEnabled(flag);
        MINUTOSPACTOCARGA.setEnabled(flag);
        HORASPACTODESCARGUE.setEnabled(flag);
        MINUTOSPACTODESCARGUE.setEnabled(flag);

    }

    private void verifyConsecutivo() {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        int valueConNumRem = Integer.valueOf(UniversalDAO.getString(""
                + "select conNumRem "
                + "from server "
                + "where server.id = '" + String.valueOf(conn) + "'", defaultServerConn));
        int valueMaxConNumRem = Integer.valueOf(UniversalDAO.getString(""
                + "select maxconNumRem "
                + "from server "
                + "where server.id = '" + String.valueOf(conn) + "'", defaultServerConn));
        int valueAvisoConNumRem = Integer.valueOf(UniversalDAO.getString(""
                + "select avisoconNumRem "
                + "from server "
                + "where server.id = '" + String.valueOf(conn) + "'", defaultServerConn));
        if (valueConNumRem < valueMaxConNumRem) {
            if (valueMaxConNumRem - valueAvisoConNumRem <= valueConNumRem) {
                Modal.show("Alerta", "Quedan " + String.valueOf(valueMaxConNumRem - valueConNumRem) + " consecutivos disponibles para remesas", this, "", "warning");
            }
        } else {
            Modal.show("Error", "No hay consecutivos disponibles para remesas", this, "", "error");
            this.allowRecords = false;
            this.dispose();
        }
    }

    private void callbackNUMIDREMITENTE(Object o) {
        String nombreCompleto = UniversalDAO.getString(""
                + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
        NOMIDREMITENTE.setText(nombreCompleto);
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select concat(CODSEDETERCERO,' - ',NOMSEDETERCERO,' - ',NOMENCLATURADIRECCION) "
                + "from sede where NUMIDTERCERO='" + o.toString() + "'", conn);
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < data.size(); i++) {
            model.addElement(data.get(i));
        }
        CODSEDEREMITENTE.setEnabled(true);
        CODSEDEREMITENTE.setModel(model);
        CODSEDEREMITENTE.setSelectedItem(null);
    }

    private void buildAutoCompleterNUMIDREMITENTE() {
        acNUMIDREMITENTE = new TextAutoCompleter(NUMIDREMITENTE, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                callbackNUMIDREMITENTE(o);
            }
        });
    }

    private void callbackNUMIDPROPIETARIO(Object o) {
        String nombreCompleto = UniversalDAO.getString(""
                + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
        NOMIDPROPIETARIO.setText(nombreCompleto);
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select concat(CODSEDETERCERO,' - ',NOMSEDETERCERO,' - ',NOMENCLATURADIRECCION) "
                + "from sede where NUMIDTERCERO='" + o.toString() + "'", conn);
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < data.size(); i++) {
            model.addElement(data.get(i));
        }
        CODSEDEPROPIETARIO.setEnabled(true);
        CODSEDEPROPIETARIO.setModel(model);
        CODSEDEPROPIETARIO.setSelectedItem(null);
    }

    private void buildAutoCompleterNUMIDPROPIETARIO() {
        acNUMIDPROPIETARIO = new TextAutoCompleter(NUMIDPROPIETARIO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                callbackNUMIDPROPIETARIO(o);
            }
        });
    }

    private void buildAutoCompleterNUMIDDESTINATARIO() {
        acNUMIDDESTINATARIO = new TextAutoCompleter(NUMIDDESTINATARIO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                callbackNUMIDDESTINATARIO(o);
            }
        });
    }

    private void callbackNUMIDDESTINATARIO(Object o) {
        String nombreCompleto = UniversalDAO.getString(""
                + "select concat(NOMIDTERCERO,' ',PRIMERAPELLIDOIDTERCERO,' ',SEGUNDOAPELLIDOIDTERCERO) "
                + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
        NOMIDDESTINATARIO.setText(nombreCompleto);
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select concat(CODSEDETERCERO,' - ',NOMSEDETERCERO,' - ',NOMENCLATURADIRECCION) "
                + "from sede where NUMIDTERCERO='" + o.toString() + "'", conn);
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < data.size(); i++) {
            model.addElement(data.get(i));
        }
        CODSEDEDESTINATARIO.setEnabled(true);
        CODSEDEDESTINATARIO.setModel(model);
        CODSEDEDESTINATARIO.setSelectedItem(null);
    }

    private void callbackMERCANCIAREMESA(String o) {
        String sCLASIFICACIONCARGA = UniversalDAO.getString(""
                + "SELECT clasificacion "
                + "from nombre_producto "
                + "where cod='" + o + "';", conn);
        String sNOMBRECARGA = UniversalDAO.getString(""
                + "select nombre "
                + "from nombre_producto "
                + "where cod='" + o + "'", conn);
        String sCODNATURALEZACARGA = UniversalDAO.getString(""
                + "select naturaleza "
                + "from clasificacion_producto "
                + "where nombre='" + sCLASIFICACIONCARGA + "'", conn);
        ArrayList<String> aCLASIFICACIONCARGA = UniversalDAO.getArrayList(""
                + "SELECT nombre "
                + "from clasificacion_producto", conn);
        DefaultComboBoxModel dcbmCLASIFICACIONCARGA = new DefaultComboBoxModel();
        for (int i = 0; i < aCLASIFICACIONCARGA.size(); i++) {
            dcbmCLASIFICACIONCARGA.addElement(aCLASIFICACIONCARGA.get(i));
        }
        CLASIFICACIONCARGA.setModel(dcbmCLASIFICACIONCARGA);

        ArrayList<String> aNOMBRECARGA = UniversalDAO.getArrayList(
                "select nombre from nombre_producto", conn);
        DefaultComboBoxModel dcbmNOMBRECARGA = new DefaultComboBoxModel();
        for (int i = 0; i < aNOMBRECARGA.size(); i++) {
            dcbmNOMBRECARGA.addElement(aNOMBRECARGA.get(i));
        }
        NOMBRECARGA.setModel(dcbmNOMBRECARGA);

        ArrayList<String> aCODNATURALEZACARGA = UniversalDAO.getArrayList(
                "SELECT nombre from naturaleza_carga", conn);
        DefaultComboBoxModel dcbmCODNATURALEZACARGA = new DefaultComboBoxModel();
        for (int i = 0; i < aCODNATURALEZACARGA.size(); i++) {
            dcbmCODNATURALEZACARGA.addElement(aCODNATURALEZACARGA.get(i));
        }
        CODNATURALEZACARGA.setModel(dcbmCODNATURALEZACARGA);

        CODNATURALEZACARGA.setEnabled(true);
        CODNATURALEZACARGA.setSelectedItem(sCODNATURALEZACARGA);
        CLASIFICACIONCARGA.setEnabled(true);
        CLASIFICACIONCARGA.setSelectedItem(sCLASIFICACIONCARGA);
        NOMBRECARGA.setEnabled(true);
        NOMBRECARGA.setSelectedItem(sNOMBRECARGA);
    }

    private void buildAutoCompleterMERCANCIAREMESA() {
        acMERCANCIAREMESA = new TextAutoCompleter(MERCANCIAREMESA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                callbackMERCANCIAREMESA(o.toString());
            }
        });
        ArrayList<String> cods = UniversalDAO.getArrayList(""
                + "select cod "
                + "from nombre_producto;", conn);
        for (int i = 0; i < cods.size(); i++) {
            acMERCANCIAREMESA.addItem(cods.get(i));
        }
    }

    private void buildAutoCompleterBUSCARNOMBRECARGA() {
        acBUSCARNOMBRECARGA = new TextAutoCompleter(BUSCARNOMBRECARGA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String sCod = UniversalDAO.getString(""
                        + "select cod "
                        + "from nombre_producto "
                        + "where nombre='" + o.toString() + "';", conn);
                String sCLASIFICACIONCARGA = UniversalDAO.getString(""
                        + "SELECT clasificacion "
                        + "from nombre_producto "
                        + "where cod='" + sCod + "';", conn);
                String sNOMBRECARGA = UniversalDAO.getString(""
                        + "select nombre "
                        + "from nombre_producto "
                        + "where cod='" + sCod + "'", conn);
                String sCODNATURALEZACARGA = UniversalDAO.getString(""
                        + "select naturaleza "
                        + "from clasificacion_producto "
                        + "where nombre='" + sCLASIFICACIONCARGA + "'", conn);
                ArrayList<String> aCLASIFICACIONCARGA = UniversalDAO.getArrayList(""
                        + "SELECT nombre "
                        + "from clasificacion_producto", conn);
                DefaultComboBoxModel dcbmCLASIFICACIONCARGA = new DefaultComboBoxModel();
                for (int i = 0; i < aCLASIFICACIONCARGA.size(); i++) {
                    dcbmCLASIFICACIONCARGA.addElement(aCLASIFICACIONCARGA.get(i));
                }
                CLASIFICACIONCARGA.setModel(dcbmCLASIFICACIONCARGA);

                ArrayList<String> aNOMBRECARGA = UniversalDAO.getArrayList(
                        "select nombre from nombre_producto", conn);
                DefaultComboBoxModel dcbmNOMBRECARGA = new DefaultComboBoxModel();
                for (int i = 0; i < aNOMBRECARGA.size(); i++) {
                    dcbmNOMBRECARGA.addElement(aNOMBRECARGA.get(i));
                }
                NOMBRECARGA.setModel(dcbmNOMBRECARGA);

                ArrayList<String> aCODNATURALEZACARGA = UniversalDAO.getArrayList(
                        "SELECT nombre from naturaleza_carga", conn);
                DefaultComboBoxModel dcbmCODNATURALEZACARGA = new DefaultComboBoxModel();
                for (int i = 0; i < aCODNATURALEZACARGA.size(); i++) {
                    dcbmCODNATURALEZACARGA.addElement(aCODNATURALEZACARGA.get(i));
                }
                CODNATURALEZACARGA.setModel(dcbmCODNATURALEZACARGA);

                CODNATURALEZACARGA.setEnabled(true);
                CODNATURALEZACARGA.setSelectedItem(sCODNATURALEZACARGA);
                CLASIFICACIONCARGA.setEnabled(true);
                CLASIFICACIONCARGA.setSelectedItem(sCLASIFICACIONCARGA);
                NOMBRECARGA.setEnabled(true);
                NOMBRECARGA.setSelectedItem(sNOMBRECARGA);
            }
        });
        ArrayList<String> names = UniversalDAO.getArrayList(""
                + "select nombre "
                + "from nombre_producto;", conn);
        for (int i = 0; i < names.size(); i++) {
            acBUSCARNOMBRECARGA.addItem(names.get(i));
        }
        acBUSCARNOMBRECARGA.setMode(0);

    }

    private void FillNUMIDTERCERO(JComboBox TIPOIDTERCERO,
            TextAutoCompleter acNUMIDTERCERO,
            JTextField NUMIDTERCERO,
            JTextField NOMIDTERCERO,
            JTextField field
    ) {
        if (TIPOIDTERCERO.getSelectedItem() != null) {
            NUMIDTERCERO.setText("");
            NOMIDTERCERO.setText("");
            String TIPOIDTERCERO_value = TIPOIDTERCERO.getSelectedItem().toString();
            String COD = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_id "
                    + "where nombre='" + TIPOIDTERCERO_value + "';", conn);
            field.setText(COD);
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

    private void fillSEDETERCERO(JTextField NUMIDTERCERO, JComboBox CODSEDETERCERO, JTextField NOMENCLATURADIRECCION, JTextField MUNICIPIORNDC, JTextField auxField) {
        if (CODSEDETERCERO.getSelectedItem() != null) {
            String aux = CODSEDETERCERO.getSelectedItem().toString().split(" - ")[0];
            auxField.setText(aux);
            String direccion = UniversalDAO.getString(""
                    + "select sede.NOMENCLATURADIRECCION\n"
                    + "from sede, tercero, municipio\n"
                    + "where\n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + auxField.getText() + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO and\n"
                    + "municipio.id = sede.CODMUNICIPIORNDC", conn);
            String municipio = UniversalDAO.getString(""
                    + "select municipio.nombre\n"
                    + "from sede, tercero, municipio\n"
                    + "where\n"
                    + "tercero.NUMIDTERCERO = '" + NUMIDTERCERO.getText() + "' and\n"
                    + "sede.CODSEDETERCERO = '" + auxField.getText() + "' and\n"
                    + "sede.NUMIDTERCERO = tercero.NUMIDTERCERO and\n"
                    + "municipio.id = sede.CODMUNICIPIORNDC", conn);

            NOMENCLATURADIRECCION.setText(direccion);
            MUNICIPIORNDC.setText(municipio);
        }
    }

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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CODNATURALEZACARGA = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        PERMISOCARGAEXTRA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        DESCRIPCIONCORTAPRODUCTO = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CLASIFICACIONCARGA = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        NOMBRECARGA = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        MERCANCIAREMESA = new javax.swing.JTextField();
        CANTIDADCARGADA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        UNIDADMEDIDACAPACIDAD = new javax.swing.JComboBox<>();
        labelPesoContenedorVacio = new javax.swing.JLabel();
        PESOCONTENEDORVACIO = new javax.swing.JTextField();
        CLEARPRODUCTO = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        BUSCARNOMBRECARGA = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        TIPOIDPROPIETARIO = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        NUMIDPROPIETARIO = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        CODSEDEPROPIETARIO = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        NOMIDPROPIETARIO = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        NOMENCLATURADIRECCIONPROPIETARIO = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        MUNICIPIORNDCPROPIETARIO = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        CLEARPROPIETARIO = new javax.swing.JButton();
        fieldCODSEDEPROPIETARIO = new javax.swing.JTextField();
        fieldCODTIPOIDPROPIETARIO = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        TIPOIDREMITENTE = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        NUMIDREMITENTE = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        CODSEDEREMITENTE = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        NOMIDREMITENTE = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        NOMENCLATURADIRECCIONREMITENTE = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        MUNICIPIORNDCREMITENTE = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        CLEARREMITENTE = new javax.swing.JButton();
        fieldCODSEDEREMITENTE = new javax.swing.JTextField();
        fieldCODTIPOIDREMITENTE = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        TIPOIDDESTINATARIO = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        NUMIDDESTINATARIO = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        CODSEDEDESTINATARIO = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        NOMIDDESTINATARIO = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        NOMENCLATURADIRECCIONDESTINATARIO = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        MUNICIPIORNDCDESTINATARIO = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        CLEARDESTINATARIO = new javax.swing.JButton();
        fieldCODSEDEDESTINATARIO = new javax.swing.JTextField();
        fieldCODTIPOIDDESTINATARIO = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        DUENOPOLIZA = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        NUMPOLIZATRANSPORTE = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        COMPANIASEGURO = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        FECHAVENCIMIENTOPOLIZACARGA = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        BUSCARASEGURADORA = new javax.swing.JTextField();
        CLEARASEGURADORA = new javax.swing.JButton();
        CLEARSEGURO = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        FECHACITAPACTADACARGUE = new com.toedter.calendar.JDateChooser();
        jLabel43 = new javax.swing.JLabel();
        FECHALLEGADACARGUE = new com.toedter.calendar.JDateChooser();
        jLabel44 = new javax.swing.JLabel();
        FECHAENTRADACARGUE = new com.toedter.calendar.JDateChooser();
        jLabel45 = new javax.swing.JLabel();
        FECHASALIDACARGUE = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        HORACITAPACTADACARGUE = new com.github.lgooddatepicker.components.TimePicker();
        HORALLEGADACARGUEREMESA = new com.github.lgooddatepicker.components.TimePicker();
        HORAENTRADACARGUEREMESA = new com.github.lgooddatepicker.components.TimePicker();
        HORASALIDACARGUEREMESA = new com.github.lgooddatepicker.components.TimePicker();
        jLabel11 = new javax.swing.JLabel();
        HORASPACTOCARGA = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        HORASPACTODESCARGUE = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        MINUTOSPACTOCARGA = new javax.swing.JSpinner();
        MINUTOSPACTODESCARGUE = new javax.swing.JSpinner();
        CLEARTIEMPOSORIGEN = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        NUMIDGPS = new javax.swing.JTextField();
        HORACITAPACTADADESCARGUEREMESA = new com.github.lgooddatepicker.components.TimePicker();
        FECHACITAPACTADADESCARGUE = new com.toedter.calendar.JDateChooser();
        CLEARTIEMPOSDESTINO = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        CONSECUTIVOREMESA = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        CODOPERACIONTRANSPORTE = new javax.swing.JComboBox<>();
        CODTIPOEMPAQUE = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        CONSECUTIVOCARGADIVIDIDA = new javax.swing.JTextField();
        CLEARCARACTERISTICASMERCANCIA = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OBSERVACIONES = new javax.swing.JTextArea();
        CLEAROBSERVACIONES = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        connectionStatus = new javax.swing.JButton();
        CLEARALL = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        registrar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        remesa = new javax.swing.JMenuItem();
        manifiesto = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        registrarTerceros = new javax.swing.JMenuItem();
        registrarVehiculos = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        acercaDe = new javax.swing.JMenuItem();

        setTitle("Registro remesa");

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1240, 600));
        jScrollPane1.setRequestFocusEnabled(false);

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));
        jLayeredPane1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 300));

        jLabel1.setText("Producto");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setText("Naturaleza Carga");

        CODNATURALEZACARGA.setModel(RemesaDAO.getNaturalezaCarga(conn));
        CODNATURALEZACARGA.setPrototypeDisplayValue("");
        CODNATURALEZACARGA.setSelectedItem(null);
        CODNATURALEZACARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODNATURALEZACARGAActionPerformed(evt);
            }
        });

        jLabel3.setText("Permiso Carga Extra (INVIAS) ");

        PERMISOCARGAEXTRA.setEnabled(false);
        PERMISOCARGAEXTRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERMISOCARGAEXTRAActionPerformed(evt);
            }
        });

        jLabel4.setText("Descripción Corta Producto\t");

        jLabel5.setText("Clasificación");

        CLASIFICACIONCARGA.setEnabled(false);
        CLASIFICACIONCARGA.setInheritsPopupMenu(true);
        CLASIFICACIONCARGA.setPrototypeDisplayValue("");
        CLASIFICACIONCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLASIFICACIONCARGAActionPerformed(evt);
            }
        });

        jLabel6.setText("Nombre");

        NOMBRECARGA.setEnabled(false);
        NOMBRECARGA.setPrototypeDisplayValue("");
        NOMBRECARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOMBRECARGAActionPerformed(evt);
            }
        });

        jLabel7.setText("Código Producto");

        MERCANCIAREMESA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MERCANCIAREMESAKeyReleased(evt);
            }
        });

        CANTIDADCARGADA.setEnabled(false);

        jLabel9.setText("Cantidad Cargada");

        jLabel10.setText("Unidad Medida");

        UNIDADMEDIDACAPACIDAD.setModel(RemesaDAO.getUnidadMedida(conn));
        UNIDADMEDIDACAPACIDAD.setEnabled(false);
        UNIDADMEDIDACAPACIDAD.setPrototypeDisplayValue("");
        UNIDADMEDIDACAPACIDAD.setSelectedItem(null);

        labelPesoContenedorVacio.setText("Peso contenedor Vacío");

        PESOCONTENEDORVACIO.setEnabled(false);
        PESOCONTENEDORVACIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PESOCONTENEDORVACIOActionPerformed(evt);
            }
        });

        CLEARPRODUCTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARPRODUCTO.setToolTipText("Limpiar los datos");
        CLEARPRODUCTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARPRODUCTOActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/buscar.png"))); // NOI18N
        jLabel8.setText("Búsqueda");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8)))
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(labelPesoContenedorVacio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DESCRIPCIONCORTAPRODUCTO, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PERMISOCARGAEXTRA, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MERCANCIAREMESA, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(PESOCONTENEDORVACIO))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CANTIDADCARGADA)
                                    .addComponent(UNIDADMEDIDACAPACIDAD, 0, 112, Short.MAX_VALUE)))
                            .addComponent(CODNATURALEZACARGA, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CLASIFICACIONCARGA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NOMBRECARGA, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BUSCARNOMBRECARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARPRODUCTO)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(CLEARPRODUCTO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CODNATURALEZACARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(PERMISOCARGAEXTRA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DESCRIPCIONCORTAPRODUCTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CLASIFICACIONCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(NOMBRECARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(BUSCARNOMBRECARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CANTIDADCARGADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(MERCANCIAREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(UNIDADMEDIDACAPACIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PESOCONTENEDORVACIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPesoContenedorVacio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 300));

        jLabel12.setText("Propietario de la Carga");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel58.setText("Tipo Identificación");

        jLabel59.setText("*");
        jLabel59.setForeground(new java.awt.Color(204, 0, 0));

        TIPOIDPROPIETARIO.setModel(ManifiestoDAO.tipo_id(conn));
        TIPOIDPROPIETARIO.setPrototypeDisplayValue("");
        TIPOIDPROPIETARIO.setSelectedItem(null);
        TIPOIDPROPIETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOIDPROPIETARIOActionPerformed(evt);
            }
        });

        jLabel60.setText("Número Identificación");

        jLabel61.setText("Sede");

        CODSEDEPROPIETARIO.setEnabled(false);
        CODSEDEPROPIETARIO.setPrototypeDisplayValue("");
        CODSEDEPROPIETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODSEDEPROPIETARIOActionPerformed(evt);
            }
        });

        jLabel62.setText("Nombre");

        NOMIDPROPIETARIO.setEditable(false);

        jLabel63.setText("Dirección");

        NOMENCLATURADIRECCIONPROPIETARIO.setEditable(false);

        jLabel64.setText("Municipio");

        MUNICIPIORNDCPROPIETARIO.setEditable(false);

        jLabel65.setText("*");
        jLabel65.setForeground(new java.awt.Color(204, 0, 0));

        CLEARPROPIETARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARPROPIETARIO.setToolTipText("Limpiar los datos");
        CLEARPROPIETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARPROPIETARIOActionPerformed(evt);
            }
        });

        fieldCODSEDEPROPIETARIO.setEnabled(false);

        fieldCODTIPOIDPROPIETARIO.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                        .addComponent(CLEARPROPIETARIO))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel60)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NOMIDPROPIETARIO)
                            .addComponent(NOMENCLATURADIRECCIONPROPIETARIO)
                            .addComponent(MUNICIPIORNDCPROPIETARIO)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(NUMIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TIPOIDPROPIETARIO, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CODSEDEPROPIETARIO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldCODSEDEPROPIETARIO, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(fieldCODTIPOIDPROPIETARIO))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARPROPIETARIO)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(TIPOIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODTIPOIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(NUMIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(CODSEDEPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODSEDEPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(NOMIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(NOMENCLATURADIRECCIONPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(MUNICIPIORNDCPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Remitente - Sitio de Cargue");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel22.setText("Tipo Identificación");

        jLabel23.setText("*");
        jLabel23.setForeground(new java.awt.Color(204, 0, 0));

        TIPOIDREMITENTE.setModel(ManifiestoDAO.tipo_id(conn));
        TIPOIDREMITENTE.setPrototypeDisplayValue("");
        TIPOIDREMITENTE.setSelectedItem(null);
        TIPOIDREMITENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOIDREMITENTEActionPerformed(evt);
            }
        });

        jLabel24.setText("Número Identificación");

        jLabel25.setText("Sede");

        CODSEDEREMITENTE.setEnabled(false);
        CODSEDEREMITENTE.setPrototypeDisplayValue("");
        CODSEDEREMITENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODSEDEREMITENTEActionPerformed(evt);
            }
        });

        jLabel26.setText("Nombre");

        NOMIDREMITENTE.setEditable(false);

        jLabel27.setText("Dirección");

        NOMENCLATURADIRECCIONREMITENTE.setEditable(false);

        jLabel28.setText("Municipio");

        MUNICIPIORNDCREMITENTE.setEditable(false);

        jLabel49.setText("*");
        jLabel49.setForeground(new java.awt.Color(204, 0, 0));

        CLEARREMITENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARREMITENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARREMITENTEActionPerformed(evt);
            }
        });

        fieldCODSEDEREMITENTE.setEnabled(false);
        fieldCODSEDEREMITENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCODSEDEREMITENTEActionPerformed(evt);
            }
        });

        fieldCODTIPOIDREMITENTE.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARREMITENTE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NOMIDREMITENTE)
                            .addComponent(NOMENCLATURADIRECCIONREMITENTE)
                            .addComponent(MUNICIPIORNDCREMITENTE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(NUMIDREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 292, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CODSEDEREMITENTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TIPOIDREMITENTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldCODSEDEREMITENTE, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                    .addComponent(fieldCODTIPOIDREMITENTE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARREMITENTE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(TIPOIDREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODTIPOIDREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(NUMIDREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(CODSEDEREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODSEDEREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(NOMIDREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(NOMENCLATURADIRECCIONREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(MUNICIPIORNDCREMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setText("Destinatario - Sitio de Descargue");
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel50.setText("Tipo Identificación");

        jLabel51.setText("*");
        jLabel51.setForeground(new java.awt.Color(204, 0, 0));

        TIPOIDDESTINATARIO.setModel(ManifiestoDAO.tipo_id(conn));
        TIPOIDDESTINATARIO.setPrototypeDisplayValue("");
        TIPOIDDESTINATARIO.setSelectedItem(null);
        TIPOIDDESTINATARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOIDDESTINATARIOActionPerformed(evt);
            }
        });

        jLabel52.setText("Número Identificación");

        jLabel53.setText("Sede");

        CODSEDEDESTINATARIO.setEnabled(false);
        CODSEDEDESTINATARIO.setPrototypeDisplayValue("");
        CODSEDEDESTINATARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODSEDEDESTINATARIOActionPerformed(evt);
            }
        });

        jLabel54.setText("Nombre");

        NOMIDDESTINATARIO.setEditable(false);

        jLabel55.setText("Dirección");

        NOMENCLATURADIRECCIONDESTINATARIO.setEditable(false);

        jLabel56.setText("Municipio");

        MUNICIPIORNDCDESTINATARIO.setEditable(false);

        jLabel57.setText("*");
        jLabel57.setForeground(new java.awt.Color(204, 0, 0));

        CLEARDESTINATARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARDESTINATARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARDESTINATARIOActionPerformed(evt);
            }
        });

        fieldCODSEDEDESTINATARIO.setEnabled(false);

        fieldCODTIPOIDDESTINATARIO.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                        .addComponent(CLEARDESTINATARIO))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MUNICIPIORNDCDESTINATARIO)
                            .addComponent(NOMENCLATURADIRECCIONDESTINATARIO)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(NUMIDDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(NOMIDDESTINATARIO)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TIPOIDDESTINATARIO, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CODSEDEDESTINATARIO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldCODSEDEDESTINATARIO, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(fieldCODTIPOIDDESTINATARIO))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARDESTINATARIO)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addComponent(TIPOIDDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODTIPOIDDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(NUMIDDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(CODSEDEDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCODSEDEDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(NOMIDDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(NOMENCLATURADIRECCIONDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(MUNICIPIORNDCDESTINATARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setText("Seguro de Mercancía");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel37.setText("Tomador de Póliza");

        DUENOPOLIZA.setModel(RemesaDAO.getTomadorPoliza(conn));
        DUENOPOLIZA.setPrototypeDisplayValue("");
        DUENOPOLIZA.setSelectedItem(null);
        DUENOPOLIZA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DUENOPOLIZAActionPerformed(evt);
            }
        });

        jLabel38.setText("Numero de Póliza");

        jLabel39.setText("Aseguradora");

        COMPANIASEGURO.setModel(RemesaDAO.getAseguradoras(conn));
        COMPANIASEGURO.setPrototypeDisplayValue("");
        COMPANIASEGURO.setSelectedItem(null);

        jLabel40.setText("Vencimiento");

        FECHAVENCIMIENTOPOLIZACARGA.setDateFormatString("dd/MM/yyyy");
        FECHAVENCIMIENTOPOLIZACARGA.setMinSelectableDate(new Date());

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/buscar.png"))); // NOI18N
        jLabel31.setText("Buscar aseguradora");

        CLEARASEGURADORA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARASEGURADORA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARASEGURADORAActionPerformed(evt);
            }
        });

        CLEARSEGURO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARSEGURO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARSEGUROActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARSEGURO))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel37)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BUSCARASEGURADORA)
                            .addComponent(DUENOPOLIZA, javax.swing.GroupLayout.Alignment.TRAILING, 0, 170, Short.MAX_VALUE)
                            .addComponent(COMPANIASEGURO, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel40))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CLEARASEGURADORA)
                                    .addComponent(jLabel38))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FECHAVENCIMIENTOPOLIZACARGA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NUMPOLIZATRANSPORTE, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARSEGURO)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(DUENOPOLIZA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(NUMPOLIZATRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(COMPANIASEGURO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40))
                    .addComponent(FECHAVENCIMIENTOPOLIZACARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEARASEGURADORA)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(BUSCARASEGURADORA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel41.setText("Tiempos Logisticos del Origen");
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel42.setText("Cita para el cargue");

        FECHACITAPACTADACARGUE.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHACITAPACTADACARGUE.setMinSelectableDate(new Date());

        jLabel43.setText("Llegada");

        FECHALLEGADACARGUE.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHALLEGADACARGUE.setMinSelectableDate(new Date());

        jLabel44.setText("Entrada");

        FECHAENTRADACARGUE.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHAENTRADACARGUE.setMinSelectableDate(new Date());

        jLabel45.setText("Salida");

        FECHASALIDACARGUE.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHASALIDACARGUE.setMinSelectableDate(new Date());

        jLabel48.setText("*");
        jLabel48.setForeground(new java.awt.Color(255, 51, 0));

        jLabel11.setText("Tiempo total cargue (Incluye Espera + Cargue)");

        HORASPACTOCARGA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));

        jLabel18.setText("Horas");

        jLabel19.setText("Minutos");

        jLabel29.setText("Tiempo total descargue (Incluye Espera + Descargue)");

        HORASPACTODESCARGUE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));

        MINUTOSPACTOCARGA.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        MINUTOSPACTODESCARGUE.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        CLEARTIEMPOSORIGEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARTIEMPOSORIGEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARTIEMPOSORIGENActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48))
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FECHACITAPACTADACARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FECHALLEGADACARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FECHAENTRADACARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FECHASALIDACARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HORACITAPACTADACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HORALLEGADACARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HORAENTRADACARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HORASALIDACARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARTIEMPOSORIGEN)
                        .addContainerGap())))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(HORASPACTOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel19))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MINUTOSPACTOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(HORASPACTODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MINUTOSPACTODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(CLEARTIEMPOSORIGEN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(FECHACITAPACTADACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel42)
                                                    .addComponent(jLabel48)))
                                            .addComponent(HORACITAPACTADACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel43)
                                            .addComponent(FECHALLEGADACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(HORALLEGADACARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel44)
                                    .addComponent(FECHAENTRADACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(HORAENTRADACARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45)
                            .addComponent(FECHASALIDACARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(HORASALIDACARGUEREMESA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(HORASPACTOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MINUTOSPACTOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(HORASPACTODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MINUTOSPACTODESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel46.setText("Tiempos Logisticos de Destino");
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel47.setText("Cita para el descargue");

        jLabel66.setText("Nit Empresa de GPS autorizada para Cumplido Inicial de Remesa");

        FECHACITAPACTADADESCARGUE.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHACITAPACTADADESCARGUE.setMinSelectableDate(new Date());

        CLEARTIEMPOSDESTINO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARTIEMPOSDESTINO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARTIEMPOSDESTINOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHACITAPACTADADESCARGUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HORACITAPACTADADESCARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NUMIDGPS, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CLEARTIEMPOSDESTINO)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47)
                            .addComponent(HORACITAPACTADADESCARGUEREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FECHACITAPACTADADESCARGUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CLEARTIEMPOSDESTINO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(NUMIDGPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setText("Corrección de Remesa");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Consecutivo de la Remesa");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        CONSECUTIVOREMESA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CONSECUTIVOREMESA.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CONSECUTIVOREMESA, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CONSECUTIVOREMESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Características de la mercancía");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel16.setText("Tipo de operación");

        jLabel17.setText("Tipo de Empaque");

        CODOPERACIONTRANSPORTE.setModel(RemesaDAO.getTipoOperacion(conn));
        CODOPERACIONTRANSPORTE.setSelectedItem(null);
        CODOPERACIONTRANSPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODOPERACIONTRANSPORTEActionPerformed(evt);
            }
        });

        CODTIPOEMPAQUE.setModel(RemesaDAO.getTipoEmpaque(conn));
        CODTIPOEMPAQUE.setSelectedItem(null);
        CODTIPOEMPAQUE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CODTIPOEMPAQUEItemStateChanged(evt);
            }
        });
        CODTIPOEMPAQUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CODTIPOEMPAQUEActionPerformed(evt);
            }
        });

        jLabel30.setText("Consecutivo carga dividida");

        CLEARCARACTERISTICASMERCANCIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARCARACTERISTICASMERCANCIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARCARACTERISTICASMERCANCIAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                        .addComponent(CLEARCARACTERISTICASMERCANCIA))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CODTIPOEMPAQUE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CODOPERACIONTRANSPORTE, 0, 263, Short.MAX_VALUE)
                            .addComponent(CONSECUTIVOCARGADIVIDIDA))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CLEARCARACTERISTICASMERCANCIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(CODOPERACIONTRANSPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(CODTIPOEMPAQUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(CONSECUTIVOCARGADIVIDIDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label.setText("Observaciones");
        label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        OBSERVACIONES.setColumns(20);
        OBSERVACIONES.setLineWrap(true);
        OBSERVACIONES.setRows(2);
        OBSERVACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(OBSERVACIONES);

        CLEAROBSERVACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEAROBSERVACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAROBSERVACIONESActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEAROBSERVACIONES)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLEAROBSERVACIONES)
                    .addComponent(label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/tabla.png"))); // NOI18N
        jButton5.setToolTipText("Mostrar registros");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        connectionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/connection.png"))); // NOI18N

        CLEARALL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        CLEARALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARALLActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(connectionStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(CLEARALL, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLEARALL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(connectionStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CLEARALL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        registrar.setText("Registrar Remesa");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jMenu3.add(registrar);
        jMenu3.add(jSeparator3);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarRemesa() {
        try {
            Thread registro = new Thread(new RecordRemesa(getValues(), this, connectionStatus, CONSECUTIVOREMESA.getText(), dataSource, conn));
            registro.start();
        } catch (Exception e) {
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.start();
        }
    }

    private void CLEARPRODUCTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARPRODUCTOActionPerformed
        CLEARPRODUCTO();
    }//GEN-LAST:event_CLEARPRODUCTOActionPerformed

    private void CLEARPRODUCTO() {
        try {
            BUSCARNOMBRECARGA.setText("");
            CODNATURALEZACARGA.setSelectedItem(null);
            CODNATURALEZACARGA.setEnabled(true);
            PERMISOCARGAEXTRA.setText("");
            PERMISOCARGAEXTRA.setEnabled(false);
            DESCRIPCIONCORTAPRODUCTO.setText("");
            //DESCRIPCIONCORTAPRODUCTO.setEnabled(false);
            CLASIFICACIONCARGA.setSelectedItem(null);
            CLASIFICACIONCARGA.removeAllItems();
            CLASIFICACIONCARGA.setEnabled(false);
            NOMBRECARGA.setSelectedItem(null);
            NOMBRECARGA.removeAllItems();
            NOMBRECARGA.setEnabled(false);
            MERCANCIAREMESA.setText("");
            PESOCONTENEDORVACIO.setText("");
            PESOCONTENEDORVACIO.setEnabled(false);
            CANTIDADCARGADA.setText("");
            CANTIDADCARGADA.setEnabled(false);
            UNIDADMEDIDACAPACIDAD.setSelectedItem(null);
            UNIDADMEDIDACAPACIDAD.setEnabled(false);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void CODNATURALEZACARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODNATURALEZACARGAActionPerformed
        if (CODNATURALEZACARGA.getSelectedItem() != null) {
            if (CLASIFICACIONCARGA.getModel().getSize() != 1) {
                String s = CODNATURALEZACARGA.getSelectedItem().toString();
                setModelCLASIFICACIONCARGA(s);
            }
        }
    }//GEN-LAST:event_CODNATURALEZACARGAActionPerformed

    private void setModelCLASIFICACIONCARGA(String CODNATURALEZACARGA) {
        DefaultComboBoxModel dcbmCLASIFICACIONCARGA = UniversalDAO.getComboBoxModel(
                "select nombre from clasificacion_producto where naturaleza='" + CODNATURALEZACARGA + "';", conn);
        CLASIFICACIONCARGA.setModel(dcbmCLASIFICACIONCARGA);
        CLASIFICACIONCARGA.setEnabled(true);
        CLASIFICACIONCARGA.setSelectedItem(null);

    }

    private void CLASIFICACIONCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLASIFICACIONCARGAActionPerformed
        if (CLASIFICACIONCARGA.getSelectedItem() != null) {
            String s = CLASIFICACIONCARGA.getSelectedItem().toString();
            setModelNOMBRECARGA(s);
        }
    }//GEN-LAST:event_CLASIFICACIONCARGAActionPerformed

    private void setModelNOMBRECARGA(String CLASIFICACIONCARGA) {
        DefaultComboBoxModel dcbmNOMBRECARGA = UniversalDAO.getComboBoxModel(
                "select nombre from nombre_producto where clasificacion='" + CLASIFICACIONCARGA + "';", conn);
        NOMBRECARGA.setModel(dcbmNOMBRECARGA);
        NOMBRECARGA.setEnabled(true);
        NOMBRECARGA.setSelectedItem(null);

    }

    private void NOMBRECARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOMBRECARGAActionPerformed
        if (NOMBRECARGA.getSelectedItem() != null) {
            String s = NOMBRECARGA.getSelectedItem().toString();
            String sMERCANCIAREMESA = UniversalDAO.getString(
                    "select cod from nombre_producto where nombre='" + s + "';", conn);
            MERCANCIAREMESA.setText(sMERCANCIAREMESA);
        }
    }//GEN-LAST:event_NOMBRECARGAActionPerformed


    private void TIPOIDPROPIETARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOIDPROPIETARIOActionPerformed
        FillNUMIDTERCERO(TIPOIDPROPIETARIO, acNUMIDPROPIETARIO, NUMIDPROPIETARIO, NOMIDPROPIETARIO, fieldCODTIPOIDPROPIETARIO);
    }//GEN-LAST:event_TIPOIDPROPIETARIOActionPerformed

    private void CODTIPOEMPAQUEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CODTIPOEMPAQUEItemStateChanged

    }//GEN-LAST:event_CODTIPOEMPAQUEItemStateChanged

    private void CODOPERACIONTRANSPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODOPERACIONTRANSPORTEActionPerformed
        try {
            if (CODOPERACIONTRANSPORTE.getSelectedItem() != null) {
                if (CODOPERACIONTRANSPORTE.getSelectedItem().toString().equals("Paqueteo")) {
//                    CLEARPRODUCTO();
                    DESCRIPCIONCORTAPRODUCTO.setText("PAQUETES VARIOS");
                    DESCRIPCIONCORTAPRODUCTO.setEnabled(true);
                    String[] array1 = {"OTROS"};
                    CLASIFICACIONCARGA.setModel(new DefaultComboBoxModel<>(array1));
                    CLASIFICACIONCARGA.setEnabled(true);
                    String[] array2 = {"MISCELANEOS CONTENIDOS EN PAQUETES ( PAQUETEO )"};
                    NOMBRECARGA.setEnabled(true);
                    NOMBRECARGA.setModel(new DefaultComboBoxModel<>(array2));
                    MERCANCIAREMESA.setEnabled(true);
                    MERCANCIAREMESA.setText("009880");
                    CANTIDADCARGADA.setEnabled(true);
                    UNIDADMEDIDACAPACIDAD.setEnabled(true);
                }
                if (CODOPERACIONTRANSPORTE.getSelectedItem().toString().equals("Contenedor Cargado")) {
//                    CLEARPRODUCTO();
                    PESOCONTENEDORVACIO.setEnabled(true);
                    UNIDADMEDIDACAPACIDAD.setEnabled(true);
                    CANTIDADCARGADA.setEnabled(true);
                }
                if (CODOPERACIONTRANSPORTE.getSelectedItem().toString().equals("Contenedor Vacio")) {
//                    CLEARPRODUCTO();
                    CODNATURALEZACARGA.setEnabled(false);
                    DESCRIPCIONCORTAPRODUCTO.setText("CONTENEDOR VACIO");
                    CLASIFICACIONCARGA.addItem("VARIOS");
                    CLASIFICACIONCARGA.setSelectedItem("VARIOS");
                    NOMBRECARGA.addItem("CONTENEDOR VACIO");
                    NOMBRECARGA.setSelectedItem("CONTENEDOR VACIO");
                    NOMBRECARGA.setEnabled(true);
                    MERCANCIAREMESA.setText("009990");
                    MERCANCIAREMESA.setEnabled(false);
                    PESOCONTENEDORVACIO.setEnabled(true);
                    UNIDADMEDIDACAPACIDAD.setEnabled(false);
                    UNIDADMEDIDACAPACIDAD.setSelectedItem("Kilogramos");
                    CANTIDADCARGADA.setEnabled(false);
                    CANTIDADCARGADA.setText("");
                    PERMISOCARGAEXTRA.setEnabled(true);

                }
                if (CODOPERACIONTRANSPORTE.getSelectedItem().toString().equals("General")) {
//                    CLEARPRODUCTO();
                    UNIDADMEDIDACAPACIDAD.setEnabled(true);
                    UNIDADMEDIDACAPACIDAD.setEnabled(true);
                    CANTIDADCARGADA.setEnabled(true);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }//GEN-LAST:event_CODOPERACIONTRANSPORTEActionPerformed

    private void CODSEDEPROPIETARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODSEDEPROPIETARIOActionPerformed
        if (CODSEDEPROPIETARIO.getSelectedItem() != null) {
            fillSEDETERCERO(NUMIDPROPIETARIO, CODSEDEPROPIETARIO, NOMENCLATURADIRECCIONPROPIETARIO, MUNICIPIORNDCPROPIETARIO, fieldCODSEDEPROPIETARIO);
        }
    }//GEN-LAST:event_CODSEDEPROPIETARIOActionPerformed

    private void CLEARPROPIETARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARPROPIETARIOActionPerformed
        CLEARPROPIETARIO();
    }//GEN-LAST:event_CLEARPROPIETARIOActionPerformed

    private void CLEARPROPIETARIO() {
        try {
            fieldCODTIPOIDPROPIETARIO.setText("");
            TIPOIDPROPIETARIO.setSelectedItem(null);
            NUMIDPROPIETARIO.setText("");
            CODSEDEPROPIETARIO.setSelectedItem(null);
            CODSEDEPROPIETARIO.setEnabled(false);
            NOMIDPROPIETARIO.setText("");
            NOMENCLATURADIRECCIONPROPIETARIO.setText("");
            MUNICIPIORNDCPROPIETARIO.setText("");
            acNUMIDPROPIETARIO.removeAllItems();
            fieldCODSEDEPROPIETARIO.setText("");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    private void TIPOIDREMITENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOIDREMITENTEActionPerformed
        FillNUMIDTERCERO(TIPOIDREMITENTE, acNUMIDREMITENTE, NUMIDREMITENTE, NOMIDREMITENTE, fieldCODTIPOIDREMITENTE);
    }//GEN-LAST:event_TIPOIDREMITENTEActionPerformed

    private void CODSEDEREMITENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODSEDEREMITENTEActionPerformed
        if (CODSEDEREMITENTE.getSelectedItem() != null) {
            fillSEDETERCERO(NUMIDREMITENTE, CODSEDEREMITENTE, NOMENCLATURADIRECCIONREMITENTE, MUNICIPIORNDCREMITENTE, fieldCODSEDEREMITENTE);
        }
    }//GEN-LAST:event_CODSEDEREMITENTEActionPerformed


    private void CLEARREMITENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARREMITENTEActionPerformed
        CLEARREMITENTE();
    }//GEN-LAST:event_CLEARREMITENTEActionPerformed

    private void CLEARREMITENTE() {
        try {
            fieldCODTIPOIDREMITENTE.setText("");
            TIPOIDREMITENTE.setSelectedItem(null);
            NUMIDREMITENTE.setText("");
            CODSEDEREMITENTE.setSelectedItem(null);
            CODSEDEREMITENTE.setEnabled(false);
            NOMIDREMITENTE.setText("");
            NOMENCLATURADIRECCIONREMITENTE.setText("");
            MUNICIPIORNDCREMITENTE.setText("");
            acNUMIDREMITENTE.removeAllItems();
            fieldCODSEDEREMITENTE.setText("");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }


    private void CLEARDESTINATARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARDESTINATARIOActionPerformed
        CLEARDESTINATARIO();
    }//GEN-LAST:event_CLEARDESTINATARIOActionPerformed

    private void CLEARDESTINATARIO() {
        try {
            fieldCODTIPOIDDESTINATARIO.setText("");
            TIPOIDDESTINATARIO.setSelectedItem(null);
            NUMIDDESTINATARIO.setText("");
            CODSEDEDESTINATARIO.setSelectedItem(null);
            CODSEDEDESTINATARIO.setEnabled(false);
            NOMIDDESTINATARIO.setText("");
            NOMENCLATURADIRECCIONDESTINATARIO.setText("");
            MUNICIPIORNDCDESTINATARIO.setText("");
            acNUMIDDESTINATARIO.removeAllItems();
            fieldCODSEDEDESTINATARIO.setText("");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    private void TIPOIDDESTINATARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOIDDESTINATARIOActionPerformed
        FillNUMIDTERCERO(TIPOIDDESTINATARIO, acNUMIDDESTINATARIO, NUMIDDESTINATARIO, NOMIDDESTINATARIO, fieldCODTIPOIDDESTINATARIO);
    }//GEN-LAST:event_TIPOIDDESTINATARIOActionPerformed

    private void CODSEDEDESTINATARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODSEDEDESTINATARIOActionPerformed
        if (CODSEDEDESTINATARIO.getSelectedItem() != null) {
            fillSEDETERCERO(NUMIDDESTINATARIO, CODSEDEDESTINATARIO, NOMENCLATURADIRECCIONDESTINATARIO, MUNICIPIORNDCDESTINATARIO, fieldCODSEDEDESTINATARIO);
        }
    }//GEN-LAST:event_CODSEDEDESTINATARIOActionPerformed

    private void PESOCONTENEDORVACIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PESOCONTENEDORVACIOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PESOCONTENEDORVACIOActionPerformed

    private void PERMISOCARGAEXTRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERMISOCARGAEXTRAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PERMISOCARGAEXTRAActionPerformed

    private void CODTIPOEMPAQUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CODTIPOEMPAQUEActionPerformed
        if (CODTIPOEMPAQUE.getSelectedItem() != null) {
            String StringCODTIPOEMPAQUE = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_empaque "
                    + "where nombre='" + CODTIPOEMPAQUE.getSelectedItem().toString() + "';", conn);
            if (StringCODTIPOEMPAQUE.equals("7")
                    || StringCODTIPOEMPAQUE.equals("8")
                    || StringCODTIPOEMPAQUE.equals("9")) {
                PESOCONTENEDORVACIO.setEnabled(true);
            }
        }
    }//GEN-LAST:event_CODTIPOEMPAQUEActionPerformed

    private void CLEARCARACTERISTICASMERCANCIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARCARACTERISTICASMERCANCIAActionPerformed
        CLEARCARACTERISTICASMERCANCIA();
    }//GEN-LAST:event_CLEARCARACTERISTICASMERCANCIAActionPerformed

    private void fieldCODSEDEREMITENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCODSEDEREMITENTEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCODSEDEREMITENTEActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (allowRecords) {
            registrarRemesa();
        } else {
            Modal.show("Error", "No hay consecutivos disponibles para remesas", this, "", "error");
        }
    }//GEN-LAST:event_registrarActionPerformed

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        openFromViewRemesas(dataSource, conn);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BUSCARNOMBRECARGA.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DUENOPOLIZAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DUENOPOLIZAActionPerformed
        if (DUENOPOLIZA.getSelectedItem() != null) {
            if (DUENOPOLIZA.getSelectedItem().toString().equals("Empresa de Transporte")) {
                NUMPOLIZATRANSPORTE.setText(UniversalDAO.getString(""
                        + "select poliza "
                        + "from server "
                        + "where id=" + String.valueOf(conn) + "", defaultServerConn));

                COMPANIASEGURO.setSelectedItem(UniversalDAO.getString(""
                        + "select aseguradora "
                        + "from server "
                        + "where id=" + String.valueOf(conn) + "", defaultServerConn));
                FECHAVENCIMIENTOPOLIZACARGA.setDate(DateParsing.fromMySQL(UniversalDAO.getString(""
                        + "select vencimiento "
                        + "from server "
                        + "where id=" + String.valueOf(conn) + "", defaultServerConn)));
            }
        }
    }//GEN-LAST:event_DUENOPOLIZAActionPerformed

    private void buildAutoCompleterBUSCARASEGURADORA() {
        acBUSCARASEGURADORA = new TextAutoCompleter(BUSCARASEGURADORA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                COMPANIASEGURO.setSelectedItem(o.toString());
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select nombre "
                + "from aseguradora", conn);
        for (int i = 0; i < data.size(); i++) {
            acBUSCARASEGURADORA.addItem(data.get(i));
        }
        acBUSCARASEGURADORA.setMode(0);
    }

    private void CLEARASEGURADORAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARASEGURADORAActionPerformed
        BUSCARASEGURADORA.setText("");
    }//GEN-LAST:event_CLEARASEGURADORAActionPerformed

    private void CLEARSEGURO() {
        BUSCARASEGURADORA.setText("");
        DUENOPOLIZA.setSelectedItem(null);
        NUMPOLIZATRANSPORTE.setText("");
        FECHAVENCIMIENTOPOLIZACARGA.setDate(null);
        COMPANIASEGURO.setSelectedItem(null);
    }

    private void CLEARSEGUROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARSEGUROActionPerformed
        CLEARSEGURO();
    }//GEN-LAST:event_CLEARSEGUROActionPerformed

    private void CLEAROBSERVACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAROBSERVACIONESActionPerformed
        CLEAROBSERVACIONES();
    }//GEN-LAST:event_CLEAROBSERVACIONESActionPerformed

    private void CLEARTIEMPOSDESTINOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARTIEMPOSDESTINOActionPerformed
        CLEARTIEMPOSDESTINO();
    }//GEN-LAST:event_CLEARTIEMPOSDESTINOActionPerformed

    private void CLEARTIEMPOSORIGENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARTIEMPOSORIGENActionPerformed
        CLEARTIEMPOSORIGEN();
    }//GEN-LAST:event_CLEARTIEMPOSORIGENActionPerformed

    private void CLEARALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARALLActionPerformed
        CLEARALL();
        setEnabledAllFields(false);
        CONSECUTIVOREMESA.setText("");
    }//GEN-LAST:event_CLEARALLActionPerformed

    private void MERCANCIAREMESAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MERCANCIAREMESAKeyReleased
        JTextField f = (JTextField) evt.getSource();
        if (acMERCANCIAREMESA.itemExists(f.getText())) {
            callbackMERCANCIAREMESA(f.getText());
        }
    }//GEN-LAST:event_MERCANCIAREMESAKeyReleased

    private void CLEARALL() {
        CLEARCARACTERISTICASMERCANCIA();
        CLEARPRODUCTO();
        CLEARREMITENTE();
        CLEARTIEMPOSDESTINO();
        CLEAROBSERVACIONES();
        CLEARPROPIETARIO();
        CLEARSEGURO();
        CLEARDESTINATARIO();
        CLEARTIEMPOSORIGEN();
    }

    private void CLEARTIEMPOSORIGEN() {
        FECHACITAPACTADACARGUE.setDate(null);
        HORACITAPACTADACARGUE.setText("");
        FECHALLEGADACARGUE.setDate(null);
        HORALLEGADACARGUEREMESA.setText("");
        FECHAENTRADACARGUE.setDate(null);
        HORAENTRADACARGUEREMESA.setText("");
        FECHASALIDACARGUE.setDate(null);
        HORASALIDACARGUEREMESA.setText("");
        HORASPACTOCARGA.setText("");
        MINUTOSPACTOCARGA.setValue(defaultServerConn);
        HORASPACTODESCARGUE.setText("");
        MINUTOSPACTODESCARGUE.setValue(defaultServerConn);
    }

    private void CLEARTIEMPOSDESTINO() {
        FECHACITAPACTADADESCARGUE.setDate(null);
        HORACITAPACTADADESCARGUEREMESA.setText("");
        NUMIDGPS.setText("");
    }

    private void CLEAROBSERVACIONES() {
        OBSERVACIONES.setText("");
    }

    public static void openFromViewRemesas(DataSourceClass dataSource,Connection conn) {
        String[] querys = {
            "select * from remesas"
        };
        OpenForm.view("Remesas", querys, dataSource, conn);
    }

    private void CLEARCARACTERISTICASMERCANCIA() {
        CODOPERACIONTRANSPORTE.setSelectedItem(null);
        CODTIPOEMPAQUE.setSelectedItem(null);
        CONSECUTIVOCARGADIVIDIDA.setText("");
    }

    private Remesa getValues() {
        Remesa remesa = new Remesa();
        Data.auth(remesa, conn);
        //CONSECUTIVOINFORMACIONCARGA no incluido 
        //CONSECUTIVOCARGADIVIDIDA no incluido                
        remesa.setCONSECUTIVOREMESA(CONSECUTIVOREMESA.getText());
        remesa.setCODOPERACIONTRANSPORTE(RemesaDAO.getCODOPERACIONTRANSPORTE(Items.getValue(CODOPERACIONTRANSPORTE), conn));
        remesa.setCODTIPOEMPAQUE(RemesaDAO.getCODTIPOEMPAQUE(Items.getValue(CODTIPOEMPAQUE), conn));
        remesa.setCODNATURALEZACARGA(RemesaDAO.getCODNATURALEZACARGA(Items.getValue(CODNATURALEZACARGA), conn));
        remesa.setPERMISOCARGAEXTRA(PERMISOCARGAEXTRA.getText());
        remesa.setDESCRIPCIONCORTAPRODUCTO(DESCRIPCIONCORTAPRODUCTO.getText());
        remesa.setMERCANCIAREMESA(MERCANCIAREMESA.getText());
        remesa.setCANTIDADCARGADA(CANTIDADCARGADA.getText());
        remesa.setUNIDADMEDIDACAPACIDAD(UniversalDAO.getString(""
                + "select cod "
                + "from unidad_medida "
                + "where nombre='" + Items.getValue(UNIDADMEDIDACAPACIDAD) + "'", conn));
        remesa.setPESOCONTENEDORVACIO(PESOCONTENEDORVACIO.getText());
        //Datos del propietario remesa
        remesa.setCODTIPOIDPROPIETARIO(fieldCODTIPOIDPROPIETARIO.getText());
        remesa.setNUMIDPROPIETARIO(NUMIDPROPIETARIO.getText());
        remesa.setCODSEDEPROPIETARIO(fieldCODSEDEPROPIETARIO.getText());
        //Datos del remitente remesa
        remesa.setCODTIPOIDREMITENTE(fieldCODTIPOIDREMITENTE.getText());
        remesa.setNUMIDREMITENTE(NUMIDREMITENTE.getText());
        remesa.setCODSEDEREMITENTE(fieldCODSEDEREMITENTE.getText());
        //Datos del destinatario remesa
        remesa.setCODTIPOIDDESTINATARIO(fieldCODTIPOIDDESTINATARIO.getText());
        remesa.setNUMIDDESTINATARIO(NUMIDDESTINATARIO.getText());
        remesa.setCODSEDEDESTINATARIO(fieldCODSEDEDESTINATARIO.getText());
        //Seguro de Mercancía remesa
        remesa.setDUENOPOLIZA(RemesaDAO.getDUENOPOLIZA(Items.getValue(DUENOPOLIZA), conn));
        remesa.setNUMPOLIZATRANSPORTE(NUMPOLIZATRANSPORTE.getText());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            remesa.setFECHAVENCIMIENTOPOLIZACARGA(formato.format(FECHAVENCIMIENTOPOLIZACARGA.getDate()));
        } catch (Exception e) {
            remesa.setFECHAVENCIMIENTOPOLIZACARGA("");
        }
        remesa.setCOMPANIASEGURO(RemesaDAO.getCOMPANIASEGURO(Items.getValue(COMPANIASEGURO), conn));
        //Tiempos Logisticos de Origen       
        try {
            remesa.setFECHALLEGADACARGUE(formato.format(FECHALLEGADACARGUE.getDate()));
        } catch (Exception e) {
            remesa.setFECHALLEGADACARGUE("");
        }
        if (HORALLEGADACARGUEREMESA.getTime() != null) {
            remesa.setHORALLEGADACARGUEREMESA(HORALLEGADACARGUEREMESA.getTime().toString());
        }
        try {
            remesa.setFECHAENTRADACARGUE(formato.format(FECHAENTRADACARGUE.getDate()));
        } catch (Exception e) {
            remesa.setFECHAENTRADACARGUE("");
        }
        if (HORAENTRADACARGUEREMESA.getTime() != null) {
            remesa.setHORAENTRADACARGUEREMESA(HORAENTRADACARGUEREMESA.getTime().toString());
        }
        try {
            remesa.setFECHASALIDACARGUE(formato.format(FECHASALIDACARGUE.getDate()));
        } catch (Exception e) {
            remesa.setFECHASALIDACARGUE("");
        }
        if (HORASALIDACARGUEREMESA.getTime() != null) {
            remesa.setHORASALIDACARGUEREMESA(HORASALIDACARGUEREMESA.getTime().toString());
        }
        remesa.setHORASPACTODESCARGUE(HORASPACTODESCARGUE.getText());
        remesa.setMINUTOSPACTODESCARGUE(String.valueOf((Integer) MINUTOSPACTODESCARGUE.getValue()));
        remesa.setHORASPACTOCARGA(HORASPACTOCARGA.getText());
        remesa.setMINUTOSPACTOCARGA(String.valueOf((Integer) MINUTOSPACTOCARGA.getValue()));
        try {
            remesa.setFECHACITAPACTADACARGUE(formato.format(FECHACITAPACTADACARGUE.getDate()));
        } catch (Exception e) {
            remesa.setFECHACITAPACTADACARGUE("");
        }
        if (HORACITAPACTADACARGUE.getTime() != null) {
            remesa.setHORACITAPACTADACARGUE(HORACITAPACTADACARGUE.getTime().toString());
        }
        //Tiempos logisticos de destino        
        try {
            remesa.setFECHACITAPACTADADESCARGUE(formato.format(FECHACITAPACTADADESCARGUE.getDate()));
        } catch (Exception e) {
            remesa.setFECHACITAPACTADADESCARGUE("");
        }
        if (HORACITAPACTADADESCARGUEREMESA.getTime() != null) {
            remesa.setHORACITAPACTADADESCARGUEREMESA(HORACITAPACTADADESCARGUEREMESA.getTime().toString());
        }
        remesa.setNUMIDGPS(NUMIDGPS.getText());
        remesa.setOBSERVACIONES(OBSERVACIONES.getText());
        remesa.setUserLog(username);
        remesa.setEstado("activo");
        //System.out.println(ProcessRemesa.grabar(remesa));
        return remesa;
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
            java.util.logging.Logger.getLogger(FormCorregirRemesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCorregirRemesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCorregirRemesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCorregirRemesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormCorregirRemesa(new DataSourceClass(), new DataSourceClass().conectar(1), "no user").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BUSCARASEGURADORA;
    private javax.swing.JTextField BUSCARNOMBRECARGA;
    private javax.swing.JTextField CANTIDADCARGADA;
    private javax.swing.JComboBox<String> CLASIFICACIONCARGA;
    private javax.swing.JButton CLEARALL;
    private javax.swing.JButton CLEARASEGURADORA;
    private javax.swing.JButton CLEARCARACTERISTICASMERCANCIA;
    private javax.swing.JButton CLEARDESTINATARIO;
    private javax.swing.JButton CLEAROBSERVACIONES;
    private javax.swing.JButton CLEARPRODUCTO;
    private javax.swing.JButton CLEARPROPIETARIO;
    private javax.swing.JButton CLEARREMITENTE;
    private javax.swing.JButton CLEARSEGURO;
    private javax.swing.JButton CLEARTIEMPOSDESTINO;
    private javax.swing.JButton CLEARTIEMPOSORIGEN;
    private javax.swing.JComboBox<String> CODNATURALEZACARGA;
    private javax.swing.JComboBox<String> CODOPERACIONTRANSPORTE;
    private javax.swing.JComboBox<String> CODSEDEDESTINATARIO;
    private javax.swing.JComboBox<String> CODSEDEPROPIETARIO;
    private javax.swing.JComboBox<String> CODSEDEREMITENTE;
    private javax.swing.JComboBox<String> CODTIPOEMPAQUE;
    private javax.swing.JComboBox<String> COMPANIASEGURO;
    private javax.swing.JTextField CONSECUTIVOCARGADIVIDIDA;
    private javax.swing.JTextField CONSECUTIVOREMESA;
    private javax.swing.JTextField DESCRIPCIONCORTAPRODUCTO;
    private javax.swing.JComboBox<String> DUENOPOLIZA;
    private com.toedter.calendar.JDateChooser FECHACITAPACTADACARGUE;
    private com.toedter.calendar.JDateChooser FECHACITAPACTADADESCARGUE;
    private com.toedter.calendar.JDateChooser FECHAENTRADACARGUE;
    private com.toedter.calendar.JDateChooser FECHALLEGADACARGUE;
    private com.toedter.calendar.JDateChooser FECHASALIDACARGUE;
    private com.toedter.calendar.JDateChooser FECHAVENCIMIENTOPOLIZACARGA;
    private com.github.lgooddatepicker.components.TimePicker HORACITAPACTADACARGUE;
    private com.github.lgooddatepicker.components.TimePicker HORACITAPACTADADESCARGUEREMESA;
    private com.github.lgooddatepicker.components.TimePicker HORAENTRADACARGUEREMESA;
    private com.github.lgooddatepicker.components.TimePicker HORALLEGADACARGUEREMESA;
    private com.github.lgooddatepicker.components.TimePicker HORASALIDACARGUEREMESA;
    private javax.swing.JFormattedTextField HORASPACTOCARGA;
    private javax.swing.JFormattedTextField HORASPACTODESCARGUE;
    private javax.swing.JTextField MERCANCIAREMESA;
    private javax.swing.JSpinner MINUTOSPACTOCARGA;
    private javax.swing.JSpinner MINUTOSPACTODESCARGUE;
    private javax.swing.JTextField MUNICIPIORNDCDESTINATARIO;
    private javax.swing.JTextField MUNICIPIORNDCPROPIETARIO;
    private javax.swing.JTextField MUNICIPIORNDCREMITENTE;
    private javax.swing.JComboBox<String> NOMBRECARGA;
    private javax.swing.JTextField NOMENCLATURADIRECCIONDESTINATARIO;
    private javax.swing.JTextField NOMENCLATURADIRECCIONPROPIETARIO;
    private javax.swing.JTextField NOMENCLATURADIRECCIONREMITENTE;
    private javax.swing.JTextField NOMIDDESTINATARIO;
    private javax.swing.JTextField NOMIDPROPIETARIO;
    private javax.swing.JTextField NOMIDREMITENTE;
    private javax.swing.JTextField NUMIDDESTINATARIO;
    private javax.swing.JTextField NUMIDGPS;
    private javax.swing.JTextField NUMIDPROPIETARIO;
    private javax.swing.JTextField NUMIDREMITENTE;
    private javax.swing.JTextField NUMPOLIZATRANSPORTE;
    private javax.swing.JTextArea OBSERVACIONES;
    private javax.swing.JTextField PERMISOCARGAEXTRA;
    private javax.swing.JTextField PESOCONTENEDORVACIO;
    private javax.swing.JComboBox<String> TIPOIDDESTINATARIO;
    private javax.swing.JComboBox<String> TIPOIDPROPIETARIO;
    private javax.swing.JComboBox<String> TIPOIDREMITENTE;
    private javax.swing.JComboBox<String> UNIDADMEDIDACAPACIDAD;
    private javax.swing.JMenuItem acercaDe;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton connectionStatus;
    private javax.swing.JTextField fieldCODSEDEDESTINATARIO;
    private javax.swing.JTextField fieldCODSEDEPROPIETARIO;
    private javax.swing.JTextField fieldCODSEDEREMITENTE;
    private javax.swing.JTextField fieldCODTIPOIDDESTINATARIO;
    private javax.swing.JTextField fieldCODTIPOIDPROPIETARIO;
    private javax.swing.JTextField fieldCODTIPOIDREMITENTE;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelPesoContenedorVacio;
    private javax.swing.JMenuItem manifiesto;
    private javax.swing.JMenuItem registrar;
    private javax.swing.JMenuItem registrarTerceros;
    private javax.swing.JMenuItem registrarVehiculos;
    private javax.swing.JMenuItem remesa;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
