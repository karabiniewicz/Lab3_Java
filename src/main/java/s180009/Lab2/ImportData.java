package s180009.Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ImportData {
    /**
     * Service for importing data.
     */
    private int threadNumber;

    public ImportData(int threadNumber) throws IllegalStateException {
        this.threadNumber = threadNumber;
    }

    public void read(List<Integer> numbers) throws IllegalStateException {
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/import.txt"))) {
            String number;
            while ((number = br.readLine()) != null && threadNumber != 0) {
                numbers.add(Integer.parseInt(number));
                threadNumber--;
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}