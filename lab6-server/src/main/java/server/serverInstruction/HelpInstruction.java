package server.serverInstruction;

import java.util.Stack;
import java.util.stream.Collectors;

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

        String answer = instructionStack.stream().filter(x -> !x.isSpecial())
                .map(x -> "--->  " + x.getName() + ": " + x.about() + "\n").collect(Collectors.joining());

        return new PrintInstruction(answer);
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