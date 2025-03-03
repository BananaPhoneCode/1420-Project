module com.example._1420project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example._1420project to javafx.fxml;
    exports com.example._1420project;
}