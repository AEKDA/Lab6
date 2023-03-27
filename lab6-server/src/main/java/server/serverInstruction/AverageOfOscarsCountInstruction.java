package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда, которая вяводит среднее значение оскаров среди всех элементов,
 * находящихся в коллекции
 */
public class AverageOfOscarsCountInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        int oscarsCount = 0;
        for (Movie movie : MovieCollection.getInstance().getData()) {
            oscarsCount += movie.getOscarCount();
        }
        try {

            String s = String.format("Middle count Oscars:%.1f\n",
                    (float) oscarsCount / (float) (MovieCollection.getInstance().getData().size())).toString();
            return new PrintInstruction(s);
        } catch (ArithmeticException e) {
            return new PrintInstruction("Middle count Oscars: 0");
        }
    }

    @Override
    public String getName() {
        return "average_of_oscars_count";
    }

    @Override
    public String about() {
        return "вывести среднее значение поля oscarsCount для всех элементов коллекции";
    }

    public boolean isSpecial() {
        return false;
    }
    public boolean needElement() {
        return false;
    }
}
