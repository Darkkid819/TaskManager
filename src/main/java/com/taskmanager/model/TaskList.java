package com.taskmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void updateTask(Task oldTask, Task newTask) {
        int index = tasks.indexOf(oldTask);
        if (index != -1) {
            tasks.set(index, newTask);
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> findTasks(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> findTasks(Predicate<Task> predicate) {
        return tasks.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}

