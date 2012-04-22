package megadownload.Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import megadownload.Connection.Connection;
import megadownload.Connection.ConnectionID;
import megadownload.Connection.TCPConnection;
import megadownload.Exceptions.LoggerException;
import megadownload.Utils.Configuration;
import megadownload.Utils.Logging;

public class ServerThread extends MegaDownloadThread {

    private ServerSocket serverSocket;

    public ServerThread(String threadName, Logger threadLog) {
        super(threadName, threadLog);
        try {
            serverSocket = new ServerSocket(Integer.parseInt(Configuration.getInstance().getProperty("port")));
            log.log(Level.INFO, "Server socket created correctly: "+ serverSocket.toString());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run(){
        log.log(Level.INFO, "Starting ServerThread");
        while(run){
            try {
                Socket sock = serverSocket.accept();
                log.log(Level.INFO, "Connection received : " + sock.toString());
                String remoteConnection = sock.getRemoteSocketAddress().toString();
                Connection con = new TCPConnection(new ConnectionID(remoteConnection), sock);
                log.log(Level.INFO, "Starting FileThread to attend petition...");
                MegaDownloadThread thread = new FileReceivingThread(remoteConnection, Logging.setupLog(remoteConnection), con);
                thread.start();
            } catch (LoggerException ex) {
                log.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                log.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
