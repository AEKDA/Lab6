package lab6.instruction;

import lab6.io.Logger;
import lab6.logic.Instruction;
import lab6.models.MovieCollection;


/**
 * Команда выводит все элементы коллекции в строковом представлении
 */
public class ShowInstruction implements Instruction{
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String about() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(String[] args) {
        for(Object element: MovieCollection.getInstance().getData()) {
            Logger.get().writeLine(element.toString());
        }
    }
}
