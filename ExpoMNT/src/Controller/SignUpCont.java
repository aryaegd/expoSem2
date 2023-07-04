package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class SignUpCont {

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Pane pnBgSignUp;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfUsername;

    @FXML
    void SignUp(ActionEvent event) throws SQLException {
        String name = tfName.getText();
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        // Menggunakan koneksi database
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        // Query SQL untuk memasukkan data pengguna baru ke dalam tabel useraccounts
        String query = "INSERT INTO useraccounts (name, username, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        // Kembali ke halaman login setelah pendaftaran berhasil
        backToLogin(event);
    }

    @FXML
    void backToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
