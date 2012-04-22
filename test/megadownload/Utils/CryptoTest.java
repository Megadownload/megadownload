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
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ganet
 */
public class CryptoTest {
    private byte[] aesKey;
    private SecretKeySpec secretKey;
    private Cipher c;

    public CryptoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        aesKey = new byte[]{ (byte)0x71, (byte)0x02, (byte)0x03,
        (byte)0xF4, (byte)0x33, (byte)0xB2, (byte)0xA7, (byte)0x08, (byte)0x01, (byte)0x02, (byte)0x03,
        (byte)0x04, (byte)0xC5, (byte)0xA3, (byte)0x17, (byte)0x51 };
        secretKey = new SecretKeySpec(aesKey, "AES");
        try {
            c = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encrypt method, of class Crypto.
     */
    @Test
    public void testEncryptandDecrypt() {
        try {
            String encrypt = new String("encrypt");    
            SealedObject expResult = null;
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            expResult = new SealedObject((Serializable) encrypt, c);
            SealedObject result = Crypto.encrypt(encrypt);
            c.init(Cipher.DECRYPT_MODE, secretKey);
            String expDecrypt = (String) expResult.getObject(c);
            String decrypt = (String) Crypto.decrypt(result);
            assertEquals(expDecrypt, decrypt);
            assertEquals(expDecrypt, encrypt);
            
            // TODO review the generated test code and remove the default call to fail.
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
