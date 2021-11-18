package com.example.paz1c_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserAddController {

    @FXML
    TextField newName, newSurname, newDate, newLogin;
    @FXML
    ComboBox newRole;
    @FXML
    Button save, cancelAdd;

    Stage stage;

    public void initialize() {
        cancelAdd.setOnAction(event -> AdminController.addingWindow.fireEvent(new WindowEvent(AdminController.addingWindow, WindowEvent.WINDOW_CLOSE_REQUEST)));
    }

    public void CreateAction() throws Exception {

        DatabseHandler dbHandler = new DatabseHandler();
        System.out.println(newName.getText());
        String name = newName.getText();
        String surname = newSurname.getText();
        String date = newDate.getText();
        String login = newLogin.getText();
        String password = "1111";
        int role = dbHandler.getIdRole((String) newRole.getValue());


        User user = new User(
                name,
                surname,
                date,
                login,
                password,
                role);

        dbHandler.addUser(user);

        Stage addingWindow = new Stage();

        AdminController.addingWindow.fireEvent(new WindowEvent(AdminController.addingWindow, WindowEvent.WINDOW_CLOSE_REQUEST));

    }

}
