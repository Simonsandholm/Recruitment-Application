package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a person in the system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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

    /**
     * Creates a new person with the given details.
     *
     * @param firstName    The first name of the person.
     * @param lastName     The last name of the person.
     * @param personNumber The personal identification number.
     * @param email        The email address of the person.
     * @param password     The person's password.
     * @param username     The username of the person.
     */
    public Person(String firstName, String lastName, String personNumber,
                  String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
