package client.logic;

import core.io.Cin;
import core.logic.InstructionInfo;
import core.logic.Observer;
import core.logic.Response;
import core.models.Movie;
import server.io.Logger;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

import java.util.Arrays;

public class Client implements Observer {

    final int port;
    private DatagramChannel dc;
    private InstructionListener instructionListener;

    public Client(int port, InstructionListener instructionListener) {
        this.port = port;
        this.instructionListener = instructionListener;
        try {
            this.dc = DatagramChannel.open();
        } catch (IOException e) {
            Logger.get().writeLine(e.getMessage());
        }

    }

    @Override
    public void update(String[] args) {
        InstructionInfo info = lexemAnalys(args);
        send(info);
        Response response = receive();
        doAction(response);
    }

    public void doAction(Response response) {
        switch (response.getClientAction()) {
            case Stop:
                instructionListener.stop();
                break;
            case Default:
                break;
            case GetElement:
                Movie movie = new Movie();
                movie.getElement(new Cin(System.in));
                InstructionInfo instructionInfo = new InstructionInfo();
                instructionInfo.setElement(movie);
                send(instructionInfo);
                break;
        }
    }

    private InstructionInfo lexemAnalys(String[] args) {
        if (args.length == 1)
            return new InstructionInfo(args[0]);
        else if (args.length == 2) {
            return new InstructionInfo(args[0], Arrays.copyOfRange(args, 1, args.length));
        }
        return new InstructionInfo();
    }

    private void send(InstructionInfo instructionInfo) {

    }

    private Response receive() {
        return null;
    }

}
