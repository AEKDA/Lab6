package server.serverInstruction;

import core.models.Movie;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;
import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда добавляющая элемент, если все значение
 * {@link lab6.models.Movie#getTotalBoxOffice()} нового элемента больше значений
 * всех
 * осталых элементоа, находящихся в коллекции
 */
public class AddIfMaxInstruction implements ServerInstruction {
    @Override
    public ClientInstruction execute(InstructionInfo info) {
        if (info.getElement() == null) {
            return new GetElementInstruction(info);
        }
        for (Movie movie : MovieCollection.getInstance().getData()) {
            if (movie.getTotalBoxOffice() >= ((Movie) info.getElement()).getTotalBoxOffice()) {
                return new PrintInstruction("Значение не было добавлено");
            }
        }
        MovieCollection.getInstance().pushElement((Movie) info.getElement());
        return new PrintInstruction("Значение было добавлено");
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String about() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return true;
    }
}
