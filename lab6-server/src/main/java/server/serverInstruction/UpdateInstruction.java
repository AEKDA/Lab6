package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда заменяет элемент id которого равен заданному
 */
public class UpdateInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs() == null || info.getArgs().length != 1) {
            throw new IllegalArgumentException("Error! the instruction is incorrect");
        }
        int update_id = 0;
        try {
            update_id = Integer.parseInt(info.getArgs()[0]);
        } catch (NumberFormatException e) {
            return new PrintInstruction("Некорректный аргумент");
        }
        for (int i = 0; i < MovieCollection.getInstance().getData().size(); i++) {
            if (MovieCollection.getInstance().getData().get(i).getId() == update_id) {
                if (info.getElement() == null) {
                    return new GetElementInstruction(info);
                }
                ((Movie) info.getElement()).setId(update_id);
                MovieCollection.getInstance().getData().set(i, (Movie) info.getElement());
                return new PrintInstruction("Элемент обновлен");
            }
        }
        throw new IllegalArgumentException("Элемента в введеным id несуществует");
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String about() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return true;
    }
}
