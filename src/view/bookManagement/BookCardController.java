package view.bookManagement;

import bean.Book;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.rmiService.RMIClient;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class BookCardController implements Initializable {
    @FXML
    private TextField authorTextField;

    @FXML
    private TextField availableStatusTextField;

    @FXML
    private TextField arrivalStatusTextField;

    @FXML
    private JFXButton closeCardButton;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private TextField ISBNTextField;

    private static Book book= null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void closeCard(ActionEvent event) {
        Stage stage = (Stage) closeCardButton.getScene().getWindow();
        stage.close();
    }


    private void setData(){
        try{
            book = AvailabilityDetailController.selectedBook;
            bookNameLabel.setText(book.getbName());
            authorLabel.setText("by "+ book.getbAuthor());
            authorTextField.setText(book.getbAuthor());
            ISBNTextField.setText(book.getbISBN());
            availableStatusTextField.setText(book.getbAvailableStatus());
            arrivalStatusTextField.setText(book.getbArrivalStatus());
        }catch(NullPointerException exception){
            exception.printStackTrace();
        }

        try {
            RMIClient.rmiInterface.addBookView(book.getbID());
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }
}
