package ch.hevs.ag.ServiceData;

import ch.hevs.ag.Model.Wallet;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.*;

public class WalletData {

    // keep everything that we have in the wallet, get public, private key from database
    private Wallet wallet;
    private static WalletData instance;

    static {
        instance = new WalletData(); // at the loading of this class, creates one object of WalletData at first
    }

    public static WalletData getInstance()
    {
        return instance; // get this object
    }

    public void loadWallet() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException // get the wallet from the database
    {
        // getting the data from the database
        // need a connection and call the driverManger to access the database
        // need have throws exception
        Connection walletConnection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement walletStatement = walletConnection.createStatement();
        ResultSet resultSet;
        resultSet = walletStatement.executeQuery("SELECT * FROM WALLET");
        // also need exception
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey pub2 = null;
        PrivateKey prv2 = null;
        while(resultSet.next())// go through the database
        {
            pub2 = keyFactory.generatePublic(new X509EncodedKeySpec(resultSet.getBytes("PUBLIC_KEY")));
            prv2 = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(resultSet.getBytes("PRIVATE_KEY")));
        }
        this.wallet = new Wallet(pub2, prv2);
    }

    public Wallet getWallet()
    {
        return wallet;
    }

}
