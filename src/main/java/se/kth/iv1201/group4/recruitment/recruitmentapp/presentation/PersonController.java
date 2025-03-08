package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.PersonService;

import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;


/**
 * Controller class for handling person-related requests.
 */
//@RestController
@Controller
//@RequestMapping("/person")
@SessionAttributes("username")
public class PersonController {

    private final PersonService personService;
    /**
     * Constructor for PersonController.
     *
     * @param personService Service for handling person-related operations.
     */
    public PersonController(PersonService personService ){
        this.personService = personService;
    }

    /*@PostMapping("/login")
    public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO){
        return null;
    }*/

    /**
     * Handles GET requests to the dashboard page.
     *
     * @param model Model to hold attributes for the view.
     * @return The name of the dashboard view.
     */
    @GetMapping("/dashboard")
    public String dashboardPage(Model model/*, @SessionAttribute("username") String username*/){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  // Get current logged-in username
        System.out.println("username:" + username);


        model.addAttribute("username", username);  // Add username to the model
        return "dashboard";
    }











    
    
}
