package ch.hevs.ag;

import ch.hevs.ag.Threads.MiningThread;
import ch.hevs.ag.Threads.PeerClient;
import ch.hevs.ag.Threads.PeerServer;
import ch.hevs.ag.Threads.UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        new UI().start(stage);
        new PeerClient().start();
        new PeerServer(6000).start();
        new MiningThread().start();

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init()
    {
        try
        {
            Connection blockchainConnection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            PreparedStatement pstmt = blockchainConnection.prepareStatement("INSERT INTO BLOCKCHAIN(PREVIOUS_HASH, CURRENT_HASH,LEDGER_ID" +
                    "CREATED_ON, CREATED_BY, MINING_POINTS, LUCK)" +
                    "VALUES(?,?,?,?,?,?,?)"); // create statements
            pstmt.setBytes(1, );
            pstmt.setBytes(2, );
            pstmt.setInt(3, );
            Statement blockchainStatement = blockchainConnection.createStatement(); // create statement using this connection to database
            ResultSet resultSet = blockchainStatement.executeQuery("SELECT * FROM BLOCKCHAIN"); // execute SQL command
            if(resultSet.next())
            {
                String firstColumn = resultSet.getString(1); // get content of specfic column
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}