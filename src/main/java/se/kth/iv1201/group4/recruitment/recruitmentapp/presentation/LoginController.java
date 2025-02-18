package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.PersonService;
//import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;



//@RestController
/**
 * Controller class handling login-related requests.
 */
@Controller
@RequestMapping("/login")
//@SessionAttributes("username")
/**
 * Constructor for LoginController.
 *
 * @param personService Service for handling person-related operations.
 */
public class LoginController {

    private final PersonService personService;

    public LoginController(PersonService personService ){
        this.personService = personService;
    }

    /**
     * Handles GET requests to the login page.
     *
     * @param model Model to hold attributes for the view.
     * @return The name of the login view.
     */
    @GetMapping("")
    public String loginPage(Model model) {
        //generatePassword();
        model.addAttribute("LoginDTO", new LoginDTO());
        return "login";
    }


    /*public  void generatePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "mySecretPassword";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);
    }*/










}

