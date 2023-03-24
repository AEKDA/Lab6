package server.instruction;

import server.exception.KeyNotFoundException;
import server.io.JSONMovieLoaer;
import server.logic.FileManager;
import server.logic.Instruction;
import server.models.MovieCollection;

/**
 * Команда сохраняет всю коллекцию в файл
 */
public class SaveMovieInstruction implements Instruction {

    @Override
    public void execute(String[] args) {
        JSONMovieLoaer loader = new JSONMovieLoaer();
        try {
            loader.write(FileManager.get().getPath("Collection").toFile().getPath(),
                    MovieCollection.getInstance().getData().toArray());
        } catch (KeyNotFoundException e) {
            MovieCollection.getInstance().getPathToCollection();
            this.execute(args);
        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String about() {
        return "сохранить коллекцию в файл";
    }
}
