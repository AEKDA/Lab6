package lab6.logic;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Класс предоставляющий информацию о коллекции
 */
public class CollectionInfo {
    private ZonedDateTime initDate;

    public CollectionInfo() {
        this.initDate = ZonedDateTime.now();
    }

    public CollectionInfo(ZonedDateTime date) {
        this.initDate = date;
    }

    @JsonSetter("initDate")
    public void setDate(ZonedDateTime zonedDateTime) {
        this.initDate = zonedDateTime;
    }

    /**
     * Метод, который возвращает время инициализации коллекции
     * 
     */
    @JsonGetter("initDate")
    public ZonedDateTime getDate() {
        return initDate;
    }
}
