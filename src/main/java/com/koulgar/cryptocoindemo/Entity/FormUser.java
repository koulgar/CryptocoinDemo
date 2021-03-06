package com.koulgar.cryptocoindemo.Entity;

import com.koulgar.cryptocoindemo.Validator.FieldMatch;
import com.koulgar.cryptocoindemo.Validator.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({@FieldMatch(first = "password", second = "matchingPassword", message = "The passwords must match")})
public class FormUser {

    private int id;

    @NotBlank(message = "cannot be empty")
    private String username;

    @NotBlank(message = "cannot be empty")
    private String password;

    @NotBlank(message = "cannot be empty")
    private String matchingPassword;

    @NotBlank(message = "cannot be empty")
    private String firstName;

    @NotBlank(message = "cannot be empty")
    private String lastName;

    @ValidEmail
    @NotBlank(message = "cannot be empty")
    private String email;


    public FormUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
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