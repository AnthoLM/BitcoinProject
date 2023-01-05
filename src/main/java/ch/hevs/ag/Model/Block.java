package ch.hevs.ag.Model;

import java.security.*;
import java.security.interfaces.RSAPublicKey;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Block {

    private byte[] prevHash;
    private byte[] currHash ;
    private String timeStamp ;
    private byte [] minedBy ;
    private Integer ledgerId = 1;
    //rewareded
    private Integer miningPoints = 0 ;
    //encryption
    private Double luck ;
    //payload
    private ArrayList<Transaction> transactionLedger = new ArrayList<Transaction>();

    //This constructor is used when we retrieve it from the db
    public Block(byte[] prevHash, byte[] currHash, String timeStamp, byte[] minedBy,Integer ledgerId,
                 Integer miningPoints, Double luck, ArrayList<Transaction> transactionLedger) {
        this.prevHash = prevHash;
        this.currHash = currHash;
        this.timeStamp = timeStamp;
        this.minedBy = minedBy;
        this.ledgerId = ledgerId;
        this.transactionLedger = transactionLedger;
        this.miningPoints = miningPoints;
        this.luck = luck;
    }
    //This constructor is used when we initiate it after retrieve.
    public Block(LinkedList<Block> currentBlockChain) {
        Block lastBlock = currentBlockChain.getLast();
        prevHash = lastBlock.getCurrHash();
        ledgerId = lastBlock.getLedgerId() + 1;
        luck = Math.random() * 1000000;
    }
    //This constructor is used only for creating the first block in the blockchain.
    public Block() {
        prevHash = new byte[]{0};
    }

    /**
     * Verify the signature and update it
     * @param signing The signature that will be verified
     * @return
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public Boolean isVerified(Signature signing) throws InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchAlgorithmException {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(this.minedBy);
        RSAPublicKey publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
        signing.initVerify(publicKey);
        //System.out.println("I'm here");
        signing.update(this.toString().getBytes());
        return signing.verify(this.currHash);
    }

    /**
     * compare 2 blocks
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return Arrays.equals(getPrevHash(), block.getPrevHash());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getPrevHash());
    }

    public byte[] getPrevHash() { return prevHash; }
    public byte[] getCurrHash() { return currHash; }

    public void setPrevHash(byte[] prevHash) { this.prevHash = prevHash; }
    public void setCurrHash(byte[] currHash) { this.currHash = currHash; }

    public ArrayList<Transaction> getTransactionLedger() { return transactionLedger; }
    public void setTransactionLedger(ArrayList<Transaction> transactionLedger) {
        this.transactionLedger = transactionLedger;
    }

    public String getTimeStamp() { return timeStamp; }
    public void setMinedBy(byte[] minedBy) { this.minedBy = minedBy; }

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }

    public byte[] getMinedBy() { return minedBy; }

    public Integer getMiningPoints() { return miningPoints; }
    public void setMiningPoints(Integer miningPoints) { this.miningPoints = miningPoints; }
    public Double getLuck() { return luck; }
    public void setLuck(Double luck) { this.luck = luck; }

    public Integer getLedgerId() { return ledgerId; }
    public void setLedgerId(Integer ledgerId) { this.ledgerId = ledgerId; }

    @Override
    public String toString() {
        return "Block{" +
                "prevHash=" + Arrays.toString(prevHash) +
                ", timeStamp='" + timeStamp + '\'' +
                ", minedBy=" + Arrays.toString(minedBy) +
                ", ledgerId=" + ledgerId +
                ", miningPoints=" + miningPoints +
                ", luck=" + luck +
                '}';
    }
}
