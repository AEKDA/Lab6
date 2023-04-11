package core.clientInstruction;

import core.logic.ClinetState;
import core.logic.Client;

public class StopClientInstruction extends ClientInstruction {
    @Override
    public ClinetState implement(Client client) {
        client.stop();
        return ClinetState.Work;
    }
}
