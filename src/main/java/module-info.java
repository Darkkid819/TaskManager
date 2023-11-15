module com.taskmanager.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.taskmanager.taskmanager to javafx.fxml;
    exports com.taskmanager.taskmanager;
}