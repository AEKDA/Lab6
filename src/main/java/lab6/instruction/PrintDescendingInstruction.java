package lab6.instruction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import lab6.io.Logger;
import lab6.logic.Instruction;
import lab6.models.Movie;
import lab6.models.MovieCollection;

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
