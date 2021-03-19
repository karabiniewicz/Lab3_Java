package s180009.Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportData {
    /**
     * Service for importing data.
     */
    private int threadNumber;
    private List<Task> tasks = new ArrayList<>();

    public ImportData(int threadNumber) throws IllegalStateException {
        this.threadNumber = threadNumber;
    }

    public void read(TaskBoard taskBoard) throws IllegalStateException {
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/import.txt"))) {
            String number;
            while ((number = br.readLine()) != null && threadNumber != 0) {
                tasks.add(Task.builder().value((Integer.parseInt(number))).build());
                threadNumber--;
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }finally {
            taskBoard.put(tasks);
        }
    }
}