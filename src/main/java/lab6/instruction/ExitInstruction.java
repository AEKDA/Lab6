package lab6.instruction;

import lab6.logic.Instruction;
import lab6.logic.InstructionListener;

/**
 * Комманда, которая заканчивает выполнение работы программы
 */
public class ExitInstruction implements Instruction {
    InstructionListener instructionListener;

    public ExitInstruction(InstructionListener instructionListener) {
        this.instructionListener = instructionListener;
    }

    @Override
    public void execute(String[] args) {
        instructionListener.stop();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String about() {
        return "завершить программу (без сохранения в файл)";
    }
}
