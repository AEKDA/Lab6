package server.logic;

import java.net.InetAddress;

public class ClientInfo {
    private int port;
    private InetAddress address;

    public ClientInfo(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClientInfo that = (ClientInfo) o;
        return that.getAddress().equals(this.getAddress()) && that.getPort() == this.getPort();
    }

}
