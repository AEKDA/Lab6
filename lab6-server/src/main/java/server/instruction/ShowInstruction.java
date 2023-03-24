package server.instruction;

import server.io.Logger;
import server.logic.Instruction;
import server.models.MovieCollection;


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
