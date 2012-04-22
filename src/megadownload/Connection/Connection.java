package megadownload.Connection;

import java.net.SocketAddress;
import megadownload.Exceptions.ConnectionsException;

/**
 * Represents a Connection between server-client
 * @author ganet
 */
public abstract class Connection {

    protected ConnectionID serviceID;

    /**
     * Class constructor
     * @param service ID from Connection
     * @param remote SocketAddress from remote server/client
     */
    public Connection(ConnectionID service){

        this.serviceID = service;
    }
    /**
     * Sends an object to remote SocketAddress
     * @param obj Object to send
     * @throws codip2p.network.exceptions.ConnectionsException
     */
    public abstract void send(Object obj) throws ConnectionsException;
    /**
     * Receives an object from remote SocketAddress
     * @return Object Object to receive
     * @throws codip2p.network.exceptions.ConnectionsException
     */
    public abstract Object recv() throws ConnectionsException;
    /**
     * Returns the remote SocketAddress
     * @return SocketAddress
     */
    public abstract SocketAddress getRemoteSocketAddress();
    /**
     * Returns the ConnectionID
     * @return serviceID
     */
    public ConnectionID getID(){
        return serviceID;
    }
    /**
     * Closes the connection with remote server/client
     * @throws codip2p.network.exceptions.ConnectionsException
     */
    public abstract void close() throws ConnectionsException;
    /**
     * Returns the current state of this connection
     * @return true if it is alive
     */
    public abstract boolean isConnectionAlive();
}