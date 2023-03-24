package server.exception;

/**
 * Исключение, которое вызывается, когда полученная инструкция не найдена
 */
public class IncorrectInstructionException extends Exception {
    public IncorrectInstructionException(String text) {
        super(text);
    }
}
