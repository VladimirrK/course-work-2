package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, Task.Type type,  LocalDateTime dateTime, String description) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().equals(date);
    }
}
