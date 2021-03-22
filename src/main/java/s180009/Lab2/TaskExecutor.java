package s180009.Lab2;

import java.util.ArrayList;

public class TaskExecutor implements Runnable {

    private TaskBoard taskBoard;
    private Object controlObject;
    private static ExportData exportData = new ExportData();

    public TaskExecutor(TaskBoard taskBoard, Object controlObject) {
        this.taskBoard = taskBoard;
        this.controlObject = controlObject;
        exportData.cleanFile();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Task task = taskBoard.take();
                ArrayList<Long> list = new ArrayList<>();

                for (long i = task.getValue() - 1; i > 0; --i) {
                    if (task.getValue() % i == 0)
                        list.add(i);
                }

                Thread.sleep(5500);
                exportData.write(task, list);
                System.out.println(Thread.currentThread().getName() + ": " + task + ", divisors: " + list);
                synchronized (controlObject) {
                    controlObject.notify();
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
