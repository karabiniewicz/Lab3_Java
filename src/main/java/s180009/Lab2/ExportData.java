package s180009.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ExportData {
    /**
     * Service for exporting data.
     *
     * @param task
     * @param list
     */
    public synchronized void write(Task task, ArrayList<Long> list) throws IllegalStateException {
        try (Writer fw = new FileWriter("src/main/resources/output.txt", true)) {
            fw.write(formOfOutput(task, list));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public synchronized void cleanFile() throws IllegalStateException {
        try (Writer fw = new FileWriter("src/main/resources/output.txt", false)) {
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private String formOfOutput(Task task, ArrayList<Long> list) {
        StringBuilder output = new StringBuilder(task.toString());
        output.append(": ");
        for (Long element : list) {
            output.append(element.toString());
            output.append(", ");
        }
        return output.toString() + '\n';
    }
}
