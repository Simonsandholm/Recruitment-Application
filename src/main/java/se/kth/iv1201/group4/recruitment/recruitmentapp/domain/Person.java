package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;

/**
 * Represents a person in the system.
 * This entity contains personal information, including name, email, username, and role.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String lastName;

    @Column(name = "pnr", nullable = false, unique = true)
    private String personNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Default constructor
    public Person() {}

    // Constructor with all required fields
    public Person(String firstName, String lastName, String personNumber,
                  String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPersonNumber() { return personNumber; }
    public void setPersonNumber(String personNumber) { this.personNumber = personNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
