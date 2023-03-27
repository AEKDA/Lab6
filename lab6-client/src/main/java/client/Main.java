package client;

import client.logic.User;
import core.logic.InstructionListener;

public class Main {
    public static void main(String[] args) {
        int port = 5343;
        try {
            if (args.length > 1) {
                if (args[0].equals("-p")) {
                    port = Integer.parseInt(args[1]);
                }
            }
        } catch (Exception e) {
        }

        InstructionListener instructionListener = new InstructionListener();

        instructionListener.registerObserver(new User(port, instructionListener));

        instructionListener.start(System.in);
    }
}
