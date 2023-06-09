package server.logic;

import java.util.Collections;
import java.util.Stack;

import core.io.Cin;
import server.exception.IncorrectPathException;
import server.exception.KeyNotFoundException;
import server.io.CollectionLoader;
import server.io.JSONCollectionInfoLoader;
import server.io.JSONMovieLoaer;
import core.models.Movie;

import java.time.ZonedDateTime;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * Класс, реализующий интерфейс {@link lab6.logic.CollectionManager}
 * Экземпляр класса может быть только один, к нему можно получить доступ с
 * помощью {@link lab6.MovieCollection#getInstance()}
 */
public class MovieCollection implements CollectionManager<Movie> {
    private static MovieCollection instance = null;
    private Stack<Movie> collectionStack;
    private CollectionInfo collectionInfo;
    private int id = 0;
    private Logger logger;

    /**
     * @return объект класса {@link lab6.MovieCollection}
     */
    public static MovieCollection getInstance() {
        if (instance == null) {
            instance = new MovieCollection();
        }
        return instance;
    }

    {
        logger = Logger.getLogger(MovieCollection.class.getName());
    }

    private MovieCollection() {
        collectionStack = new Stack<>();
        try {
            FileManager.get().pushPath("CollectionInfo", "data/CollectionInfo.json");
            String pathToInfo = FileManager.get().getPath("CollectionInfo").toFile().getPath();
            JSONCollectionInfoLoader cl = new JSONCollectionInfoLoader();
            collectionInfo = cl.read(pathToInfo);
            if (collectionInfo == null) {
                cl.write(pathToInfo, new CollectionInfo());
                this.collectionInfo = new CollectionInfo();
            }
        } catch (IncorrectPathException | KeyNotFoundException e) {
            logger.info("Файл содержащий информацию о коллекции не может быть прочитан и записан");
            this.collectionInfo = new CollectionInfo();
        }
    }

    /**
     * Сохраняет данные о коллекции в файл
     */
    @Override
    public void save() {
        JSONCollectionInfoLoader cl = new JSONCollectionInfoLoader();
        collectionInfo = new CollectionInfo(ZonedDateTime.from(ZonedDateTime.now()));
        try {
            cl.write(FileManager.get().getPath("CollectionInfo").toFile().getPath(), collectionInfo);
        } catch (KeyNotFoundException e) {
        }
    }

    @Deprecated
    public void getPathToCollection() {
        Cin cin = new Cin(System.in);
        logger.info("Введите путь к файлу, содержащему коллекцию");
        String path = cin.nextLine();
        try {
            FileManager.get().pushPath("Collection", path);
            logger.info("Файл, содержащий коллекцию, открыт");
        } catch (IncorrectPathException e) {
            logger.info(e.getMessage());
        }

    }

    /**
     * Устанавливает данные коллекции из файла, переданного в аргументы командной
     * строки
     */
    public void setStartData(String pathToCollectiion) {
        try {
            FileManager.get().pushPath("Collection", pathToCollectiion);
            logger.info("Файл, содержащий коллекцию, открыт");
        } catch (IncorrectPathException e) {
            logger.info(e.getMessage());
            return;
        }
        try {
            Path path = FileManager.get().getPath("Collection");
            if (path == null) {
                return;
            }
            CollectionLoader<Movie> io = new JSONMovieLoaer();
            Movie[] loadMovies = io.read(path.toFile().getPath());
            if (loadMovies != null) {
                MovieCollection.getInstance().setData(loadMovies);
            }
        } catch (KeyNotFoundException e) {
        }
    }

    @Deprecated
    public void setStartData() {
        getPathToCollection();
        CollectionLoader<Movie> io = new JSONMovieLoaer();
        try {
            Movie[] loadMovies = io.read(FileManager.get().getPath("Collection").toFile().getPath());
            if (loadMovies != null) {
                MovieCollection.getInstance().setData(loadMovies);
            }
        } catch (KeyNotFoundException e) {

        }
    }

    private void calcId() {
        for (Movie m : collectionStack) {
            if (m.getId() == 0) {
                m.setId(++id);
            }
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        collectionStack.clear();
    }

    /**
     * Возвращает стек который содержит элементы типа {@link lab6.models.Movie}
     * 
     * @return {@link lab6.java.util.Stack}, который содержит элементы типа
     *         {@link lab6.models.Movie}
     */
    public Stack<Movie> getData() {
        return collectionStack;
    }

    /**
     * Возвращает данные о коллекции
     * 
     * @return {@link lab6.logic.CollectionInfo}
     */
    public CollectionInfo getInfo() {
        return this.collectionInfo;
    }

    /**
     * Добавляет в коллекцию элементы из массива
     * 
     * @param movieData Массив элементов типа {@link lab6.models.Movie}
     */
    public void setData(Movie[] movieData) {
        Collections.addAll(collectionStack, movieData);
        calcId();
    }

    /**
     * Добавляет элемент в коллекцию
     * 
     * @param movie Элемент, который будет добавлен в коллекцию
     */
    public void pushElement(Movie movie) {
        movie.setId(getId());
        collectionStack.push(movie);
    }

    /**
     * Возвращает уникальный индификатор для коллекцииы
     * 
     * @return уникальный для этой коллекции id
     */
    public int getId() {
        return id++;
    }
}
