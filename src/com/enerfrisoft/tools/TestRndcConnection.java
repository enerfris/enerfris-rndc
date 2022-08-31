/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

/**
 *
 * @author sebastianaf
 */
public class TestRndcConnection implements Runnable{

    public static Boolean testRndcConnection(){
       try {
           WebServices.getConnection().atenderMensajeRNDC("testing");
           return true;
       } catch (Exception e) {
           return false;
       }
   }
    
    @Override
    public void run() {
        testRndcConnection();
    }
    
}
