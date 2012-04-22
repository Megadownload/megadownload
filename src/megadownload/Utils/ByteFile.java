/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megadownload.Utils;

import java.io.*;

/**
 * Represents a file stored in bytes.
 * @author ganet
 */
public class ByteFile implements Serializable {

    byte[] bytefile;
    String fileName;
    /**
     * Class constructor
     * @param filename Contains the path where the file is.
     * @throws IOException
     */
    public ByteFile(File file) throws IOException {
        fileName = file.getName();
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File length is too large" + file.getName());
        }
        bytefile = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytefile.length && (numRead = is.read(bytefile, offset, bytefile.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytefile.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
    }

    /**
     *
     * @param filename Contains the path where the future file will be.
     * @throws IOException
     */
    public void writeFile() throws IOException {
        File file = new File(Configuration.getInstance().getProperty("directory")+this.fileName);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytefile);
        outputStream.close();
   }
    public long numberOfBytes(){
       return bytefile.length;
    }
    
    public String fileName(){
       return this.fileName;
    }
    //More methods may be useful for this class. I thought about some of them but I can't remember right now.
}
