package server.serverInstruction;

import server.logic.ServerInstruction;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Комманда, которая заканчивает выполнение работы программы
 */
public class ExitInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        new SaveMovieInstruction().execute(info);
        return new StopClientInstruction();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String about() {
        return "завершить программу (без сохранения в файл)";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
