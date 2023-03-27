package server.serverInstruction;

import server.logic.ServerInstruction;
import core.clientInstruction.*;
import core.logic.InstructionInfo;

/**
 * Комманда исполняет скрипт, который был передан ей
 */
public class ExecuteScriptInstruction implements ServerInstruction {

    public ExecuteScriptInstruction() {
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) throws IllegalArgumentException {
        return new ClientExecuteScriptInstruction(info.getArgs());
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String about() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }
}
