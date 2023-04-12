package server.logic;

import java.net.InetAddress;
import java.util.Stack;
import java.util.LinkedList;
import java.util.logging.Logger;

import core.clientInstruction.ClientInstruction;
import core.logic.InstructionInfo;
import core.logic.Observable;
import core.logic.Observer;
import server.net.NetIOManager;

public class ClientListener implements Observable {
    private LinkedList<Observer> deque = new LinkedList<>();
    private Logger logger;
    private Stack<ClientInfo> clinetPool;
    private ClientInfo lastClient = null;

    private final int port = 5343;

    private InstructionInfo instructionInfo = null;
    private ClientInstruction answer = null;
    private NetIOManager netManager = null;

    {
        logger = Logger.getLogger(ClientListener.class.getName());
        clinetPool = new Stack<>();
        try {
            netManager = new NetIOManager(InetAddress.getLocalHost(), port);
        } catch (Exception e) {
            netManager = null;
        }
    }

    public void start() {
        logger.info("Server start work; Port = " + Integer.toString(port));
        while (true) {
            instructionInfo = netManager.receive();
            ClientInfo clientInfo = netManager.getLastClient();
            if (!existClient(clientInfo)) {
                clinetPool.push(clientInfo);
                logger.info("New Connection: Port: " + clientInfo.getPort() + " Adddress: "
                        + clientInfo.getAddress().toString());
            }
            lastClient = clientInfo;
            notifyObservers();
            netManager.send(answer, clientInfo.getAddress(), clientInfo.getPort());
        }
    }

    private boolean existClient(ClientInfo clientInfo) {
        for (ClientInfo info : clinetPool) {
            if (info.equals(clientInfo)) {
                return true;
            }
        }
        return false;
    }

    public void removeClientInfo(ClientInfo clientInfo) {
        clinetPool.remove(clientInfo);
    }

    @Override
    public void removeObserver(Observer o) {
        deque.remove(o);
    }

    public void setAnswer(ClientInstruction clientInstruction) {
        answer = clientInstruction;
    }

    public ClientInstruction getAnswer() {
        return answer;
    }

    @Override
    public void registerObserver(Observer observer) {
        deque.push(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : deque) {
            o.update(instructionInfo);
        }
    }

    public ClientInfo getLastClient() {
        return lastClient;
    }

}
