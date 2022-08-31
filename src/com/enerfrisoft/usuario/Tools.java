
package com.enerfrisoft.usuario;

import com.enerfrisoft.modal.Modal;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class Tools {

    
    private final static String alg = "AES";
    
    private final static String cI = "AES/CBC/PKCS5Padding";
    private final static String key = "92AE31A79FEEB2A3";
    private final static String iv = "0123456789ABCDEF";

    
    public static String encrypt(String cleartext, JFrame frame){
        try {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
            return new String(encodeBase64(encrypted));
        } catch (Exception e) {
            Modal.show("Error", "Hubo un problema de encriptación", frame, "", "error");
        }
        return null;
    }

    public static String decrypt(String encrypted,JFrame frame){
        try {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            byte[] enc = decodeBase64(encrypted);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(enc);
            return new String(decrypted);
        } catch (Exception e) {
            Modal.show("Error", "Hubo un problema de encriptación", frame, "", "error");
        }
        return null;
    }

    

}
