package lab6;

import lab6.instruction.*;
import lab6.logic.InstructionFetch;
import lab6.logic.InstructionListener;
import lab6.models.MovieCollection;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            MovieCollection.getInstance().setStartData(args[0]);
        } else {
            MovieCollection.getInstance().setStartData();
        }

        InstructionListener instructionListener = new InstructionListener();

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
