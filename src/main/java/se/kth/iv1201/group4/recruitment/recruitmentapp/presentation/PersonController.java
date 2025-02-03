package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.PersonService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;


//@RestController
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService ){
        this.personService = personService;
    }

    /*@PostMapping("/login")
    public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO){
        return null;
    }*/
    @GetMapping("/login2")
    public String loginPage(Model model) {
        model.addAttribute("LoginDTO", new LoginDTO());
        return "login2";
    }
    @PostMapping("/login2")
    //public String login(@RequestParam String username, @RequestParam String password) {
    public String login(@ModelAttribute("LoginDTO") LoginDTO dto, Model model) {
        //boolean isAuthenticated = userService.authenticate(username, password);

        //System.out.println(username);
        //System.out.println(password);

        if(personService.validateUser(dto)){
            System.out.println(dto.getUsername());
            System.out.println(dto.getPassword());
            System.out.println(model);
            return "/dashboard";
        }


        System.out.println(dto.getUsername());
        System.out.println(dto.getPassword());
        System.out.println(model);

        return "/login2";

    }
    //@GetMapping("/register")
    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
    personService.registerUser(dto);
    return ResponseEntity.ok("User regged very nice i like how much.");
}


    
    
}
