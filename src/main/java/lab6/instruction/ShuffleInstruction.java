package lab6.instruction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import lab6.logic.Instruction;
import lab6.models.MovieCollection;

/**
 * Команда случайным образом перемешивает все элементы коллекции
 */
public class ShuffleInstruction implements Instruction {

    @Override
    public void execute(String[] args) {
        Collections.sort(MovieCollection.getInstance().getData(), new Comparator<Object>() {
            public int compare(Object t1, Object t2) {
                Random rand = new Random();
                return (rand.nextInt() % 3 - 1);
            }
        });
    }

    @Override
    public String getName() {
        return "shuffle";
    }

    @Override
    public String about() {
        return "перемешать элементы коллекции в случайном порядке";
    }
}
