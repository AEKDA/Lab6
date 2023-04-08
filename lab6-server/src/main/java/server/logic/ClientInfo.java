package server.logic;

import java.net.InetAddress;

public class ClientInfo {
    private int port;
    private InetAddress address;
    private ClinetState state;

    public ClientInfo(int port, InetAddress address) {
        this.port = port;
        this.address = address;
        state = ClinetState.Default;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public ClinetState getState() {
        return state;
    }

    public void switchState(ClinetState state) {
        this.state = state;
    }
}
