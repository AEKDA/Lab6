package core.clientInstruction;

import core.logic.Client;

public class StopClientInstruction extends ClientInstruction {
    @Override
    public void implement(Client client) {
        client.stop();
    }
}
