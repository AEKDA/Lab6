package core.clientInstruction;

import core.logic.Client;

public class PrintInstruction extends ClientInstruction {
    private String message;

    public PrintInstruction(String message) {
        this.message = message;
    }

    public void implement(Client client) {
        System.out.println(message);
    }
}
