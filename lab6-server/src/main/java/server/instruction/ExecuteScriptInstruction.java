package server.instruction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.EmptyStackException;
import java.util.Stack;

import client.logic.InstructionListener;
import server.io.BaseReader;
import server.logic.Instruction;

/**
 * Комманда исполняет скрипт, который был передан ей
 */
public class ExecuteScriptInstruction implements Instruction {
    private InstructionListener instructionListener;
    Stack<Path> recursiveStack = new Stack<>();

    public ExecuteScriptInstruction(InstructionListener instructionListener) {
        this.instructionListener = instructionListener;
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error! The arguments are not correct");
        }
        try {
            Path path;
            try {
                if (!Paths.get(args[1]).isAbsolute()) {
                    path = Paths.get(recursiveStack.peek().normalize().getParent().toString(), args[1]);
                } else {
                    path = Paths.get(args[1]);
                }
            } catch (EmptyStackException e) {
                path = Paths.get(args[1]);
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
        for (Path string : this.recursiveStack) {
            if (Files.isSameFile(path, string)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String about() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
