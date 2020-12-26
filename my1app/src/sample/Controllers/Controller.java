package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField signUpLastName;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpCountry;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private ImageView homeButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            signUpNewUser();
            openNewScene("/sample/View/optionsPage.fxml");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String gender;
        if(maleRadioButton.isSelected())
            gender = "Male";
        else
            gender = "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);

        try {
            dbHandler.signUpUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openNewScene(String window) {
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
