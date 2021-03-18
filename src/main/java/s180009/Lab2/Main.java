package s180009.Lab2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int threadNumber = Integer.parseInt(args[0]);

        List<Integer> numbers = new ArrayList<>();

        TaskService taskService = new TaskService();

        new ImportData(threadNumber).read(numbers);

        new ExportData(threadNumber).write(numbers);
    }
}
