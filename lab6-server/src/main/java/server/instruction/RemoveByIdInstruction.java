package server.instruction;

import server.logic.Instruction;
import server.models.Movie;
import server.models.MovieCollection;

/**
 * Команда удаляет элемент из коллекции, id которого равен заданному
 */
public class RemoveByIdInstruction implements Instruction {
    private static String id = null;

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error! input args incorrect!");
        }
        id = args[1];
        MovieCollection.getInstance().getData().removeIf(RemoveByIdInstruction::test);
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
}
