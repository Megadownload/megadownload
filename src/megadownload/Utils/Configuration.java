package megadownload.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import megadownload.Exceptions.LoggerException;

/**
 *
 * @author ganet
 */
public class Configuration {

    private static Configuration config;
    private Properties props;
    private Logger log;
    private final String LOGNAME = "Configuration";

    private Configuration() {
        try {
            Logging.setupLog(LOGNAME, LOGNAME);
            this.log = Logger.getLogger(LOGNAME);

            this.props = new Properties();
            this.setDefaults();
            this.props.load(new FileInputStream("config.properties"));
        } catch (LoggerException ex) {
        } catch (IOException ex) {
            this.log.log(Level.WARNING, "Configuration file not found, using default configurations.");
        }
    }

    public static Configuration getInstance() {
        if (Configuration.config == null) {
            Configuration.config = new Configuration();
        }
        return Configuration.config;
    }

    public String getProperty(String propName) {
        return this.props.getProperty(propName);
    }

    public void setProperty(String propName, String value) {
        this.props.setProperty(propName, value);
    }

    public boolean isProperty(String propName) {
        return this.props.contains(propName);
    }

    public void saveToFile() {
        try {
            this.props.store(new FileOutputStream("config.properties"), null);

        } catch (FileNotFoundException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    private void setDefaults() {
        props.put("port","9876");
        props.put("directory", System.getProperty("user.home"));
    }
}
