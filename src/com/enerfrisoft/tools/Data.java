package com.enerfrisoft.tools;

import com.enerfrisoft.anularManifiesto.AnularManifiesto;
import com.enerfrisoft.anularRemesa.AnularRemesa;
import com.enerfrisoft.vehiculo.Vehiculo;
import com.enerfrisoft.dao.ParametrosDAO;
import com.enerfrisoft.manifiesto.Manifiesto;
import com.enerfrisoft.remesa.Remesa;
import com.enerfrisoft.tercero.Tercero;
import com.enerfrisoft.remolque.Remolque;
import com.enerfrisoft.sede.Sede;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Data {

    private static final String user1 = "";
    private static final String pass1 = "";
    private static final String nit1 = "";
    private static final String user2 = "";
    private static final String pass2 = "";
    private static final String nit2 = "";

    public static final Map<String, String> getCredentials(int key) {
        Map<String, String> out = new HashMap<String, String>();
        switch (key) {
            case 1:
                out.put("user", user1);
                out.put("pass", pass1);
                out.put("nit", nit1);
                break;
            case 2:
                out.put("user", pass2);
                out.put("pass", pass2);
                out.put("nit", nit2);
                break;

        }
        return out;
    }

    public static Vehiculo auth(Vehiculo vehiculo, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                vehiculo.setUsuario(user1);
                vehiculo.setPassword(pass1);
                vehiculo.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                vehiculo.setUsuario(user2);
                vehiculo.setPassword(pass2);
                vehiculo.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }

        return vehiculo;
    }

    public static void auth(Remesa remesa, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                remesa.setUsuario(user1);
                remesa.setPassword(pass1);
                remesa.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                remesa.setUsuario(user2);
                remesa.setPassword(pass2);
                remesa.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }

    public static void auth(Tercero tercero, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                tercero.setUsuario(user1);
                tercero.setPassword(pass1);
                tercero.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                tercero.setUsuario(user2);
                tercero.setPassword(pass2);
                tercero.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }

    }

    public static void auth(Remolque remolque, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                remolque.setUsuario(user1);
                remolque.setPassword(pass1);
                remolque.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                remolque.setUsuario(user2);
                remolque.setPassword(pass2);
                remolque.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }

    public static void auth(Sede sede, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                sede.setUsuario(user1);
                sede.setPassword(pass1);
                sede.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                sede.setUsuario(user2);
                sede.setPassword(pass2);
                sede.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }

    public static void auth(AnularRemesa anularRemesa, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                anularRemesa.setUsuario(user1);
                anularRemesa.setPassword(pass1);
                anularRemesa.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                anularRemesa.setUsuario(user2);
                anularRemesa.setPassword(pass2);
                anularRemesa.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }

    public static void auth(AnularManifiesto anularManifiesto, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                anularManifiesto.setUsuario(user1);
                anularManifiesto.setPassword(pass1);
                anularManifiesto.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                anularManifiesto.setUsuario(user2);
                anularManifiesto.setPassword(pass2);
                anularManifiesto.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }

    public static void auth(Manifiesto manifiesto, Connection conn) {
        int server = Integer.valueOf(UniversalDAO.getString(""
                + "select serverId "
                + "from info_empresa", conn));
        switch (server) {
            case 1:
                manifiesto.setUsuario(user1);
                manifiesto.setPassword(pass1);
                manifiesto.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
            case 2:
                manifiesto.setUsuario(user2);
                manifiesto.setPassword(pass2);
                manifiesto.setNUMNITEMPRESATRANSPORTE(ParametrosDAO.getEmpresa(conn).getNit());
                break;
        }
    }
}
