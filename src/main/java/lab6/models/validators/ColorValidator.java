package lab6.models.validators;

import lab6.models.Color;

/**
 * Класс проверяет верность ввода {@link lab6.models.Color}
 */
public class ColorValidator implements Validator<Color> {
    private Color val;

    public Color getValue() {
        return val;
    }

    public String getMessage() {
        return "Введите цвет глаз из следующих: RED,YELLOW, BROWN:";
    }

    public boolean check(String args) {
        try {
            val = Color.valueOf(args);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
}
