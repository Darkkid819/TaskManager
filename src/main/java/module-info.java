module com.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens com.taskmanager.views to javafx.fxml;
    opens com.taskmanager.model to javafx.base;
    exports com.taskmanager;
}