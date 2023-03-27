package server.serverInstruction;

import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.clientInstruction.*;
import core.logic.InstructionInfo;

/**
 * Команда, которая выводит ифнормацию о коллекции
 */
public class InfoInstruction implements ServerInstruction {

    public InfoInstruction() {
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("--->Information about the collection:\n");
        sb.append("--->Type: " + MovieCollection.getInstance().getData().getClass().toString() + "\n");
        sb.append("--->Date of creation: " + MovieCollection.getInstance().getInfo().getDate().toString() + "\n");
        sb.append("--->Elements count: " + Integer.toString(MovieCollection.getInstance().getData().size()) + "\n");
        return new PrintInstruction(sb.toString());
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String about() {
        return "вывести в стандартный поток вывода информацию о коллекции";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }

}
