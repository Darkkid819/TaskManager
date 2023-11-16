package com.taskmanager.operations;


import com.taskmanager.events.EventBus;
import com.taskmanager.events.TaskEvent;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskList;
import com.taskmanager.model.TaskStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TaskManager {
    private static final Logger logger = LogManager.getLogger(TaskManager.class);
    private final TaskList taskList;
    private final EventBus eventBus;

    public TaskManager() {
        this.taskList = new TaskList();
        this.eventBus = new EventBus();
        this.eventBus.subscribe(TaskEvent.class, this::onTaskEvent);
    }

    public void addTask(Task task) {
        taskList.addTask(task);
        eventBus.publish(new TaskEvent(TaskEvent.Type.TASK_ADDED, task));
    }

    public void updateTask(Task oldTask, Task newTask) {
        taskList.updateTask(oldTask, newTask);
        eventBus.publish(new TaskEvent(TaskEvent.Type.TASK_UPDATED, newTask));
    }

    public void deleteTask(Task task) {
        taskList.removeTask(task);
        eventBus.publish(new TaskEvent(TaskEvent.Type.TASK_DELETED, task));
    }

    public List<Task> getAllTasks() {
        return taskList.getTasks();
    }

    public List<Task> findTasks(TaskStatus status) {
        return taskList.findTasks(status);
    }

    public List<Task> findTasks(Predicate<Task> predicate) {
        return taskList.findTasks(predicate);
    }

    public <T> void subscribeToEvent(Class<T> eventType, Consumer<T> listener) {
        eventBus.subscribe(eventType, listener);
    }

    public <T> void unsubscribeFromEvent(Class<T> eventType, Consumer<T> listener) {
        eventBus.unsubscribe(eventType, listener);
    }

    public void onTaskEvent(TaskEvent event) {
        switch (event.type()) {
            case TASK_ADDED:
                // Log task addition using Log4j
                logger.info("Task added: " + event.task());
                break;
            case TASK_UPDATED:
                // Log task update using Log4j
                logger.info("Task updated: " + event.task());
                break;
            case TASK_DELETED:
                // Log task deletion using Log4j
                logger.info("Task deleted: " + event.task());
                break;
        }
    }
}

