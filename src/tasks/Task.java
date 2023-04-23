package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    private String title;
    private String description;
    private int id;
    private Type type;
    private static int idGenerator = 0;
    protected LocalDateTime dateTime;

    public Task(String title,
                String description,
                Type type,
                LocalDateTime dateTime) {
        this.title = title;
        this.description = description;
        this.id = ++idGenerator;
        this.type = type;
        this.dateTime = dateTime;
        setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IncorrectArgumentException("title");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IncorrectArgumentException("description");
        }
        this.description = description;
    }
    public abstract boolean appearsIn (LocalDate date);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "tasks.Task{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
    public enum Type {
        WORK,
        PERSONAL
    }
}
