package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import services.rmiService.RMIClient;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane basePane;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label refreshLabel;
    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(basePane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void setData(){
        if(RMIClient.startClient()){

            serverStatusLabel.setStyle("-fx-text-fill: #00B605 ");
            serverStatusLabel.setText("Connected with the Library Manager Server");
            refreshLabel.setVisible(false);
        }

        else{
            serverStatusLabel.setStyle("-fx-text-fill: #ff0000 ");
            serverStatusLabel.setText("Unable to connect to the Library Manager Server, Please Contact Your Administrator");
            refreshLabel.setVisible(true);
        }

    }
}
