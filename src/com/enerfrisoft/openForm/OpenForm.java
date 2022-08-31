/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.openForm;

import com.enerfrisoft.configuracion.FormConfiguracion;
import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.tools.Visual;
import com.enerfrisoft.usuario.FormUsers;
import java.sql.Connection;
import javax.swing.ComboBoxModel;

/**
 *
 * @author sebastianaf
 */
public class OpenForm {

    public static void remesa(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.remesa.FormRemesa(dataSource, conn, username).setVisible(true);
            }
        });
    }

    public static void manifiesto(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.manifiesto.FormManifiesto(username, dataSource, conn).setVisible(true);
            }
        });
    }

    public static void terceros(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.tercero.FormTerceros(dataSource, conn, username).setVisible(true);
            }
        });
    }

    public static void vehiculos(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.vehiculo.FormVehiculos(dataSource, conn, username).setVisible(true);
            }
        });
    }

    public static void acercaDe(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.enerfrisoft.tools.Visual.setLookAndFeel();
                new com.enerfrisoft.acercaDe.FormAcercaDe().setVisible(true);
            }
        });
    }

    public static void usuarios(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormUsers(username, dataSource, conn).setVisible(true);
            }
        });
    }
    public static void configuracion(DataSourceClass dataSource,Connection conn, String username) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new FormConfiguracion(dataSource, conn, username).setVisible(true);
            }
        });
    }
    
    public static void view(String view, String []querys ,DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.viewPanel.FormViewPanel(view,querys,dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void view(String view, String []querys,ComboBoxModel model ,DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.viewPanel.FormViewPanel(view,querys,model,dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void verRegistros(String username ,DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.verRegistros.VerRegistros(username, dataSource, conn).setVisible(true);
            }
        });
    }

    public static void editarConsecutivo(FormConfiguracion f,javax.swing.JTextField field,String palabra,int numero,int aviso,int limite,String nameW,String nameI, String id, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.configuracion.EditarConsecutivo(f,field,palabra,numero,aviso,limite,nameW,nameI,id,dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void exportarFormatosMenu(String username, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.exportarFormatos.FormExportarFormatos(username,dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void exportarFormatos(String view, String query ,String reportName, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.exportarFormatos.FormViewExport(view,query,reportName,dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void formAnular(String username, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.anulaciones.FormAnular(username, dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void anularRemesa(String username, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.anularRemesa.FormAnularRemesa(username, dataSource, conn).setVisible(true);
            }
        });
    }
    
    public static void anularManifiesto(String username , DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.anularManifiesto.FormAnularManifiesto(username, dataSource, conn).setVisible(true);
            }
        });
    }
    public static void corregirRemesa(String username, DataSourceClass dataSource,Connection conn) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Visual.setLookAndFeel();
                new com.enerfrisoft.corregirRemesas.FormCorregirRemesa(dataSource, conn, username).setVisible(true);
            }
        });
    }
    
}
