package Main;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
// import javafx.stage.StageStyle;
 
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../Views/Switch.fxml"));
            Scene scene = new Scene(root);
            // primaryStage.initStyle(StageStyle.UNDECORATED);

            String css = this.getClass().getResource("../values/color.css").toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setTitle("Trashure");
            Image image = new Image("img/logo.png");
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        } catch (IOException e) {
            System.out.println("ERROR nih disini");
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}