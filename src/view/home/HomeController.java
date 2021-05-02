package view.home;

import bean.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import services.rmiService.RMIClient;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane basePane;

    @FXML
    private AnchorPane card1AnchorPane;

    @FXML
    private Label card1BookNameLabel;

    @FXML
    private Label card1AuthorLabel;

    @FXML
    private Label card1AvailabilityLabel;

    @FXML
    private Label card1ISBNLabel;

    @FXML
    private AnchorPane card2AnchorPane;

    @FXML
    private Label card2BookNameLabel;

    @FXML
    private Label card2AuthorLabel;

    @FXML
    private Label card2AvailablityLabel;

    @FXML
    private Label card2ISBNLabel;

    @FXML
    private AnchorPane card3AnchorPane;

    @FXML
    private Label card3BookName;

    @FXML
    private Label card3AuthorNameLabel;

    @FXML
    private Label card3AvalabilityLabel;

    @FXML
    private Label card3ISBNLabel;

    @FXML
    private Label userLabel;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private Label dateLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHomeDetail();
        setData();
    }

    private void loadHomeDetail(){
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailPane);
    }

    @FXML
    private void logout(){
        Navigation navigation = new Navigation();
        navigation.logout(basePane);
    }

    private void setData(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);
        dateLabel.setText(strDate);


        card1AnchorPane.setVisible(true);
        card2AnchorPane.setVisible(true);
        card3AnchorPane.setVisible(true);
        try {
            List<Book> bookList = RMIClient.rmiInterface.getAllBooks();
            ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);
            ObservableList<Book> newArrivalList = FXCollections.observableArrayList();
            for(Book book : bookObservableList){
                if(book.getbArrivalStatus().equals("New Arrival"))
                    newArrivalList.add(book);
            }
            if(newArrivalList.isEmpty()){
                card1AnchorPane.setVisible(false);
                card2AnchorPane.setVisible(false);
                card3AnchorPane.setVisible(false);
            }else if(newArrivalList.size() == 1){
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(false);
                card3AnchorPane.setVisible(false);

                card1BookNameLabel.setText(newArrivalList.get(0).getbName().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(0).getbAuthor());
                card1ISBNLabel.setText(newArrivalList.get(0).getbISBN());
                card1AvailabilityLabel.setText(newArrivalList.get(0).getbArrivalStatus());
            }else if(newArrivalList.size() == 2){
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(true);
                card3AnchorPane.setVisible(false);

                card1BookNameLabel.setText(newArrivalList.get(0).getbName().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(0).getbAuthor());
                card1ISBNLabel.setText(newArrivalList.get(0).getbISBN());
                card1AvailabilityLabel.setText(newArrivalList.get(0).getbArrivalStatus());

                card2BookNameLabel.setText(newArrivalList.get(1).getbName().toUpperCase());
                card2AuthorLabel.setText(newArrivalList.get(1).getbAuthor());
                card2ISBNLabel.setText(newArrivalList.get(1).getbISBN());
                card2AvailablityLabel.setText(newArrivalList.get(1).getbArrivalStatus());
            }else{
                card1BookNameLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbName().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbAuthor());
                card1ISBNLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbISBN());
                card1AvailabilityLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbArrivalStatus());

                card2BookNameLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbName().toUpperCase());
                card2AuthorLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbAuthor());
                card2ISBNLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbISBN());
                card2AvailablityLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbArrivalStatus());

                card3BookName.setText(newArrivalList.get(newArrivalList.size() - 3).getbName());
                card3AuthorNameLabel.setText(newArrivalList.get(newArrivalList.size() - 3).getbAuthor().toUpperCase());
                card3ISBNLabel.setText(newArrivalList.get(newArrivalList.size() - 3).getbISBN());
                card3AvalabilityLabel.setText(newArrivalList.get(newArrivalList.size() - 3).getbArrivalStatus());
            }

        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }



}
