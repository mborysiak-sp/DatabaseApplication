package Controllers;

import Models.AdminTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin {
    @FXML
    private TableView<AdminTable> table;
    @FXML
    private TableColumn<AdminTable, Integer> id_user;
    @FXML
    private TableColumn<AdminTable, String> username;
    @FXML
    private TableColumn<AdminTable, String> password;
    @FXML
    private TableColumn<AdminTable, String> email;
    @FXML
    private TableColumn<AdminTable, Boolean> admin;

    ObservableList<AdminTable> objList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle) {
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        admin.setCellValueFactory(new PropertyValueFactory<>("admin"));

    }
}
