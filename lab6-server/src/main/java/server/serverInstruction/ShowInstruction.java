package server.serverInstruction;

import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда выводит все элементы коллекции в строковом представлении
 */
public class ShowInstruction implements ServerInstruction {
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String about() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        StringBuilder sb = new StringBuilder();
        for (Object element : MovieCollection.getInstance().getData()) {
            sb.append(element.toString());
        }

        return new PrintInstruction(sb.toString());
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
