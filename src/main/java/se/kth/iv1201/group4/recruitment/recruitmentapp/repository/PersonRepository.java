package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;

// JpaRepository<User, Integer> means this repository is managing User entities
// and the data type for the primary key is Integer
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Check if email is already used
    boolean existsByEmail(String email);

    // Check if username is already used
    boolean existsByUsername(String username);

    // Find user by email, used for login or retrieval
    Person findByEmail(String email);

    // Find user by username
    Person findByUsername(String username);
}

