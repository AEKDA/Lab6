package lab6.instruction;

import lab6.logic.Instruction;
import lab6.models.MovieCollection;

/**
 * Комманда очищает коллекцию
 */
public class ClearInstruction implements Instruction {

    public ClearInstruction() {
    }

    @Override
    public void execute(String[] args) {
        MovieCollection.getInstance().clear();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String about() {
        return "очистить коллекцию";
    }
}