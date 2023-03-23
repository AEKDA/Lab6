package lab6.logic;

import java.util.Collection;

/**
 * Интерфейс, который предоставляет абстрагированные методы для работы с коллекциями
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
