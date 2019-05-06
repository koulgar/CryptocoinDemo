package com.koulgar.cryptocoindemo.Entity;

import com.koulgar.cryptocoindemo.Validator.FieldMatch;
import com.koulgar.cryptocoindemo.Validator.ValidEmail;

import javax.validation.constraints.NotBlank;

public class UpdateUser {

    private int id;

    @NotBlank(message = "cannot be empty")
    private String username;

    @NotBlank(message = "cannot be empty")
    private String firstName;

    @NotBlank(message = "cannot be empty")
    private String lastName;

    @ValidEmail
    @NotBlank(message = "cannot be empty")
    private String email;


    public UpdateUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}