package server.instruction;

import client.logic.InstructionListener;
import server.logic.Instruction;

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
