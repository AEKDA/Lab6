package server.instruction;

import server.io.Cin;
import server.io.Logger;
import server.logic.Instruction;
import server.models.Movie;
import server.models.MovieCollection;

/**
 * Команда заменяет элемент id которого равен заданному
 */
public class UpdateInstruction implements Instruction {

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error! the instruction is incorrect");
        }
        int update_id = 0;
        try {
            update_id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            Logger.get().writeLine("Некорректный аргумент");
            return;
        }

        for (int i = 0; i < MovieCollection.getInstance().getData().size(); i++) {
            if (MovieCollection.getInstance().getData().get(i).getId() == update_id) {
                Movie m = new Movie();
                m.getElement(Cin.peek());
                m.setId(update_id);
                MovieCollection.getInstance().getData().set(i, m);
                return;
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
}
