/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sebastianaf
 */
public class SeguroQR {

    private static String getReq(String manifiesto) {
        String out = ""
                + "<?xml version='1.0' encoding='ISO-8859-1' ?>\n"
                + "<root>\n"
                + " <acceso>\n"
                + "  <username>"+username+"</username>\n"
                + "  <password>"+password+"</password>\n"
                + " </acceso>\n"
                + " <solicitud>\n"
                + "  <tipo>3</tipo>\n"
                + "  <procesoid>4</procesoid>\n"
                + " </solicitud>\n"
                + " <variables>\n"
                + "INGRESOID,FECHAING,SEGURIDADQR\n"
                + " </variables>\n"
                + " <documento>\n"
                + "  <NUMNITEMPRESATRANSPORTE>''</NUMNITEMPRESATRANSPORTE>\n"
                + "  <NUMMANIFIESTOCARGA>'" + manifiesto + "'</NUMMANIFIESTOCARGA>\n"
                + " </documento>\n"
                + "</root>";
        return out;
    }

    public static Map<String, String> getSeguroQR(ArrayList<String> manifiesto) {
        String seguridad = "";
        Map<String, String> out = new HashMap<>();
        try {
            for (int i = 0; i < manifiesto.size(); i++) {
                Thread.sleep(1000);
                String res = WebServices.getAnswer(getReq(manifiesto.get(i)));
                try {
                    seguridad = Parser.verify(res).get(1);
                    System.out.println(manifiesto.get(i)+" - "+seguridad);
                    out.put(manifiesto.get(i), seguridad);
                } catch (Exception e) {
                    System.out.println("no existe");
                }
            }
        } catch (Exception e) {
            System.out.println("sleep error");
        }
        return out;
    }

    public static void main(String[] args) {
        ArrayList<String> consecutivo = new ArrayList<>();
               
        Map<String, String> out = getSeguroQR(consecutivo);
        
    }
}
