package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.validator.DuplicateEmail;
import edu.miu.cs545.group01.online.market.validator.FieldMatch;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class UserRegistrationModel {

    @NotBlank
    @Size(min = 3, max = 20, message = "{registration.username}")
    private String userName;

    @NotBlank(message = "{registration.email}")
    @Email
    @DuplicateEmail
    private String email;

    @NotEmpty(message = "{registration.password}")
    private String password;
    @NotEmpty(message = "{registration.confirm}")
    private String confirmPassword;

    public UserRegistrationModel() {
    }

    public UserRegistrationModel(String userName, String email, String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
