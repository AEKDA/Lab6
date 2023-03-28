package server.serverInstruction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;
import core.clientInstruction.*;

import core.logic.InstructionInfo;

/**
 * Команда выводит элементы Коллекции в порядке убывания имени
 */
public class PrintDescendingInstruction implements ServerInstruction {

    @SuppressWarnings("all")
    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs() != null) {
            throw new IllegalArgumentException("Error! Argument of Instruction incorrect");
        }
        Stack<Movie> tmp = (Stack<Movie>) MovieCollection.getInstance().getData().clone();
        Collections.sort(tmp, new Comparator<Movie>() {
            public int compare(Movie t1, Movie t2) {
                return t1.getName().compareTo(t2.getName()) * -1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Object o : tmp) {
            sb.append(o.toString());
        }

        return new PrintInstruction(sb.toString());
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String about() {
        return "вывести элементы коллекции в порядке убывания";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
