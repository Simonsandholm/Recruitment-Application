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


//@RestController
@Controller
@RequestMapping("/person")
@SessionAttributes("username")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService ){
        this.personService = personService;
    }

    /*@PostMapping("/login")
    public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO){
        return null;
    }*/


    @GetMapping("/dashboard")
    public String dashboardPage(Model model/*, @SessionAttribute("username") String username*/){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  // Get current logged-in username
        System.out.println("username:" + username);


        model.addAttribute("username", username);  // Add username to the model
        return "/dashboard";
    }

    @GetMapping("/recruiter")
    public String recruiterPage(Model model/*, @SessionAttribute("username") String username*/){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  // Get current logged-in username
        System.out.println("username:" + username);


        model.addAttribute("username", username);  // Add username to the model
        return "/recruiter";
    }




    //@GetMapping("/register")
    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
    personService.registerUser(dto);
    return ResponseEntity.ok("User regged very nice i like how much.");
}




    
    
}
