module com.example.lapss {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.lapss to javafx.fxml;
    exports com.example.lapss;
    exports com.example.lapss.connect;
    opens com.example.lapss.connect to javafx.fxml;
    exports com.example.lapss.objects;
    opens com.example.lapss.objects to javafx.fxml;
}