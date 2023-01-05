package ch.hevs.ag.Model;

import java.security.*;

public class Wallet {

    private KeyPair keyPair ;

    public Wallet() throws NoSuchAlgorithmException{
        this(2048, KeyPairGenerator.getInstance("RSA")) ;
    }
    public Wallet(Integer keySize, KeyPairGenerator keyPairGenerator){
        keyPairGenerator.initialize(keySize);
        this.keyPair = keyPairGenerator.generateKeyPair() ;
    }

    //Constructor for importing Keys only
    public Wallet(PublicKey publicKey, PrivateKey privateKey) {
        this.keyPair = new KeyPair(publicKey,privateKey);
    }

    public KeyPair getKeyPair() { return keyPair; }

    public PublicKey getPublicKey() { return keyPair.getPublic(); }
    public PrivateKey getPrivateKey() { return keyPair.getPrivate(); }
}
