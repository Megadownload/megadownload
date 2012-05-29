package megadownload.Connection;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import megadownload.Exceptions.ConnectionsException;

/**
 * Represents a Connection between server-client. Implements Connection
 *
 * @author nacho
 */
public class TCPConnection extends Connection {

    protected Socket connection;

    /**
     * Class Constructor
     *
     * @param service ID of ConnectionService
     * @param connection Socket that represents the real connection
     */
    public TCPConnection(ConnectionID service, Socket connection) {
        super(service);
        this.connection = connection;
    }

    /**
     * Returns the current state of this connection
     *
     * @return true if it is alive
     */
    @Override
    public synchronized boolean isConnectionAlive() {
        return !this.connection.isClosed();
    }

    /**
     * Sends an object to remote SocketAddress
     *
     * @param obj Object to send
     * @throws megadownload.Exceptions.ConnectionsException
     */
    @Override
    public synchronized void send(Object obj) throws ConnectionsException {
        if (obj == null) {
            throw new ConnectionsException("Object to send is null!");        
        }
        OutputStream output;
        ObjectOutputStream dataout;
        byte[] message;
        try {
            output = connection.getOutputStream();
            dataout = new ObjectOutputStream(output);
            dataout.flush();
            message = convertToByteArray(obj);
            dataout.writeInt(message.length);
            dataout.flush();
            dataout.write(message);
            dataout.flush();
        } catch (IOException ex) {
            throw new ConnectionsException("Connection send error", ex);
        }
    }

    /**
     * Receives an object from remote SocketAddress
     *
     * @return Object Object to receive
     * @throws megadownload.Exceptions.ConnectionsException
     */
    @Override
    public synchronized Object recv() throws ConnectionsException {
        InputStream input;
        ObjectInputStream datain;
        byte[] message;
        try {
            input = connection.getInputStream();
            datain = new ObjectInputStream(input);
            message = new byte[datain.readInt()];
            datain.readFully(message);
        } catch (StreamCorruptedException ex) {
            throw new ConnectionsException("StreamCorrupted while receiving data", ex);
        } catch (IOException ex) {
            throw new ConnectionsException("Connection receivement", ex);
        }
        return this.convertToData(message);
    }

    /**
     * Closes the connection with remote server/client
     *
     * @throws megadownload.Exceptions.ConnectionsException
     */
    @Override
    public synchronized void close() throws ConnectionsException {
        try {
            this.connection.close();
        } catch (IOException ex) {
            throw new ConnectionsException("Cannot close the socket", ex);
        }

    }

    protected byte[] convertToByteArray(Object data) throws ConnectionsException {
        ByteArrayOutputStream byteOutput;
        ObjectOutputStream dataOut;
        try {
            byteOutput = new ByteArrayOutputStream();
            dataOut = new ObjectOutputStream(byteOutput);
            dataOut.writeObject(data);
            byte[] bytes = byteOutput.toByteArray();
            return bytes;
        } catch (IOException ex) {
            throw new ConnectionsException("Conversion to byte array error", ex);
        }
    }

    protected Object convertToData(byte[] bytes) throws ConnectionsException {

        Object data;
        ByteArrayInputStream byteInput;
        ObjectInputStream dataIn;
        try {
            byteInput = new ByteArrayInputStream(bytes);
            dataIn = new ObjectInputStream(byteInput);
            data = (Object) dataIn.readObject();
            return data;
        } catch (ClassNotFoundException ex) {
            throw new ConnectionsException("Class not found exception", ex);
        } catch (IOException ex) {
            throw new ConnectionsException("Conversion to Data error", ex);
        }
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        return connection.getRemoteSocketAddress();
    }
}
