/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.vehiculo;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.remolque.Remolque;
import com.enerfrisoft.remolque.RegistrarRemolque;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.enerfrisoft.error.QueryError;
import com.enerfrisoft.tools.Data;
import com.enerfrisoft.manifiesto.ManifiestoDAO;
import com.enerfrisoft.openForm.OpenForm;
import com.enerfrisoft.remesa.RemesaDAO;
import com.enerfrisoft.tools.DateParsing;
import com.enerfrisoft.tools.Items;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.VerifyConnection;
import com.enerfrisoft.tools.Visual;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class FormVehiculos extends javax.swing.JFrame {

    private TextAutoCompleter acNUMIDPROPIETARIO;
    private TextAutoCompleter acNUMIDTENEDOR;
    private TextAutoCompleter acMARCAVEHICULOCARGA;
    private TextAutoCompleter acLINEAVEHICULOCARGA;
    private TextAutoCompleter acNUMPLACA;
    private TextAutoCompleter acNUMNITASEGURADORASOAT;
    
    private String username;
    private Boolean updateVehiculo;
    private Boolean updateRemolque;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    /**
     * Creates new form FormVehiculos
     */
    public FormVehiculos(DataSourceClass dataSource, Connection conn, String username) {
        
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        
        this.username = username;
        this.updateVehiculo = false;
        this.updateRemolque = false;
        initComponents();
        buildAutoCompleterColor();
        buildAutoCompleterNUMIDPROPIETARIO();
        buildAutoCompleterNUMIDTENEDOR();
        buildAutoCompleterNUMPLACA();
        buildAutoCompleterNUMNITASEGURADORASOAT();
        acMARCAVEHICULOCARGA = new TextAutoCompleter(MARCAVEHICULOCARGA);
        acLINEAVEHICULOCARGA = new TextAutoCompleter(LINEAVEHICULOCARGA);
        connectionStatus.doClick();
        Visual.windowIcon(this);
    }

    private void buildAutoCompleterNUMNITASEGURADORASOAT() {
        acNUMNITASEGURADORASOAT = new TextAutoCompleter(BUSCARNUMNITASEGURADORASOAT, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                try {
                    NUMNITASEGURADORASOAT.setSelectedItem(o.toString());
                } catch (Exception e) {
                    System.out.println("acNUMNITASEGURADORASOAT Error");
                }
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select aseguradora.nombre "
                + "from aseguradora", conn);
        for (int i = 0; i < data.size(); i++) {
            acNUMNITASEGURADORASOAT.addItem(data.get(i));
        }
        acNUMNITASEGURADORASOAT.setMode(0);
    }

    private void buildAutoCompleterNUMPLACA() {
        acNUMPLACA = new TextAutoCompleter(NUMPLACA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                try {
                    String value = UniversalDAO.getString(""
                            + "select remolque.NUMPLACA "
                            + "from remolque "
                            + "where remolque.NUMPLACA = '" + o.toString() + "';", conn);
                    if (!value.equals("")) {
                        Map<String, String> data = UniversalDAO.getRow(""
                                + "call form_remolques('" + o.toString() + "')", conn);
                        
                        updateRemolque = true;
                        
                        tarjeta_propiedad.setText(data.get("tarjeta_propiedad"));
                        CONFIGURACIONUNIDADCARGA.setSelectedItem(data.get("CONFIGURACIONUNIDADCARGA"));
                        MARCAVEHICULOCARGA.setText(data.get("MARCAVEHICULOCARGA"));
                        CODMARCAVEHICULOCARGA.setText(data.get("CODMARCAVEHICULOCARGA"));
                        LINEAVEHICULOCARGA.setText(data.get("LINEAVEHICULOCARGA"));
                        CODLINEAVEHICULOCARGA.setText(data.get("CODLINEAVEHICULOCARGA"));
                        ANOFABRICACIONVEHICULOCARGA.setValue(Integer.valueOf(data.get("ANOFABRICACIONVEHICULOCARGA")));
                        serie_motor.setText(data.get("serie_motor"));
                        CODCOLORVEHICULOCARGA.setText(data.get("CODCOLORVEHICULOCARGA"));
                        COLORVEHICULOCARGA.setText(data.get("COLORVEHICULOCARGA"));
                        PESOVEHICULOVACIO.setValue(Integer.valueOf(data.get("PESOVEHICULOVACIO")));
                        NUMEJES.setValue(Integer.valueOf(data.get("NUMEJES")));
                        TIPOCARROCERIA.setSelectedItem(data.get("TIPOCARROCERIA"));
                        TIPOCOMBUSTIBLE.setSelectedItem(data.get("TIPOCOMBUSTIBLE"));
                        NUMCHASIS.setText(data.get("NUMCHASIS"));
                        CAPACIDADUNIDADCARGA.setValue(Integer.valueOf(data.get("CAPACIDADUNIDADCARGA")));
                        afiliacion.setText(data.get("afiliacion"));
                        rntc.setText(data.get("rntc"));
                        NUMSEGUROSOAT.setText(data.get("NUMSEGUROSOAT"));
                        FECHAVENCIMIENTOSOAT.setDate(DateParsing.fromMySQL(data.get("FECHAVENCIMIENTOSOAT")));
                        NUMNITASEGURADORASOAT.setSelectedItem(data.get("NUMNITASEGURADORASOAT"));
                        TIPOIDPROPIETARIO.setSelectedItem(data.get("TIPOIDPROPIETARIO"));
                        NUMIDPROPIETARIO.setText(data.get("NUMIDPROPIETARIO"));
                        NOMIDPROPIETARIO.setText(data.get("NOMIDPROPIETARIO"));
                        TIPOIDTENEDOR.setSelectedItem(data.get("TIPOIDTENEDOR"));
                        NUMIDTENEDOR.setText(data.get("NUMIDTENEDOR"));
                        NOMIDTENEDOR.setText(data.get("NOMIDTENEDOR"));
                        OBSERVACIONES.setText(data.get("OBSERVACIONES"));
                    } else {
                        Map<String, String> data = UniversalDAO.getRow(""
                                + "call form_vehiculos('" + o.toString() + "')", conn);
                        
                        updateVehiculo = true;
                        
                        tarjeta_propiedad.setText(data.get("tarjeta_propiedad"));
                        CONFIGURACIONUNIDADCARGA.setSelectedItem(data.get("CONFIGURACIONUNIDADCARGA"));
                        MARCAVEHICULOCARGA.setText(data.get("MARCAVEHICULOCARGA"));
                        CODMARCAVEHICULOCARGA.setText(data.get("CODMARCAVEHICULOCARGA"));
                        LINEAVEHICULOCARGA.setText(data.get("LINEAVEHICULOCARGA"));
                        CODLINEAVEHICULOCARGA.setText(data.get("CODLINEAVEHICULOCARGA"));
                        ANOFABRICACIONVEHICULOCARGA.setValue(Integer.valueOf(data.get("ANOFABRICACIONVEHICULOCARGA")));
                        serie_motor.setText(data.get("serie_motor"));
                        CODCOLORVEHICULOCARGA.setText(data.get("CODCOLORVEHICULOCARGA"));
                        COLORVEHICULOCARGA.setText(data.get("COLORVEHICULOCARGA"));
                        PESOVEHICULOVACIO.setValue(Integer.valueOf(data.get("PESOVEHICULOVACIO")));
                        NUMEJES.setValue(Integer.valueOf(data.get("NUMEJES")));
                        TIPOCARROCERIA.setSelectedItem(data.get("TIPOCARROCERIA"));
                        TIPOCOMBUSTIBLE.setSelectedItem(data.get("TIPOCOMBUSTIBLE"));
                        NUMCHASIS.setText(data.get("NUMCHASIS"));
                        CAPACIDADUNIDADCARGA.setValue(Integer.valueOf(data.get("CAPACIDADUNIDADCARGA")));
                        afiliacion.setText(data.get("afiliacion"));
                        rntc.setText(data.get("rntc"));
                        OBSERVACIONES.setText(data.get("OBSERVACIONES"));
                        NUMSEGUROSOAT.setText(data.get("NUMSEGUROSOAT"));
                        FECHAVENCIMIENTOSOAT.setDate(DateParsing.fromMySQL(data.get("FECHAVENCIMIENTOSOAT")));
                        NUMNITASEGURADORASOAT.setSelectedItem(data.get("NUMNITASEGURADORASOAT"));
                        TIPOIDPROPIETARIO.setSelectedItem(data.get("TIPOIDPROPIETARIO"));
                        NUMIDPROPIETARIO.setText(data.get("NUMIDPROPIETARIO"));
                        NOMIDPROPIETARIO.setText(data.get("NOMIDPROPIETARIO"));
                        TIPOIDTENEDOR.setSelectedItem(data.get("TIPOIDTENEDOR"));
                        NUMIDTENEDOR.setText(data.get("NUMIDTENEDOR"));
                        NOMIDTENEDOR.setText(data.get("NOMIDTENEDOR"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage() + ":" + String.valueOf(e.getStackTrace()[0].getLineNumber()));
                }
            }
        });
        ArrayList<String> data = UniversalDAO.getArrayList(""
                + "select vehiculo.NUMPLACA "
                + "from vehiculo", conn);
        ArrayList<String> data2 = UniversalDAO.getArrayList(""
                + "select remolque.NUMPLACA "
                + "from remolque", conn);
        for (int i = 0; i < data.size(); i++) {
            acNUMPLACA.addItem(data.get(i));
        }
        for (int i = 0; i < data2.size(); i++) {
            acNUMPLACA.addItem(data2.get(i));
        }

    }

    private void buildAutoCompleterNUMIDPROPIETARIO() {
        acNUMIDPROPIETARIO = new TextAutoCompleter(NUMIDPROPIETARIO, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String nombre = UniversalDAO.getString(""
                        + "select NOMIDTERCERO "
                        + "from tercero "
                        + "where NUMIDTERCERO='" + o.toString() + "'", conn);
                String apellido = UniversalDAO.getString(""
                        + "select PRIMERAPELLIDOIDTERCERO "
                        + "from tercero where NUMIDTERCERO='" + o.toString() + "'", conn);
                NOMIDPROPIETARIO.setText(nombre + " " + apellido);
            }
        });
    }

    private void buildAutoCompleterNUMIDTENEDOR() {
        acNUMIDTENEDOR = new TextAutoCompleter(NUMIDTENEDOR, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                String nombre = UniversalDAO.getString(""
                        + "select NOMIDTERCERO "
                        + "from tercero "
                        + "where NUMIDTERCERO='" + o.toString() + "'", conn);
                String apellido = UniversalDAO.getString(""
                        + "select PRIMERAPELLIDOIDTERCERO "
                        + "from tercero "
                        + "where NUMIDTERCERO='" + o.toString() + "'", conn);
                NOMIDTENEDOR.setText(nombre + " " + apellido);
            }
        });
    }

    private void FillNUMIDTERCERO(JComboBox TIPOIDTERCERO,
            TextAutoCompleter acNUMIDTERCERO,
            JTextField NUMIDTERCERO,
            JTextField NOMIDTERCERO
    ) {
        if (TIPOIDTERCERO.getSelectedItem() != null) {
            NUMIDTERCERO.setText("");
            NOMIDTERCERO.setText("");
            String TIPOIDTERCERO_value = TIPOIDTERCERO.getSelectedItem().toString();
            String COD = UniversalDAO.getString(""
                    + "select cod "
                    + "from tipo_id "
                    + "where nombre='" + TIPOIDTERCERO_value + "';", conn);
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

    private void buildAutoCompleterMARCAVEHICULOCARGA(Boolean semiremolque) {
        ArrayList<String> marcas = new ArrayList<>();
        if (semiremolque) {
            acMARCAVEHICULOCARGA = new TextAutoCompleter(MARCAVEHICULOCARGA, new AutoCompleterCallback() {
                @Override
                public void callback(Object o) {
                    CODMARCAVEHICULOCARGA.setText(VehiculoDAO.getCodMarcaSemiremolque(o.toString(), conn));
                }
            });
            marcas = VehiculoDAO.getMarcasSemiremolques(conn);
        } else {
            acMARCAVEHICULOCARGA = new TextAutoCompleter(MARCAVEHICULOCARGA, new AutoCompleterCallback() {
                @Override
                public void callback(Object o) {
                    CODMARCAVEHICULOCARGA.setText(VehiculoDAO.getCODMARCAVEHICULOCARGA(o.toString(), conn));
                    buildAutoCompleterLINEAVEHICULOCARGA();
                }
            });
            marcas = VehiculoDAO.getMARCAVEHICULOCARGA(conn);
        }
        acMARCAVEHICULOCARGA.removeAllItems();
        acMARCAVEHICULOCARGA.setMode(0);
        for (int i = 0; i < marcas.size(); i++) {
            acMARCAVEHICULOCARGA.addItem(marcas.get(i));
        }
    }

    private void buildAutoCompleterLINEAVEHICULOCARGA() {
        LINEAVEHICULOCARGA.setEditable(true);
        acLINEAVEHICULOCARGA = new TextAutoCompleter(LINEAVEHICULOCARGA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                CODLINEAVEHICULOCARGA.setText(VehiculoDAO.getCODLINEAVEHICULOCARGA(o.toString(), MARCAVEHICULOCARGA.getText(), conn));
            }
        });
        acLINEAVEHICULOCARGA.removeAllItems();
        ArrayList<String> lineas = VehiculoDAO.getLINEAVEHICULOCARGA(CODMARCAVEHICULOCARGA.getText(), conn);
        for (int i = 0; i < lineas.size(); i++) {
            acLINEAVEHICULOCARGA.addItem(lineas.get(i));
        }
    }

    private void buildAutoCompleterColor() {

        TextAutoCompleter acColor = new TextAutoCompleter(COLORVEHICULOCARGA, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                CODCOLORVEHICULOCARGA.setText(VehiculoDAO.getCodColor(o.toString(), conn));
            }
        });
        ArrayList<String> colores = VehiculoDAO.getColores(conn);
        for (int i = 0; i < colores.size(); i++) {
            acColor.addItem(colores.get(i));
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        NUMPLACA = new javax.swing.JTextField();
        MARCAVEHICULOCARGA = new javax.swing.JTextField();
        NUMCHASIS = new javax.swing.JTextField();
        LINEAVEHICULOCARGA = new javax.swing.JTextField();
        TIPOCOMBUSTIBLE = new javax.swing.JComboBox<>();
        COLORVEHICULOCARGA = new javax.swing.JTextField();
        TIPOCARROCERIA = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OBSERVACIONES = new javax.swing.JTextArea();
        CONFIGURACIONUNIDADCARGA = new javax.swing.JComboBox<>();
        CODLINEAVEHICULOCARGA = new javax.swing.JTextField();
        CODMARCAVEHICULOCARGA = new javax.swing.JTextField();
        CODCOLORVEHICULOCARGA = new javax.swing.JTextField();
        CODCONFIGURACIONUNIDADCARGA = new javax.swing.JTextField();
        PESOVEHICULOVACIO = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        CAPACIDADUNIDADCARGA = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        NUMEJES = new javax.swing.JSpinner();
        ANOFABRICACIONVEHICULOCARGA = new com.toedter.calendar.JYearChooser();
        CODTIPOCARROCERIA = new javax.swing.JTextField();
        CODTIPOCOMBUSTIBLE = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tarjeta_propiedad = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        serie_motor = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        afiliacion = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        rntc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        NUMSEGUROSOAT = new javax.swing.JTextField();
        FECHAVENCIMIENTOSOAT = new com.toedter.calendar.JDateChooser();
        NUMNITASEGURADORASOAT = new javax.swing.JComboBox<>();
        BUSCARNUMNITASEGURADORASOAT = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TIPOIDPROPIETARIO = new javax.swing.JComboBox<>();
        NUMIDPROPIETARIO = new javax.swing.JTextField();
        NOMIDPROPIETARIO = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TIPOIDTENEDOR = new javax.swing.JComboBox<>();
        NUMIDTENEDOR = new javax.swing.JTextField();
        NOMIDTENEDOR = new javax.swing.JTextField();
        limpiarCaracteristicas = new javax.swing.JButton();
        connectionStatus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        registrar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        remesa = new javax.swing.JMenuItem();
        manifiesto = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        registrarTerceros = new javax.swing.JMenuItem();
        registrarVehiculos = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        acercaDe = new javax.swing.JMenuItem();

        setTitle("Registro vehículos");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));
        jLayeredPane1.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Registro de Vehículos y Remolques");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Características generales del Vehiculo");

        jLabel3.setText("Placa");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setText("Marca");

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setText("Modelo");

        jLabel8.setText("Peso Vacio");

        jLabel9.setText("Serie chasis");

        jLabel10.setText("Configuración");

        jLabel11.setText("Linea");

        jLabel13.setText("Capacidad");

        jLabel14.setText("Tipo combustible");

        jLabel15.setText("Numero ejes");

        jLabel16.setText("Color");

        jLabel17.setText("Carrocería");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("*");

        NUMCHASIS.setEnabled(false);

        LINEAVEHICULOCARGA.setEnabled(false);

        TIPOCOMBUSTIBLE.setModel(VehiculoDAO.getTipoCombustible(conn));
        TIPOCOMBUSTIBLE.setEnabled(false);
        TIPOCOMBUSTIBLE.setSelectedItem(null);
        TIPOCOMBUSTIBLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOCOMBUSTIBLEActionPerformed(evt);
            }
        });

        COLORVEHICULOCARGA.setEnabled(false);

        TIPOCARROCERIA.setModel(VehiculoDAO.getTipoCarroceria(conn));
        TIPOCARROCERIA.setSelectedItem(null);
        TIPOCARROCERIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOCARROCERIAActionPerformed(evt);
            }
        });

        jLabel12.setText("Observaciones");

        OBSERVACIONES.setColumns(20);
        OBSERVACIONES.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        OBSERVACIONES.setLineWrap(true);
        jScrollPane2.setViewportView(OBSERVACIONES);

        CONFIGURACIONUNIDADCARGA.setModel(VehiculoDAO.getConfiguracionVehiculo(conn));
        CONFIGURACIONUNIDADCARGA.setSelectedItem(null);
        CONFIGURACIONUNIDADCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIGURACIONUNIDADCARGAActionPerformed(evt);
            }
        });

        CODLINEAVEHICULOCARGA.setEditable(false);

        CODMARCAVEHICULOCARGA.setEditable(false);

        CODCOLORVEHICULOCARGA.setEditable(false);

        CODCONFIGURACIONUNIDADCARGA.setEditable(false);

        PESOVEHICULOVACIO.setModel(new javax.swing.SpinnerNumberModel(100, 100, null, 50));

        jLabel34.setText("kg");

        CAPACIDADUNIDADCARGA.setModel(new javax.swing.SpinnerNumberModel(100, 100, null, 100));

        jLabel35.setText("kg");

        NUMEJES.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        ANOFABRICACIONVEHICULOCARGA.setMinimum(1950);
        ANOFABRICACIONVEHICULOCARGA.setValue(2000);

        CODTIPOCARROCERIA.setEditable(false);

        CODTIPOCOMBUSTIBLE.setEditable(false);

        jLabel36.setText("T.P.");

        jLabel37.setText("Serie motor");

        jLabel38.setText("Afiliación");

        jLabel39.setText("RNTC No.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CODMARCAVEHICULOCARGA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CODLINEAVEHICULOCARGA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(COLORVEHICULOCARGA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CODCOLORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CODCONFIGURACIONUNIDADCARGA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel38))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TIPOCOMBUSTIBLE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TIPOCARROCERIA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CODTIPOCARROCERIA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CODTIPOCOMBUSTIBLE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(NUMEJES, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CAPACIDADUNIDADCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NUMPLACA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tarjeta_propiedad))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(afiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rntc))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(MARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CONFIGURACIONUNIDADCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LINEAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(PESOVEHICULOVACIO, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel34))
                                    .addComponent(NUMCHASIS, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ANOFABRICACIONVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel37)
                                        .addGap(2, 2, 2)
                                        .addComponent(serie_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(NUMPLACA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(tarjeta_propiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel20)
                    .addComponent(CONFIGURACIONUNIDADCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODCONFIGURACIONUNIDADCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(MARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODMARCAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel21)
                    .addComponent(LINEAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODLINEAVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ANOFABRICACIONVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(serie_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(COLORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODCOLORVEHICULOCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel19)
                    .addComponent(PESOVEHICULOVACIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel15)
                    .addComponent(NUMEJES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(TIPOCARROCERIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODTIPOCARROCERIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TIPOCOMBUSTIBLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODTIPOCOMBUSTIBLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(NUMCHASIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CAPACIDADUNIDADCARGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(afiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(rntc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("SOAT");

        jLabel23.setText("Número Poliza");

        jLabel24.setText("Fecha vencimiento");

        jLabel25.setText("Aseguradora");

        FECHAVENCIMIENTOSOAT.setDateFormatString("EEEE, dd MMMM yyyy");
        FECHAVENCIMIENTOSOAT.setMinSelectableDate(new Date());

        NUMNITASEGURADORASOAT.setModel(RemesaDAO.getAseguradoras(conn));
        NUMNITASEGURADORASOAT.setPrototypeDisplayValue("");
        NUMNITASEGURADORASOAT.setSelectedItem(null);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/buscar.png"))); // NOI18N
        jLabel40.setText("Buscar aseguradora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel23)))
                                .addComponent(jLabel24))
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NUMSEGUROSOAT)
                            .addComponent(FECHAVENCIMIENTOSOAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NUMNITASEGURADORASOAT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BUSCARNUMNITASEGURADORASOAT)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(NUMSEGUROSOAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FECHAVENCIMIENTOSOAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(NUMNITASEGURADORASOAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BUSCARNUMNITASEGURADORASOAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Propietario");

        jLabel27.setText("Tipo identificación");

        jLabel28.setText("Numero identificación");

        jLabel29.setText("Nombre");

        TIPOIDPROPIETARIO.setModel(ManifiestoDAO.tipo_id(conn));
        TIPOIDPROPIETARIO.setSelectedItem(null);
        TIPOIDPROPIETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOIDPROPIETARIOActionPerformed(evt);
            }
        });

        NOMIDPROPIETARIO.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TIPOIDPROPIETARIO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NUMIDPROPIETARIO)
                            .addComponent(NOMIDPROPIETARIO)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TIPOIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(NUMIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(NOMIDPROPIETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Tenedor");

        jLabel31.setText("Tipo identificación");

        jLabel32.setText("Numero identificación");

        jLabel33.setText("Nombre");

        TIPOIDTENEDOR.setModel(ManifiestoDAO.tipo_id(conn));
        TIPOIDTENEDOR.setSelectedItem(null);
        TIPOIDTENEDOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPOIDTENEDORActionPerformed(evt);
            }
        });

        NOMIDTENEDOR.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TIPOIDTENEDOR, 0, 184, Short.MAX_VALUE)
                            .addComponent(NUMIDTENEDOR)
                            .addComponent(NOMIDTENEDOR)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TIPOIDTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(NUMIDTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(NOMIDTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        limpiarCaracteristicas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/limpiar.png"))); // NOI18N
        limpiarCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCaracteristicasActionPerformed(evt);
            }
        });

        connectionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/connection.png"))); // NOI18N
        connectionStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionStatusActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/tabla.png"))); // NOI18N
        jButton1.setToolTipText("Mostrar registros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(limpiarCaracteristicas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(connectionStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiarCaracteristicas))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(limpiarCaracteristicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(connectionStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        registrar.setText("Registrar Vehiculo");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jMenu3.add(registrar);
        jMenu3.add(jSeparator2);

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
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarVehiculo() {
        try {
            String tipoVehiculo = VehiculoDAO.getTipoVehiculo(Items.getValue(CONFIGURACIONUNIDADCARGA), conn);

            if (tipoVehiculo.equals("Cabezote") || tipoVehiculo.equals("Rígido")) {
                Thread registro = new Thread(new RegistrarVehiculo(getValuesVehiculo(),updateVehiculo, Items.getValue(CONFIGURACIONUNIDADCARGA), this, connectionStatus, dataSource, conn));
                registro.start();
            } else {
                Thread registro = new Thread(new RegistrarRemolque(getValuesRemolque(),updateRemolque, this, connectionStatus, Items.getValue(CONFIGURACIONUNIDADCARGA), dataSource, conn));
                registro.start();
            }

        } catch (Exception e) {
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.start();
        }
    }

    private Remolque getValuesRemolque() {
        try {
            Remolque remolque = new Remolque();
            Data.auth(remolque, conn);
            //Caracteristicas Generales del Remolque
            remolque.setNUMPLACA(NUMPLACA.getText());
            remolque.setTarjeta_propiedad(tarjeta_propiedad.getText());
            remolque.setCODCONFIGURACIONUNIDADCARGA(CODCONFIGURACIONUNIDADCARGA.getText());
            remolque.setCODMARCAVEHICULOCARGA(CODMARCAVEHICULOCARGA.getText());
            remolque.setNUMEJES(NUMEJES.getValue().toString());
            remolque.setANOFABRICACIONVEHICULOCARGA(String.valueOf(ANOFABRICACIONVEHICULOCARGA.getYear()));
            //No disponible ANOREPOTENCIACION
            remolque.setPESOVEHICULOVACIO(PESOVEHICULOVACIO.getValue().toString());
            remolque.setCAPACIDADUNIDADCARGA(CAPACIDADUNIDADCARGA.getValue().toString());
            remolque.setUNIDADMEDIDACAPACIDAD("Kilogramos");
            remolque.setCODTIPOCARROCERIA(CODTIPOCARROCERIA.getText());
            remolque.setNUMCHASIS(NUMCHASIS.getText());
            remolque.setNUMSEGUROSOAT(NUMSEGUROSOAT.getText());
            try {
                remolque.setFECHAVENCIMIENTOSOAT(FECHAVENCIMIENTOSOAT.getDate());
            } catch (Exception e) {
                remolque.setFECHAVENCIMIENTOSOAT(null);
            }
            try {
                remolque.setNUMNITASEGURADORASOAT(UniversalDAO.getString("select id from aseguradora where nombre='" + NUMNITASEGURADORASOAT.getSelectedItem().toString() + "'", conn));
            } catch (Exception e) {
                System.out.println("numnit error");
                remolque.setNUMNITASEGURADORASOAT("");
            }

            remolque.setCODTIPOIDPROPIETARIO(VehiculoDAO.getTipoId(Items.getValue(TIPOIDPROPIETARIO), conn));
            remolque.setNUMIDPROPIETARIO(NUMIDPROPIETARIO.getText());
            remolque.setCODTIPOIDTENEDOR(VehiculoDAO.getTipoId(Items.getValue(TIPOIDTENEDOR), conn));
            remolque.setNUMIDTENEDOR(NUMIDTENEDOR.getText());
            remolque.setRntc(rntc.getText());
            remolque.setAfiliacion(afiliacion.getText());
            remolque.setEstado("activo");
            remolque.setUserLog(username);
            remolque.setObservaciones(OBSERVACIONES.getText());
            return remolque;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage() + e.getClass().toString() + "getValues()");
        }
        return new Remolque();
    }

    private Vehiculo getValuesVehiculo() {
        try {
            Vehiculo vehiculo = new Vehiculo();
            Data.auth(vehiculo, conn);
            //Caracteristicas Generales del Vehiculo
            vehiculo.setNUMPLACA(NUMPLACA.getText());
            vehiculo.setTarjeta_propiedad(tarjeta_propiedad.getText());
            vehiculo.setSerie_motor(serie_motor.getText());
            vehiculo.setAfiliacion(afiliacion.getText());
            vehiculo.setCODCONFIGURACIONUNIDADCARGA(CODCONFIGURACIONUNIDADCARGA.getText());
            vehiculo.setCODMARCAVEHICULOCARGA(CODMARCAVEHICULOCARGA.getText());
            vehiculo.setCODLINEAVEHICULOCARGA(CODLINEAVEHICULOCARGA.getText());
            vehiculo.setNUMEJES(NUMEJES.getValue().toString());
            vehiculo.setANOFABRICACIONVEHICULOCARGA(String.valueOf(ANOFABRICACIONVEHICULOCARGA.getYear()));
            //No disponible ANOREPOTENCIACION
            vehiculo.setRntc(rntc.getText());
            vehiculo.setCODCOLORVEHICULOCARGA(CODCOLORVEHICULOCARGA.getText());
            vehiculo.setPESOVEHICULOVACIO(PESOVEHICULOVACIO.getValue().toString());
            vehiculo.setCAPACIDADUNIDADCARGA(CAPACIDADUNIDADCARGA.getValue().toString());
            vehiculo.setUNIDADMEDIDACAPACIDAD("Kilogramos");
            vehiculo.setCODTIPOCARROCERIA(CODTIPOCARROCERIA.getText());
            vehiculo.setNUMCHASIS(NUMCHASIS.getText());
            vehiculo.setCODTIPOCOMBUSTIBLE(CODTIPOCOMBUSTIBLE.getText());
            vehiculo.setNUMSEGUROSOAT(NUMSEGUROSOAT.getText());
            vehiculo.setFECHAVENCIMIENTOSOAT(FECHAVENCIMIENTOSOAT.getDate());
            vehiculo.setNUMNITASEGURADORASOAT(VehiculoDAO.getNitAseguradora(Items.getValue(NUMNITASEGURADORASOAT), conn));
            vehiculo.setCODTIPOIDPROPIETARIO(VehiculoDAO.getTipoId(Items.getValue(TIPOIDPROPIETARIO), conn));
            vehiculo.setNUMIDPROPIETARIO(NUMIDPROPIETARIO.getText());
            vehiculo.setCODTIPOIDTENEDOR(VehiculoDAO.getTipoId(Items.getValue(TIPOIDTENEDOR), conn));
            vehiculo.setNUMIDTENEDOR(NUMIDTENEDOR.getText());
            vehiculo.setEstado("activo");
            vehiculo.setUserLog(username);
            vehiculo.setObservaciones(OBSERVACIONES.getText());
            return vehiculo;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage() + e.getClass().toString() + "getValues()");
        }
        return new Vehiculo();
    }


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void limpiarCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCaracteristicasActionPerformed
        limpiarCaracteristicas();
    }//GEN-LAST:event_limpiarCaracteristicasActionPerformed

    private void CONFIGURACIONUNIDADCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIGURACIONUNIDADCARGAActionPerformed
        try {
            if (!CONFIGURACIONUNIDADCARGA.getSelectedItem().toString().equals("")) {
                setCONFIGURACIONUNIDADCARGA();
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage() + ":CONFIGURACIONUNIDADCARGAActionPerformed");
        }

    }//GEN-LAST:event_CONFIGURACIONUNIDADCARGAActionPerformed

    private void TIPOCARROCERIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOCARROCERIAActionPerformed
        try {
            if (!TIPOCARROCERIA.getSelectedItem().toString().equals("")) {
                setTIPOCARROCERIA();
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_TIPOCARROCERIAActionPerformed

    private void TIPOCOMBUSTIBLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOCOMBUSTIBLEActionPerformed
        try {
            if (!TIPOCOMBUSTIBLE.getSelectedItem().toString().equals("")) {
                setTIPOCOMBUSTIBLE();
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_TIPOCOMBUSTIBLEActionPerformed

    private void connectionStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionStatusActionPerformed
        try {
            Thread verificacion = new Thread(new VerifyConnection(connectionStatus, this));
            verificacion.start();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_connectionStatusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openFormViewVehiculo(dataSource, conn);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void openFormViewVehiculo(DataSourceClass dataSource,Connection conn) {
        String[] preModel = {
            "Vehiculos",
            "Remolques"
        };
        DefaultComboBoxModel model = new DefaultComboBoxModel(preModel);
        String[] querys = {
            "select * from vehiculos",
            "select * from remolques"
        };
        OpenForm.view("Vehiculos", querys, model, dataSource, conn);
    }

    private void TIPOIDPROPIETARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOIDPROPIETARIOActionPerformed
        FillNUMIDTERCERO(TIPOIDPROPIETARIO, acNUMIDPROPIETARIO, NUMIDPROPIETARIO, NOMIDPROPIETARIO);
    }//GEN-LAST:event_TIPOIDPROPIETARIOActionPerformed


    private void TIPOIDTENEDORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPOIDTENEDORActionPerformed
        FillNUMIDTERCERO(TIPOIDTENEDOR, acNUMIDTENEDOR, NUMIDTENEDOR, NOMIDTENEDOR);
    }//GEN-LAST:event_TIPOIDTENEDORActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        registrarVehiculo();
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

    private void setTIPOCOMBUSTIBLE() {
        CODTIPOCOMBUSTIBLE.setText(VehiculoDAO.getCodTipoCombustible(TIPOCOMBUSTIBLE.getSelectedItem().toString(), conn));
    }

    private void setTIPOCARROCERIA() {
        CODTIPOCARROCERIA.setText(VehiculoDAO.getCodTipoCarroceria(TIPOCARROCERIA.getSelectedItem().toString(), conn));
    }

    private void setCONFIGURACIONUNIDADCARGA() {
        CODCONFIGURACIONUNIDADCARGA.setText(VehiculoDAO.getCODCONFIGURACIONUNIDADCARGA(CONFIGURACIONUNIDADCARGA.getSelectedItem().toString(), conn));

        String tipoVehiculo = VehiculoDAO.getTipoVehiculo(CONFIGURACIONUNIDADCARGA.getSelectedItem().toString(), conn);

        if (tipoVehiculo.equals("Rígido") || tipoVehiculo.equals("Cabezote")) {
            activarCamposVehiculos();
            buildAutoCompleterMARCAVEHICULOCARGA(false);
        } else {
            desactivarCamposVehiculos();
            buildAutoCompleterMARCAVEHICULOCARGA(true);

        }

    }

    private void desactivarCamposVehiculos() {
        LINEAVEHICULOCARGA.setEnabled(false);
        COLORVEHICULOCARGA.setEnabled(false);
        TIPOCOMBUSTIBLE.setEnabled(false);
        NUMCHASIS.setEnabled(false);
        serie_motor.setEnabled(false);
//        NUMSEGUROSOAT.setEnabled(false);
//        FECHAVENCIMIENTOSOAT.setEnabled(false);
//        NUMNITASEGURADORASOAT.setEnabled(false);
    }

    private void activarCamposVehiculos() {
        LINEAVEHICULOCARGA.setEnabled(true);
        COLORVEHICULOCARGA.setEnabled(true);
        TIPOCOMBUSTIBLE.setEnabled(true);
        NUMCHASIS.setEnabled(true);
        serie_motor.setEnabled(true);
//        NUMSEGUROSOAT.setEnabled(true);
//        FECHAVENCIMIENTOSOAT.setEnabled(true);
//        NUMNITASEGURADORASOAT.setEnabled(true);
    }

    private void limpiarCaracteristicas() {
        updateRemolque = false;
        updateVehiculo = false;
        desactivarCamposVehiculos();
        afiliacion.setText("");
        rntc.setText("");
        serie_motor.setText("");
        tarjeta_propiedad.setText("");
        CODTIPOCOMBUSTIBLE.setText("");
        CONFIGURACIONUNIDADCARGA.setSelectedItem(null);
        CODCOLORVEHICULOCARGA.setText("");
        ANOFABRICACIONVEHICULOCARGA.setValue(2000);
        CAPACIDADUNIDADCARGA.setValue(100);
        COLORVEHICULOCARGA.setText("");
        CODCONFIGURACIONUNIDADCARGA.setText("");
        CODLINEAVEHICULOCARGA.setText("");
        CODMARCAVEHICULOCARGA.setText("");
        TIPOCARROCERIA.setSelectedItem(null);
        CODTIPOCARROCERIA.setText("");
        TIPOCOMBUSTIBLE.setSelectedItem(null);
        TIPOIDPROPIETARIO.setSelectedItem(null);
        TIPOIDTENEDOR.setSelectedItem(null);
        FECHAVENCIMIENTOSOAT.setDate(null);
        LINEAVEHICULOCARGA.setText("");
        MARCAVEHICULOCARGA.setText("");
        NUMCHASIS.setText("");
        NUMIDPROPIETARIO.setText("");
        NUMIDTENEDOR.setText("");
        NUMNITASEGURADORASOAT.setSelectedItem(null);
        NUMPLACA.setText("");
        NUMSEGUROSOAT.setText("");
        PESOVEHICULOVACIO.setValue(100);
        NUMEJES.setValue(1);
        OBSERVACIONES.setText("");
        NOMIDPROPIETARIO.setText("");
        NOMIDTENEDOR.setText("");
        acNUMIDPROPIETARIO.removeAllItems();
        acNUMIDTENEDOR.removeAllItems();
        acMARCAVEHICULOCARGA.removeAllItems();
        acLINEAVEHICULOCARGA.removeAllItems();
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
            java.util.logging.Logger.getLogger(FormVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormVehiculos(new DataSourceClass(), new DataSourceClass().conectar(1), "noUser").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser ANOFABRICACIONVEHICULOCARGA;
    private javax.swing.JTextField BUSCARNUMNITASEGURADORASOAT;
    private javax.swing.JSpinner CAPACIDADUNIDADCARGA;
    private javax.swing.JTextField CODCOLORVEHICULOCARGA;
    private javax.swing.JTextField CODCONFIGURACIONUNIDADCARGA;
    private javax.swing.JTextField CODLINEAVEHICULOCARGA;
    private javax.swing.JTextField CODMARCAVEHICULOCARGA;
    private javax.swing.JTextField CODTIPOCARROCERIA;
    private javax.swing.JTextField CODTIPOCOMBUSTIBLE;
    private javax.swing.JTextField COLORVEHICULOCARGA;
    private javax.swing.JComboBox<String> CONFIGURACIONUNIDADCARGA;
    private com.toedter.calendar.JDateChooser FECHAVENCIMIENTOSOAT;
    private javax.swing.JTextField LINEAVEHICULOCARGA;
    private javax.swing.JTextField MARCAVEHICULOCARGA;
    private javax.swing.JTextField NOMIDPROPIETARIO;
    private javax.swing.JTextField NOMIDTENEDOR;
    private javax.swing.JTextField NUMCHASIS;
    private javax.swing.JSpinner NUMEJES;
    private javax.swing.JTextField NUMIDPROPIETARIO;
    private javax.swing.JTextField NUMIDTENEDOR;
    private javax.swing.JComboBox<String> NUMNITASEGURADORASOAT;
    private javax.swing.JTextField NUMPLACA;
    private javax.swing.JTextField NUMSEGUROSOAT;
    private javax.swing.JTextArea OBSERVACIONES;
    private javax.swing.JSpinner PESOVEHICULOVACIO;
    private javax.swing.JComboBox<String> TIPOCARROCERIA;
    private javax.swing.JComboBox<String> TIPOCOMBUSTIBLE;
    private javax.swing.JComboBox<String> TIPOIDPROPIETARIO;
    private javax.swing.JComboBox<String> TIPOIDTENEDOR;
    private javax.swing.JMenuItem acercaDe;
    private javax.swing.JTextField afiliacion;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton connectionStatus;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton limpiarCaracteristicas;
    private javax.swing.JMenuItem manifiesto;
    private javax.swing.JMenuItem registrar;
    private javax.swing.JMenuItem registrarTerceros;
    private javax.swing.JMenuItem registrarVehiculos;
    private javax.swing.JMenuItem remesa;
    private javax.swing.JTextField rntc;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTextField serie_motor;
    private javax.swing.JTextField tarjeta_propiedad;
    // End of variables declaration//GEN-END:variables
}
