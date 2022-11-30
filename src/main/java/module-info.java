module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens ch.hevs.ag to javafx.fxml;
    exports ch.hevs.ag;
}