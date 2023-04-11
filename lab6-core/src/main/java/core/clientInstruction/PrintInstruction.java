package core.clientInstruction;

import core.logic.ClinetState;
import core.io.Logger;
import core.logic.Client;

public class PrintInstruction extends ClientInstruction {
    private String message;

    public PrintInstruction(String message) {
        this.message = message;
    }

    public ClinetState implement(Client client) {
        Logger.get().writeLine(message);
        return ClinetState.Work;
    }
}
