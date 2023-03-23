package lab6.logic;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.LinkedHashMap;

import lab6.exception.IncorrectPathException;
import lab6.exception.KeyNotFoundException;

/**
 * Класс содерит аргумент командной строки, которые можно получить из любой
 * точки программы
 */
public class FileManager {
    private static FileManager instance = null;
    private LinkedHashMap<String, Path> pathMap;

    private FileManager() {
        pathMap = new LinkedHashMap<>();
    }

    public static FileManager get() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    /**
     * Проверка на корректность путя к файлу, если путь не правельный, просит ввести
     * новый путь вручную
     * 
     * @param p путь к файлу
     * @return возращает корректный путь
     */
    public Path getAvailablePath(String path) throws IncorrectPathException {
        boolean exist, notExist;
        Path basePath = Paths.get(path);
        exist = Files.exists(basePath);
        notExist = Files.notExists(basePath);

        if (!exist && !notExist) {
            throw new IncorrectPathException("Нет доступа к файлу");
        }
        if (Files.isDirectory(basePath)) {
            throw new IncorrectPathException("Вы ввели путь к директории");
        }
        if (exist && Files.isRegularFile(basePath)) {
            boolean canRead = Files.isReadable(basePath);
            boolean canWrite = Files.isWritable(basePath);
            if (canRead && canWrite) {
                return basePath;
            } else {
                throw new IncorrectPathException("Нет доступа к файлу");
            }
        }
        if (notExist) {
            try {
                if (basePath.getParent() != null) {
                    Files.createDirectories(basePath.getParent());
                }
                Files.createFile(basePath);
            } catch (IOException e) {
                throw new IncorrectPathException("Файл не был создан");
            }
        }
        return basePath;
    }

    public void pushPath(String key, String path) throws IncorrectPathException {
        pathMap.put(key, getAvailablePath(path));
    }

    public Path getPath(String key) throws KeyNotFoundException {
        Path p = pathMap.get(key);
        if (p == null) {
            throw new KeyNotFoundException("Ключ: " + key + " не был найден");
        }
        return p;
    }

    public void removePath(String key) {
        pathMap.remove(key);
    }
}
