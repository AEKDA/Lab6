package core.clientInstruction;

import java.io.Serializable;

import core.logic.ClinetState;
import core.logic.Client;

public abstract class ClientInstruction implements Serializable {
    private ClientInstruction nextInstruction = null;
    private static final long serialVersionUID = 1L;

    protected abstract ClinetState implement(Client client) throws IllegalArgumentException;

    public ClientInstruction addInstruction(ClientInstruction clientInstruction) {
        nextInstruction = clientInstruction;
        return nextInstruction;
    }

    public void execute(Client client) {
        try {
            client.switchState(implement(client));
        } catch (IllegalArgumentException e) {
            return;
        }
        if (nextInstruction != null) {
            nextInstruction.execute(client);
        }
    }
}
