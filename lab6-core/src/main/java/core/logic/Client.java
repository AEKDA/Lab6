package core.logic;

public interface Client {

    void setInstruction(InstructionInfo[] instructionList);

    void stop();

    InstructionListener getListener();
}
