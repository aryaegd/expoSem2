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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditBrgCont implements Initializable {

    @FXML
    private Button btnCariEdBrg;

    @FXML
    private Button btnEdBrg;

    @FXML
    private Button btnBalikDashboardRT;

    @FXML
    private Button btnHapusBrg;

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
    private Pane pnFormEdt;

    @FXML
    private TextField tfEdBeratBrg;

    @FXML
    private TextField tfEdJenisBrg;

    @FXML
    private TextField tfEdKodeBrg;

    @FXML
    private TextField tfEdLokBrg;

    @FXML
    private TextField tfEdNamaBrg;

    @FXML
    private TextField tfEdHargaBrg;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void cariEdBrg(ActionEvent event) {
        String kodeBrg = tfEdKodeBrg.getText();
        Barang selectedBarang = null;

        for (Barang barang : barangList) {
            if (barang.getKodeBrg().equals(kodeBrg)) {
                selectedBarang = barang;
                break;
            }
        }

        if (selectedBarang != null) {
            tfEdNamaBrg.setText(selectedBarang.getNamaBrg());
            tfEdJenisBrg.setText(selectedBarang.getJenisBrg());
            tfEdBeratBrg.setText(String.valueOf(selectedBarang.getBeratBrg()));
            tfEdLokBrg.setText(selectedBarang.getLokasiBrg());
            tfEdHargaBrg.setText(String.valueOf(selectedBarang.getHargaBrg()));
        } else {
            clearEditFields();
        }
    }

    @FXML
    void editBrg(ActionEvent event) {
        String kodeBrg = tfEdKodeBrg.getText();
        String namaBrg = tfEdNamaBrg.getText();
        String jenisBrg = tfEdJenisBrg.getText();
        double beratBrg = Double.parseDouble(tfEdBeratBrg.getText());
        String lokasiBrg = tfEdLokBrg.getText();
        double hargaBrg = Double.parseDouble(tfEdHargaBrg.getText());

        Barang editedBarang = new Barang(kodeBrg, namaBrg, jenisBrg, beratBrg, lokasiBrg, hargaBrg);
        updateBarang(editedBarang);
        clearEditFields();
        showDataFromDatabase();
    }


    private void updateBarang(Barang barang) {
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
        tfEdKodeBrg.clear();
        tfEdNamaBrg.clear();
        tfEdJenisBrg.clear();
        tfEdBeratBrg.clear();
        tfEdLokBrg.clear();
    }

    @FXML
    void hapusBrg(ActionEvent event) {
        String kodeBrg = tfEdKodeBrg.getText();
        Barang selectedBarang = null;

        for (Barang barang : barangList) {
            if (barang.getKodeBrg().equals(kodeBrg)) {
                selectedBarang = barang;
                break;
            }
        }

        if (selectedBarang != null) {
            deleteBarang(selectedBarang);
            clearEditFields();
            showDataFromDatabase();
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

}
