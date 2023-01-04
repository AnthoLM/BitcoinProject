module com.example.demo {
    exports ch.hevs.ag.Controller;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.base;

    opens ch.hevs.ag.Controller to javafx.fxml;
    opens ch.hevs.ag.Model to javafx.base;

    opens ch.hevs.ag to javafx.fxml;
    exports ch.hevs.ag.Threads to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
    exports ch.hevs.ag;
}