package com.example.paz1c_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class UserEditController {

    @FXML
    TextField editName, editSurname, editDate, editLogin;

    @FXML
    ComboBox editRole;

    @FXML
    Button saveEdit, cancelEdit, cancel;
    User user = AdminController.user;

    @FXML
    public void initialize() {
        editName.setText(user.getName());
        editSurname.setText(user.getSurname());
        editDate.setText(user.getDate());
        editLogin.setText(user.getLogin());
        editRole.getSelectionModel().select(user.getRole() - 1);

        cancelEdit.setOnAction(event ->
                AdminController.editingWindow.fireEvent(new WindowEvent(AdminController.editingWindow, WindowEvent.WINDOW_CLOSE_REQUEST))
        );
    }

    public void reset() throws Exception {
        DatabseHandler dbHandler = new DatabseHandler();

        dbHandler.resetPassword(user.getId());
    }

    @FXML
    public void doDeleteUser() throws Exception {
        DatabseHandler dbHandler = new DatabseHandler();

        dbHandler.deleteUser(user.getId());

        AdminController.editingWindow.fireEvent(new WindowEvent(AdminController.editingWindow, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

}
