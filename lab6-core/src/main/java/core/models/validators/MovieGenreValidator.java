package server.models.validators;

import server.models.MovieGenre;


/**
 * Класс проверяет верность ввода {@link lab6.models.MovieGenre}
 */
public class MovieGenreValidator implements Validator<MovieGenre> {
    private MovieGenre val;

    public boolean check(String args) {
        try {
            val = MovieGenre.valueOf(args);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public String getMessage() {
        return "Введите Жанр фильма из следующих: DRAMA, COMEDY, ADVENTURE, THRILLER, SCIENCE_FICTION:";
    }

    public MovieGenre getValue() {
        return val;
    }
}
