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

public class MainWindow {

    //link between this class and the view of mainWindow
    @FXML
    public TableView<Transaction> tableview = new TableView<>();
    @FXML // come from FXMl
    private TableColumn<Transaction, String> from;
    @FXML
    private TableColumn<Transaction, String> to;
    @FXML
    private TableColumn<Transaction, Integer> value;
    @FXML
    private TableColumn<Transaction, String> timestamp;
    @FXML
    private TableColumn<Transaction, String> signature;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField eCoins;
    @FXML
    private TextArea publicKey;

    // method that initialize the value that you write in the mainWindow
    public void initialize()
    {
      //get data access into the cell  of the columns
        Base64.Encoder encoder = Base64.getEncoder(); // make the key is converted into String
        from.setCellFactory(new PropertyValueFactory<>("fromFx")); // make link between the value from FX present in the code
        to.setCellFactory(new PropertyValueFactory<>("toFX"));
        value.setCellFactory(new PropertyValueFactory<>("valueFX"));
        timestamp.setCellFactory(new PropertyValueFactory<>("timestampFX"));
        signature.setCellFactory(new PropertyValueFactory<>("signatureFX"));

        // set the text of the textBox from the box to the blockchainData => singletone and call the method of the object
        eCoins.setText(BlockchainData.getInstance().getWalletBallanceFX());
        publicKey.setText(encoder.encodeToString(WalletData.getInstance().getWallet().getPublicKey().getEncoded()));

        //populate the tableview with transaction
        tableview.setItems(BlockchainData.getInstance().getTransactionLedgerFX());
        // FX at the end of methods means JavaFX

        tableview.getSelectionModel().select(0); // the first one is selected, the cursor is on the first one

    }

    // method inside the view of the mainWindow
    public void toNewTransactionController()
    {

    }

    //
    public void refresh()
    {

    }

    //
    public void handleExit()
    {

    }
}
