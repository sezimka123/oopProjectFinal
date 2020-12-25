package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.DatabaseHandler;
import sample.User;

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

        signUpButton.setOnAction(event -> signUpNewUser());
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpButton.getText();
        String lastName = signUpButton.getText();
        String userName = signUpButton.getText();
        String password = signUpButton.getText();
        String location = signUpButton.getText();
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

}