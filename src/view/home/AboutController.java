package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.Navigation;

public class AboutController {

    @FXML
    private Label userLabel;

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);
    }

    @FXML
    private void loadAbout(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAbout(actionEvent);
    }

}
