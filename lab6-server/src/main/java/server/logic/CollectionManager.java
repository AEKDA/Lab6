package server.logic;

import java.util.Collection;

import core.logic.CollectionElement;

/**
 * Интерфейс, который предоставляет методы для работы с
 * коллекциями
 */
public interface CollectionManager<T extends CollectionElement> {

    void clear();

    Collection<T> getData();

    CollectionInfo getInfo();

    void setData(T[] movieData);

    void pushElement(T t);

    void save();

    int getId();
}
