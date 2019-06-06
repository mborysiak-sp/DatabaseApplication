import Controllers.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        primaryStage.setTitle("Ｄａｔａｂａｓｅ　Ａｐｐｌｉｃａｔｉｏｎ　央ぞ波");
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/Data/favicon.png"));
        primaryStage.show();
        DBConnector.getConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
