package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    @SpringBootTest is good for integration tests
    where different components like service and repository interact.
    This is needed because beans run in scopes and will not be present in test scopes.

    @TestInstance reduces object creation overhead
    by keeping one test instance for ALL methods (TestInstance.Lifecycle.PER_CLASS).
    Because by default JUnit creates new instance for each test method.
*/
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    // Inserting duplicate email on purpose
    @Test
    public void testSaveDuplicateEmail() {
        RegisterDTO firstUser = new RegisterDTO
                ("Varzpet",
                        "khachatryan",
                        "iambest@mail.com",
                        "123456",
                        "varzpet",
                        "abc123"
                );
        RegisterDTO secondUser = new RegisterDTO
                ("Chingiz",
                        "Abbasov",
                        "iambest@mail.com",
                        "123456",
                        "chingiz",
                        "abc123"
                );
        assertThrows(RuntimeException.class, () -> userService.registerUser(secondUser));
    }

    // Inserting duplicate username on purpose
    @Test
    public void testSaveDuplicateUsername() {
        RegisterDTO firstUser = new RegisterDTO
                ("Varzpet",
                        "khachatryan",
                        "iambest1@mail.com",
                        "123456",
                        "itIsMine",
                        "abc123"
                );
        RegisterDTO secondUser = new RegisterDTO
                ("Chingiz",
                        "Abbasov",
                        "iambest2@mail.com",
                        "123456",
                        "itIsMine",
                        "abc123"
                );
        assertThrows(RuntimeException.class, () -> userService.registerUser(secondUser));
    }

    @Test
    public void testRegisterValidUser() {
        RegisterDTO newUser = new RegisterDTO
                (
                        "Valid", "User", "valid@mail.com", "987654", "validuser", "securepassword"
                );
        assertDoesNotThrow(() -> userService.registerUser(newUser));
    }

    @Test
    public void testRegisterBlankFieldsMustFail() {
        RegisterDTO invalidUser = new RegisterDTO(
                "", "", "", "", "", ""
        );

        assertThrows(RuntimeException.class, () -> userService.registerUser(invalidUser));
    }


}
