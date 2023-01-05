package ch.hevs.ag.Controller;

import ch.hevs.ag.Model.Transaction;
import ch.hevs.ag.ServiceData.BlockchainData;
import ch.hevs.ag.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.*;
import java.sql.SQLException;
import java.util.Base64;

public class AddNewTransactionController {

    @FXML
    private TextField toAddress ;
    @FXML
    private TextField value ;

    public void createNewTransaction() throws GeneralSecurityException, SQLException {
        Base64.Decoder decoder = Base64.getDecoder();
        Signature signing = Signature.getInstance("SHA256withRSA");
        Integer ledgerId = BlockchainData.getInstance().getTransactionLedgerFX().get(0).getLedgerId();
        byte[] sendB = decoder.decode(toAddress.getText());
        Transaction transaction = new Transaction(WalletData.getInstance().getWallet(), sendB, Integer.parseInt(value.getText()), ledgerId, signing);

        BlockchainData.getInstance().addTransaction(transaction, false);
        BlockchainData.getInstance().addTransactionState(transaction);
    }
}
