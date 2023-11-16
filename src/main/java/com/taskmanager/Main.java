package com.taskmanager;

import com.taskmanager.views.ViewFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory.showMainView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
