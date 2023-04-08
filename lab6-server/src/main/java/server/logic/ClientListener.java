package server.logic;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.logging.Logger;

import core.clientInstruction.ClientInstruction;
import core.logic.InstructionInfo;
import core.logic.Observable;
import core.logic.Observer;
import core.net.NetIOManager;

public class ClientListener implements Observable {
    private LinkedList<Observer> deque = new LinkedList<>();
    private Logger logger;

    private final int port = 5343;

    private InstructionInfo info = null;
    private ClientInstruction answer = null;
    private NetIOManager netManager = null;

    {
        logger = Logger.getLogger(ClientListener.class.getName());
        try {
            netManager = new NetIOManager(InetAddress.getLocalHost(), port);
        } catch (Exception e) {
            netManager = null;
        }
    }

    public void start() {
        logger.info("Server start work; Port = " + Integer.toString(port));
        while (true) {
            info = netManager.receive();
            netManager.getLastClient();
            notifyObservers();
            netManager.send(answer, netManager.getLastClient().getAddress(), netManager.getLastClient().getPort());
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
