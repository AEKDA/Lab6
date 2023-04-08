package core.clientInstruction;

import core.logic.CollectionElement;
import core.logic.InstructionInfo;
import core.logic.Client;
import core.models.Movie;
import core.io.Cin;

public class GetElementInstruction extends ClientInstruction {
    public void implement(Client client) {
        CollectionElement collectionElement = new Movie();
        collectionElement.getElement(new Cin(System.in));
        InstructionInfo info = new InstructionInfo();
        info.setElement(collectionElement);
        client.getNetManager().send(info, client.getServerHost(), client.getServerPort());
    }
}
