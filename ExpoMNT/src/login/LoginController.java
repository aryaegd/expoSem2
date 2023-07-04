package login;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileReader;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String XML_FILE_PATH = "ExpoMNT/src/login/users.xml";

    public void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            // Load users from XML file
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("user", User.class);
            FileReader fileReader = new FileReader(XML_FILE_PATH);
            User[] users = (User[]) xstream.fromXML(fileReader);
            fileReader.close();

            // Validate login credentials
            boolean valid = false;
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    valid = true;
                    break;
                }
            }

            if (valid) {
                showAlert("Login Successful", "Welcome, " + username + "!");
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load user data.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
