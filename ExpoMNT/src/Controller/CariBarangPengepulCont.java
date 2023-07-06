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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CariBarangPengepulCont implements Initializable {

    @FXML
    private Button btnBalikDashboardPengepul;

    @FXML
    private Button btnCariBrgLokasi;

    @FXML
    private Button btnCariKodeBrg;

    @FXML
    private Button btnTawaran;

    @FXML
    private TableView<Barang> tvListBrg;

    @FXML
    private TableColumn<Barang, Double> colBerat;

    @FXML
    private TableColumn<Barang, String> colJenis;

    @FXML
    private TableColumn<Barang, Integer> colKode;

    @FXML
    private TableColumn<Barang, String> colLokasi;

    @FXML
    private TableColumn<Barang, String> colNama;

    @FXML
    private TableColumn<Barang, Double> colHarga;

    private ObservableList<Barang> barangList;

    @FXML
    private TextField tfCariBrg;

    @FXML
    private TextField tfDataBeratBrg;

    @FXML
    private TextField tfDataJenisBrg;

    @FXML
    private TextField tfDataLokasiBrg;

    @FXML
    private TextField tfDataNamaBrg;

    @FXML
    private TextField tfKodeBrgP;

    @FXML
    private TextField tfTawaran;

    

    private Stage stage;

    private Scene scene;

    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBrg"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaBrg"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisBrg"));
        colBerat.setCellValueFactory(new PropertyValueFactory<>("beratBrg"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiBrg"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBrg"));

        showDataFromDatabase();
    }

    private void showDataFromDatabase() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM databarang");
            
            barangList = FXCollections.observableArrayList();
            
            while (resultSet.next()) {
                String kodeBrg = resultSet.getString("kodeBrg");
                String namaBrg = resultSet.getString("namaBrg");
                String jenisBrg = resultSet.getString("jenisBrg");
                double beratBrg = resultSet.getDouble("beratBrg");
                String lokasiBrg = resultSet.getString("lokasiBrg");
                Double hargaBrg = resultSet.getDouble("hargaBrg");
                
                Barang barang = new Barang(kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg);
                barangList.add(barang);
            }
            
            tvListBrg.setItems(barangList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void balikDashboardPengepul(ActionEvent event) {
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
    void cariBrgLokasi(ActionEvent event) {
        String lokasi = tfCariBrg.getText(); // Mendapatkan input lokasi dari tfCariBrg

        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "SELECT * FROM databarang WHERE lokasiBrg = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lokasi);
            ResultSet resultSet = statement.executeQuery();
            
            barangList = FXCollections.observableArrayList();
            
            while (resultSet.next()) {
                String kodeBrg = resultSet.getString("kodeBrg");
                String namaBrg = resultSet.getString("namaBrg");
                String jenisBrg = resultSet.getString("jenisBrg");
                double beratBrg = resultSet.getDouble("beratBrg");
                String lokasiBrg = resultSet.getString("lokasiBrg");
                Double hargaBrg = resultSet.getDouble("hargaBrg");
                
                Barang barang = new Barang(kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg);
                barangList.add(barang);
            }
            
            tvListBrg.setItems(barangList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cariKodeBrg(ActionEvent event) {
        String kodeBrg = tfKodeBrgP.getText(); // Mendapatkan input kode barang dari tfKodeBrgP

        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "SELECT * FROM databarang WHERE kodeBrg = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, kodeBrg);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String namaBrg = resultSet.getString("namaBrg");
                String jenisBrg = resultSet.getString("jenisBrg");
                double beratBrg = resultSet.getDouble("beratBrg");
                String lokasiBrg = resultSet.getString("lokasiBrg");
                double hargaBrg = resultSet.getDouble("hargaBrg");
                
                tfDataNamaBrg.setText(namaBrg);
                tfDataJenisBrg.setText(jenisBrg);
                tfDataBeratBrg.setText(Double.toString(beratBrg));
                tfDataLokasiBrg.setText(lokasiBrg);
                tfTawaran.setText(Double.toString(hargaBrg));
            } else {
                // Barang dengan kodeBrg tersebut tidak ditemukan
                // Lakukan penanganan kesalahan di sini, seperti menampilkan pesan error
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void masukTawaran(ActionEvent event) {
        String kodeBrg = tfKodeBrgP.getText();
        String namaBrg = tfDataNamaBrg.getText();
        String jenisBrg = tfDataJenisBrg.getText();
        double beratBrg = Double.parseDouble(tfDataBeratBrg.getText());
        String lokasiBrg = tfDataLokasiBrg.getText();
        double hargaBrg = Double.parseDouble(tfTawaran.getText());

        Barang editedBarang = new Barang(kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg);
        updateDataBarang(editedBarang);
        clearEditFields();
        showDataFromDatabase();
    }

    private void updateDataBarang(Barang barang) {
    DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "UPDATE databarang SET namaBrg = ?, jenisBrg = ?, beratBrg = ?, lokasiBrg = ?, hargaBrg = ? WHERE kodeBrg = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, barang.getNamaBrg());
            preparedStatement.setString(2, barang.getJenisBrg());
            preparedStatement.setDouble(3, barang.getBeratBrg());
            preparedStatement.setString(4, barang.getLokasiBrg());
            preparedStatement.setDouble(5, barang.getHargaBrg());
            preparedStatement.setString(6, barang.getKodeBrg());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearEditFields() {
        tfKodeBrgP.clear();
        tfDataNamaBrg.clear();
        tfDataJenisBrg.clear();
        tfDataBeratBrg.clear();
        tfDataLokasiBrg.clear();
        tfTawaran.clear();
    }



}
