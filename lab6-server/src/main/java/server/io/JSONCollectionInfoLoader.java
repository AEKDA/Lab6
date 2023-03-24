package server.io;

import java.time.ZonedDateTime;
import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.databind.module.SimpleModule;

import server.logic.CollectionInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Класс, абстрагирующий парсер json'а, с помощью которго можно считать
 * {@link lab6.logic.CollectionInfo}
 */
public class JSONCollectionInfoLoader {
    private ObjectMapper objectMapper;

    public JSONCollectionInfoLoader() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(ZonedDateTime.class, new JaksonZonedDateTimeDeserializer());
        simpleModule.addSerializer(ZonedDateTime.class, new JaksonZonedDateTimeSerializer());
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
    }

    /**
     * 
     * @param path путь до файла, содержащего данные о коллекции
     * @return данные о коллекции считанные из файла
     */
    public CollectionInfo read(String path) {
        try {
            return objectMapper.readValue(new File(path), CollectionInfo.class);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Метод, который записывает в в файл данные из объекта типа
     * {@link lab6.logic.CollectionInfo}
     * 
     * @param path  путь до файла
     * @param array объект, который будет серриализован
     */
    public void write(String path, CollectionInfo val) {
        try {
            objectMapper.writeValue(new File(path), val);
        } catch (IOException e) {
            Logger.get().writeLine(e.getMessage());
        }
    }
}
