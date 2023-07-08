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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TransaksiPengepulCont implements Initializable {

    @FXML
    private Button btnBalikDashboardP;

    @FXML
    private Button btnCariFinal;

    @FXML
    private Button btnFinalTr;

    @FXML
    private TableColumn<Barang, String> colKode;

    @FXML
    private TableColumn<Barang, String> colNama;

    @FXML
    private TableColumn<Barang, Double> colHarga;

    @FXML
    private ImageView imgTF;

    @FXML
    private Label lbCOD;

    @FXML
    private RadioButton rbCod;

    @FXML
    private RadioButton rbTfBank;

    @FXML
    private TextField tfCariKodeFinal;

    @FXML
    private TextField tfHargaFinal;

    @FXML
    private TextField tfNamaFinal;

    @FXML
    private TableView<Barang> tvListBrg;

    private ObservableList<Barang> barangList;

    private Stage stage;

    private Scene scene;

    private Parent root;


    @FXML
    void cariFinal(ActionEvent event) {
        String kodeBrg = tfCariKodeFinal.getText();
        Barang selectedBarang = null;

        for (Barang barang : barangList) {
            if (barang.getKodeBrg().equals(kodeBrg)) {
                selectedBarang = barang;
                break;
            }
        }

        if (selectedBarang != null) {
            tfNamaFinal.setText(selectedBarang.getNamaBrg());
            tfHargaFinal.setText(String.valueOf(selectedBarang.getHargaBrg()));
        } else {
            tfNamaFinal.clear();
            tfHargaFinal.clear();
        }
    }

    @FXML
    void balikDashP(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/DashboardPengepul.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void COD(ActionEvent event) {
        // Sembunyikan komponen imgTF
        imgTF.setVisible(false);
        // Tampilkan komponen lbCOD
        lbCOD.setVisible(true);
    }

    @FXML
    void tfBank(ActionEvent event) {
        // Tampilkan komponen imgTF
        imgTF.setVisible(true);
        // Sembunyikan komponen lbCOD
        lbCOD.setVisible(false);
    }

    @FXML
    void finalTr(ActionEvent event) {
        String kodeBrg = tfCariKodeFinal.getText();
        Barang selectedBarang = null;

        for (Barang barang : barangList) {
            if (barang.getKodeBrg().equals(kodeBrg)) {
                selectedBarang = barang;
                break;
            }
        }

        if (selectedBarang != null) {
            deleteBarang(selectedBarang);
            clearFields();
            showDataFromDatabase();
            imgTF.setVisible(false);
            lbCOD.setVisible(false);
        }
    }

    private void deleteBarang(Barang barang) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "DELETE FROM databarang WHERE kodeBrg = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, barang.getKodeBrg());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tfCariKodeFinal.clear();
        tfNamaFinal.clear();
        tfHargaFinal.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBrg"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaBrg"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBrg"));

        showDataFromDatabase();
        // Inisialisasi tersembunyi
        imgTF.setVisible(false);
        lbCOD.setVisible(false);
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
    
                if (namaBrg.contains("[SETUJU]")) {
                    Barang barang = new Barang(kodeBrg, namaBrg, null, 0.0, null, hargaBrg);
                    barangList.add(barang);
                }
            }
    
            tvListBrg.setItems(barangList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
