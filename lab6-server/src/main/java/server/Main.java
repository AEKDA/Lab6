package server;

import server.logic.ClientListener;
import server.logic.InstructionFetch;
import server.logic.MovieCollection;
import server.serverInstruction.*;

public class Main {
    public static void main(String[] args) {
        MovieCollection.getInstance()
                .setStartData("data/CollectionData.json");
        ClientListener clientListener = new ClientListener();
        InstructionFetch instructionFetch = new InstructionFetch(clientListener);
        instructionFetch.addInstruction(new HelpInstruction(instructionFetch.getInstructionStack()),
                new AverageOfOscarsCountInstruction(), new FilterContainsNameInstruction(),
                new PrintDescendingInstruction(), new ExecuteScriptInstruction(), new RemoveByIdInstruction(),
                new SaveMovieInstruction(), new InsertAtInstruction(), new AddIfMaxInstruction(),
                new ShuffleInstruction(), new UpdateInstruction(), new ClearInstruction(), new InfoInstruction(),
                new ShowInstruction(), new ExitInstruction(), new AddInstruction(),
                new NewConnectionInstruction(instructionFetch), new CheckConnectionInstruction());

        clientListener.registerObserver(instructionFetch);
        clientListener.start();

    }
}
