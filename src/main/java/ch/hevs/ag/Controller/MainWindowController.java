package ch.hevs.ag.Controller;

import ch.hevs.ag.Model.Transaction;
import ch.hevs.ag.ServiceData.BlockchainData;
import ch.hevs.ag.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Base64;

public class MainWindowController {

    //get access to the table in the UI with all the columns inside it
    @FXML
    public TableView<Transaction> tableView = new TableView<>() ;
    @FXML
    private TableColumn<Transaction, String> from ;
    @FXML
    private TableColumn<Transaction, String> to;
    @FXML
    private TableColumn<Transaction, Integer> value;
    @FXML
    private TableColumn<Transaction, String> timeStamp;
    @FXML
    private TableColumn<Transaction, String> signature;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField eCoins ;
    @FXML
    private TextArea publicKey ;

    //initialize all the value that you can get in the window
    public void initialize()
    {
        Base64.Encoder encoder = Base64.getEncoder() ;

        from.setCellFactory(new PropertyValueFactory<>("fromFX"));
        to.setCellFactory(new PropertyValueFactory<>("toFX"));
        value.setCellFactory(new PropertyValueFactory<>("valueFX"));
        timeStamp.setCellFactory(new PropertyValueFactory<>("timeStampFX"));
        signature.setCellFactory(new PropertyValueFactory<>("signatureFX"));

        //get the balance of the wallet
        eCoins.setText(BlockchainData.getInstance().getWalletBallanceFX());
        publicKey.setText(encoder.encodeToString(WalletData.getInstance().getWallet().getPublicKey().getEncoded()));

        //Fill the tableView in the UI
        tableView.setItems(BlockchainData.getInstance().getTransactionLedgerFX());
        //The cursor will be on the first line (last transaction)
        tableView.getSelectionModel().select(0);
    }

    public void toNewTransactionController()
    {

    }

    public void refresh()
    {

    }

    public void handleExit()
    {

    }
}
