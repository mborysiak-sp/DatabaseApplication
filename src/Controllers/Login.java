package Controllers;

import Models.LoggingTable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

public class Login {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void Login(ActionEvent event) throws Exception {
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT [password], [admin] FROM [user] WHERE username = " + "'" + username.getText() + "'");
            String gotPassword = "";
            Boolean gotAdmin = false;
            while (rs.next()) {
                gotPassword = rs.getString("password");
                gotAdmin = rs.getBoolean("admin");
            }
            if (password.getText().equals(gotPassword) && !password.getText().isEmpty()) {
                recordLog();
                Stage primaryStage = new Stage();
                Parent root;
                Scene scene;
                if(gotAdmin == true)
                {
                    root = FXMLLoader.load(getClass().getResource("/Views/Admin.fxml"));
                    scene = new Scene(root, 1280, 480);
                }

                else
                {
                    root = FXMLLoader.load(getClass().getResource("/Views/User.fxml"));
                    scene = new Scene(root, 640, 480);
                }
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            con.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void recordLog() throws SQLException {
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT id_user FROM [user] WHERE username = " + "'" + username.getText() + "'");
            Integer id_user = null;
            while (rs.next()) {
                id_user = rs.getInt("id_user");
            }
            con.createStatement().executeQuery("INSERT INTO [logging] ([date], [hour], id_user) VALUES ( CONVERT(DATE, GETDATE()), CONVERT(TIME, GETDATE()) ," + id_user + ");");
            con.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
