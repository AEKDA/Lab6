package core.clientInstruction;

import core.logic.CollectionElement;
import core.logic.InstructionInfo;
import core.logic.Client;
import core.models.Movie;
import core.logic.ClinetState;
import core.io.Cin;

public class GetElementInstruction extends ClientInstruction {
    private InstructionInfo info;

    public GetElementInstruction(InstructionInfo info) {
        this.info = info;
    }

    public ClinetState implement(Client client) {
        CollectionElement collectionElement = new Movie();
        collectionElement.getElement(Cin.peek());
        info.setElement(collectionElement);
        client.getNetManager().send(info, client.getServerHost(), client.getServerPort());
        return ClinetState.Wait;
    }
}
