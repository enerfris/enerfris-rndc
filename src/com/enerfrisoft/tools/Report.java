/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import com.enerfrisoft.dao.DataSourceClass;
import com.enerfrisoft.modal.Modal;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author sebastianaf
 */
public class Report {

    public static String getField(String field) {
        switch (field) {
            case "manifiesto":
                return "NUMMANIFIESTOCARGA";
            case "ordenCargue":
                return "NUMMANIFIESTOCARGA";
            case "remesa":
                return "CONSECUTIVOREMESA";
        }
        return "";
    }

    private static Map BuildParametersManifiesto(String reportName, String consecutivo, int server) {
        Map parameters = new HashMap();
        parameters.put(getField(reportName), consecutivo);

        File PATH_LOGO = new File("src/com/enerfrisoft/reports/manifiesto/logo" + String.valueOf(server) + ".png");
        String sPATH_LOGO = PATH_LOGO.toURI().toString().substring(6);
        parameters.put("PATH_LOGO", sPATH_LOGO);

        File PATH_WATERMARK = new File("src/com/enerfrisoft/reports/watermark" + String.valueOf(server) + ".png");
        String sPATH_WATERMARK = PATH_WATERMARK.toURI().toString().substring(6);
        parameters.put("PATH_WATERMARK", sPATH_WATERMARK);

        File PATH_FIRMA = new File("src/com/enerfrisoft/reports/firma" + String.valueOf(server) + ".png");
        String sPATH_FIRMA = PATH_FIRMA.toURI().toString().substring(6);
        parameters.put("PATH_FIRMA", sPATH_FIRMA);

        File PATH_MANIFIESTO01 = new File("src/com/enerfrisoft/reports/manifiesto/manifiesto01.jasper");
        String sPATH_MANIFIESTO01 = PATH_MANIFIESTO01.toURI().toString().substring(6);
        parameters.put("PATH_MANIFIESTO01", sPATH_MANIFIESTO01);

        File PATH_MANIFIESTO02 = new File("src/com/enerfrisoft/reports/manifiesto/manifiesto02.jasper");
        String sPATH_MANIFIESTO02 = PATH_MANIFIESTO02.toURI().toString().substring(6);
        parameters.put("PATH_MANIFIESTO02", sPATH_MANIFIESTO02);

        File PATH_MANIFIESTO03 = new File("src/com/enerfrisoft/reports/manifiesto/manifiesto03.jasper");
        String sPATH_MANIFIESTO03 = PATH_MANIFIESTO03.toURI().toString().substring(6);
        parameters.put("PATH_MANIFIESTO03", sPATH_MANIFIESTO03);

        return parameters;
    }

    public static void view(String reportName, JFrame frame, String consecutivo, Connection conn) {
        try {
            int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
            
            Map parameters = new HashMap();
            parameters.put(getField(reportName), consecutivo);

            File PATH_LOGO = new File("src/com/enerfrisoft/reports/logo" + String.valueOf(server) + ".png");
            String sPATH_LOGO = PATH_LOGO.toURI().toString().substring(6);
            parameters.put("PATH_LOGO", sPATH_LOGO);
            
            File PATH_WATERMARK = new File("src/com/enerfrisoft/reports/watermark" + String.valueOf(server) + ".png");
            String sPATH_WATERMARK = PATH_WATERMARK.toURI().toString().substring(6);
            parameters.put("PATH_WATERMARK", sPATH_WATERMARK);
            
            File PATH_FIRMA = new File("src/com/enerfrisoft/reports/firma" + String.valueOf(server) + ".png");
            String sPATH_FIRMA = PATH_FIRMA.toURI().toString().substring(6);
            parameters.put("PATH_FIRMA", sPATH_FIRMA);

            File f = new File("src/com/enerfrisoft/reports/" + reportName + ".jasper");
            String path = f.toURI().toString().substring(6);

            if (reportName.equals("manifiesto")) {
                parameters = BuildParametersManifiesto(reportName, consecutivo, server);
                f = new File("src/com/enerfrisoft/reports/manifiesto/manifiesto04.jasper");
                path = f.toURI().toString().substring(6);
            }

            JasperReport report = null;
            try {
                //System.out.println(path);
                report = (JasperReport) JRLoader.loadObjectFromFile(path);
                DataSourceClass source = new DataSourceClass();
                Connection connection = null;
                try {
                    connection = source.conectar(server);
                } catch (Exception e) {
                    Modal.show("Error", "Error de conexión a la base de datos", frame, "", "error");
                }

                JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
                JasperViewer view = new JasperViewer(print, false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Visual.setLookAndFeel();
                        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        view.setTitle((reportName + ": " + consecutivo).toUpperCase());
                        Visual.windowIcon(view);
                        view.setVisible(true);
                    }
                });

            } catch (Exception e) {
                Modal.show("Error", "No se pudo ubicar el reporte", frame, "", "error");
                Modal.show("Error", e.getLocalizedMessage(), frame, "large", "error");
            }
        } catch (Exception e) {
            Modal.show("Error", e.getLocalizedMessage(), frame, "large", "error");
        }
    }
}
