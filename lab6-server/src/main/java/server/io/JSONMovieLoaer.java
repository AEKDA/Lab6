package server.io;

import java.io.File;
import java.time.ZonedDateTime;
import java.io.IOException;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.io.BaseReader;
import core.io.Logger;
import core.models.Movie;

/**
 * Класс реализующий загрузку и записать из json файла в коллекцию содержащую
 * элементы типа{@link lab6.models.Movie}
 */
public class JSONMovieLoaer implements CollectionLoader<Movie> {
    private ObjectMapper objectMapper;

    public JSONMovieLoaer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(ZonedDateTime.class, new JaksonZonedDateTimeDeserializer());
        simpleModule.addSerializer(ZonedDateTime.class, new JaksonZonedDateTimeSerializer());
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
    }

    /**
     * @param path Путь до json файла
     * @return Массив элементов типа Movie
     */
    @Override
    public Movie[] read(String path) {
        try {
            BaseReader bReader = new BaseReader(path);
            if (bReader.read().isEmpty()) {
                return null;
            }
            return objectMapper.readValue(new File(path), Movie[].class);
        } catch (DatabindException e) {
            Logger.get().writeLine("Указанный Json некорректен");
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * @param path  путь до файла
     * @param array Массив из которого будут взяты элементы типа Movie
     */
    @Override
    public void write(String path, Object array) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), array);
        } catch (IOException e) {
            Logger.get().writeLine(e.getMessage());
        }
    }

}
