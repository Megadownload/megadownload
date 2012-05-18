package megadownload.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import megadownload.Exceptions.LoggerException;

/**
 * Log utilities class
 */
public class Logging {

    private final static Map<String, String> config = new HashMap<String, String>();
    private static boolean init = false;
    private final static String defaultPath = "log/";

    /**
     * Sets the Logging path
     *
     * @param path
     */
    public static void setLogPath(String path) {
        if (path.length() > 0 && !path.endsWith("/")) {
            path += "/";
        }
        config.put("logpath", path);
    }

    /**
     * Configure the Logging Subsystem
     *
     * @param config
     */
    public static void configure(Map<String, String> config) {
        if (config.containsKey("logpath")) {
            setLogPath(config.get("logpath"));
        }
    }

    /**
     * Set up the log
     *
     * @param Log name
     * @return a Logger object
     * @throws LoggerException
     */
    public static Logger setupLog(String string) throws LoggerException {
        return setupLog(string, string);
    }

    /**
     * Setup the log in which all messages, errors and messages will be written
     *
     * @param Log name
     * @param Log filename
     * @return a Logger object
     * @throws LoggerException
     */
    public static Logger setupLog(String string, String filename) throws LoggerException {
        if (!init) {
            if (config.containsKey("logpath")) {
                new File(config.get("logpath")).mkdirs();
            } else {
                new File(defaultPath).mkdirs();
            }
            setLog("", "main");
            init = true;
        }
        Logger logger = setLog(string, filename);
        logger.addHandler(new ConsoleHandler());
        return logger;
    }

    private static Logger setLog(String string, String filename) throws LoggerException, SecurityException {
        Logger logger = Logger.getLogger(string);
        Handler[] handlers = logger.getHandlers();
        for (Handler h : handlers) {
            logger.removeHandler(h);
            h.close();
        }
        String LogDirectory = "log/";
        if (config.containsKey("logpath")) {
            LogDirectory = config.get("logpath");
        }
        try {
            FileHandler fhandler = new FileHandler(LogDirectory.concat(filename).concat(".log"));
            fhandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fhandler);
        } catch (Exception ex) {
            throw new LoggerException("Cannot create log", ex);
        }
        return logger;
    }
}
