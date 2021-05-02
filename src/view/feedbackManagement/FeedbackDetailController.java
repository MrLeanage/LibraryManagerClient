package view.feedbackManagement;

import bean.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import services.rmiService.RMIClient;
import services.rmiService.RMIInterface;
import util.utility.UtilityMethod;
import util.utility.userAlerts.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FeedbackDetailController implements Initializable {

    @FXML
    private AnchorPane detailPane;

    @FXML
    private TextField titileTextField;

    @FXML
    private ComboBox<String> categoryChoiceBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descripionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> categoryList = FXCollections.observableArrayList("Service Appreciation","Service Complaint");
        categoryChoiceBox.setValue("Service Appreciation");
        categoryChoiceBox.setItems(categoryList);
    }

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailPane);
    }
    @FXML
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(detailPane);
    }

    @FXML
    private void giveFeedback(ActionEvent event){
        clearLabels();
        if(validateFields()){
            Feedback feedback = new Feedback();
            feedback.setfCategory(categoryChoiceBox.getValue());
            feedback.setfTitle(titileTextField.getText());
            feedback.setfDescription(descriptionTextArea.getText());
            feedback.setfDate(UtilityMethod.getCurrentDate());
            feedback.setfTime(UtilityMethod.currentTime());
            feedback.setfStatus("Pending");

            try {
                if(RMIClient.rmiInterface.giveFeedback(feedback)){
                    AlertPopUp.sentSuccessfully("Feedback Sent Successfully");
                    clearFields();
                }else
                    AlertPopUp.insertionFailed("Failed to send Feedback!");
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }

        }else{
            validateMessage();
        }
    }

    private boolean validateFields(){
        return DataValidation.TextFieldNotEmpty(titileTextField)
                && DataValidation.TextAreaNotEmpty(descriptionTextArea)

                && DataValidation.isValidMaximumLength(titileTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400);
    }

    private void validateMessage(){
        if(!(DataValidation.TextFieldNotEmpty(titileTextField)
                && DataValidation.TextAreaNotEmpty(descriptionTextArea))){
            DataValidation.TextFieldNotEmpty(titileTextField, titleLabel, "Title cannot bbe Empty!");
            DataValidation.TextAreaNotEmpty(descriptionTextArea, descripionLabel, "Description Cannot be Emmpty");
        }

        if(!(DataValidation.isValidMaximumLength(titileTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400))){
            DataValidation.isValidMaximumLength(titileTextField.getText(), 45, titleLabel, "Maximum Length Exceeded, Limit 45");
            DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400, descripionLabel, "Maximum length Exceeded, Limit 400");
        }
    }

    private void clearFields(){
        titileTextField.setText("");
        descriptionTextArea.setText("");
    }

    private void clearLabels(){
        titleLabel.setText("");
        descripionLabel.setText("");
    }


}
