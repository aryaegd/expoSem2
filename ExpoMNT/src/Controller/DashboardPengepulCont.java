package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardPengepulCont {

    @FXML
    private Button btnCariBrg;

    @FXML
    private Button btnTransaksiPengepul;

    @FXML
    private Button btnRiwayatPengepul;

    private Parent root;

    private Stage stage;

    private Scene scene;

    @FXML
    void CariBrg(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/CariBarangPengepul.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void transaksiPengepul(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/TransaksiPengepul.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void riwayatPengepul(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/RiwayatPengepul.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
