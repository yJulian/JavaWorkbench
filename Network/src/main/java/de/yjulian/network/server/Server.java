package de.yjulian.network.server;

import java.net.ServerSocket;

public interface Server {

    /**
     * Should return the ServerSocket implementation used to
     * implement a server side socket.
     *
     * @return the ServerSocket used for communication
     */
    ServerSocket getServerSocket();

    /**
     * Should return the port the server listens on.
     *
     * @return the port as an int
     */
    int getPort();

}
