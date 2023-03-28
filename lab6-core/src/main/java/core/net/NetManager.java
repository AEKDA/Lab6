package core.net;

import core.exception.ConnectErrorException;

import java.net.InetAddress;

public interface NetManager {

    public void close();

    public void send(Object object, InetAddress host, int port);

    public Object receive() throws ConnectErrorException;
}
