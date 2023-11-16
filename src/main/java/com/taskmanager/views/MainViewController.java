package com.taskmanager.views;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.operations.TaskManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> titleColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> dueDateColumn;
    @FXML
    private TableColumn<Task, String> statusColumn;

    private final TaskManager taskManager;

    public MainViewController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize TableView columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load and display tasks
        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        taskTable.setItems(FXCollections.observableList(tasks));
    }


    @FXML
    private void handleAddTask() {
        ViewFactory.showTaskEditView(new Task("", "", LocalDate.now(), TaskStatus.PENDING), task -> {
            taskManager.addTask(task);
            loadTasks();
        });
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            ViewFactory.showTaskEditView(selectedTask, task -> {
                taskManager.updateTask(selectedTask, task);
                loadTasks();
            });
        } else {
            showAlert("No Task Selected", "Please select a task to edit.");
        }
    }

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskManager.deleteTask(selectedTask);
            loadTasks();
        } else {
            showAlert("No Task Selected", "Please select a task to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Task Manager Application");
        alert.setContentText("This is a simple task management application.\nVersion: 1.0");
        alert.showAndWait();
    }
}
