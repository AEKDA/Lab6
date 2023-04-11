package core.clientInstruction;

import core.logic.Client;
import core.logic.InstructionInfo;

import java.util.Stack;

import core.logic.ClinetState;

public class StackContentInstruction extends ClientInstruction {

    private Stack<InstructionInfo> instructionStack = new Stack<>();

    public StackContentInstruction(Stack<InstructionInfo> info) {
        instructionStack = info;
    }

    @Override
    protected ClinetState implement(Client client) throws IllegalArgumentException {
        client.setInstruction(instructionStack.toArray(InstructionInfo[]::new));
        return ClinetState.Work;
    }
}
