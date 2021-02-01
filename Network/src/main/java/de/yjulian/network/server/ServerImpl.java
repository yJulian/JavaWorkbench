package de.yjulian.network.server;

import de.yjulian.network.client.ClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public abstract class ServerImpl<T extends ClientImpl> implements Server{

    protected static final Logger LOGGER = LoggerFactory.getLogger("de.yjulian.network");

    private final ServerSocket serverSocket;
    private final int port;
    private final Thread clientRegisterThread;

    protected final List<T> clients = new ArrayList<>();

    /**
     * Create a new Server
     *
     * @param port the port the server should listen on
     * @throws IOException when there are errors opening the {@link ServerSocket}
     */
    public ServerImpl(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);


        this.clientRegisterThread = new Thread(getClientRegistration());
        this.clientRegisterThread.setDaemon(true);
        this.clientRegisterThread.setName("Server | Client register thread");
        this.clientRegisterThread.start();
    }

    /**
     * Get the {@link ServerSocket} where all clients connect to.
     *
     * @return a {@link ServerSocket}
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Return the current Thread used to register the clients when they try to connect.
     *
     * @return a Thread
     */
    public Thread getClientRegisterThread() {
        return clientRegisterThread;
    }

    /**
     * Get the port the {@link ServerSocket} listens to
     *
     * @return an int
     */
    public int getPort() {
        return port;
    }

    /**
     * Runnable used to register all new connecting clients.
     *
     * @return the runnable that handles the registration.
     */
    public abstract Runnable getClientRegistration();

    /**
     * Method to get all known clients that connected to the server socket since the startup.
     *
     * @return a list with {@link ClientImpl}
     */
    public List<T> getClients() {
        return clients;
    }

    /**
     * Method to get all known clients that are actually connected to the server.
     *
     * @return a list with {@link ClientImpl}
     */
    public List<T> getConnectedClients() {
        cleanupClients();
        return clients;
    }

    /**
     * Remove all clients from the client array where the socket is not in open state.
     *
     * @return true if any client got removed
     */
    private boolean cleanupClients() {
        return this.clients.removeIf(client -> !client.getSocket().isClosed());
    }

}
