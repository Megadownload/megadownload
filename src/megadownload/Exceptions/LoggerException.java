package megadownload.Exceptions;

/**
 *
 * @author ganet
 */
public class LoggerException extends Exception {

    public LoggerException(String string) {
        super(string);
    }

    public LoggerException(String string, Exception ex) {
        super(string, ex);
    }

    public LoggerException(Exception ex) {
        super(ex);
    }

}
