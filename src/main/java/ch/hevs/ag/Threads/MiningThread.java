package ch.hevs.ag.Threads;

import ch.hevs.ag.ServiceData.BlockchainData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MiningThread extends Thread{

    public void run(){
        while(true)
        {
            long lastMinedBlock = LocalDateTime.parse(BlockchainData.getInstance().getCurrentBlockChain().getLast().getTimeStamp()).toEpochSecond(ZoneOffset.UTC);

            if(lastMinedBlock + BlockchainData.getTimeoutInterval()< LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
            {
                System.out.println("Too old");
            }
            else if (lastMinedBlock + BlockchainData.getMiningInterval() - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)>0)
            {
                System.out.println("Current blockchain");
            }
            else
            {
                System.out.println("mining new block");
                BlockchainData.getInstance().mineBlock();
                System.out.println(BlockchainData.getInstance().getWalletBallanceFX());
            }
            try {
                Thread.sleep(2000);
                if(BlockchainData.getInstance().isExit()){break;}
                BlockchainData.getInstance().setMiningPoints(BlockchainData.getInstance().getMiningPoints()+2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
