package com.enerfrisoft.tools;


import java.rmi.RemoteException;
import wsdl.IBPMServices;
import wsdl.IBPMServicesservice_Impl;


public class WebServices{
    
    public static IBPMServices getConnection(){
        try {
            IBPMServicesservice_Impl conection = new IBPMServicesservice_Impl();
            IBPMServices webService = conection.getIBPMServicesPort();
            return webService;
        } catch (Exception e) {
            return null;
        }
    }
    
   public static String getAnswer(String request){
        try {
            
            String answer  = getConnection().atenderMensajeRNDC(request);
            
            return answer;
        } catch (RemoteException e) {
                System.out.println(e.getLocalizedMessage());
                return "";
        }
    }
   
   public static Boolean testRndcConnection(){
       try {
           getConnection().atenderMensajeRNDC("testing");
           return true;
       } catch (Exception e) {
           return false;
       }
   }   
}
