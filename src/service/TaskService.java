package service;

import exception.TaskNotFoundException;
import tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        taskMap = new HashMap<>();
        removedTasks = new ArrayList<>();
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int id) {
        Task removedTask = taskMap.remove(id);
        if (removedTask != null) {
            removedTasks.add(removedTask);
        } else {
            throw new TaskNotFoundException("Задача с указанным id не найдена!");
        }

        return removedTask;
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        Collection<Task> taskList = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.appearsIn(date)) {
                taskList.add(task);
            }
        }

        return taskList;
    }
}
