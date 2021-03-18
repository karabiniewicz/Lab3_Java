package s180009.Lab2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ExportData {
    /**
     * Service for exporting data.
     */
    private int threadNumber;
    public ExportData(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void write(List<Integer> numbers)throws IllegalStateException {
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("src/main/resources/output.txt"))) {
            for (Integer number : numbers) {
                bw.write(number.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
