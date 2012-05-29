/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megadownload.Utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ganet
 */
public class ByteFileTest {

    private File file;
    private ByteFile byteFile;

    public ByteFileTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        file = null;
        byteFile = null;
    }

    @After
    public void tearDown() {
     /*   File deleteFile = new File("NewFile");
        if (deleteFile.exists()) {
            file.delete();
        }*/
    }

    /**
     * Test of constructor Method for a file that does not exist
     */
    @Test
    public void testConstructorNewFile() {
        file = new File("NewFile");
        try {
            byteFile = new ByteFile(file);
        } catch (IOException ex) {
            assertTrue(ex instanceof FileNotFoundException);
        }
    }

    /**
     * Test of constructor Method for a null value
     */
    @Test
    public void testConstructorNullFile() {
        try {
            byteFile = new ByteFile(null);
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    /**
     * Test of constructor Method for a file thats existes (README.txt)
     */
    @Test
    public void testConstructorExistingFile() {
        file = new File("README");
        try {
            byteFile = new ByteFile(file);
        } catch (Exception ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(true);
    }

    /**
     * Test of writeFile method, of class ByteFile for a new file created.
     */
    @Test
    public void testWriteNewFile() {
        file = new File("NewFile");
        boolean contentEquals = false;
        try {
            file.createNewFile();
            byteFile = new ByteFile(file);
            byteFile.writeFile();
            File writtenFile = new File(Configuration.getInstance().getProperty("directory") + byteFile.fileName());
            contentEquals = FileUtils.contentEquals(file, writtenFile);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(contentEquals);
    }

    /**
     * Test of writeFile method, of class ByteFile for a file already created
     * (README.txt)
     */
    @Test
    public void testWriteExistingFile() {
        file = new File("README");
        boolean contentEquals = false;
        try {
            byteFile = new ByteFile(file);
            byteFile.writeFile();
            File writtenFile = new File(Configuration.getInstance().getProperty("directory") + byteFile.fileName());
            contentEquals = FileUtils.contentEquals(file, writtenFile);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(contentEquals);
    }

    /**
     * Test of numberOfBytes method, of class ByteFile for a new file created
     */
    @Test
    public void testNumberOfBytesNewFile() {
        file = new File("NewFile");
        try {
            file.createNewFile();
            byteFile = new ByteFile(file);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(file.length(), byteFile.numberOfBytes());
    }

    /**
     * Test of numberOfBytes method, of class ByteFile for a file already
     * created (README.txt)
     */
    @Test
    public void testNumberOfBytesExistingFile() {
        file = new File("README");
        try {
            byteFile = new ByteFile(file);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(file.length(), byteFile.numberOfBytes());
    }

    /**
     * Test of fileName method, of class ByteFile for a new file created
     */
    @Test
    public void testFileNameNewFile() {
        file = new File("NewFile");
        try {
            file.createNewFile();
            byteFile = new ByteFile(file);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(file.getName(), byteFile.fileName());
    }

    /**
     * Test of fileName method, of class ByteFile for a file already created
     * (README.txt)
     */
    @Test
    public void testFileNameExistingFile() {
        try {
            file = new File("README");
            byteFile = new ByteFile(file);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(file.getName(), byteFile.fileName());
    }
}
