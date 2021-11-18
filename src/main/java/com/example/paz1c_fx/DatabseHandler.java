package com.example.paz1c_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabseHandler extends Config {

    Connection dbConenection;

    public Connection getDbConenection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConenection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConenection;
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        String insert = "INSERT INTO " + Constant.USER_TABLE + "(" +
                Constant.USER_NAME + "," +
                Constant.USER_SURNAME + "," +
                Constant.USER_DATE + "," +
                Constant.USER_LOGIN + "," +
                Constant.USER_PASSWORD + "," +
                Constant.USER_ROLE +

                ")" +
                "VALUES(?,?,?,?,?,?)";


        PreparedStatement prSt = getDbConenection().prepareStatement(insert);
        prSt.setString(1, user.getName());
        prSt.setString(2, user.getSurname());
        prSt.setString(3, user.getDate());
        prSt.setString(4, user.getLogin());
        prSt.setString(5, user.getPassword());
        prSt.setInt(6, user.getRole());


        prSt.executeUpdate();

    }

    public int getIdRole(String roleName) throws ClassNotFoundException, SQLException {
        String getId = "SELECT " + Constant.ROLE_ID + " FROM " + Constant.ROLES_TABLE + " WHERE " + Constant.ROLE_ROLE + " LIKE " + "\'" + roleName + "\'";
        System.out.println(getId);

        int id = -1;

        PreparedStatement prSt = getDbConenection().prepareStatement(getId);

        ResultSet resultSet = prSt.executeQuery();

        if (resultSet.next()) {
            id = resultSet.getInt(Constant.ROLE_ID);
        }


        return id;
    }

    public ObservableList<User> getAllUser(String name, String Surname) throws ClassNotFoundException, SQLException {
        ObservableList<User> usersData = FXCollections.observableArrayList();
        String getUsers = "SELECT * FROM " + Constant.USER_TABLE + " JOIN " + Constant.ROLES_TABLE + " ON " + Constant.ROLES_TABLE + "." + Constant.ROLE_ID + "=" + Constant.USER_TABLE + "." + Constant.USER_ROLE;


        if (Surname.equals("") && !name.equals("")) {
            getUsers = "SELECT * FROM " + Constant.USER_TABLE + " JOIN " + Constant.ROLES_TABLE + " ON " + Constant.ROLES_TABLE + "." + Constant.ROLE_ID + "=" + Constant.USER_TABLE + "." + Constant.USER_ROLE + " WHERE " + Constant.USER_NAME + " LIKE " + "\'" + name + "\'";
        } else if (name.equals("") && !Surname.equals("")) {
            getUsers = "SELECT * FROM " + Constant.USER_TABLE + " JOIN " + Constant.ROLES_TABLE + " ON " + Constant.ROLES_TABLE + "." + Constant.ROLE_ID + "=" + Constant.USER_TABLE + "." + Constant.USER_ROLE + " WHERE " + Constant.USER_SURNAME + " LIKE " + "\'" + Surname + "\'";
        } else if (!Surname.equals("") && !name.equals("")) {
            getUsers = "SELECT * FROM " + Constant.USER_TABLE + " JOIN " + Constant.ROLES_TABLE + " ON " + Constant.ROLES_TABLE + "." + Constant.ROLE_ID + "=" + Constant.USER_TABLE + "." + Constant.USER_ROLE + " WHERE " + Constant.USER_NAME + " LIKE " + "\'" + name + "\'" + " AND " + Constant.USER_SURNAME + " LIKE " + "\'" + Surname + "\'";
        }

        System.out.println(getUsers);
        PreparedStatement prSt = getDbConenection().prepareStatement(getUsers);

        ResultSet resultSet = prSt.executeQuery();

        while (resultSet.next()) {

            int idUserForTable = resultSet.getInt(Constant.USER_ID);
            String nameUserForTable = resultSet.getString(Constant.USER_NAME);
            String surnameUserForTable = resultSet.getString(Constant.USER_SURNAME);
            String dateUserForTable = resultSet.getString(Constant.USER_DATE);
            String loginUserForTable = resultSet.getString(Constant.USER_LOGIN);
            String passwordUserForTable = resultSet.getString(Constant.USER_PASSWORD);
            int roleUserForTable = resultSet.getInt(Constant.USER_ROLE);
            String stringRoleUserForTable = resultSet.getString(Constant.ROLES_TABLE + "." + Constant.ROLE_ROLE);

            usersData.add(new User(
                    idUserForTable,
                    nameUserForTable,
                    surnameUserForTable,
                    dateUserForTable,
                    loginUserForTable,
                    passwordUserForTable,
                    roleUserForTable,
                    stringRoleUserForTable));
        }


        return usersData;
    }

    public void resetPassword(int userID) throws ClassNotFoundException, SQLException {

        String reset = "UPDATE " + Constant.USER_TABLE + " SET " + Constant.USER_PASSWORD + "=1111" + " WHERE " + Constant.USER_ID + "=" + "?";

        PreparedStatement prSt = getDbConenection().prepareStatement(reset);

        prSt.setString(1, Integer.toString(userID));

        prSt.executeUpdate();


    }

    public void deleteUser(int userId) throws ClassNotFoundException, SQLException {

        String delete = "DELETE FROM " + Constant.USER_TABLE + " WHERE " + Constant.USER_ID + " LIKE " + userId;

        PreparedStatement prSt = getDbConenection().prepareStatement(delete);

        prSt.executeUpdate();

    }

    public User chekUser(String login, String password) throws ClassNotFoundException, SQLException {
        User user = null;

        String chek = "SELECT * FROM " + Constant.USER_TABLE + " JOIN " + Constant.ROLES_TABLE + " ON " + Constant.ROLES_TABLE + "." + Constant.ROLE_ID + "=" + Constant.USER_TABLE + "." + Constant.USER_ROLE + " WHERE " + Constant.USER_LOGIN + " LIKE " + "\'" + login + "\'" + " AND " + Constant.USER_PASSWORD + " LIKE " + "\'" + password + "\'";
        System.out.println(chek);
        PreparedStatement prSt = getDbConenection().prepareStatement(chek);

        ResultSet resultSet = prSt.executeQuery();

        while (resultSet.next()) {

            int idUserForTable = resultSet.getInt(Constant.USER_ID);
            String nameUserForTable = resultSet.getString(Constant.USER_NAME);
            String surnameUserForTable = resultSet.getString(Constant.USER_SURNAME);
            String dateUserForTable = resultSet.getString(Constant.USER_DATE);
            String loginUserForTable = resultSet.getString(Constant.USER_LOGIN);
            String passwordUserForTable = resultSet.getString(Constant.USER_PASSWORD);
            int roleUserForTable = resultSet.getInt(Constant.USER_ROLE);
            String stringRoleUserForTable = resultSet.getString(Constant.ROLES_TABLE + "." + Constant.ROLE_ROLE);

            user = new User(
                    idUserForTable,
                    nameUserForTable,
                    surnameUserForTable,
                    dateUserForTable,
                    loginUserForTable,
                    passwordUserForTable,
                    roleUserForTable,
                    stringRoleUserForTable);
        }
        return user;
    }


}
