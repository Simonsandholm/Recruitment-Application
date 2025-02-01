package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;

import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;



//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }

    /*@PostMapping("/login")
    public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO){
        return null;
    }*/
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //@GetMapping("/register")
    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
    userService.registerUser(dto); 
    return ResponseEntity.ok("User regged very nice i like how much.");
}


    
    
}
