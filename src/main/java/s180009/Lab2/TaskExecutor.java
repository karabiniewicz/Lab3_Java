package s180009.Lab2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskExecutor implements Runnable {

    private TaskBoard taskBoard;
    private TaskResults taskResults;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Task task = taskBoard.take();
                ArrayList<Integer> list = new ArrayList<>();

                for (int i = task.getValue() - 1; i > 0; --i) {
                    if (task.getValue() % i == 0)
                        list.add(i);
                }

                Thread.sleep(5000);
                taskResults.put(task, list);
                System.out.println(Thread.currentThread().getName() + ": " + task + ", divisors: " + list);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
