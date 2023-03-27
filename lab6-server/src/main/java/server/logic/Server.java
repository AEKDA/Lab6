package server.logic;

public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public Server() {
        port = 5653;
    }

    public int getPort() {
        return port;
    }

}
