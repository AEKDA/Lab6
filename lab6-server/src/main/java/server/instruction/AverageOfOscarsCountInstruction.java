package server.instruction;

import server.io.Logger;
import server.logic.Instruction;
import server.models.Movie;
import server.models.MovieCollection;

/**
 * Команда, которая вяводит среднее значение оскаров среди всех элементов,
 * находящихся в коллекции
 */
public class AverageOfOscarsCountInstruction implements Instruction {

    @Override
    public void execute(String[] args) {
        int oscarsCount = 0;
        for (Movie movie : MovieCollection.getInstance().getData()) {
            oscarsCount += movie.getOscarCount();
        }
        try {
            Logger.get().write("Middle count Oscars:%.1f\n",
                    (float) oscarsCount / (float) (MovieCollection.getInstance().getData().size()));
        } catch (ArithmeticException e) {
            Logger.get().writeLine("Middle count Oscars: 0");
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
}
