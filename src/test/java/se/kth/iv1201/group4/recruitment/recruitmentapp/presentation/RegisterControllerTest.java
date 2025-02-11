package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterControllerTest {

    // Creating a mock version of the classes for testing. Good for skipping logic and just test
    // The mocks behave as defined in the "when", return what we want.
    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    // Inject the mocks above into userController, this bypasses dependencies
    @InjectMocks
    private RegisterController registerController;

    // Is needed to initialize the mocks before the tests, otherwise we get a nullPointer
    public RegisterControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRegisterPage() {
        String register = registerController.register(model);
        assertThat(register).isEqualTo("register");

        // Check that the controller adds a registerDTO object to the model for thymeleaf to use
        verify(model).addAttribute(eq("registerDTO"), any(RegisterDTO.class));
    }


    @Test
    public void testRegisterWithValidationErrors() {
        RegisterDTO invalidUser = new RegisterDTO("", "", "", "", "", "");

        // The mock object bindingResult is always true here
        when(bindingResult.hasErrors()).thenReturn(true);

        String response = registerController.register(invalidUser, bindingResult, model);
        assertThat(response).isEqualTo("register");
    }

    @Test
    public void testRegisterSuccessPage() {
        String dashboard = registerController.registerSuccess();
        assertThat(dashboard).isEqualTo("dashboard");
    }

}
