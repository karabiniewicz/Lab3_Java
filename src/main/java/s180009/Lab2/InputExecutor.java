package s180009.Lab2;

import java.util.List;
import java.util.Scanner;

public class InputExecutor implements Runnable {

    private final Object controlObject;
    private final TaskBoard taskBoard;

    InputExecutor(TaskBoard taskBoard, Object controlObject){
        this.taskBoard = taskBoard;
        this.controlObject = controlObject;
    }

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        String s1 = "";
        while (!Thread.interrupted()) {
            if (!s1.equals("exit") && s.hasNext()) {
                s1 = s.next();
                int newNumber = 0;
                try {
                    newNumber = Integer.parseInt(s1);
                } catch (NumberFormatException ex) {
                    if (s1.equals("exit")) {
                        System.out.println("You try to exit the program!");
                        while (!taskBoard.getTasks().isEmpty()){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        synchronized (controlObject) {
                            controlObject.notify();
                        }
                        newNumber = 0;
                    }
                }
                taskBoard.put(List.of(Task.builder().value(newNumber).build()));
                System.out.println("Received: " + newNumber);
            }
        }
        s.close();
    }
}
