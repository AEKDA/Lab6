package server.serverInstruction;

import core.logic.InstructionInfo;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;
import core.clientInstruction.*;

/**
 * Комманда, которая выводит все элементы, значение поля name которых содержит
 * заданную подстроку
 */
public class FilterContainsNameInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        if (info.getArgs() == null || info.getArgs().length != 1) {
            throw new IllegalArgumentException("Error! The arguments are not correct");
        }
        StringBuilder sb = new StringBuilder();
        for (Movie movie : MovieCollection.getInstance().getData()) {
            if (movie.getName().contains(info.getArgs()[0])) {
                sb.append(movie.toString());
            }
        }
        return new PrintInstruction(sb.toString());
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String about() {
        return "вывести элементы, значение поля name которых содержит заданную подстроку";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
