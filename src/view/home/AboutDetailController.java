package view.home;

import bean.About;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import services.rmiService.RMIClient;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AboutDetailController implements Initializable {

    @FXML
    private AnchorPane detailPane;

    @FXML
    private TextField libraryNameTextField;

    @FXML
    private TextField contactPersonTextField;

    @FXML
    private TextField contactDesignationTextField;

    @FXML
    private TextArea libraryAddressTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private TextField conatctEmailTextField;

    @FXML
    private ImageView imageView;

    private  static About aboutInfo = null;

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailPane);
    }

    @FXML
    private void loadAbout(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAbout(detailPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }
    private void setData(){
        try {
            aboutInfo = RMIClient.rmiInterface.getLibraryInformation();
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        if(aboutInfo != null){
            libraryNameTextField.setText(aboutInfo.getaLibraryName());
            libraryAddressTextField.setText(aboutInfo.getaLibraryAddress());
            contactPersonTextField.setText(aboutInfo.getaContactPerson());
            contactDesignationTextField.setText(aboutInfo.getaContactDesignation());
            conatctEmailTextField.setText(aboutInfo.getaContactEmail());
            contactNumberTextField.setText(aboutInfo.getaLibraryNumber());
        }


    }
}
