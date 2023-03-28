package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;
import core.clientInstruction.*;
import core.logic.InstructionInfo;

/**
 * Команда добавляет новый элемент в коллекцию
 */
public class AddInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs() != null) {
            throw new IllegalArgumentException("Error! input args incorrect!");
        }

        MovieCollection.getInstance().pushElement((Movie) info.getElement());
        return new PrintInstruction("Элемент добавлен");
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String about() {
        return "добавить новый элемент в коллекцию";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return true;
    }
}
