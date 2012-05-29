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
 * Represents a class capable of encrypt an Object to a SealedObject and vice
 * versa
 *
 * @author ganet
 */
public class Crypto {

    private static final byte[] aesKeyData = {(byte) 0x71, (byte) 0x02, (byte) 0x03,
        (byte) 0xF4, (byte) 0x33, (byte) 0xB2, (byte) 0xA7, (byte) 0x08, (byte) 0x01, (byte) 0x02, (byte) 0x03,
        (byte) 0x04, (byte) 0xC5, (byte) 0xA3, (byte) 0x17, (byte) 0x51};
    private static boolean encrypt = true;
    private static boolean decrypt = false;

    /**
     * Encrypts an Object to a SealedObject
     *
     * @param obj The object to encrypt
     * @return The obtained SealedObject
     */
    public static SealedObject encrypt(Object obj) {
        SealedObject so = (SealedObject) transformData(obj, encrypt);
        return so;
    }

    /**
     * Decrypts a SealedObject to an Object
     *
     * @param so The SealedObject to decrypt
     * @return The obtained object
     */
    public static Object decrypt(SealedObject so) {
        Object obj = transformData(so, decrypt); 
        return obj;
    }

    private static Object transformData(Object obj, boolean bool) {
        SecretKeySpec secretKey = new SecretKeySpec(aesKeyData, "AES");
        SealedObject so;
        try {
            Cipher _cipher = Cipher.getInstance("AES");
            if (bool) {
                _cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                so = new SealedObject((Serializable) obj, _cipher);
                return so;
            } else {
                so = (SealedObject) obj;
                _cipher.init(Cipher.DECRYPT_MODE, secretKey);
                Object object = (Object) so.getObject(_cipher);
                return object;
            }
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
        //no debería llegar nunca aquí.
        return null;
    }
}
