package ch.hevs.ag;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*; // allow to have connection

public class HESCoin extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new UI().start(stage);
        new PeerClient().start();
        new PeerServer(6000).start(); // port number
        new MiningThread().start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void init()
    {
        try
        {
            Connection blockchainConnection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            PreparedStatement pstmt = blockchainConnection.prepareStatement("INSERT INTO BLOCKCHAIN(PREVIOUS_HASH, CURRENT_HASH,LEDGER_ID" +
                    " CREATED_ON, CRETED_BY, MINING_POINTS, LUCK)" +
                    "VALUES(?,?,?,?,?,?,?)"); // create statements
            pstmt.setBytes(1, );
            pstmt.setBytes(2, );
            pstmt.setInt(3, );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
