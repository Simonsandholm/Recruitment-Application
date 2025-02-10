package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Person number is required.")
    private String personNumber;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;



    public RegisterDTO(String firstName, String lastName, String mail, String socialSecurityNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = mail;
        this.personNumber = socialSecurityNumber;
        this.username = username;
        this.password = password;

    }

    public RegisterDTO() {}

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPersonNumber() { return personNumber; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
