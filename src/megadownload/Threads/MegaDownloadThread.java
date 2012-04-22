package megadownload.Threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MegaDownloadThread extends Thread {

    private String name;
    protected Logger log;
    protected boolean run;

    public MegaDownloadThread(String threadName, Logger threadLog) {
        super(threadName);
        name = threadName;
        log = threadLog;
      
    }

    @Override
    public synchronized void start() {
        run = true;
        super.start();
    }

    public void stopThread() {
        this.log.log(Level.INFO, this.getThreadName() + ": Stopping ...");
        this.run = false;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger threadLog) {
        log = threadLog;
    }

    public String getThreadName() {
        return name;
    }

    public boolean isRunning() {
        return run;
    }
}