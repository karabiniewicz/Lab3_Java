package s180009.Lab2;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private int threadNumber;

    private TaskResults taskResults;

    public TaskService(TaskResults taskResults, int threadNumber ) {
        this.threadNumber = threadNumber;
        this.taskResults = taskResults;
    }

    public Thread generateTasks() {

        TaskBoard taskBoard = new TaskBoard();
        List<Thread> threads = new ArrayList<>();
        Object controlObject = new Object();
        TaskExecutor taskExecutor = TaskExecutor.builder().taskBoard(taskBoard).taskResults(taskResults).build();

        new ImportData(threadNumber).read(taskBoard);
        for (int i = 0; i < threadNumber; ++i) {
            threads.add(new Thread(taskExecutor));
        }

        InputExecutor inputExecutor = new InputExecutor(taskBoard, controlObject);
        threads.add(new Thread(inputExecutor));

        Timeout timeout = Timeout.builder()
                .threads(threads)
                .controlObject(controlObject)
                .build();

        Thread timeoutThread = new Thread(timeout);
        timeoutThread.start();

        for (Thread thread : threads) {
            thread.start();
        }

        return timeoutThread;
    }
}
