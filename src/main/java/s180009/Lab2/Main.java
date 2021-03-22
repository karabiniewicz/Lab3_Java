package s180009.Lab2;

public class Main {
    private static int threadNumber;

    public static void main(String[] args) {
        if (args.length > 0 && !args[0].isEmpty()) {
            threadNumber = Integer.parseInt(args[0]);
        }

        TaskService taskService = new TaskService(threadNumber);

        System.out.println("Starting new session");
        try {
            taskService.generateTasks().join();
            System.out.println("Session ends");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
