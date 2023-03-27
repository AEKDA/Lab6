package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда удаляет элемент из коллекции, id которого равен заданному
 */
public class RemoveByIdInstruction implements ServerInstruction {
    private static String id = null;

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs().length != 1) {
            throw new IllegalArgumentException("Error! input args incorrect!");
        }
        try {
            if (Integer.parseInt(info.getArgs()[0]) >= MovieCollection.getInstance().getData().size()
                    || Integer.parseInt(info.getArgs()[0]) < 0) {
                return new PrintInstruction("Некорректный аргумент");
            }
        } catch (NumberFormatException e) {
            return new PrintInstruction("Некорректный аргумент");
        }

        id = info.getArgs()[0];
        MovieCollection.getInstance().getData().removeIf(RemoveByIdInstruction::test);
        return null;
    }

    public static boolean test(Movie m) {
        try {
            return m.getId() == Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String about() {
        return "удалить элемент из коллекции по его id";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
