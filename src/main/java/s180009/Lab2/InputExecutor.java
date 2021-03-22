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
        String input = "";
        while (!Thread.interrupted()) {
            if (!input.equals("exit") && s.hasNext()) {
                input = s.next();
                int newNumber = 0;
                try {
                    newNumber = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    if (input.equals("exit")) {
                        System.out.println("You try to exit the program!");
                        while (!taskBoard.isEmpty()){
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
                if (newNumber != 0) {
                    taskBoard.put(List.of(Task.builder().value(newNumber).build()));
                    System.out.println("Received: " + newNumber);
                }
            }
        }
    }
}
