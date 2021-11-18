package com.example.paz1c_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;

public class AdminController {
    @FXML
    Button predajca, veduci, skladnik, Admin, adding, cancelEdit;

    @FXML
    TextField nameUserSearch, surnameUserSearch;

    @FXML
    Button cancelAdd;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> idOfUsers;

    @FXML
    private TableColumn<User, String> nameOfUsers;

    @FXML
    private TableColumn<User, String> surnameOfUsers;

    @FXML
    private TableColumn<User, String> dataOfUsers;

    @FXML
    private TableColumn<User, String> roleOfUsers;

    public static User user = null;

    public static Stage addingWindow = new Stage();
    public static Stage editingWindow = new Stage();

    public void handlerPredajca() throws  Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("predajca.fxml"));
        Stage window = (Stage) predajca.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1200, 800));

    }

    public void hendlerVeduci() throws  Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Veduci.fxml"));
        Stage window =(Stage) veduci.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1200,800));

    }

    public void hendlerSkladnik() throws  Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Skladnik.fxml"));
        Stage window =(Stage) skladnik.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1200,800));

    }

    public void hendlerAdmin() throws  Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin.fxml"));
        Stage window =(Stage) Admin.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1200,800));

    }

    @FXML
    public void initialize(){
        DatabseHandler dbHandler = new DatabseHandler();

        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            users = dbHandler.getAllUser("","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idOfUsers.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
        surnameOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));
        dataOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Date"));
        roleOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("stringRole"));

        tableUsers.setItems(users);

        try {
            doubleClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser() throws  Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Pridanie.fxml"));
        Stage window = (Stage) adding.getScene().getWindow();

        Scene secondScene = new Scene(fxmlLoader.load(), 400, 400);

        addingWindow.setScene(secondScene);

        addingWindow.initOwner(window);
        addingWindow.setOnCloseRequest(E ->{

            initialize();


        });
        addingWindow.show();
    }


    public void searchUser() throws Exception{
        String nameForSearchUser = "";
        String surnameForSearchUser = "";

        if(!nameUserSearch.getText().equals(null) && !surnameUserSearch.getText().equals(null)) {
            nameForSearchUser = nameUserSearch.getText();
            surnameForSearchUser = surnameUserSearch.getText();
        }
        DatabseHandler dbHandler = new DatabseHandler();

        ObservableList<User> users = FXCollections.observableArrayList();
        users = dbHandler.getAllUser(nameForSearchUser, surnameForSearchUser);

        idOfUsers.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
        surnameOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));
        dataOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("Date"));
        roleOfUsers.setCellValueFactory(new PropertyValueFactory<User, String>("stringRole"));

        tableUsers.setItems(users);

        doubleClickListener();
    }

    public void doubleClickListener() throws Exception{
        tableUsers.setRowFactory( tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    user = row.getItem();
                    try {
                        editingUser(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    public void editingUser(User user) throws  Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Uprava.fxml"));
        Stage window = (Stage) adding.getScene().getWindow();


        Scene secondScene = new Scene(fxmlLoader.load(), 400, 400);

        editingWindow.setScene(secondScene);
        editingWindow.initModality(Modality.WINDOW_MODAL);
        editingWindow.initOwner(window);
        editingWindow.setOnCloseRequest(E ->{

            initialize();


        });
        editingWindow.getOnCloseRequest().handle(new WindowEvent(editingWindow, WindowEvent.WINDOW_CLOSE_REQUEST));



        editingWindow.show();
    }
}
