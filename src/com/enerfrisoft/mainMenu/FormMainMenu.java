/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.mainMenu;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.error.QueryError;
import com.enerfrisoft.modal.Modal;
import com.enerfrisoft.openForm.OpenForm;
import com.enerfrisoft.tools.UniversalDAO;
import com.enerfrisoft.tools.VerifyConnection;
import com.enerfrisoft.tools.Visual;
import com.enerfrisoft.usuario.Tools;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastianaf
 */
public class FormMainMenu extends javax.swing.JFrame {

    private String username;
    private Boolean admin;
    private DataSourceClass dataSource;
    private Connection conn;
    private Connection defaultServerConn;

    /**
     * Creates new form FrameMainMenu
     */
    public FormMainMenu(String username, DataSourceClass dataSource, Connection conn) {
        initComponents();
        this.admin = false;
        this.username = username;
        this.dataSource = dataSource;
        this.conn = conn;
        this.defaultServerConn = this.dataSource.conectar(0);
        Visual.windowIcon(this);
        init();
    }

    private void init() {
        try {
            String nombreCortoEmpresa = UniversalDAO.getString(""
                    + "select nombreCorto "
                    + "from info_empresa "
                    + "where id='1'", conn);
                    
            String nombre = UniversalDAO.getString(""
                    + "select nombre "
                    + "from usuario "
                    + "where username='" + Tools.encrypt(username, this) + "'", defaultServerConn);
            
            userName.setText(nombreCortoEmpresa + "/" + username);

            String tipoUsuario = UniversalDAO.getString(""
                    + "select tipo_usuario "
                    + "from usuario "
                    + "where username='" + Tools.encrypt(username, this) + "'", defaultServerConn);
            if (tipoUsuario.equals("administrador")) {
                this.admin = true;
                gestionDeUsuarios.setEnabled(true);
                anulaciones.setEnabled(true);
            }

            Modal.show("Bienvenido(a)", "Bienvenido(a) " + Tools.decrypt(nombre, this), this, "", "ok");

        } catch (Exception e) {
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
        jLabel1 = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        connectionStatus = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        registrarManifiesto = new javax.swing.JButton();
        registrarRemesa = new javax.swing.JButton();
        anulaciones = new javax.swing.JButton();
        gestionDeUsuarios = new javax.swing.JButton();
        registrarVehiculos = new javax.swing.JButton();
        registrarTerceros = new javax.swing.JButton();
        verRegistros = new javax.swing.JButton();
        exportarFormatos = new javax.swing.JButton();
        configurar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        itemGestionarUsuarios = new javax.swing.JMenuItem();
        itemVerRegistros = new javax.swing.JMenuItem();
        itemExportarFormatos = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        remesa = new javax.swing.JMenuItem();
        manifiesto = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        registrarTerceros1 = new javax.swing.JMenuItem();
        registrarVehiculos1 = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        itemConfigurar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        acercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Enerfrisoft - Carga");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Menu principal");

        userName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/User Account_24px.png"))); // NOI18N
        userName.setText("<username>");
        userName.setFocusable(false);
        userName.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        connectionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/connection.png"))); // NOI18N
        connectionStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionStatusActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        registrarManifiesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/manifiesto.png"))); // NOI18N
        registrarManifiesto.setText("Registrar manifiesto de carga");
        registrarManifiesto.setAlignmentY(0.0F);
        registrarManifiesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        registrarManifiesto.setIconTextGap(7);
        registrarManifiesto.setMargin(new java.awt.Insets(0, 10, 0, 0));
        registrarManifiesto.setPreferredSize(new java.awt.Dimension(80, 80));
        registrarManifiesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarManifiestoActionPerformed(evt);
            }
        });

        registrarRemesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/remesa.png"))); // NOI18N
        registrarRemesa.setText("Registrar remesa de carga");
        registrarRemesa.setAlignmentY(0.0F);
        registrarRemesa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        registrarRemesa.setIconTextGap(7);
        registrarRemesa.setMargin(new java.awt.Insets(0, 10, 0, 0));
        registrarRemesa.setPreferredSize(new java.awt.Dimension(80, 80));
        registrarRemesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarRemesaActionPerformed(evt);
            }
        });

        anulaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/File Delete_48px.png"))); // NOI18N
        anulaciones.setText("Anulaciones");
        anulaciones.setAlignmentY(0.0F);
        anulaciones.setEnabled(false);
        anulaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        anulaciones.setIconTextGap(7);
        anulaciones.setMargin(new java.awt.Insets(0, 10, 0, 0));
        anulaciones.setPreferredSize(new java.awt.Dimension(80, 80));
        anulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulacionesActionPerformed(evt);
            }
        });

        gestionDeUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/users.png"))); // NOI18N
        gestionDeUsuarios.setText("Gestionar usuarios");
        gestionDeUsuarios.setAlignmentY(0.0F);
        gestionDeUsuarios.setEnabled(false);
        gestionDeUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gestionDeUsuarios.setIconTextGap(7);
        gestionDeUsuarios.setMargin(new java.awt.Insets(0, 10, 0, 0));
        gestionDeUsuarios.setPreferredSize(new java.awt.Dimension(80, 80));
        gestionDeUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionDeUsuariosActionPerformed(evt);
            }
        });

        registrarVehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/vehiculos.png"))); // NOI18N
        registrarVehiculos.setText("Registrar Vehiculos y Remolques");
        registrarVehiculos.setAlignmentY(0.0F);
        registrarVehiculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        registrarVehiculos.setIconTextGap(7);
        registrarVehiculos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        registrarVehiculos.setPreferredSize(new java.awt.Dimension(80, 80));
        registrarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarVehiculosActionPerformed(evt);
            }
        });

        registrarTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/terceros.png"))); // NOI18N
        registrarTerceros.setText("Registrar Terceros y Conductores");
        registrarTerceros.setAlignmentY(0.0F);
        registrarTerceros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        registrarTerceros.setIconTextGap(7);
        registrarTerceros.setMargin(new java.awt.Insets(0, 10, 0, 0));
        registrarTerceros.setPreferredSize(new java.awt.Dimension(80, 80));
        registrarTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarTercerosActionPerformed(evt);
            }
        });

        verRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/ver.png"))); // NOI18N
        verRegistros.setText("Ver registros");
        verRegistros.setAlignmentY(0.0F);
        verRegistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        verRegistros.setIconTextGap(7);
        verRegistros.setMargin(new java.awt.Insets(0, 10, 0, 0));
        verRegistros.setPreferredSize(new java.awt.Dimension(80, 80));
        verRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verRegistrosActionPerformed(evt);
            }
        });

        exportarFormatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/manifiesto/img/exportar.png"))); // NOI18N
        exportarFormatos.setText("Exportar formatos");
        exportarFormatos.setAlignmentY(0.0F);
        exportarFormatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exportarFormatos.setIconTextGap(7);
        exportarFormatos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        exportarFormatos.setPreferredSize(new java.awt.Dimension(80, 80));
        exportarFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarFormatosActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrarVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrarTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exportarFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(registrarManifiesto, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registrarRemesa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gestionDeUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(anulaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarManifiesto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarRemesa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportarFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gestionDeUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anulaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        configurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/enerfrisoft/gui/sources/Settings_20px.png"))); // NOI18N
        configurar.setToolTipText("Configuración");
        configurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userName)
                        .addGap(32, 32, 32)
                        .addComponent(configurar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionStatus)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userName, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1))
                    .addComponent(connectionStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setText("Archivo");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        itemGestionarUsuarios.setText("Gestionar usuarios");
        itemGestionarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionarUsuariosActionPerformed(evt);
            }
        });
        jMenu3.add(itemGestionarUsuarios);

        itemVerRegistros.setText("Ver registros");
        itemVerRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerRegistrosActionPerformed(evt);
            }
        });
        jMenu3.add(itemVerRegistros);

        itemExportarFormatos.setText("Exportar formatos");
        itemExportarFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarFormatosActionPerformed(evt);
            }
        });
        jMenu3.add(itemExportarFormatos);
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

        registrarTerceros1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        registrarTerceros1.setText("Registrar Terceros y Conductores");
        registrarTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarTerceros1ActionPerformed(evt);
            }
        });
        jMenu4.add(registrarTerceros1);

        registrarVehiculos1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        registrarVehiculos1.setText("Registrar Vehiculos y Remolques");
        registrarVehiculos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarVehiculos1ActionPerformed(evt);
            }
        });
        jMenu4.add(registrarVehiculos1);

        jMenuBar1.add(jMenu4);

        ayuda.setText("Opciones");

        itemConfigurar.setText("Configuración");
        itemConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfigurarActionPerformed(evt);
            }
        });
        ayuda.add(itemConfigurar);
        ayuda.add(jSeparator1);

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarManifiestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarManifiestoActionPerformed
        com.enerfrisoft.openForm.OpenForm.manifiesto(dataSource, conn, username);
    }//GEN-LAST:event_registrarManifiestoActionPerformed

    private void registrarRemesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarRemesaActionPerformed
        com.enerfrisoft.openForm.OpenForm.remesa(dataSource, conn, username);
    }//GEN-LAST:event_registrarRemesaActionPerformed

    private void anulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulacionesActionPerformed
        OpenForm.formAnular(username, dataSource, conn);
    }//GEN-LAST:event_anulacionesActionPerformed

    private void gestionDeUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionDeUsuariosActionPerformed
        com.enerfrisoft.openForm.OpenForm.usuarios( dataSource, conn, username);
    }//GEN-LAST:event_gestionDeUsuariosActionPerformed

    private void registrarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarVehiculosActionPerformed
        com.enerfrisoft.openForm.OpenForm.vehiculos(dataSource, conn, username);
    }//GEN-LAST:event_registrarVehiculosActionPerformed

    private void registrarTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarTercerosActionPerformed
        com.enerfrisoft.openForm.OpenForm.terceros(dataSource, conn, username);
    }//GEN-LAST:event_registrarTercerosActionPerformed

    private void verRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verRegistrosActionPerformed
        openVerRegistros();
    }//GEN-LAST:event_verRegistrosActionPerformed

    private void exportarFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarFormatosActionPerformed
        openExportarFormatos();
    }//GEN-LAST:event_exportarFormatosActionPerformed

    private void connectionStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionStatusActionPerformed
        try {
            Thread verificacion = new Thread(new VerifyConnection(connectionStatus, this));
            verificacion.start();
        } catch (Exception e) {
            Thread report = new Thread(new QueryError(new com.enerfrisoft.error.Error(e)));
            report.start();

        }
    }//GEN-LAST:event_connectionStatusActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        Icon icon = new ImageIcon(this.getClass().getResource("/com/enerfrisoft/gui/sources/warning.png"));
        int t = JOptionPane.showConfirmDialog(this, "¿Realmente desea salir? \nSe cerrarán todas las ventanas del programa", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if (t == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void remesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remesaActionPerformed
        com.enerfrisoft.openForm.OpenForm.remesa(dataSource, conn, username);
    }//GEN-LAST:event_remesaActionPerformed

    private void manifiestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manifiestoActionPerformed
        com.enerfrisoft.openForm.OpenForm.manifiesto(dataSource, conn, username);
    }//GEN-LAST:event_manifiestoActionPerformed

    private void registrarTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarTerceros1ActionPerformed
        com.enerfrisoft.openForm.OpenForm.terceros(dataSource, conn, username);
    }//GEN-LAST:event_registrarTerceros1ActionPerformed

    private void registrarVehiculos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarVehiculos1ActionPerformed
        com.enerfrisoft.openForm.OpenForm.vehiculos(dataSource, conn, username);
    }//GEN-LAST:event_registrarVehiculos1ActionPerformed

    private void acercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeActionPerformed
        com.enerfrisoft.openForm.OpenForm.acercaDe(dataSource, conn, username);
    }//GEN-LAST:event_acercaDeActionPerformed

    private void itemVerRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVerRegistrosActionPerformed
        openVerRegistros();
    }//GEN-LAST:event_itemVerRegistrosActionPerformed

    private void openVerRegistros() {
        com.enerfrisoft.openForm.OpenForm.verRegistros(username, dataSource, conn);
    }

    private void itemGestionarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarUsuariosActionPerformed
        openGestionarUsuarios();
    }//GEN-LAST:event_itemGestionarUsuariosActionPerformed

    private void openGestionarUsuarios() {
        if (admin) {
            com.enerfrisoft.openForm.OpenForm.usuarios(dataSource, conn, username);
        } else {
            Modal.show("Aviso", "Esta opción solo está disponible para administradores", this, "", "warning");
        }
    }

    private void itemExportarFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarFormatosActionPerformed
        openExportarFormatos();
    }//GEN-LAST:event_itemExportarFormatosActionPerformed

    private void openExportarFormatos() {
        com.enerfrisoft.openForm.OpenForm.exportarFormatosMenu(username, dataSource, conn);
    }

    private void itemConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfigurarActionPerformed
        openConfigurar();
    }//GEN-LAST:event_itemConfigurarActionPerformed

    private void configurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configurarActionPerformed
        openConfigurar();
    }//GEN-LAST:event_configurarActionPerformed

//    private void corregirRemesasActionPerformed(java.awt.event.ActionEvent evt) {
//        com.enerfrisoft.openForm.OpenForm.corregirRemesa(username, server);
//    }
    private void openConfigurar() {
        if (admin) {
            com.enerfrisoft.openForm.OpenForm.configuracion(dataSource, conn, username);
        } else {
            Modal.show("Aviso", "Esta opción solo está disponible para administradores", this, "", "warning");
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
            java.util.logging.Logger.getLogger(FormMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormMainMenu("testingTT", new DataSourceClass() , new DataSourceClass().conectar(1)).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acercaDe;
    private javax.swing.JButton anulaciones;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton configurar;
    private javax.swing.JButton connectionStatus;
    private javax.swing.JButton exportarFormatos;
    private javax.swing.JButton gestionDeUsuarios;
    private javax.swing.JMenuItem itemConfigurar;
    private javax.swing.JMenuItem itemExportarFormatos;
    private javax.swing.JMenuItem itemGestionarUsuarios;
    private javax.swing.JMenuItem itemVerRegistros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem manifiesto;
    private javax.swing.JButton registrarManifiesto;
    private javax.swing.JButton registrarRemesa;
    private javax.swing.JButton registrarTerceros;
    private javax.swing.JMenuItem registrarTerceros1;
    private javax.swing.JButton registrarVehiculos;
    private javax.swing.JMenuItem registrarVehiculos1;
    private javax.swing.JMenuItem remesa;
    private javax.swing.JMenuItem salir;
    private javax.swing.JLabel userName;
    private javax.swing.JButton verRegistros;
    // End of variables declaration//GEN-END:variables
}
