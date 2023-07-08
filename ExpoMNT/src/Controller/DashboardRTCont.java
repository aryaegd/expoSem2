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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashboardRTCont implements Initializable {

    @FXML
    private Button btnPindahEdit;

    @FXML
    private Button btnTbhBrg;

    @FXML
    private Button btnTransaksi;

    @FXML
    private Label lblDashboard;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblYourProduct;

    @FXML
    private Pane pnFormTbh;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField tfBeratBrg;

    @FXML
    private TextField tfJenisBrg;

    @FXML
    private TextField tfKodeBrg;

    @FXML
    private TextField tfLokBrg;

    @FXML
    private TextField tfNamaBrg;

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
    void pindahEdit(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/editBrgRT.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void daftarTrans(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/TransaksiRT.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tbhBrg(ActionEvent event) {
        String kodeBrg = tfKodeBrg.getText();
        String namaBrg = tfNamaBrg.getText();
        String jenisBrg = tfJenisBrg.getText();
        double beratBrg = Double.parseDouble(tfBeratBrg.getText());
        String lokasiBrg = tfLokBrg.getText();
        double hargaBrg = 0.0; // Sesuaikan dengan sumber harga barang
        
        Barang barang = new Barang(kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg);
        insertBarang(barang);
        clearFields();
        showDataFromDatabase();
    }

    private void insertBarang(Barang barang) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            String query = "INSERT INTO databarang (kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, barang.getKodeBrg());
            preparedStatement.setString(2, barang.getNamaBrg());
            preparedStatement.setString(3, barang.getJenisBrg());
            preparedStatement.setDouble(4, barang.getBeratBrg());
            preparedStatement.setString(5, barang.getLokasiBrg());
            preparedStatement.setDouble(6, barang.getHargaBrg());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tfKodeBrg.clear();
        tfNamaBrg.clear();
        tfJenisBrg.clear();
        tfBeratBrg.clear();
        tfLokBrg.clear();
    }
}
