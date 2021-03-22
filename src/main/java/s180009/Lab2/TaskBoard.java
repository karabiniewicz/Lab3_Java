package s180009.Lab2;


import java.util.ArrayList;
import java.util.List;

public class TaskBoard {
    private List<Task> tasks = new ArrayList<>();

    public synchronized Task take() throws InterruptedException {
        while (tasks.isEmpty()){
            wait();
        }
        return tasks.remove(0);
    }

    public synchronized void put(List<Task> quests) {
        this.tasks.addAll(quests);
        notifyAll();
    }

    public synchronized Boolean isEmpty(){
        return this.tasks.isEmpty();
    }

}
