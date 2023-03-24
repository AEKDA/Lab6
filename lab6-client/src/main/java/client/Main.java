package client;

import client.logic.Client;
import client.logic.InstructionListener;

public class Main {

    public static void main(String[] args) {
        int port = 5653;
        try {
            if (args.length > 1) {
                if (args[0].equals("-p")) {
                    port = Integer.parseInt(args[1]);
                }
            }
        } catch (Exception e) {
        }
        InstructionListener instructionListener = new InstructionListener();

        instructionListener.registerObserver(new Client(port, instructionListener));

        instructionListener.start(System.in);

    }
}
