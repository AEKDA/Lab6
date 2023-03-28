package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;
import core.clientInstruction.*;
import core.logic.InstructionInfo;

/**
 * Команда добавляет элемент в заданную позицию в коллекции
 */
public class InsertAtInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs() == null || info.getArgs().length != 1) {
            throw new IllegalArgumentException("Error! You didn't enter the index");
        }
        int at;
        try {
            at = Integer.parseInt(info.getArgs()[0]);
        } catch (NumberFormatException e) {
            return new PrintInstruction("Некорректный индекс");
        }
        if (at < 1 || at > MovieCollection.getInstance().getData().size()) {
            return new PrintInstruction("Некорректный индекс");
        }
        try {
            MovieCollection.getInstance().getData().insertElementAt((Movie) info.getElement(),
                    Integer.parseInt(info.getArgs()[1]));
            return new PrintInstruction("Объект добавлен");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new PrintInstruction("Error! this index doesn't exist");
        }
    }

    @Override
    public String getName() {
        return "insert_at";
    }

    @Override
    public String about() {
        return "добавить новый элемент в заданную позицию";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return true;
    }
}
