package lab6.models.validators;


/**
 * Класс проверяет верность ввода имени режиссера
 */
public class DirectorNameValidator extends NameValidator {
    @Override
    public String getMessage() {
        return "Введите имя режиссера:";
    }
}
