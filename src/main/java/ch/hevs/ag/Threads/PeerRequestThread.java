package ch.hevs.ag.Threads;

import ch.hevs.ag.Model.Block;
import ch.hevs.ag.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class PeerRequestThread extends Thread{

    private Socket socket ;

    public PeerRequestThread(Socket socket)
    {
        this.socket = socket ;
    }

    @Override
    public void run ()
    {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()) ;

            LinkedList<Block> recievedBC = (LinkedList<Block>) objectInput.readObject();
            System.out.println("LedgerId = " + recievedBC.getLast().getLedgerId());

            objectOutput.writeObject(BlockchainData.getInstance().getBlockchainConsensus(recievedBC));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
