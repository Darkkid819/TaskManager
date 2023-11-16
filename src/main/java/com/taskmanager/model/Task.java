package com.taskmanager.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Task {
    private final StringProperty title;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> dueDate;
    private final ObjectProperty<TaskStatus> status;

    public Task(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.status = new SimpleObjectProperty<>(status);
    }

    // Getter methods for JavaFX properties
    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public TaskStatus getStatus() {
        return status.get();
    }

    // Setter methods for JavaFX properties
    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public void setStatus(TaskStatus status) {
        this.status.set(status);
    }

    // Property getters for JavaFX binding
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public ObjectProperty<TaskStatus> statusProperty() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title.get() + '\'' +
                ", description='" + description.get() + '\'' +
                ", dueDate=" + dueDate.get() +
                ", status=" + status.get() +
                '}';
    }
}