package server.logic;

import java.net.InetAddress;

import core.logic.ClinetState;

public class ClientInfo {
    private int port;
    private InetAddress address;
    private ClinetState state;

    public ClientInfo(int port, InetAddress address) {
        this.port = port;
        this.address = address;
        state = ClinetState.Work;
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
