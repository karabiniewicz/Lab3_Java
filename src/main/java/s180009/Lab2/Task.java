package s180009.Lab2;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Task{
    private long value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}