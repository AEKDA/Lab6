package lab6.models.validators;


/**
 * Класс проверяет верность ввода Названия места
 */
public class LocationNameValidator extends NameValidator {
    @Override
    public String getMessage() {
        return "Введите название локации: ";
    }
}
