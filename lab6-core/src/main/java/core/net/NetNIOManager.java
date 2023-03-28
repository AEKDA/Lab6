package core.net;

import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.time.Duration;

import core.exception.ConnectErrorException;
import core.io.Logger;
import core.util.Serializer;
import core.clientInstruction.*;

public class NetNIOManager implements NetManager, AutoCloseable {
    private DatagramChannel dc;
    private final InetSocketAddress clientAddress;
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
            Thread.sleep(Duration.ofMillis(500));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientInstruction receive() throws ConnectErrorException {
        try {
            ByteBuffer buf = ByteBuffer.allocate(32768);
            dc.receive(buf);
            if (buf.position() == 0) {
                throw new ConnectErrorException("");
            }
            ClientInstruction response = (ClientInstruction) Serializer.deserialize(buf.array());
            return response;
        } catch (ClassNotFoundException | IOException e) {
            return new PrintInstruction(e.getMessage());
        }
    }
}
