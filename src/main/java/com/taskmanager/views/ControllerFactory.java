package com.taskmanager.views;

import com.taskmanager.operations.TaskManager;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static final Logger logger = LogManager.getLogger(ControllerFactory.class);

    @Override
    public Object call(Class<?> type) {
        if (type == MainViewController.class) {
            MainViewController controller = new MainViewController(new TaskManager());
            return controller;
        } else {
            logger.warn("Unsupported controller type requested: " + type.getName());
            throw new IllegalArgumentException("Unsupported controller type: " + type.getName());
        }
    }
}
