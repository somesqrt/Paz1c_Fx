package com.example.paz1c_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    TextField loginUserToLogin;

    @FXML
    Button signup;

    @FXML
    PasswordField passwordToLogin;

    public static User currentUser;

    private String loginForLogin, passwordForLogin;

    @FXML
    public void toSignup() throws Exception {
        loginForLogin = loginUserToLogin.getText();
        passwordForLogin = passwordToLogin.getText();

        if (loginForLogin.equals("") || passwordForLogin.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Incorrect data");
            alert.setHeaderText(null);
            alert.setContentText("Polia pre prihlasovacie meno a heslo nemôžu zostať prázdne.");

            alert.showAndWait();
        } else {
            DatabseHandler dbHelper = new DatabseHandler();
            currentUser = dbHelper.chekUser(loginForLogin, passwordForLogin);
            if (currentUser == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Login failed.");
                alert.setHeaderText(null);
                alert.setContentText("Prihlasovacie meno alebo heslo je nesprávne");

                alert.showAndWait();
            } else {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = null;
                if (currentUser.getRole() == 1) {
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("predajcaNonAdmin.fxml.fxml"));
                } else if (currentUser.getRole() == 2) {
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("VeduciNonAdmin.fxml.fxml"));
                } else if (currentUser.getRole() == 3) {
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
                } else if (currentUser.getRole() == 4) {
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin.fxml"));
                }

                Stage stage1 = new Stage();
                stage1 = (Stage) signup.getScene().getWindow();
                stage1.close();

                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                stage.setScene(scene);
                stage.show();

            }

        }

    }

}
