package server.serverInstruction;

import java.util.Comparator;
import java.util.stream.Collectors;

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

        String collectionSorted = MovieCollection.getInstance().getData().stream().sorted(new Comparator<Movie>() {
            public int compare(Movie t1, Movie t2) {
                return t2.getName().compareTo(t1.getName());
            }
        }).map(x -> x.toString()).collect(Collectors.joining());

        return new PrintInstruction(collectionSorted);
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
