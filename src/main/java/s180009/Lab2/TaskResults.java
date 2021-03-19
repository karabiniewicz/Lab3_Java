package s180009.Lab2;

import lombok.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@NoArgsConstructor
@ToString
public class TaskResults {

    private final Map<Task, ArrayList> map = new TreeMap<>();

    public synchronized void put(Task task, ArrayList list) {
        map.put(task, list);
    }
}
