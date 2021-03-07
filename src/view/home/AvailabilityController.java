package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.Navigation;

public class AvailabilityController {
    @FXML
    private Label userLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<?> bookTable;

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);
    }
    @FXML
    private void loadAvailability(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAvailability(actionEvent);
    }
}
