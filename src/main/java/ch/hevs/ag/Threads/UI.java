package ch.hevs.ag.Threads;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UI extends Application {

    @Override
    public void start(Stage stage) {
        Parent root = null ;
        try{
            System.out.println(getClass().getResource("/ch/hevs/ag/hello-view.fxml"));
            root = FXMLLoader.load(getClass().getResource("/ch/hevs/ag/hello-view.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        stage.setTitle("Weebz");
        stage.setScene(new Scene(root, 900, 700));
        stage.show();
    }
}
