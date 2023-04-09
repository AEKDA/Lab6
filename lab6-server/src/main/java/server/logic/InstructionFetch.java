package server.logic;

import java.util.Collections;
import java.util.Stack;

import core.logic.ClinetState;
import core.logic.InstructionInfo;
import core.logic.Observer;
import core.clientInstruction.GetElementInstruction;
import core.clientInstruction.PrintInstruction;
import server.exception.IncorrectInstructionException;

/**
 * a class that contains data about instructions and calls them
 */
public class InstructionFetch implements Observer {

    private Stack<ServerInstruction> instructionStack = new Stack<>();
    private ClientListener clientListener = null;

    public InstructionFetch(ClientListener server) {
        this.clientListener = server;
    }

    @Override
    public void update(InstructionInfo info) {
        try {
            // Получаем инструкцию и обрабатываем исключения
            ServerInstruction i = getInstruction(info);
            // Смотрим состояние последнего клиента
            switch (clientListener.getLastClient().getState()) {
                case Work:
                    // если веденой команде нужен элемент, отправляем запрос на ввод
                    // Иначе выполняем команду без элемента
                    if (i.needElement() && info.getElement() == null) {
                        clientListener.getLastClient().switchState(ClinetState.Wait);
                        clientListener.setAnswer(new GetElementInstruction());
                        break;
                    } else {
                        try {
                            clientListener.setAnswer(i.execute(info));
                        } catch (IllegalArgumentException e) {
                            clientListener
                                    .setAnswer(new PrintInstruction((info.getInstruction() + ": " + e.getMessage())));
                        }

                    }
                case Wait:
                    // Устанавливаем состояние клиента на состояние по умолчанию и выполняем
                    // инструкцию
                    try {
                        clientListener.setAnswer(i.execute(info));
                    } catch (IllegalArgumentException e) {
                        clientListener
                                .setAnswer(new PrintInstruction((info.getInstruction() + ": " + e.getMessage())));
                    }
                    clientListener.getLastClient().switchState(ClinetState.Work);
                    break;
            }
        } catch (IncorrectInstructionException e) {
            clientListener.setAnswer(new PrintInstruction(e.getMessage()));
        }

    }

    private ServerInstruction getInstruction(InstructionInfo info) throws IncorrectInstructionException {
        if (info.getInstruction().equals("")) {
            throw new IncorrectInstructionException("Error! You have not entered the instructions");
        }

        for (ServerInstruction instruction : instructionStack) {
            if ((instruction.getName().equals(info.getInstruction()))
                    && (instruction.isSpecial() == info.isSpecial())) {
                return instruction;
            }
        }
        throw new IncorrectInstructionException(
                "Error! The entered instruction is undefined: " + info.getInstruction());
    }

    /**
     * Adds the passed instruction to the instruction stack
     * 
     * @param instruction The instruction that will be added to the stack
     * @return An instance of the same class, in order to be able to call functions
     *         along the chain
     */
    public InstructionFetch addInstruction(ServerInstruction instruction) {
        instructionStack.push(instruction);
        return this;
    }

    public InstructionFetch addInstruction(ServerInstruction... instruction) {
        Collections.addAll(instructionStack, instruction);
        return this;
    }

    /**
     * @return {@link lab5.java.util.Stack} containing all instructions
     */
    public Stack<ServerInstruction> getInstructionStack() {
        return instructionStack;
    }
}
