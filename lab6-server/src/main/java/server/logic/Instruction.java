package server.logic;


/**
 * Интерфейс, реализующий паттерн Команда
 */
public interface Instruction {

    public void execute(String[] args) throws IllegalArgumentException;

    public String getName();

    public String about();
}