package server.serverInstruction;

import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Комманда очищает коллекцию
 */
public class ClearInstruction implements ServerInstruction {

    public ClearInstruction() {
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        MovieCollection.getInstance().clear();
        return new PrintInstruction("Коллекция очищена");
    }

    @Override
    public String getName() {
        return "clear";
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String about() {
        return "очистить коллекцию";
    }
    public boolean needElement() {
        return false;
    }
}