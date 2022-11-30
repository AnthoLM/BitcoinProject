package Model;

import java.security.*;

public class Wallet {

    private KeyPair keyPair ;

    public Wallet() throws NoSuchAlgorithmException{
        this(2048, KeyPairGenerator.getInstance("DSA")) ;
    }
    public Wallet(Integer keySize, KeyPairGenerator keyPairGenerator){
        keyPairGenerator.initialize(keySize);
        this.keyPair = keyPairGenerator.generateKeyPair() ;
    }
}
