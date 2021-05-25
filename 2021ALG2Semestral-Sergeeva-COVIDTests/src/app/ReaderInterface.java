package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Viktoriia Sergeeva
 */
public interface ReaderInterface {
    List<String[]> readFile(File file, String separator, boolean header) throws FileNotFoundException, IOException;
}