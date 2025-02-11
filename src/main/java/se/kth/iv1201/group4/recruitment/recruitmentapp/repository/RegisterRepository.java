package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;

// JpaRepository<User, Integer> means this repository is managing User entities
// and the data type for the primary key is Integer

/**
 * The User repository that is mapped to the person table.
 */
@Repository
public interface RegisterRepository extends JpaRepository<Person, Integer> {
    // Check if email is already used

    /**
     *
     * @param email of the user.
     * @return true if a user exists with the given email.
     */
    boolean existsByEmail(String email);

    /**
     *
     * @param username of the user.
     * @return true if a user exists with the given username.
     */
    boolean existsByUsername(String username);

    /**
     *
     * @param email of the user.
     * @return the user with the given email.
     */
    User findByEmail(String email);

    /**
     *
     * @param username of the user.
     * @return the user with the given username.
     */
    User findByUsername(String username);

}

