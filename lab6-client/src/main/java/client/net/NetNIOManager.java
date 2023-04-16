package client.net;

import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import core.exception.ConnectErrorException;
import core.io.Logger;
import core.net.NetManager;
import core.util.Serializer;
import core.clientInstruction.*;

public class NetNIOManager implements NetManager, AutoCloseable {
    private DatagramChannel dc;
    private final InetSocketAddress clientAddress;
    private int delay = 100;
    {
        clientAddress = new InetSocketAddress(0);
        try {
            dc = DatagramChannel.open();
            dc.bind(clientAddress);
            dc.configureBlocking(false);
        } catch (Exception e) {
            Logger.get().writeLine("NetNIOManager: " + e.getMessage());
        }
    }

    public NetNIOManager(int delay) {
        this.delay = delay;
    }

    @Override
    public void close() {
        try {
            dc.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void send(Object object, InetAddress host, int serverPort) {
        try {
            byte[] buffer = Serializer.serialize(object);
            ByteBuffer buf = ByteBuffer.wrap(buffer);
            dc.send(buf, new InetSocketAddress(host, serverPort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientInstruction receive() throws ConnectErrorException {
        try {
            ByteBuffer buf = ByteBuffer.allocate(32768);
            TimeUnit.MILLISECONDS.sleep(delay);
            dc.receive(buf);
            if (buf.position() == 0) {
                throw new ConnectErrorException("");
            }
            ClientInstruction response = (ClientInstruction) Serializer.deserialize(buf.array());
            return response;
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            return new PrintInstruction(e.getMessage());
        }
    }
}
