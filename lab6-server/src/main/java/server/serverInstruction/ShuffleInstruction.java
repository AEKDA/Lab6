package server.serverInstruction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда случайным образом перемешивает все элементы коллекции
 */
public class ShuffleInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        Collections.sort(MovieCollection.getInstance().getData(), new Comparator<Object>() {
            public int compare(Object t1, Object t2) {
                Random rand = new Random();
                return (rand.nextInt() % 3 - 1);
            }
        });

        return new PrintInstruction("Коллекция отсортирована");
    }

    @Override
    public String getName() {
        return "shuffle";
    }

    @Override
    public String about() {
        return "перемешать элементы коллекции в случайном порядке";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
