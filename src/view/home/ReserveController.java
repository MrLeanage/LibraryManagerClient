package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.Navigation;

public class ReserveController {

    @FXML
    private Label userLabel;

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
    @FXML
    private void loadReserve(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadReserve(actionEvent);
    }
}
