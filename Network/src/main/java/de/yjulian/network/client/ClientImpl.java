package de.yjulian.network.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public abstract class ClientImpl implements Client{

    private final Socket socket;
    private final int port;
    private final InetAddress address;

    /**
     * Create a new Client
     *
     * @param port the port the socket should connect to
     * @param address the address from the remote server
     * @throws IOException when no connection could ne established
     */
    protected ClientImpl(int port, InetAddress address) throws IOException {
        this.port = port;
        this.address = address;
        this.socket = new Socket(address.getHostAddress(), port);
    }

    /**
     * Constructor used to represent a client with a finished initialized socket.
     *
     * @param socket the socket
     */
    protected ClientImpl(Socket socket) {
        this.socket = socket;
        this.port = socket.getPort();
        this.address = socket.getInetAddress();
    }

    /**
     * Return the socket object for registering input and output streams
     *
     * @return the socket object
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Get the port the socket listens and sends on
     *
     * @return an int
     */
    public int getPort() {
        return port;
    }

    /**
     * Get the {@link InetAddress} from the remote server
     *
     * @return an {@link InetAddress}
     */
    public InetAddress getRemote() {
        return address;
    }

    /**
     * Get the output stream from the socket
     *
     * @return the output stream
     * @throws IOException if the socket is closed or an IO error occurs
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    /**
     * Get the input stream from the socket
     *
     * @return the input stream
     * @throws IOException if the socket is closed or an IO error occurs
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

}
