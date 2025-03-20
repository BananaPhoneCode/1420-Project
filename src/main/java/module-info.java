module com.example._1420project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.xmlbeans;
    requires jdk.jfr;
    requires org.apache.logging.log4j;

    opens com.example._1420project to javafx.fxml;
    exports com.example._1420project;
}