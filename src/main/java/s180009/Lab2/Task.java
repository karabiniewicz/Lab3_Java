package s180009.Lab2;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
public class Task implements Comparable<Task>{
    private int value;

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.value, o.value);
    }
}