package lab6.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Coordinates {
    private float x;
    private double y; // Максимальное значение поля: 777

    public Coordinates() {
    }

    public Coordinates(float f, double d) throws IllegalArgumentException {
        this.setX(f);
        this.setY(d);
    }

    @JsonSetter("x")
    public void setX(float x) {
        this.x = x;
    }

    @JsonSetter("y")
    public void setY(double y) throws IllegalArgumentException {
        if (y > 777)
            throw new IllegalArgumentException("Error! the y value cannot be greater than 777");
        this.y = y;
    }

    @JsonGetter("x")
    public float getX() {
        return this.x;
    }

    @JsonGetter("y")
    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "{  x = " + Float.toString(x) + ";  y = " + Double.toString(y) + "  }";
    }

}