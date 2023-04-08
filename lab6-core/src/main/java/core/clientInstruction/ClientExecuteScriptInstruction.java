package core.clientInstruction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.EmptyStackException;
import java.util.Stack;

import core.logic.Client;
import core.logic.InstructionListener;
import core.io.BaseReader;

/**
 * Комманда исполняет скрипт, который был передан ей
 */
public class ClientExecuteScriptInstruction extends ClientInstruction {
    private static Stack<Path> recursiveStack = new Stack<>();
    private String[] args;

    public ClientExecuteScriptInstruction(String[] args) {
        this.args = args;
    }

    @Override
    protected void implement(Client client) throws IllegalArgumentException {
        InstructionListener instructionListener = client.getListener();
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Error! The arguments are not correct");
        }
        try {
            Path path;
            try {
                if (!Paths.get(args[0]).isAbsolute()) {
                    path = Paths.get(recursiveStack.peek().normalize().getParent().toString(), args[0]);
                } else {
                    path = Paths.get(args[0]);
                }
            } catch (EmptyStackException e) {
                path = Paths.get(args[0]);
            }
            boolean exist = Files.exists(path);
            boolean notExist = Files.notExists(path);
            if (!exist && !notExist) {
                throw new IllegalArgumentException("Нет доступа к файлу");
            }
            if (Files.isDirectory(path)) {
                throw new IllegalArgumentException("Вы ввели путь к директории");
            }
            if (!pathCheck(path)) {
                return;
            }
            if (!exist) {
                throw new IllegalArgumentException("Error! File not Found");
            }
            if (exist && Files.isReadable(path)) {
                String script;
                BaseReader br = new BaseReader(path.toString());
                script = br.read();
                recursiveStack.push(path.toAbsolutePath());
                instructionListener.start(new ByteArrayInputStream(script.getBytes(StandardCharsets.UTF_8)));
                recursiveStack.pop();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error! File not Found");
        }
    }

    public boolean pathCheck(Path path) throws IOException {
        for (Path string : recursiveStack) {
            if (Files.isSameFile(path, string)) {
                return false;
            }
        }
        return true;
    }
}
