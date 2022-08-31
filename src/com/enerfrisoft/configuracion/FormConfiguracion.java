/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.configuracion;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.modal.Modal;
import com.enerfrisoft.openForm.OpenForm;
import com.enerfrisoft.tools.DateParsing;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.Visual;
import com.enerfrisoft.usuario.Tools;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sebastianaf
 */
public class FormConfiguracion extends javax.swing.JFrame {

    private String username;

    private String conPalOCTT;
    private int conNumOCTT;
    private int avisoOCTT;
    private int maxOCTT;
    private String conPalRemTT;
    private int conNumRemTT;
    private int avisoRemTT;
    private int maxRemTT;
    private String conPalManTT;
    private int conNumManTT;
    private int avisoManTT;
    private int maxManTT;
    private String conPalOCTC;
    private int conNumOCTC;
    private int avisoOCTC;
    private int maxOCTC;
    private String conPalRemTC;
    private int conNumRemTC;
    private int avisoRemTC;
    private int maxRemTC;
    private String conPalManTC;
    private int conNumManTC;
    private int avisoManTC;
    private int maxManTC;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;
    
    /**
     * Creates new form FormConfiguracion
     */
    public FormConfiguracion(DataSourceClass dataSource,Connection conn, String username) {
        this.conn = conn;
        this.dataSource = dataSource;
        this.defaultServerConn = this.dataSource.conectar(0);
        this.username = username;
        
        initComponents();
        Visual.windowIcon(this);
        fillData();
        verifyConfig();
    }

    private void verifyConfig() {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        String param1 = UniversalDAO.getString(""
                + "select server.activarDireccionesLibres "
                + "from server "
                + "where server.id = '" + String.valueOf(server) + "'", defaultServerConn);
        //System.out.println(param1);
        if (param1.equals("1")) {
            jCheckBox1.setSelected(true);
        }
    }

    public void fillData() {
        try {
            aseguradora1.setSelectedItem(UniversalDAO.getString(""
                    + "select aseguradora "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            poliza1.setText(UniversalDAO.getString(""
                    + "select poliza "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            vencimiento1.setDate(DateParsing.fromMySQL(UniversalDAO.getString(""
                    + "select vencimiento "
                    + "from server "
                    + "where id='1'", defaultServerConn)));
            aseguradora2.setSelectedItem(UniversalDAO.getString(""
                    + "select aseguradora "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            poliza2.setText(UniversalDAO.getString(""
                    + "select poliza "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            vencimiento2.setDate(DateParsing.fromMySQL(UniversalDAO.getString(""
                    + "select vencimiento "
                    + "from server "
                    + "where id='2'", defaultServerConn)));

            conPalOCTT = UniversalDAO.getString(""
                    + "select conPalOC "
                    + "from server "
                    + "where id='1';", defaultServerConn);
            conNumOCTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumOC "
                    + "from server "
                    + "where id='1';", defaultServerConn));
            avisoOCTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumOC "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            maxOCTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumOC "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            conOCTT.setText(conPalOCTT + conNumOCTT);

            conPalRemTT = UniversalDAO.getString(""
                    + "select conPalRem "
                    + "from server "
                    + "where id='1';", defaultServerConn);
            conNumRemTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumRem "
                    + "from server "
                    + "where id='1';", defaultServerConn));
            avisoRemTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumRem "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            maxRemTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumRem "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            conRemTT.setText(conPalRemTT + conNumRemTT);

            conPalManTT = UniversalDAO.getString(""
                    + "select conPalMan "
                    + "from server "
                    + "where id='1';", defaultServerConn);
            conNumManTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumMan "
                    + "from server "
                    + "where id='1';", defaultServerConn));
            avisoManTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumMan "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            maxManTT = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumMan "
                    + "from server "
                    + "where id='1'", defaultServerConn));
            conManTT.setText(conPalManTT + conNumManTT);

            conPalOCTC = UniversalDAO.getString(""
                    + "select conPalOC "
                    + "from server "
                    + "where id='2';", defaultServerConn);
            conNumOCTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumOC "
                    + "from server "
                    + "where id='2';", defaultServerConn));
            avisoOCTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumOC "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            maxOCTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumOC "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            conOCTC.setText(conPalOCTC + conNumOCTC);

            conPalRemTC = UniversalDAO.getString(""
                    + "select conPalRem "
                    + "from server "
                    + "where id='2';", defaultServerConn);
            conNumRemTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumRem "
                    + "from server "
                    + "where id='2';", defaultServerConn));
            avisoRemTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumRem "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            maxRemTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumRem "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            conRemTC.setText(conPalRemTC + conNumRemTC);

            conPalManTC = UniversalDAO.getString(""
                    + "select conPalMan "
                    + "from server "
                    + "where id='2';", defaultServerConn);
            conNumManTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select conNumMan "
                    + "from server "
                    + "where id='2';", defaultServerConn));
            avisoManTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select avisoconNumMan "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            maxManTC = Integer.valueOf(UniversalDAO.getString(""
                    + "select maxconNumMan "
                    + "from server "
                    + "where id='2'", defaultServerConn));
            conManTC.setText(conPalManTC + conNumManTC);

            usuarioTT.setText(Tools.decrypt(UniversalDAO.getString(""
                    + "select username "
                    + "from server "
                    + "where id='1'", defaultServerConn), this));
            passTT.setText(Tools.decrypt(UniversalDAO.getString(""
                    + "select passwd "
                    + "from server "
                    + "where server.id = '1'", defaultServerConn), this));

            usuarioTC.setText(Tools.decrypt(UniversalDAO.getString(""
                    + "select username "
                    + "from server "
                    + "where id='2'", defaultServerConn), this));
            passTC.setText(Tools.decrypt(UniversalDAO.getString(""
                    + "select passwd "
                    + "from server "
                    + "where server.id = '2'", defaultServerConn), this));

        } catch (Exception e) {
            System.out.println("exception");
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        conOCTT = new javax.swing.JTextField();
        ordenCargueTT = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        conRemTT = new javax.swing.JTextField();
        remesaTT = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        conManTT = new javax.swing.JTextField();
        manifiestoTT = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        conOCTC = new javax.swing.JTextField();
        ordenCargueTC = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        conRemTC = new javax.swing.JTextField();
        remesaTC = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        conManTC = new javax.swing.JTextField();
        manifiestoTC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuarioTT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        passTT = new javax.swing.JPasswordField();
        usuarioTC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        passTC = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        aseguradora1 = new javax.swing.JComboBox<>();
        poliza1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        aseguradora2 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        poliza2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        vencimiento1 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        vencimiento2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Consecutivos Actuales");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Empresa1");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Empresa2");

        jLabel19.setText("Orden de cargue");

        conOCTT.setEditable(false);
        conOCTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conOCTT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ordenCargueTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        ordenCargueTT.setText("Editar");
        ordenCargueTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenCargueTTActionPerformed(evt);
            }
        });

        jLabel21.setText("Remesa");

        conRemTT.setEditable(false);
        conRemTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conRemTT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        remesaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        remesaTT.setText("Editar");
        remesaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remesaTTActionPerformed(evt);
            }
        });

        jLabel22.setText("Manifiesto");

        conManTT.setEditable(false);
        conManTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conManTT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        manifiestoTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        manifiestoTT.setText("Editar");
        manifiestoTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manifiestoTTActionPerformed(evt);
            }
        });

        jLabel23.setText("Orden de cargue");

        conOCTC.setEditable(false);
        conOCTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conOCTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ordenCargueTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        ordenCargueTC.setText("Editar");
        ordenCargueTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenCargueTCActionPerformed(evt);
            }
        });

        jLabel24.setText("Remesa");

        conRemTC.setEditable(false);
        conRemTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conRemTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        remesaTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        remesaTC.setText("Editar");
        remesaTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remesaTCActionPerformed(evt);
            }
        });

        jLabel25.setText("Manifiesto");

        conManTC.setEditable(false);
        conManTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        conManTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        manifiestoTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/edit.png"))); // NOI18N
        manifiestoTC.setText("Editar");
        manifiestoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manifiestoTCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conRemTC, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remesaTC))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conManTC, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manifiestoTC))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conOCTT, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ordenCargueTT))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conRemTT, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remesaTT))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conManTT, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manifiestoTT))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(conOCTC, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ordenCargueTC)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conOCTT)
                    .addComponent(ordenCargueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conRemTT)
                    .addComponent(remesaTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conManTT)
                    .addComponent(manifiestoTT))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conOCTC)
                    .addComponent(ordenCargueTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conRemTC)
                    .addComponent(remesaTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conManTC)
                    .addComponent(manifiestoTC))
                .addGap(22, 22, 22))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Configuración");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Datos de usuarios RNDC");

        jLabel3.setText("Usuario");

        usuarioTT.setEditable(false);
        usuarioTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usuarioTT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuarioTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioTTActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Empresa1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Empresa2");

        jLabel7.setText("Contraseña");

        passTT.setEditable(false);
        passTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passTT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        usuarioTC.setEditable(false);
        usuarioTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usuarioTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuarioTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioTCActionPerformed(evt);
            }
        });

        jLabel8.setText("Usuario");

        jLabel9.setText("Contraseña");

        passTC.setEditable(false);
        passTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passTC, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usuarioTC, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usuarioTT, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passTT, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(usuarioTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usuarioTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("Permitir ingresar palabras libres");
        jCheckBox1.setToolTipText("Permitir ingresar palabras libres para todos los usuarios en el campo de dirección para registrar un tercero");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Parametros");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jCheckBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Pólizas registradas");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/guardar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Empresa aseguradora");

        aseguradora1.setModel(UniversalDAO.getComboBoxModel(""
            + "select aseguradora.nombre "
            + "from aseguradora", conn));

    jLabel12.setText("Número de póliza");

    jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel16.setText("Empresa1");

    jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel17.setText("Empresa2");

    jLabel18.setText("Empresa aseguradora");

    aseguradora2.setModel(UniversalDAO.getComboBoxModel(""
        + "select aseguradora.nombre "
        + "from aseguradora", conn));

jLabel20.setText("Número de póliza");

jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/guardar.png"))); // NOI18N
jButton2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
    }
    });

    vencimiento1.setDateFormatString("EEEE, dd MMMM yyyy");

    jLabel26.setText("Vencimiento");

    jLabel27.setText("Vencimiento");

    vencimiento2.setDateFormatString("EEEE, dd MMMM yyyy");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12)
                        .addComponent(jLabel10)
                        .addComponent(jLabel26))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(aseguradora1, 0, 180, Short.MAX_VALUE)
                        .addComponent(poliza1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(vencimiento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel17)
                .addComponent(jLabel13)
                .addComponent(jLabel16)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel20)
                        .addComponent(jLabel18)
                        .addComponent(jLabel27))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(vencimiento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(aseguradora2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(poliza2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))))
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel13)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel16)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(aseguradora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(poliza1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(vencimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel26))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton2)
            .addGap(5, 5, 5)
            .addComponent(jLabel17)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(aseguradora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel20)
                .addComponent(poliza2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(vencimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel27))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton1)
            .addGap(25, 25, 25))
    );

    jButton3.setText("Aceptar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3)
            .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton3)
            .addContainerGap())
    );

    jMenu3.setText("Archivo");
    jMenu3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenu3ActionPerformed(evt);
        }
    });
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
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioTTActionPerformed

    private void usuarioTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioTCActionPerformed

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

    private void ordenCargueTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenCargueTTActionPerformed
        OpenForm.editarConsecutivo(this, conOCTT, conPalOCTT, conNumOCTT,avisoOCTT,maxOCTT, "conPalOC", "conNumOC", "1",dataSource, conn);
    }//GEN-LAST:event_ordenCargueTTActionPerformed

    private void remesaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remesaTTActionPerformed
        OpenForm.editarConsecutivo(this, conRemTT, conPalRemTT, conNumRemTT,avisoRemTT,maxRemTT, "conPalRem", "conNumRem", "1",dataSource, conn);
    }//GEN-LAST:event_remesaTTActionPerformed

    private void manifiestoTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manifiestoTTActionPerformed
        OpenForm.editarConsecutivo(this, conManTT, conPalManTT, conNumManTT,avisoManTT,maxManTT, "conPalMan", "conNumMan", "1",dataSource, conn);
    }//GEN-LAST:event_manifiestoTTActionPerformed

    private void ordenCargueTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenCargueTCActionPerformed
        OpenForm.editarConsecutivo(this, conOCTC, conPalOCTC, conNumOCTC,avisoOCTC,maxOCTC, "conPalOC", "conNumOC", "2",dataSource, conn);
    }//GEN-LAST:event_ordenCargueTCActionPerformed

    private void remesaTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remesaTCActionPerformed
        OpenForm.editarConsecutivo(this, conRemTC, conPalRemTC, conNumRemTC,avisoRemTC,maxRemTC, "conPalRem", "conNumRem", "2",dataSource, conn);
    }//GEN-LAST:event_remesaTCActionPerformed

    private void manifiestoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manifiestoTCActionPerformed
        OpenForm.editarConsecutivo(this, conManTC, conPalManTC, conNumManTC,avisoManTC,maxManTC, "conPalMan", "conNumMan", "2",dataSource, conn);
    }//GEN-LAST:event_manifiestoTCActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));  
        String state = UniversalDAO.getString(""
                + "select server.activarDireccionesLibres "
                + "from server "
                + "where id='" + String.valueOf(server) + "'", defaultServerConn);
        switch (state) {
            case "0":
                UniversalDAO.execute(""
                        + "update server "
                        + "set server.activarDireccionesLibres = '1' "
                        + "where server.id='" + String.valueOf(server) + "'", defaultServerConn);
                break;
            case "1":
                UniversalDAO.execute(""
                        + "update server "
                        + "set server.activarDireccionesLibres = '0' "
                        + "where server.id='" + String.valueOf(server) + "'", defaultServerConn);
                break;
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        registrarPoliza(aseguradora1.getSelectedItem().toString(), poliza1.getText(), vencimiento1.getDate(), 1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarPoliza(aseguradora2.getSelectedItem().toString(), poliza2.getText(), vencimiento2.getDate(), 2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void registrarPoliza(String aseguradora, String poliza, Date vencimiento, int server) {
        if (UniversalDAO.executeVerifing(""
                + "update server "
                + "set "
                + "aseguradora = '" + aseguradora + "', "
                + "poliza = '" + poliza + "', "
                + "vencimiento = '" + DateParsing.toMySQL(vencimiento) + "' "
                + "where server.id = '" + String.valueOf(server) + "';", defaultServerConn)) {
            Modal.show("Aviso", "La póliza ha sido registrada", this, "", "ok");
        } else {
            Modal.show("Error", "La póliza no pudo registrarse", this, "", "error");
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
            java.util.logging.Logger.getLogger(FormConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new FormConfiguracion(new DataSourceClass(), new DataSourceClass().conectar(1), "testingTT").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acercaDe;
    private javax.swing.JComboBox<String> aseguradora1;
    private javax.swing.JComboBox<String> aseguradora2;
    private javax.swing.JMenu ayuda;
    private javax.swing.JTextField conManTC;
    private javax.swing.JTextField conManTT;
    private javax.swing.JTextField conOCTC;
    private javax.swing.JTextField conOCTT;
    private javax.swing.JTextField conRemTC;
    private javax.swing.JTextField conRemTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem manifiesto;
    private javax.swing.JButton manifiestoTC;
    private javax.swing.JButton manifiestoTT;
    private javax.swing.JButton ordenCargueTC;
    private javax.swing.JButton ordenCargueTT;
    private javax.swing.JPasswordField passTC;
    private javax.swing.JPasswordField passTT;
    private javax.swing.JTextField poliza1;
    private javax.swing.JTextField poliza2;
    private javax.swing.JMenuItem registrarTerceros;
    private javax.swing.JMenuItem registrarVehiculos;
    private javax.swing.JMenuItem remesa;
    private javax.swing.JButton remesaTC;
    private javax.swing.JButton remesaTT;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTextField usuarioTC;
    private javax.swing.JTextField usuarioTT;
    private com.toedter.calendar.JDateChooser vencimiento1;
    private com.toedter.calendar.JDateChooser vencimiento2;
    // End of variables declaration//GEN-END:variables

}
