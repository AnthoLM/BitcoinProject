package ch.hevs.ag;

import ch.hevs.ag.Model.Wallet;
import ch.hevs.ag.Threads.MiningThread;
import ch.hevs.ag.Threads.PeerClient;
import ch.hevs.ag.Threads.PeerServer;
import ch.hevs.ag.Threads.UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
        try {
            //This creates your wallet if there is none and gives you a KeyPair.
            //We will create it in separate db for better security and ease of portability.
            Connection walletConnection = DriverManager
                    .getConnection("jdbc:sqlite:db\\wallet.db");
            Statement walletStatment = walletConnection.createStatement();

            ResultSet resultSet = walletStatment.executeQuery(" SELECT * FROM WALLET ");
            if (!resultSet.next()) {
                Wallet newWallet = new Wallet();
                byte[] pubBlob = newWallet.getPublicKey().getEncoded();
                byte[] prvBlob = newWallet.getPrivateKey().getEncoded();
                PreparedStatement pstmt = walletConnection
                        .prepareStatement("INSERT INTO WALLET(PRIVATE_KEY, PUBLIC_KEY) " +
                                " VALUES (?,?) ");
                pstmt.setBytes(1, prvBlob);
                pstmt.setBytes(2, pubBlob);
                pstmt.executeUpdate();
            }
            resultSet.close();
            walletStatment.close();
            walletConnection.close();


            Connection blockchainConnection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\Users\\antoine.widmer\\Desktop\\Blockchain\\git\\testHESCoin\\testHESCoin\\db\\blockchain.db");
            Statement blockchainStmt = blockchainConnection.createStatement();
            ResultSet resultSetBlockchain = blockchainStmt.executeQuery(" SELECT * FROM BLOCKCHAIN ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}