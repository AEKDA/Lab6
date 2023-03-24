package server.io;

/**
 * Класс абстрагирующий вывод в консоль
 */
public class Logger {

    private Logger() {
    }

    private static Logger log = null;

    /**
     * @return Возвращает единственный экземпляр класса
     */
    public static Logger get() {
        if (log == null) {
            log = new Logger();
        }
        return log;
    }

    /**
     * Выводит строку в консоль с переходом на новую строчку
     * 
     * @param s та строка, которая будет вывведена на экзан
     */
    public void writeLine(String s) {
        System.out.println(s);
    }

    /**
     * Выводит строку в консоль без перехода на новую строчку
     * 
     * @param s та строка, которая будет вывведена на экзан
     */
    public void write(String s) {
        System.out.printf("%s", s);
    }

    /**
     * Выводит строку в консоль с заданым форматом вывода
     * 
     * @param args те элементы, которые будут выведены на экзан в соответсвие с
     *          паттерном
     */
    public void write(String format, Object... args) {
        System.out.printf(format, args);
    }
}
