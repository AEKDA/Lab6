package lab6.models.validators;


/**
 * Класс проверяет верность ввода колличества оскаров
 */
public class OscarCountValidator implements Validator<Long> {
    private long val;

    @Override
    public Long getValue() {
        return val;
    }

    @Override
    public boolean check(String args) {
        try {
            val = Long.parseLong(args);
            if (args == null || args.isEmpty() || val < 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMessage() {
        return "Введите количество оскаров (целое число):";
    }
}
