package ch.hevs.ag.Threads;

import ch.hevs.ag.Model.Block;
import ch.hevs.ag.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PeerClient extends Thread{

    private final Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    public PeerClient ()
    {
        this.queue.add(12351);

    }

    public void run ()
    {
        while (true)
        {
            try {
                System.out.println(queue.peek());
                Socket socket = new Socket("192.168.17.40", queue.peek());
                System.out.println("Successful");
                queue.add(queue.poll());
                socket.setSoTimeout(5000);

                ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream()) ;
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()) ;
                LinkedList<Block> blockchain = BlockchainData.getInstance().getCurrentBlockChain();
                //send
                //Problem here

                objectOutput.writeObject(blockchain);

                //read
                LinkedList<Block> returnedBlockchain = (LinkedList<Block>) objectInput.readObject();
                //control
                BlockchainData.getInstance().getBlockchainConsensus(returnedBlockchain) ;
                Thread.sleep(2000);

            } catch (SocketTimeoutException e){
                System.out.println("Socket time out");
                queue.add(queue.poll());
            } catch (IOException e) {
                System.out.println("client error :" + e.getMessage() + " -- on port :" + queue.peek());
                queue.add(queue.poll());
            } catch (InterruptedException | ClassNotFoundException e) {
                e.printStackTrace();
                queue.add(queue.poll());
            }
        }
    }
}
