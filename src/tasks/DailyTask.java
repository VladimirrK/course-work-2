package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String title, Task.Type type, LocalDateTime dateTime, String description) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().isBefore(date) || dateTime.toLocalDate().equals(date);
    }
}
