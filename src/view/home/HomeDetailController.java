package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Navigation;

public class HomeDetailController {

    @FXML
    private Label userLabel;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(detailPane);
    }
    @FXML
    private void loadAvailability(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAvailability(detailPane);
    }
    @FXML
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(detailPane);
    }
    @FXML
    private void loadRequestBook(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadRequestBook(detailPane);
    }
    @FXML
    private void loadAbout(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAbout(detailPane);
    }


}
