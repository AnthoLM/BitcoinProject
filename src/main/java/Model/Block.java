package Model;
import java.util.ArrayList;

public class Block {

    private byte[] previousHash;
    private byte[] currentHash ;
    private String timeStamp ;
    private byte [] mineBy ;
    private Integer ledgerId = 1;
    //rewareded
    private Integer miningPoint = 0 ;
    //encryption
    private Double luck ;
    //payload
    private ArrayList<Transaction> transactionLedge = new ArrayList<Transaction>();
}
