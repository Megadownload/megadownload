/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megadownload.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * Represents a class capable of encrypt an Object to a SealedObject and vice versa
 * @author ganet
 */
public class Crypto {

    private static final byte[] aesKeyData = { (byte)0x71, (byte)0x02, (byte)0x03,
        (byte)0xF4, (byte)0x33, (byte)0xB2, (byte)0xA7, (byte)0x08, (byte)0x01, (byte)0x02, (byte)0x03,
        (byte)0x04, (byte)0xC5, (byte)0xA3, (byte)0x17, (byte)0x51 };

    /**
     * Encrypts an Object to a SealedObject
     * @param obj The object to encrypt
     * @return The obtained SealedObject
     */
    public static SealedObject encrypt(Object obj){
        SealedObject so = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(aesKeyData, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            so = new SealedObject((Serializable) obj, c);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }

    /**
     * Decrypts a SealedObject to an Object
     * @param so The SealedObject to decrypt
     * @return The obtained object
     */
    public static Object decrypt(SealedObject so) {
        Object s = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(aesKeyData, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secretKey);
            s = (Object) so.getObject(c);
        } catch (IOException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
       return s;
    }
}
