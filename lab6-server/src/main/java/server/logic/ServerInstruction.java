package server.logic;

import core.clientInstruction.ClientInstruction;
import core.logic.InstructionInfo;

/**
 * Интерфейс, реализующий паттерн Команда
 */
public interface ServerInstruction {

    ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException;

    String getName();

    String about();

    boolean isSpecial();

    boolean needElement();

}