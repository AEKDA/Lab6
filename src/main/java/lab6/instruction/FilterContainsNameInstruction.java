package lab6.instruction;

import lab6.io.Logger;
import lab6.logic.Instruction;
import lab6.models.Movie;
import lab6.models.MovieCollection;

/**
 * Комманда, которая выводит все элементы, значение поля name которых содержит
 * заданную подстроку
 */
public class FilterContainsNameInstruction implements Instruction {

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error! The arguments are not correct");
        }
        for (Movie movie : MovieCollection.getInstance().getData()) {
            if (movie.getName().contains(args[1])) {
                Logger.get().writeLine(movie.toString());
            }
        }
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String about() {
        return "вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
