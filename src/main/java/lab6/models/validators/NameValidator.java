package lab6.models.validators;


/**
 * Класс проверяет верность ввода имени
 */
public class NameValidator implements Validator<String> {
    private String val;

    public boolean check(String args) {
        if (args == null || args.strip().isEmpty()) {
            return false;
        }
        val = args.strip();
        return true;
    }

    public String getMessage() {
        return "Ведите название фильма";
    }

    public String getValue() {
        return val;
    }
}
