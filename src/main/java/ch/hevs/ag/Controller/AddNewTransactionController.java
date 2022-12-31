package ch.hevs.ag.Controller;

import ch.hevs.ag.Model.Transaction;
import ch.hevs.ag.ServiceData.BlockchainData;
import ch.hevs.ag.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.*;
import java.util.Base64;

public class AddNewTransactionController {

    @FXML
    private TextField toAddress ;
    @FXML
    private TextField value ;

    public void createNewTransaction() throws GeneralSecurityException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] sendB = decoder.decode(toAddress.getText());
        Transaction transaction = new Transaction(WalletData.getInstance().getWallet(), sendB, Integer.parseInt(value.getText()), BlockchainData.getInstance().getCurrentBlockChain().getFirst().getLedgerId(), Signature.getInstance("SHA256withDSA"));

        BlockchainData blockchainData = new BlockchainData();
        blockchainData.getInstance().addTransaction(transaction, false); //pas sur
        blockchainData.getInstance().addTransactionState(transaction);

        //close window and open mainWindow
    }
}
