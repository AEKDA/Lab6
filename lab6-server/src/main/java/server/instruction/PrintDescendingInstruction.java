package server.instruction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import server.io.Logger;
import server.logic.Instruction;
import server.models.Movie;
import server.models.MovieCollection;

/**
 * Команда выводит элементы Коллекции в порядке убывания имени
 */
public class PrintDescendingInstruction implements Instruction {

    @SuppressWarnings("all")
    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Error! Argument of Instruction incorrect");
        }
        Stack<Movie> tmp = (Stack<Movie>)MovieCollection.getInstance().getData().clone();
        Collections.sort(tmp, new Comparator<Movie>() {
            public int compare(Movie t1, Movie t2) {
                return t1.getName().compareTo(t2.getName()) * -1;
            }
        });
        for (Object o : tmp) {
            Logger.get().writeLine(o.toString());
        }
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String about() {
        return "вывести элементы коллекции в порядке убывания";
    }
}
