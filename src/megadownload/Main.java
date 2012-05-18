/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megadownload;

import java.util.logging.Level;
import java.util.logging.Logger;
import megadownload.Exceptions.LoggerException;
import megadownload.Threads.ServerThread;
import megadownload.Utils.Logging;

/**
 *
 * @author ganet
 */
public class Main {
    
     private static Logger log;
     
     /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MegaDownloadUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MegaDownloadUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MegaDownloadUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MegaDownloadUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MegaDownloadUI().setVisible(true);
            }
        });

        ServerThread serverThread = null;
        try {
            serverThread = new ServerThread("ServerThread", Logging.setupLog("ServerThread"));
        } catch (LoggerException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        serverThread.start();
    }
}
