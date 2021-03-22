package s180009.Lab2;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private int threadNumber;

    public TaskService(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public Thread generateTasks() {

        TaskBoard taskBoard = new TaskBoard();
        List<Thread> threads = new ArrayList<>();
        Object controlObject = new Object();
        Object controlObject2 = new Object();

        TaskExecutor taskExecutor = new TaskExecutor(taskBoard, controlObject2);

        new ImportData(threadNumber).read(taskBoard);
        for (int i = 0; i < threadNumber; ++i) {
            threads.add(new Thread(taskExecutor));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        Thread inputThread = new Thread(new Thread(new InputExecutor(taskBoard, controlObject)));
        inputThread.start();

        Timeout timeout = Timeout.builder()
                .threads(threads)
                .controlObject(controlObject)
                .controlObject2(controlObject2)
                .inputThread(inputThread)
                .build();

        Thread timeoutThread = new Thread(timeout);
        timeoutThread.start();

        return timeoutThread;
    }
}
