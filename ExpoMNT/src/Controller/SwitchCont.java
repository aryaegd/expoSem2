package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SwitchCont {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane paneSwitch;

    @FXML
    private Button btnRT;

    @FXML
    private Button btnPG;

    @FXML
    private Button btnExit;


    @FXML
    public void RT(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PG(ActionEvent event) {
        // try {
        //     root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //     scene = new Scene(root);
        //     stage.setScene(scene);
        //     stage.show();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}

