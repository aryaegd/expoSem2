package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class DashboardRTCont {

    @FXML
    private Button btnAddBarang;

    @FXML
    private Button btnList;

    @FXML
    private Button btnTbhBrg;

    @FXML
    private Button btnTransaksi;

    @FXML
    private TableColumn<?, ?> colBerat;

    @FXML
    private TableColumn<?, ?> colJenis;

    @FXML
    private TableColumn<?, ?> colKode;

    @FXML
    private TableColumn<?, ?> colLokasi;

    @FXML
    private TableColumn<?, ?> colNama;

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
    private TableView<?> tableView;

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
