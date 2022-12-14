package ch.hevs.ag.Controller;

import ch.hevs.ag.Model.Transaction;
import ch.hevs.ag.ServiceData.BlockchainData;
import ch.hevs.ag.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.*;

public class AddNewTransactionController {

    @FXML
    private TextField toAddress ;
    @FXML
    private TextField value ;

    public void createNewTransaction() throws GeneralSecurityException {
        Transaction transaction = new Transaction(WalletData.getInstance().getWallet(), toAddress.getText().getBytes(), Integer.valueOf(value.getText()), BlockchainData.getInstance().getCurrentBlockChain().getLast().getLedgerId(), Signature.getInstance("SHA256withDSA"));

        BlockchainData blockchainData = new BlockchainData();
        blockchainData.addTransactionState(transaction);
        blockchainData.addTransaction(transaction, false); //pas sur

        //close window and open mainWindow
    }
}
