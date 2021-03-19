package s180009.Lab2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Timeout implements Runnable {

    /**
     * Collection of running threads.
     */
    @Singular
    private List<Thread> threads;

    private Object controlObject;

    @Override
    public void run() {
        synchronized (controlObject) {
            try {
                System.out.println("Waiting for inputs:");
                controlObject.wait();
                System.out.println("Ending program");
            } catch (InterruptedException ex) {
            }
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}