package s180009.Lab2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportData {
    /**
     * Service for exporting data.
     */
    public void write(TaskResults taskResults)throws IllegalStateException {
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("src/main/resources/output.txt"))) {
            for (Task task : taskResults.getMap().keySet()) {
                System.out.println(task + ": " +taskResults.getMap().get(task));
//                bw.write(number.toString());
//                bw.newLine();
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}