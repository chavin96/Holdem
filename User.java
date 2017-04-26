package com.ifhampain.holdem;

/**
 * Created by Ifham Pain on 4/26/2017.
 */
public class User {
    // private variables
    int id;
    String email;
    String password;

    // empty constructor
    public User() {

    }

    // all parameters in constructor
    public User(int id, String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters for all fields
    public int get_id() {
        return id;
    }

    public String get_email() {
        return email;
    }

    public String get_password() {
        return password;
    }

    // Setters for all fields
    public void set_id(int id) {
        this.id = id;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public void set_password(String password) {
        this.password = password;
    }
}

