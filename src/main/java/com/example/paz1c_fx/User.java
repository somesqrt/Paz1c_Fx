package com.example.paz1c_fx;

public class User {
    private int id;
    private String Name;
    private String Surname;
    private String Date;
    private String login;
    private String password;
    private int role;
    private String stringRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringRole() {
        return stringRole;
    }

    public void setStringRole(String stringRole) {
        this.stringRole = stringRole;
    }

    public User(int id, String name, String surname, String date, String login, String password, int role, String stringRole) {
        this.id = id;
        Name = name;
        Surname = surname;
        Date = date;
        this.login = login;
        this.password = password;
        this.role = role;
        this.stringRole = stringRole;
    }

    public User(String name, String surname, String date, String login, String password, int role) {

        Name = name;
        Surname = surname;
        Date = date;
        this.login = login;
        this.password = password;
        this.role = role;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
