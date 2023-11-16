package com.taskmanager.events;


import com.taskmanager.model.Task;

public record TaskEvent(Type type, Task task) {
    public enum Type {
        TASK_ADDED,
        TASK_UPDATED,
        TASK_DELETED
    }

}