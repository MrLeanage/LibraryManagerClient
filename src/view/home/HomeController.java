package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.Navigation;

public class HomeController {

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
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(actionEvent);
    }
    @FXML
    private void loadRequestBook(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadRequestBook(actionEvent);
    }
    @FXML
    private void loadAbout(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAbout(actionEvent);
    }


}
