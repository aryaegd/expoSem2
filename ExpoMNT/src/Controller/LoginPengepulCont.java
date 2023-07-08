package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPengepulCont {

    @FXML
    private Pane bgLgn;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUpInst;

    @FXML
    private Button btnBalikSwitch;

    @FXML
    private Label lbLogin;

    @FXML
    private PasswordField pfPass;

    @FXML
    private TextField tfUname;

    private Parent root;

    private Scene scene;

    private Stage stage;

    @FXML
    void SignIn(ActionEvent event) throws IOException, SQLException {
        String username = tfUname.getText();
        String password = pfPass.getText();

        // Menggunakan koneksi database
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        // Mengecek apakah username dan password valid
        String query = "SELECT * FROM pengepulaccounts WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Jika username dan password valid, pindah ke tampilan DashboardRT.fxml
            root = FXMLLoader.load(getClass().getResource("../Views/DashboardPengepul.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // Jika username atau password tidak valid, tampilkan pesan kesalahan
            lbLogin.setText("Username or password is incorrect");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @FXML
    void sgnUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/SignUpPengepul.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void balikSwitch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Switch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
