package server.logic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import core.clientInstruction.ClientInstruction;
import core.io.Logger;
import core.logic.InstructionInfo;
import core.logic.Observable;
import core.logic.Observer;
import core.util.Serializer;

public class ClientListener implements Observable {
    private LinkedList<Observer> deque = new LinkedList<>();
    private LinkedHashSet<InetAddress> clientList = new LinkedHashSet<>();

    private final int port = 5343;

    private InstructionInfo info = null;
    private ClientInstruction answer = null;

    public void start() {

        try (DatagramSocket ds = new DatagramSocket(port, InetAddress.getLocalHost())) {
            while (true) {
                DatagramPacket dp;
                ByteBuffer buffer = ByteBuffer.allocate(16384);
                dp = new DatagramPacket(buffer.array(), 16384);
                ds.receive(dp);
                clientList.add(dp.getAddress());
                int lastClient = dp.getPort();
                InetAddress lastAddress = dp.getAddress();
                try {
                    this.info = (InstructionInfo) Serializer.deserialize(dp.getData());
                    notifyObservers();
                    byte[] data = Serializer.serialize(answer);
                    dp = new DatagramPacket(data, data.length, lastAddress, lastClient);
                    ds.send(dp);
                } catch (IOException | ClassNotFoundException e) {
                    Logger.get().writeLine(e.getMessage());
                }

            }
        } catch (IOException e) {
            Logger.get().writeLine(e.getMessage());
        }
    }

    @Override
    public void removeObserver(Observer o) {
        deque.remove(o);
    }

    public void setAnswer(ClientInstruction clientInstruction) {
        answer = clientInstruction;
    }

    @Override
    public void registerObserver(Observer observer) {
        deque.push(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : deque) {
            o.update(info);
        }
    }

}
