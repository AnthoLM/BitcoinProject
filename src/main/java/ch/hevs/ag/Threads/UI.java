package ch.hevs.ag.Threads;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UI extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = null ;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            System.out.println("I'm here");
        }catch (IOException e){
            e.printStackTrace();
        }
        stage.setTitle("Weebz");
        stage.setScene(new Scene(root, 900, 700));
        stage.show();
    }
}
