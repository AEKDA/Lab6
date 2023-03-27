package server.serverInstruction;

import java.util.Stack;

import server.logic.ServerInstruction;

import core.logic.InstructionInfo;
import core.clientInstruction.*;

/**
 * Команда, которая выводит информацию о всех остальных командах
 */
public class HelpInstruction implements ServerInstruction {
    private String name;
    private Stack<ServerInstruction> instructionStack;

    public HelpInstruction(Stack<ServerInstruction> instructionStack) {
        name = "help";
        this.instructionStack = instructionStack;
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        StringBuilder sb = new StringBuilder();
        for (ServerInstruction inst : instructionStack) {
            if (!inst.isSpecial())
                sb.append("--->  " + inst.getName() + ": " + inst.about() + "\n");
        }
        return new PrintInstruction(sb.toString());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String about() {
        return "вывести справку по доступным командам";
    }

    public boolean isSpecial() {
        return false;
    }

    public boolean needElement() {
        return false;
    }

}