package lab6.instruction;

import lab6.io.Cin;
import lab6.logic.Instruction;
import lab6.models.Movie;
import lab6.models.MovieCollection;

/**
 * Команда добавляющая элемент, если все значение
 * {@link lab6.models.Movie#getTotalBoxOffice()} нового элемента больше значений всех
 * осталых элементоа, находящихся в коллекции
 */
public class AddIfMaxInstruction implements Instruction {
    @Override
    public void execute(String[] args) {
        Movie m = new Movie();
        m.getElement(Cin.peek());
        for (Movie movie : MovieCollection.getInstance().getData()) {
            if (movie.getTotalBoxOffice() >= m.getTotalBoxOffice()) {
                return;
            }
        }
        MovieCollection.getInstance().pushElement(m);
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String about() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
