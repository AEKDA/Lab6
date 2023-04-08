package server.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import core.io.Logger;
import core.logic.InstructionInfo;
import core.net.NetManager;
import core.util.Serializer;
import server.logic.ClientInfo;

public class NetIOManager implements NetManager, AutoCloseable {
    private DatagramSocket ds;
    private ClientInfo lastClient;

    public NetIOManager(InetAddress host, int port) {
        try {
            ds = new DatagramSocket(port, host);
        } catch (Exception e) {
            Logger.get().writeLine("NetIOManager::Constructor: " + e.getMessage());
        }
    }

    public ClientInfo getLastClient() {
        return lastClient;
    }

    @Override
    public void send(Object object, InetAddress host, int port) {
        try {
            byte[] data = Serializer.serialize(object);
            DatagramPacket dp = new DatagramPacket(data, data.length, host, port);
            ds.send(dp);
        } catch (IOException e) {
            Logger.get().writeLine("NetIOManager::send::Serializer::serialize: " + e.getMessage());
        }

    }

    public void close() {
        ds.close();
    }

    @Override
    public InstructionInfo receive() {
        DatagramPacket dp;
        ByteBuffer buffer = ByteBuffer.allocate(32768);
        dp = new DatagramPacket(buffer.array(), 32768);
        try {
            ds.receive(dp);
            lastClient = new ClientInfo(dp.getPort(), dp.getAddress());
            return (InstructionInfo) Serializer.deserialize(dp.getData());
        } catch (Exception e) {
            Logger.get().writeLine("NetIOManager::receive: " + e.getMessage());
        }
        return null;
    }

}
