package com.taskmanager.views;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TaskEditViewController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private ComboBox<TaskStatus> statusComboBox;

    private Task task;
    private Consumer<Task> onSave;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dueDatePicker.setValue(LocalDate.now());
        statusComboBox.getItems().setAll(TaskStatus.values());
        statusComboBox.getItems().setAll(TaskStatus.values());
    }

    public void setTask(Task task, Consumer<Task> onSave) {
        this.task = task;
        this.onSave = onSave;

        if (task != null) {
            titleField.setText(task.getTitle());
            descriptionField.setText(task.getDescription());
            dueDatePicker.setValue(task.getDueDate());
            statusComboBox.setValue(task.getStatus());
        } else {
            dueDatePicker.setValue(LocalDate.now());
            statusComboBox.setValue(TaskStatus.PENDING);
        }
    }

    @FXML
    private void handleSave() {
        if (task == null) {
            task = new Task(titleField.getText(),
                    descriptionField.getText(),
                    dueDatePicker.getValue(),
                    statusComboBox.getValue());
        } else {
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            task.setDueDate(dueDatePicker.getValue());
            task.setStatus(statusComboBox.getValue());
        }

        onSave.accept(task);
        closeDialog();
    }

    @FXML
    private void handleCancel() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
