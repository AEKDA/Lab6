package server.instruction;

import server.logic.Instruction;
import server.models.MovieCollection;

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