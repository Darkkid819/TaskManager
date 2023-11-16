package com.taskmanager.operations;

import com.taskmanager.model.TaskList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class DataPersistence {

    private static final Logger logger = LogManager.getLogger(DataPersistence.class);
    private final String filePath;

    public DataPersistence(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList taskList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(taskList);
            logger.info("Tasks successfully saved to file.");
        } catch (IOException e) {
            logger.error("Error saving tasks to file: ", e);
            handleSaveError(e);
        }
    }

    public TaskList loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            logger.info("Tasks successfully loaded from file.");
            return (TaskList) in.readObject();
        } catch (FileNotFoundException e) {
            logger.warn("No existing task list found. A new one will be created.", e);
            return new TaskList();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error loading tasks from file: ", e);
            handleLoadError(e);
            return new TaskList();
        }
    }

    private void handleSaveError(IOException e) {
        // Implement the logic to handle save errors
        // This could include logging the error, notifying a monitoring service,
        // or providing feedback to the user (e.g., through a dialog in the UI)
    }

    private void handleLoadError(Exception e) {
        // Implement the logic to handle load errors
        // Similar to handleSaveError, but for errors occurring during loading
    }
}