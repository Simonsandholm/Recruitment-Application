package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class RegisterRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    /*@Test
    public void testSaveUser() {
        // Create a test user and save it to db
        User testUser = new User("test_name", "test_lastname", "test@mail.com", "123456", "test_username", "abc123");
        //registerRepository.save(testUser);

        // Now we check if user really is saved
        //User foundUser = registerRepository.findByUsername("test_username");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("test_username");
        assertThat(foundUser.getEmail()).isEqualTo("test@mail.com");
    }*/

    @Test
    public void testIfUserExists() {
        User testUser = new User("test_name", "test_lastname", "test@mail.com", "123456", "test_username", "abc123");
        //registerRepository.save(testUser);

        boolean exists = personRepository.existsByEmail(testUser.getEmail());
        assertThat(exists).isTrue();

    }



}
