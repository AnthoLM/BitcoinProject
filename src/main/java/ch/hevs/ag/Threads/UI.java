package ch.hevs.ag.Threads;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null ;
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/MainWindow.fxml"));
        fxmlLoader.setRoot(new BorderPane());
        root = fxmlLoader.load();
         */

        try{
            root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml")) ;
        }catch (IOException e){
            e.printStackTrace();
        }
        stage.setTitle("Weebz");
        stage.setScene(new Scene(root, 900, 700));
        stage.show();
    }
}
