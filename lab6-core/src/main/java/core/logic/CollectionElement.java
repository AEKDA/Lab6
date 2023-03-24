package server.logic;

import server.io.Cin;

/**
 * Интерфейс, который должны реалзовывать элемменты коллекции для работы с данными
 */
public interface CollectionElement {
    void getElement(Cin is);
}
