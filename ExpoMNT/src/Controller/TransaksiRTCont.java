package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Barang;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TransaksiRTCont implements Initializable {

    @FXML
    private Button btnBalikDashboardRT;

    @FXML
    private Button btnCariTrBrg;

    @FXML
    private TableColumn<Barang, String> colKode;

    @FXML
    private TableColumn<Barang, String> colNama;

    @FXML
    private TableColumn<Barang, Double> colHarga;

    @FXML
    private Pane pnFormEdt;

    @FXML
    private RadioButton rbSetuju;

    @FXML
    private RadioButton rbTidakSetuju;

    @FXML
    private Label lbStatus;

    @FXML
    private TextField tfTrNamaBrg;

    @FXML
    private TextField tfTrHargaBrg;

    @FXML
    private TextField tfTrKodeBrg;

    @FXML
    private TableView<Barang> tvListBrg;

    private ObservableList<Barang> barangList;

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

    @FXML
    void cariTrBrg(ActionEvent event) {
        String kodeBrg = tfTrKodeBrg.getText();
        Barang selectedBarang = null;

        for (Barang barang : barangList) {
            if (barang.getKodeBrg().equals(kodeBrg)) {
                selectedBarang = barang;
                break;
            }
        }

        if (selectedBarang != null) {
            tfTrNamaBrg.setText(selectedBarang.getNamaBrg());
            tfTrHargaBrg.setText(String.valueOf(selectedBarang.getHargaBrg()));
        } else {
            tfTrNamaBrg.clear();
            tfTrHargaBrg.clear();
        }
    }


    @FXML
    void pilihanSetuju(ActionEvent event) {
        if (rbSetuju.isSelected()) {
            String kodeBrg = tfTrKodeBrg.getText();
            String namaBrg = tfTrNamaBrg.getText();
            
            // Memperbarui nama barang di dalam ObservableList
            for (Barang barang : barangList) {
                if (barang.getKodeBrg().equals(kodeBrg)) {
                    barang.setNamaBrg("[SETUJU] " + namaBrg);
                    
                    // Memperbarui data di database
                    updateNamaBarang(barang.getKodeBrg(), barang.getNamaBrg());
                    
                    break;
                }
            }

            lbStatus.setText("Anda setuju dengan tawaran.");
            // Memperbarui TableView
            tvListBrg.refresh();
            tfTrKodeBrg.clear();
            tfTrNamaBrg.clear();
            tfTrHargaBrg.clear();
        }
    }

    private void updateNamaBarang(String kodeBrg, String namaBrg) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "UPDATE databarang SET namaBrg = ? WHERE kodeBrg = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, namaBrg);
            preparedStatement.setString(2, kodeBrg);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void pilihanTidakSetuju(ActionEvent event) {
        if (rbTidakSetuju.isSelected()) {
            String kodeBrg = tfTrKodeBrg.getText();
            
            // Mengubah harga barang menjadi 0 di dalam ObservableList
            for (Barang barang : barangList) {
                if (barang.getKodeBrg().equals(kodeBrg)) {
                    barang.setHargaBrg(0.0);
                    
                    // Mengubah data di database
                    updateHargaBarang(barang.getKodeBrg(), 0.0);
                    
                    break;
                }
            }

            // Memperbarui TableView
            lbStatus.setText("Anda tidak setuju dengan tawaran.");
            tvListBrg.refresh();
            tfTrKodeBrg.clear();
            tfTrNamaBrg.clear();
            tfTrHargaBrg.clear();
        }
    }

    private void updateHargaBarang(String kodeBrg, double hargaBrg) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "UPDATE databarang SET hargaBrg = ? WHERE kodeBrg = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, hargaBrg);
            preparedStatement.setString(2, kodeBrg);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBrg"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaBrg"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBrg"));

        showDataFromDatabase();
    }

    private void showDataFromDatabase() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT kodeBrg, namaBrg, hargaBrg FROM databarang WHERE hargaBrg > 0.0");

            barangList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String kodeBrg = resultSet.getString("kodeBrg");
                String namaBrg = resultSet.getString("namaBrg");
                Double hargaBrg = resultSet.getDouble("hargaBrg");

                Barang barang = new Barang(kodeBrg, namaBrg, null, 0.0, null, hargaBrg);
                barangList.add(barang);
            }

            tvListBrg.setItems(barangList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
