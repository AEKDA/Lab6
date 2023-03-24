package server.io;

/**
 * Интерфейс, содержащий методы загрузки и записы элемента типа T
 */
public interface CollectionLoader<T> {

    T[] read(String path);

    void write(String path, Object array);
}
