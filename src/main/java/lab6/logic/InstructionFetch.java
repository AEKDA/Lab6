package lab6.logic;

import java.util.Stack;

import lab6.exception.IncorrectInstructionException;
import lab6.io.Logger;

/**
 * a class that contains data about instructions and calls them
 */
public class InstructionFetch implements Observer {

    private Stack<Instruction> instructionStack = new Stack<>();

    @Override
    public void update(String[] args) {
        try {
            Instruction i = getInstruction(args);
            i.execute(args);
        } catch (IncorrectInstructionException e) {
            Logger.get().writeLine(e.getMessage());
        } catch (IllegalArgumentException e) {
            Logger.get().writeLine(args[0] + ": " + e.getMessage());
        }
    }

    private Instruction getInstruction(String[] args) throws IncorrectInstructionException {
        if (args.length == 0) {
            throw new IncorrectInstructionException("Error! You have not entered the instructions");
        }

        for (Instruction instruction : instructionStack) {
            if (instruction.getName().equals(args[0])) {
                return instruction;
            }
        }
        throw new IncorrectInstructionException("Error! The entered instruction is undefined: " + args[0]);
    }

    /**
     * Adds the passed instruction to the instruction stack
     * 
     * @param instruction The instruction that will be added to the stack
     * @return An instance of the same class, in order to be able to call functions
     *         along the chain
     */
    public InstructionFetch addInstruction(Instruction instruction) {
        instructionStack.push(instruction);
        return this;
    }

    /**
     * @return {@link lab5.java.util.Stack} containing all instructions
     */
    public Stack<Instruction> getInstructionStack() {
        return instructionStack;
    }
}
