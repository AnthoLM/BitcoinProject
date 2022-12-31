package ch.hevs.ag.Threads;

import ch.hevs.ag.Model.Block;
import ch.hevs.ag.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class PeerClient extends Thread{

    public PeerClient ()
    {

    }

    public void run ()
    {
        while (true)
        {
            try {
                Socket socket = new Socket("127.0.0.1", 6000);

                ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream()) ;
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()) ;

                LinkedList<Block> blockchain = BlockchainData.getInstance().getCurrentBlockChain();
                //send
                objectOutput.writeObject(blockchain);
                //read
                LinkedList<Block> returnedBlockchain = (LinkedList<Block>) objectInput.readObject();
                //control
                BlockchainData.getInstance().getBlockchainConsensus(returnedBlockchain) ;

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
