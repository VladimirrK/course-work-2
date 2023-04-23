package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return (dateTime.toLocalDate().isBefore(date) || dateTime.toLocalDate().equals(date))
                && dateTime.getDayOfWeek().equals(date.getDayOfWeek());
    }
}
