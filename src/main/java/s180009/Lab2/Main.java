package s180009.Lab2;

import java.util.logging.Level;

public class Main {
    private static int threadNumber;

    public static void main(String[] args) {
        if (args.length > 0 && !args[0].isEmpty()) {
            threadNumber = Integer.parseInt(args[0]);
        }
        TaskResults taskResults = new TaskResults();

        TaskService taskService = new TaskService(taskResults, threadNumber);

        System.out.println("Starting new session");
        try {
            taskService.generateTasks().join();
            System.out.println("Session ends");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        new ExportData().write(taskResults);

    }
}
