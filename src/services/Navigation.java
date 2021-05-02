package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.rmiService.RMIClient;
import util.utility.userAlerts.AlertPopUp;

import java.io.IOException;
import java.util.Optional;

public class Navigation {
    private static AnchorPane currentBasePane;
    public void logout(AnchorPane basePane){
        try{
            Optional<ButtonType> action = AlertPopUp.logoutConfirmation();
            if(action.get() == ButtonType.OK){
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/login.fxml"));
                basePane.getChildren().setAll(pane);
                RMIClient.clearRMISession();
                currentBasePane = basePane;
            }

        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadHome(AnchorPane basePane){

        try{
            if(RMIClient.isServerUp()){
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/home.fxml"));
                basePane.getChildren().setAll(pane);
            }else{
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/login.fxml"));
                basePane.getChildren().setAll(pane);
            }
            currentBasePane = basePane;

        }catch(IOException ex){
            AlertPopUp.generalError(ex);
            ex.printStackTrace();
        }
    }

    public void loadHomeDetail(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/homeDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            sessionExpired();
        }
    }
    public void loadAvailability(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/bookManagement/availabilityDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            sessionExpired();
        }
    }
    public void loadFeedback(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/feedbackManagement/feedbackDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            sessionExpired();
        }
    }
    public void loadRequestBook(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/requestManagement/requestBookDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            sessionExpired();
        }
    }
    public void loadAbout(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/aboutDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            sessionExpired();
        }
    }
    private void sessionExpired(){
        Optional<ButtonType> action = AlertPopUp.sessionExpired();
        if(action.get() == ButtonType.OK){
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("/view/home/login.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            currentBasePane.getChildren().setAll(pane);
            RMIClient.clearRMISession();
        }
    }
}
