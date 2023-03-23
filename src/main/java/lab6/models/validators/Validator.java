package lab6.models.validators;


/**
 * Интерфейс, который предоставляет методы для отслеживания корректности ввода 
 */
public interface Validator<T> {
    boolean check(String args);
    String getMessage();
    T getValue();
}
