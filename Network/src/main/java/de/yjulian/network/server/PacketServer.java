package de.yjulian.network.server;

import de.yjulian.network.client.PacketClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PacketServer extends ServerImpl<PacketClient>{

    /**
     * Create a new Server
     *
     * @param port the port the server should listen on
     * @throws IOException when there are errors opening the {@link ServerSocket}
     */
    public PacketServer(int port) throws IOException {
        super(port);
    }

    @Override
    public Runnable getClientRegistration() {
        return () -> {
            while (!this.getServerSocket().isClosed()) {
                try {
                    // Accept every new client connection
                    Socket socket = this.getServerSocket().accept();
                    // Create a new packet client object and save it
                    clients.add(new PacketClient(socket));
                } catch (IOException e) {
                    LOGGER.warn("Exception while accepting new client", e);
                }
            }
        };
    }

}
