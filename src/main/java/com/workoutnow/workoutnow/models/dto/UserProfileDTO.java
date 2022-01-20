package com.workoutnow.workoutnow.models.dto;

import com.workoutnow.workoutnow.models.User;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserProfileDTO {

    private User user;

    @NotBlank
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters.")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters.")
    private String lastName;

    @Email(message = "Please enter a vaild email address.")
    private String email;


    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
