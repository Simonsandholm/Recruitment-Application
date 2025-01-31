package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {

    @NotBlank(message = "First name is required boi")
    private String firstName;

    @NotBlank(message = "Last name is required bitch")
    private String lastName;

    @Email(message = "Invalid email format u piece of shit")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Person number is required, karabakh bizimdir")
    private String personNumber;

    @NotBlank(message = "Username is required, ALYEV GOAT")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPersonNumber() { return personNumber; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
