package server;

import server.logic.ClientListener;
import server.logic.InstructionFetch;
import server.serverInstruction.*;

public class Main {
    public static void main(String[] args) {

        ClientListener clientListener = new ClientListener();
        InstructionFetch instructionFetch = new InstructionFetch(clientListener);
        instructionFetch.addInstruction(new HelpInstruction(instructionFetch.getInstructionStack()))
                .addInstruction(new AverageOfOscarsCountInstruction())
                .addInstruction(new FilterContainsNameInstruction())
                .addInstruction(new PrintDescendingInstruction())
                .addInstruction(new ExecuteScriptInstruction())
                .addInstruction(new RemoveByIdInstruction())
                .addInstruction(new SaveMovieInstruction())
                .addInstruction(new InsertAtInstruction())
                .addInstruction(new AddIfMaxInstruction())
                .addInstruction(new ShuffleInstruction())
                .addInstruction(new UpdateInstruction())
                .addInstruction(new ClearInstruction())
                .addInstruction(new InfoInstruction())
                .addInstruction(new ShowInstruction())
                .addInstruction(new ExitInstruction())
                .addInstruction(new AddInstruction())
                .addInstruction(new NewConnectionInstruction(instructionFetch));

        clientListener.registerObserver(instructionFetch);
        clientListener.start();

    }
}
