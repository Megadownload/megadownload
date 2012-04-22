package megadownload.Exceptions;

/**
 *
 * @author ganet
 */
public class ConnectionsException extends Exception {

    public ConnectionsException(String string) {
        super(string);
    }

    public ConnectionsException(String string, Exception ex) {
        super(string, ex);
    }

    public ConnectionsException(Exception ex) {
        super(ex);
    }

}
