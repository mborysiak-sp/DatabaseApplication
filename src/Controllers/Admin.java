package Controllers;

import Models.AdminTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    @FXML
    private TableView<AdminTable> adminTable;
    @FXML
    private TableColumn<AdminTable, Integer> id_user;
    @FXML
    private TableColumn<AdminTable, String> username ;
    @FXML
    private TableColumn<AdminTable, String> password ;
    @FXML
    private TableColumn<AdminTable, String> email ;
    @FXML
    private TableColumn<AdminTable, Boolean> admin;

    ObservableList<AdminTable> objList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DBConnector.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM [user]");
            while (rs.next()) {
                objList.add(new AdminTable(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getBoolean("admin")));

            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        id_user.setCellValueFactory(new PropertyValueFactory("id_user"));
        username.setCellValueFactory(new PropertyValueFactory("username"));
        password.setCellValueFactory(new PropertyValueFactory("password"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        admin.setCellValueFactory(new PropertyValueFactory("admin"));

        adminTable.setItems(objList);
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        adminTable.setEditable(true);

    }
    public void deleteUser(ActionEvent event) throws Exception{
        ObservableList<AdminTable> selectedUser, allUsers;
        allUsers = adminTable.getItems();

        selectedUser = adminTable.getSelectionModel().getSelectedItems();

        for (AdminTable table : selectedUser)
            allUsers.remove(table);
    }
}
