package server.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Location {
    private long x;
    private double y;
    private float z;
    private String name; // Строка не может быть пустой, Поле не может быть null

    public Location() {
    }

    public Location(long x, double y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public void setCoordinates(Vector3 v) {
        setX(v.x);
        setY(v.y);
        setZ(v.z);
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @JsonSetter("name")
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Error! a string cannot take a value of zero or null");
        this.name = name;
    }

    public long getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    @JsonGetter("name")
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{ name = " + this.name + ";  x = " + Long.toString(this.x) + ";  y = " + Double.toString(y) + ";  z = "
                + Float.toString(z) + "  }";
    }
}