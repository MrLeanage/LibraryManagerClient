package view.bookManagement;

import bean.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.Navigation;
import services.SearchService;
import services.rmiService.RMIClient;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class AvailabilityDetailController implements Initializable {

    @FXML
    private AnchorPane detailPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> availabilityColumn;

    @FXML
    private TableColumn<Book, String> arrivalColumn;

    @FXML
    private TableColumn<Book, String> actionColumn;

    public static Book selectedBook = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableData();
        searchTable();
    }

    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailPane);
    }
    @FXML
    private void loadAvailability(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadAvailability(detailPane);
    }

    private void loadTableData(){

        ObservableList<Book> bookObservableList = null;
        try {
            List<Book> bookList = RMIClient.rmiInterface.getAllBooks();
            bookObservableList = FXCollections.observableList(bookList);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }

        isbnColumn.setCellValueFactory( new PropertyValueFactory<>("bISBN"));
        bookNameColumn.setCellValueFactory( new PropertyValueFactory<>("bName"));
        authorColumn.setCellValueFactory( new PropertyValueFactory<>("bAuthor"));
        availabilityColumn.setCellValueFactory( new PropertyValueFactory<>("bAvailableStatus"));
        arrivalColumn.setCellValueFactory( new PropertyValueFactory<>("bArrivalStatus"));
        actionColumn.setCellValueFactory( new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Book, String>, TableCell<Book, String>> parentCellFactory
                =
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {

                            final Button button = new Button("View");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnMouseClicked(event -> {
                                        // student = StudentTable.getSelectionModel().getSelectedItem();
                                        //String sID = student.getsID();
                                    });
                                    button.setOnAction(event -> {
                                        Book book = getTableView().getItems().get(getIndex());
                                        selectedBook = book;
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("bookCard.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        BookCardController bookCardController = loader.getController();

                                        Parent p = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(p));
                                        stage.setResizable(false);
                                        stage.sizeToScene();

                                        stage.showAndWait();
                                        loadTableData();
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(parentCellFactory);
        bookTable.setItems(null);
        bookTable.setItems(bookObservableList);
    }
    public void searchTable(){

        SearchService searchService = new SearchService();

        SortedList<Book> sortedData = searchService.searchTable(searchTextField);

        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());
        //adding sorted and filtered data to the table
        bookTable.setItems(sortedData);
    }


}
