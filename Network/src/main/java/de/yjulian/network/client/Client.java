package de.yjulian.network.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public interface Client {

    Socket getSocket();
    int getPort();
    InetAddress getRemote();
    OutputStream getOutputStream() throws IOException;
    InputStream getInputStream() throws IOException;

}
