package core.logic;

import java.io.Serializable;

public class InstructionInfo implements Serializable {
    private String instructionName = "";
    private String[] args;
    private boolean isElement = false;
    private CollectionElement collectionElement = null;

    public InstructionInfo() {
        instructionName = "";
        args = null;
    }

    public InstructionInfo(String name) {
        instructionName = name;
    }

    public InstructionInfo(String name, String[] args) {
        instructionName = name;
        this.args = args;
    }

    public CollectionElement getElement() {
        return this.collectionElement;
    }

    public void setElement(CollectionElement collectionElement) {
        this.collectionElement = collectionElement;
        isElement = true;
    }

    public boolean IsElement() {
        return isElement;
    }

    public String getInstruction() {
        return this.instructionName;
    }

    public void setInstruction(String name) {
        this.instructionName = name;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}
