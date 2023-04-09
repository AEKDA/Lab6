package client.logic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import client.net.NetNIOManager;
import core.clientInstruction.ClientInstruction;
import core.exception.ConnectErrorException;
import core.logic.InstructionInfo;
import core.logic.InstructionListener;
import core.logic.Observer;
import core.net.NetManager;
import core.logic.Client;
import core.logic.ClinetState;
import core.io.Logger;

public class User implements Observer, Client {
    private final int serverPort;
    private InetAddress serverHost;
    private InstructionListener instructionListener;
    private InstructionInfo[] instructionList;
    private NetNIOManager netManager;
    private ClinetState state;

    public User(int port, InstructionListener instructionListener) {
        this.serverPort = port;
        this.instructionListener = instructionListener;
        startConnect(1);
    }

    {
        state = ClinetState.Work;
        try {
            serverHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            serverHost = null;
        }
        netManager = new NetNIOManager();
    }

    public void startConnect(int attempt) {
        Logger.get().write("Попытка связаться с сервером №%d\n", attempt);
        netManager.send(new InstructionInfo("__connection", true), serverHost, serverPort);
        try {
            netManager.receive().execute(this);
            Logger.get().writeLine("Соединение установленно");
        } catch (ConnectErrorException e) {
            if (attempt == 3) {
                Logger.get().write("Сервер недоступен, попробуйте позже\n");
                instructionListener.stop();
                return;
            }
            Logger.get().write("Не удалось получить ответ от сервера\n");
            startConnect(attempt + 1);
        }
    }

    private void checkConnectToServer(int attempt) {
        Logger.get().write("Попытка связаться с сервером №%d\n", attempt);
        netManager.send(new InstructionInfo("__check_connection", true), serverHost, serverPort);
        try {
            netManager.receive().execute(this);
        } catch (ConnectErrorException e) {
            if (attempt == 3) {
                Logger.get().write("Сервер недоступен, попробуйте позже\n");
                instructionListener.stop();
                return;
            }
            Logger.get().write("Не удалось получить ответ от сервера\n");
            checkConnectToServer(attempt + 1);
        }
    }

    public InstructionListener getListener() {
        return instructionListener;
    }

    public void stop() {
        instructionListener.stop();
        netManager.close();
    }

    public void setInstruction(InstructionInfo[] instructionList) {
        this.instructionList = instructionList;
    }

    @Override
    public void update(InstructionInfo info) {
        if (checkInstruction(info)) {
            try {
                ClientInstruction response = netManager.receive();
                doAction(response);
            } catch (ConnectErrorException | NullPointerException e) {
                Logger.get().writeLine("Ответ от сервера не был получен");
                checkConnectToServer(1);
            }
        } else {
            Logger.get().writeLine("Вы ввели неизвестную команду");
        }
    }

    public boolean checkInstruction(InstructionInfo info) {
        for (InstructionInfo inst : instructionList) {
            if (inst.getInstruction().equals(info.getInstruction())) {
                netManager.send(info, serverHost, serverPort);
                return true;
            }
        }
        return false;
    }

    public void doAction(ClientInstruction response) {
        response.execute(this);
    }

    public NetManager getNetManager() {
        return netManager;
    }

    public int getServerPort() {
        return serverPort;
    }

    public InetAddress getServerHost() {
        return serverHost;
    }

    public void switchState(ClinetState state) {
        this.state = state;
    }

}
