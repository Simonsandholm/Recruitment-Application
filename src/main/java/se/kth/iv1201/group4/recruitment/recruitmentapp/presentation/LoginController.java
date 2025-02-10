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
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;



//@RestController
@Controller
@RequestMapping("/login")
@SessionAttributes("username")
public class LoginController {

    private final PersonService personService;

    public LoginController(PersonService personService ){
        this.personService = personService;
    }

    /*@PostMapping("/login")
    public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO){
        return null;
    }*/
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



    /*@PostMapping("")
    //public String login(@RequestParam String username, @RequestParam String password) {
    public String login(@ModelAttribute("LoginDTO") LoginDTO dto, Model model) {
        //boolean isAuthenticated = userService.authenticate(username, password);

        //System.out.println(username);
        //System.out.println(password);

        if(personService.validateUser(dto)){
            System.out.println(dto.getUsername());
            System.out.println(dto.getPassword());
            System.out.println(model);
            model.addAttribute("username", dto.getUsername());
            return "redirect:/person/dashboard";
            //return "/dashboard";
        }


        System.out.println(dto.getUsername());
        System.out.println(dto.getPassword());
        System.out.println(model);
        model.addAttribute("loginError", true);
        return "/login2";

    }*/






}

