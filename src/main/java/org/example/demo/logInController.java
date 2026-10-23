package org.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class logInController {
    private ArrayList<User> users;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @FXML
    private Label wrong;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void onHelloButtonClick() {

        wrong.setVisible(false);
        wrong.setManaged(false);

        for (User user : users) {

            if (user.getEmail().equals(emailField.getText()) &&
                    user.getPass().equals(passwordField.getText())) {

                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("welcome.fxml")
                    );

                    Parent root = loader.load();

                    Stage stage = (Stage) emailField.getScene().getWindow();

                    stage.setScene(new Scene(root));
                    stage.setTitle("Welcome");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return;
            }
        }

        wrong.setVisible(true);
        wrong.setManaged(true);
    }
}