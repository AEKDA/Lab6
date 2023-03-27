package core.models.validators;

import core.models.Coordinates;


/**
 * Класс проверяет верность ввода Координат
 */
public class CoordinatesValidator implements Validator<Coordinates> {
    private Coordinates val;

    @Override
    public boolean check(String args) {
        String[] input = args.strip().split(" +");
        if (input.length != 2) {
            return false;
        }
        try {
            val = new Coordinates(Float.parseFloat(input[0]), Double.parseDouble(input[1]));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getMessage() {
        return "Ведите координаты x и y через пробел (x, y числа с плавающей запятой, y <= 777):";
    }

    @Override
    public Coordinates getValue() {
        return val;
    }
}
