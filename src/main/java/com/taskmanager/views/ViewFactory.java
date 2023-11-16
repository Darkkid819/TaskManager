package com.taskmanager.views;

import com.taskmanager.model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.function.Consumer;

public class ViewFactory {

    private static final Logger logger = LogManager.getLogger(ViewFactory.class);

    public static void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(ViewFactory.class.getResource("MainView.fxml"));
            ControllerFactory controllerFactory = new ControllerFactory();
            loader.setControllerFactory(controllerFactory::call);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Task Manager");
            stage.show();
        } catch (IOException e) {
            logger.error("Error showing Main View", e);
            showErrorDialog("Error", "Cannot Load View", "Unable to show the Main View.");
        }
    }

    public static void showTaskEditView(Task task, Consumer<Task> onSave) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewFactory.class.getResource("TaskEditView.fxml"));
            Parent root = loader.load();

            TaskEditViewController controller = loader.getController();
            controller.setTask(task, onSave);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            logger.error("Error showing Task Edit View", e);
            showErrorDialog("Error", "Cannot Load View", "Unable to show the Task Edit View.");
        }
    }

    private static void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
