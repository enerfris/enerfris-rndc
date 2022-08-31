/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.sede;

import com.enerfrisoft.dao.DataSourceClass;

/**
 *
 * @author sebastianaf
 */
public class Testing {
    public static void main(String[] args) {
        Sede sede = new Sede();
        sede.setNUMIDTERCERO("8913047622");
        sede.setCODMUNICIPIORNDC("76001000");
        sede.setCODSEDETERCERO("01");
        sede.setNOMSEDETERCERO("Italcol Palmira");
        sede.setNOMENCLATURADIRECCION("Km 11 vía Recta Palmira - Cali");
        sede.setNUMTELEFONOCONTACTO("2750505");
        
        Thread t = new Thread(new UpdateSede(sede,new DataSourceClass(), new DataSourceClass().conectar(1)));
        t.start();
        
    }
}
