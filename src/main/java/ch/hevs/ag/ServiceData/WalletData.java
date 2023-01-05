package ch.hevs.ag.ServiceData;

import ch.hevs.ag.Model.Wallet;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.sql.*;

public class WalletData {

    private Wallet wallet ;
    private  static final WalletData instance ;
    //in the creation of the class, it creates an object instance
    //No need to call this method (it is also impossible to call it)
    static
    {
        instance = new WalletData();
    }

    public static WalletData getInstance(){return instance;}

    public void loadWallet() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {

        //get the data from the DB
        Connection walletConnection = DriverManager.getConnection("jdbc:sqlite:DB\\Wallet.sqlite");
        Statement walletStatement = walletConnection.createStatement();
        ResultSet resultSet;
        resultSet = walletStatement.executeQuery("SELECT * FROM WALLET");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA") ;

        PublicKey publicKey = null ;
        PrivateKey privateKey = null ;

        while(resultSet.next()){
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(resultSet.getBytes("PUBLIC_KEY")));
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(resultSet.getBytes("PRIVATE_KEY")));
        }
        this.wallet = new Wallet(publicKey, privateKey) ;
    }
    public Wallet getWallet() {
        return wallet;
    }
}
