package server.serverInstruction;

import server.exception.KeyNotFoundException;
import server.io.JSONMovieLoaer;
import server.logic.FileManager;
import server.logic.ServerInstruction;
import server.logic.MovieCollection;

import java.util.logging.Logger;

import core.clientInstruction.*;
import core.logic.InstructionInfo;

/**
 * Команда сохраняет всю коллекцию в файл
 */
public class SaveMovieInstruction implements ServerInstruction {
    private Logger logger;

    {
        logger = Logger.getLogger(SaveMovieInstruction.class.getName());
    }

    @Override
    public ClientInstruction execute(InstructionInfo info) {
        JSONMovieLoaer loader = new JSONMovieLoaer();
        try {
            loader.write(FileManager.get().getPath("Collection").toFile().getPath(),
                    MovieCollection.getInstance().getData().toArray());
        } catch (KeyNotFoundException e) {
            // MovieCollection.getInstance().getPathToCollection();
            logger.info("Коллекция не может быть сохранена");
        }
        return new PrintInstruction("Коллекция сохранена");
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String about() {
        return "сохранить коллекцию в файл";
    }

    public boolean isSpecial() {
        return true;
    }

    public boolean needElement() {
        return false;
    }
}
