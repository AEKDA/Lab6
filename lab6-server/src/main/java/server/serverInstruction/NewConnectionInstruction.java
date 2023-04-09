package server.serverInstruction;

import java.util.Stack;

import core.clientInstruction.ClientInstruction;
import core.clientInstruction.StackContentInstruction;
import core.logic.InstructionInfo;
import server.logic.InstructionFetch;
import server.logic.ServerInstruction;

public class NewConnectionInstruction implements ServerInstruction {
    private InstructionFetch fetch;

    public NewConnectionInstruction(InstructionFetch instructionFetch) {
        this.fetch = instructionFetch;
    }

    public ClientInstruction execute(InstructionInfo info) {
        Stack<InstructionInfo> infoStack = new Stack<>();

        for (ServerInstruction serverInstruction : fetch.getInstructionStack()) {
            InstructionInfo out = new InstructionInfo(serverInstruction.getName(), serverInstruction.isSpecial());
            infoStack.push(out);
        }

        return new StackContentInstruction(infoStack);
    }

    public String about() {
        return "";
    }

    public String getName() {
        return "__connection";
    }

    public boolean isSpecial() {
        return true;
    }

    public boolean needElement() {
        return false;
    }
}
