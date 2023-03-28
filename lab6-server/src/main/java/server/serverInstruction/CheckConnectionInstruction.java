package server.serverInstruction;

import core.clientInstruction.ClientInstruction;
import core.clientInstruction.PrintInstruction;
import core.logic.InstructionInfo;
import server.logic.ServerInstruction;

public class CheckConnectionInstruction implements ServerInstruction {

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        return new PrintInstruction("Соединение установленно");
    }

    @Override
    public String about() {
        return "";
    }

    @Override
    public String getName() {
        return "__check_connection";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean needElement() {
        return false;
    }

}
