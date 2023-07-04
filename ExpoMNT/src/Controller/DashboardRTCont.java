package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Barang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class DashboardRTCont implements Initializable {

    @FXML
    private Button btnAddBarang;

    @FXML
    private Button btnList;

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
    void List(ActionEvent event) {

    }

    @FXML
    void addBrg(ActionEvent event) {

    }

    @FXML
    void daftarTrans(ActionEvent event) {

    }

    @FXML
    void tbhBrg(ActionEvent event) {

    }

}
