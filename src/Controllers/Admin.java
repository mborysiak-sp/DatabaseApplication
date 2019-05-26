package Controllers;

import Models.AdminTable;
import Models.LoggingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    @FXML
    private TableView<AdminTable> adminTable;
    @FXML
    private TableColumn<AdminTable, Integer> col_id_user;
    @FXML
    private TableColumn<AdminTable, String> col_username ;
    @FXML
    private TableColumn<AdminTable, String> col_password ;
    @FXML
    private TableColumn<AdminTable, String> col_email ;
    @FXML
    private TableColumn<AdminTable, Boolean> col_admin;

    @FXML
    private TableView<LoggingTable> loggingTable;
    @FXML
    private TableColumn<LoggingTable, Integer> id_logging;
    @FXML
    private TableColumn<LoggingTable, Date> date ;
    @FXML
    private TableColumn<LoggingTable, Time> hour ;
    @FXML
    private TableColumn<LoggingTable, Integer> id_user_logging ;

    @FXML
    private TextField id_user;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField admin;

    @FXML
    private Button logout;

    ObservableList<AdminTable> objList = FXCollections.observableArrayList();
    ObservableList<LoggingTable> loggingList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DBConnector.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM [user]");
            while (rs.next()) {
                objList.add(new AdminTable(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getBoolean("admin")));
            }
            rs = con.createStatement().executeQuery("SELECT * FROM [logging]");
            while (rs.next()) {
                loggingList.add(new LoggingTable(rs.getInt("id_logging"), rs.getDate("date"), rs.getTime("hour"), rs.getInt("id_user")));
            }
            con.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        col_id_user.setCellValueFactory(new PropertyValueFactory("id_user"));
        col_username.setCellValueFactory(new PropertyValueFactory("username"));
        col_password.setCellValueFactory(new PropertyValueFactory("password"));
        col_email.setCellValueFactory(new PropertyValueFactory("email"));
        col_admin.setCellValueFactory(new PropertyValueFactory("admin"));

        id_logging.setCellValueFactory(new PropertyValueFactory("id_logging"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        hour.setCellValueFactory(new PropertyValueFactory("hour"));
        id_user_logging.setCellValueFactory(new PropertyValueFactory("id_user"));

        adminTable.setItems(objList);
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        adminTable.setEditable(true);

        loggingTable.setItems(loggingList);
    }

    public void refreshTable() throws SQLException {
        try {

            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM [user]");
            objList.removeAll(objList);
            while (rs.next()) {
                objList.add(new AdminTable(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getBoolean("admin")));
            }
            adminTable.setItems(objList);
            con.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    public void editUser(ActionEvent event) throws Exception {
        try {
            Connection con = DBConnector.getConnection();
            if(!username.getText().isEmpty())
                con.createStatement().executeQuery("UPDATE [user] SET username =" + "'" + username.getText() + "'"  + " WHERE id_user =" + " " + id_user.getText() +"");
            if(!password.getText().isEmpty())
                con.createStatement().executeQuery("UPDATE [user] SET [password] = '" + password.getText() + "'" + " WHERE id_user = " + id_user.getText());
            if(!email.getText().isEmpty())
                con.createStatement().executeQuery("UPDATE [user] SET email = '" + email.getText() + "'" + " WHERE id_user = " + id_user.getText());
            if(!admin.getText().isEmpty())
                con.createStatement().executeQuery("UPDATE [user] SET [admin] = " + admin.getText() + " WHERE id_user = " + id_user.getText());
            con.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        refreshTable();
    }

    public void addUser(ActionEvent event) throws Exception {
        try {
            Connection con = DBConnector.getConnection();
            con.createStatement().executeQuery("INSERT INTO [user] (username, [password], email, [admin]) VALUES (" + "'" + username.getText() + "'," + "'" + password.getText() + "'," +  "'" + email.getText() + "'," + admin.getText() + ");");
            con.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
        refreshTable();
    }

    public void deleteUser(ActionEvent event) throws Exception{
        ObservableList<AdminTable> selectedUser;

        selectedUser = adminTable.getSelectionModel().getSelectedItems();

        for (AdminTable table : selectedUser) {
            Integer gotId = table.getId_user();
            try {
                Connection con = DBConnector.getConnection();
                con.createStatement().executeQuery("DELETE FROM [user] WHERE id_user = " + gotId);
                con.close();
            } catch (SQLException exception) {
                System.out.println(exception);
            }
        }
        refreshTable();
    }

    public void logout (ActionEvent event) throws Exception {
        Stage stage = (Stage)adminTable.getScene().getWindow();
        stage.close();
    }
}
