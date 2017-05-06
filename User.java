package com.example.chavin.holdem;

/**
 * Created by Ifham Pain on 5/6/2017.
 */

public class User {
    // private variables
    int id;
    String email;
    String password;
    boolean gender;

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
    public boolean get_gender(){
        return gender;
    }

    // Setters for all fields
    public void set_id(int id) {
        this.id = id;
    }

    public void set_email(String email) {
        this.email = email;
    }
    public void set_password(String password){
        this.password = password;
    }
    public void set_gender(boolean gender){
        this.gender = gender;
    }

}