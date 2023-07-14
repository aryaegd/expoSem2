package Controller;

import Model.Riwayat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RiwayatRTCont implements Initializable {

    @FXML
    private Button btnBalikDashboardRT;

    @FXML
    private TableColumn<Riwayat, String> colNama;

    @FXML
    private TableColumn<Riwayat, Double> colHarga;

    @FXML
    private TableView<Riwayat> tvListBrg;

    private ObservableList<Riwayat> riwayatList;

    private Parent root;

    private Scene scene;

    private Stage stage;

    @FXML
    void balikDashRT(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/DashboardRT.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        riwayatList = FXCollections.observableArrayList();
        showDataFromDatabase();

        tvListBrg.setItems(riwayatList);
    }

    private void showDataFromDatabase() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT namaBrg, hargaBrg FROM riwayattr");

            while (resultSet.next()) {
                String namaBrg = resultSet.getString("namaBrg");
                double hargaBrg = resultSet.getDouble("hargaBrg");

                Riwayat riwayat = new Riwayat(namaBrg, hargaBrg);
                riwayatList.add(riwayat);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
