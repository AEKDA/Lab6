package lab6.models.validators;


/**
 * Класс проверяет верность ввода роста 
 */
public class HeightValidator implements Validator<Integer> {
    private Integer val;

    public Integer getValue() {
        return val;
    }

    public String getMessage() {
        return "Введите рост режиссера (целое число):";
    }

    public boolean check(String args) {
        if(args == null || args.isEmpty()) {
            return false;
        }
        try {
            val = Integer.parseInt(args);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
