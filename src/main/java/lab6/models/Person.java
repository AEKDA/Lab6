package lab6.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Person {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Integer height; // Поле не может быть null, Значение поля должно быть больше 0
    private Color eyeColor; // Поле может быть null
    private Country nationality; // Поле может быть null
    private Location location; // Поле не может быть null

    @JsonSetter("name")
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Error! a name cannot take a value of zero or null");
        this.name = name;
    }

    @JsonSetter("height")
    public void setHeight(Integer height) throws IllegalArgumentException {
        if (height == null || height < 1)
            throw new IllegalArgumentException("Error! a height cannot take a value of zero or be less than zero");
        this.height = height;
    }

    @JsonSetter("eyeColor")
    public void setEyeColor(Color color) throws IllegalArgumentException {
        if (color == null)
            throw new IllegalArgumentException("Error! a color cannot take a value of null");
        this.eyeColor = color;
    }

    @JsonSetter("nationality")
    public void setNationality(Country country) throws IllegalArgumentException {
        if (country == null)
            throw new IllegalArgumentException("Error! a country cannot take a value of null");
        this.nationality = country;
    }

    @JsonSetter("location")
    public void setLocation(Location location) throws IllegalArgumentException {
        if (location == null)
            throw new IllegalArgumentException("Error! a location cannot take a value of null");
        this.location = location;
    }

    @JsonGetter("name")
    public String getName() {
        return this.name;
    }

    @JsonGetter("height")
    public Integer getHeight() {
        return this.height;
    }

    @JsonGetter("eyeColor")
    public Color getEveColor() {
        return this.eyeColor;
    }

    @JsonGetter("nationality")
    public Country getNationality() {
        return this.nationality;
    }

    @JsonGetter("location")
    public Location geLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "{\n  " +
                "  name = " + this.name + "\n  " +
                "  height = " + this.height.toString() + "\n  " +
                "  eyeColor = " + this.eyeColor.toString() + "\n  " +
                "  nationality = " + this.nationality.toString() + "\n  " +
                "  location = " + this.location.toString() + "\n  }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.eyeColor, this.height, this.location, this.nationality);
    }
}
