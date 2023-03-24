package server.instruction;

import server.io.Cin;
import server.io.Logger;
import server.logic.Instruction;
import server.models.Movie;
import server.models.MovieCollection;

/**
 * Команда добавляет элемент в заданную позицию в коллекции
 */
public class InsertAtInstruction implements Instruction {

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error! You didn't enter the index");
        }
        int at;
        try {
            at = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            Logger.get().writeLine("Некорректный индекс");
            return;
        }
        if (at < 1 || at > MovieCollection.getInstance().getData().size()) {
            Logger.get().writeLine("Некорректный индекс");
            return;
        }
        Movie m = new Movie();
        m.getElement(Cin.peek());
        try {
            MovieCollection.getInstance().getData().insertElementAt(m, Integer.parseInt(args[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.get().writeLine("Error! this index doesn't exist");
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
}
