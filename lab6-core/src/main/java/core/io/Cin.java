package server.io;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Класс, содержащий {@link lab5.java.util.Scanner} и {@link lab6.io.Cin.Type},
 * реализующий
 * очередь, с помощью которой можно получать последний созданный экземпляр
 */
public class Cin {
    private Type type;
    private Scanner scanner;

    /**
     * @param is создает сканнер с этим источником и устанавливает в зависимости от
     *           источника {@link lab6.io.Cin.Type}
     */
    public Cin(InputStream is) {
        if (is == System.in) {
            type = Type.STD;
        } else {
            type = Type.FILE;
        }
        scanner = new Scanner(is);
    }

    /**
     * Возвращает сканнер
     * 
     * @return сканнер этого экземпляра
     */
    public Scanner getScanner() {
        return scanner;
    }

    public String nextLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            if (type == Type.STD) {
                scanner = new Scanner(System.in);
            }
            return "\n";
        }
    }

    /**
     * Возвращает тип источника
     * 
     * @return тип источника
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Тип источника
     */
    public static enum Type {
        FILE, STD
    }

    /**
     * Добавляет указзный Cin в стек
     * 
     * @param cin
     */
    public static void push(Cin cin) {
        cinStack.push(cin);
    }

    /**
     * Возвращает последний объект типа {@link lab6.io.Cin} находящий в стеке, но не
     * удаляет
     * его из стека
     */
    public static Cin peek() {
        return cinStack.peek();
    }

    /**
     * Возвращает последний объект типа {@link lab6.io.Cin} находящий в стеке и
     * удаляет его
     * его из стека
     */
    public static Cin pop() {
        return cinStack.pop();
    }

    private static Stack<Cin> cinStack = new Stack<>();
}
