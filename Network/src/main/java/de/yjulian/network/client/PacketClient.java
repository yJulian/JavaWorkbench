package de.yjulian.network.client;

import de.yjulian.network.Packet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class PacketClient extends ClientImpl{

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * Constructor to connect to a new server
     *
     * @param port the port
     * @param address the address
     * @throws IOException when no connection could be established
     */
    public PacketClient(int port, InetAddress address) throws IOException {
        super(port, address);
        initialize();
    }

    /**
     * Constructor to create a new object with a finished initialized socket connection.
     *
     * @param socket the socket
     */
    public PacketClient(Socket socket) throws IOException {
        super(socket);
        initialize();
    }

    /**
     * Internal method to initialize the object out- and input stream
     *
     * @throws IOException when the socket is closed or an IO error occurs.
     */
    private void initialize() throws IOException {
        outputStream = new ObjectOutputStream(this.getSocket().getOutputStream());
        inputStream = new ObjectInputStream(this.getSocket().getInputStream());
    }

    /**
     * Get the output stream linked to the socket.
     *
     * @return an {@link ObjectOutputStream}
     */
    @Override
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Get the input stream linked to the socket.
     *
     * @return an {@link ObjectInputStream}
     */
    @Override
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Method to send {@link Packet}s to the server.
     *
     * @param packet the packet or any object from a subclass
     * @throws IOException when the socket is closed or an io error occurs.
     */
    public void sendObject(Packet packet) throws IOException {
        this.outputStream.writeObject(packet);
        this.outputStream.flush();
    }

}
