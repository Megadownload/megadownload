package megadownload.Connection;

import java.io.Serializable;

/**
 * ID of a Connection
 * @author nacho
 */
public class ConnectionID implements Serializable {

    protected String serviceID;

    /**
     * Class Constructor
     * @param name Name of the Connection
     */
    public ConnectionID(String name) {

        this.serviceID = name;
    }

    /**
     * Returns the string serviceID
     * @return String
     */
    @Override
    public String toString() {
        return this.serviceID;
    }

   @Override
   public boolean equals(Object obj) {
        if (obj instanceof ConnectionID) {
            ConnectionID that = (ConnectionID) obj;
            return this.serviceID.equals(that.serviceID);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return this.serviceID.hashCode();
    }
}
