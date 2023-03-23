package lab6.models.validators;

import lab6.models.Vector3;

/**
 * Класс проверяет верность ввода {@link lab6.models.Vector3}
 */
public class LocationCoordsValidator implements Validator<Vector3> {
    private Vector3 val = new Vector3();

    public Vector3 getValue() {
        return val;
    }

    public String getMessage() {
        return "Введите местоположение съемок в формате x y z (x: целое, y и z число с плавающей запятой)";
    }

    public boolean check(String args) {
        if (args == null || args.strip().isEmpty()) {
            return false;
        }
        String[] input = args.split(" +");
        if (input.length != 3) {
            return false;
        }
        try {
            val.x = (Long.parseLong(input[0]));
            val.y = (Double.parseDouble(input[1]));
            val.z = (Float.parseFloat(input[2]));
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
}
