package client.logic;

import core.clientInstruction.ClientInstruction;
import core.clientInstruction.PrintInstruction;
import core.logic.InstructionInfo;
import core.logic.InstructionListener;
import core.logic.Observer;
import core.models.Movie;
import core.util.Serializer;
import core.logic.Client;
import core.io.Cin;
import core.io.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class User implements Observer, Client {
    private DatagramChannel dc;
    final int serverPort;
    final InetSocketAddress clientAddress;
    private InstructionListener instructionListener;
    private InstructionInfo[] instructionList;

    public User(int port, InstructionListener instructionListener) {
        this.serverPort = port;
        this.instructionListener = instructionListener;

        startConnect();
    }

    public void startConnect() {
        send(new InstructionInfo("__connection", true));
        receive().execute(this);
    }

    public InstructionListener getListener() {
        return instructionListener;
    }

    {
        clientAddress = new InetSocketAddress(0);
        try {
            dc = DatagramChannel.open();
            dc.bind(clientAddress);
        } catch (Exception e) {
            Logger.get().writeLine(e.getMessage());
        }
    }

    public void stop() {
        instructionListener.stop();
    }

    public void setInstruction(InstructionInfo[] instructionList) {
        this.instructionList = instructionList;
    }

    public boolean checkElement(String instructionName) {
        for (InstructionInfo info : instructionList) {
            if (info.getInstruction().equals(instructionName) && info.IsElement()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(InstructionInfo info) {
        if (checkElement(info.getInstruction())) {
            Movie movie = new Movie();
            movie.getElement(Cin.peek());
            info.setElement(movie);
        }
        send(info);
        ClientInstruction response = receive();
        doAction(response);
    }

    public void doAction(ClientInstruction response) {
        response.execute(this);
    }

    private void send(InstructionInfo instructionInfo) {
        try {
            byte[] buffer = Serializer.serialize(instructionInfo);
            ByteBuffer buf = ByteBuffer.wrap(buffer);
            dc.send(buf, new InetSocketAddress(InetAddress.getLocalHost(), serverPort));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ClientInstruction receive() {
        try {
            ByteBuffer buf = ByteBuffer.allocate(16384);
            dc.receive(buf);
            ClientInstruction response = (ClientInstruction) Serializer.deserialize(buf.array());
            return response;
        } catch (IOException e) {
            return new PrintInstruction(e.getMessage());
        } catch (ClassNotFoundException e) {
            return new PrintInstruction(e.getMessage());
        }
    }

}
