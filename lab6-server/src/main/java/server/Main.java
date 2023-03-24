package server;

import server.instruction.*;
import server.logic.InstructionFetch;
import server.logic.MovieCollection;

public class Main {
    public static void main(String[] args) {

        InstructionFetch instructionFetch = new InstructionFetch();
        instructionFetch.addInstruction(new HelpInstruction(instructionFetch.getInstructionStack()))
                .addInstruction(new ClearInstruction())
                .addInstruction(new InfoInstruction())
                .addInstruction(new ShowInstruction())
                .addInstruction(new AddInstruction())
                .addInstruction(new UpdateInstruction())
                .addInstruction(new ExitInstruction(instructionListener))
                .addInstruction(new ShuffleInstruction())
                .addInstruction(new AverageOfOscarsCountInstruction())
                .addInstruction(new SaveMovieInstruction())
                .addInstruction(new RemoveByIdInstruction())
                .addInstruction(new PrintDescendingInstruction())
                .addInstruction(new ExecuteScriptInstruction(instructionListener))
                .addInstruction(new FilterContainsNameInstruction())
                .addInstruction(new InsertAtInstruction())
                .addInstruction(new AddIfMaxInstruction());

        instructionListener.registerObserver(instructionFetch);
        instructionListener.start(System.in);

    }
}
