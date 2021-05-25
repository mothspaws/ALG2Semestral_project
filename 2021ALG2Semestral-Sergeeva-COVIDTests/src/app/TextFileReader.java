package app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktoriia Sergeeva
 */
public class TextFileReader implements ReaderInterface {

    @Override
    public List<String[]> readFile(File file, String separator, boolean header) throws FileNotFoundException, IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            if (header) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(separator);
                data.add(parts);
            }
        }
        return data;
    }
}
