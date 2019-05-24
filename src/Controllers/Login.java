package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Login {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void Login(ActionEvent event) throws Exception {
        if (username.getText().equals("user") && password.getText().equals("pass"))
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Admin.fxml"));
            Scene scene = new Scene(root, 640,480);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
