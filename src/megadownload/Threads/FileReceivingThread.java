package megadownload.Threads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;
import megadownload.Connection.Connection;
import megadownload.Exceptions.ConnectionsException;
import megadownload.Utils.ByteFile;
import megadownload.Utils.Crypto;

public class FileReceivingThread extends MegaDownloadThread {

    private Connection con;

    public FileReceivingThread(String threadName, Logger threadLog, Connection con) {
        super(threadName, threadLog);
        this.con = con;
    }


    @Override
    public void run() {
        while (run) {
            ByteFile file;
            try {
                file = (ByteFile) Crypto.decrypt( (SealedObject) con.recv());
                log.log(Level.INFO, "File received: " + file.fileName());
                file.writeFile();
                log.log(Level.INFO, "File writed: " + file.fileName());
            } catch (IOException ex) {
                log.getLogger(FileReceivingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectionsException ex) {
                log.getLogger(FileReceivingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.run = false;
        }
    }

}
