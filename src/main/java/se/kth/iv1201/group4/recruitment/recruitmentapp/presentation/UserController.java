package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;

import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }
   
    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
    userService.registerUser(dto); 
    return ResponseEntity.ok("User regged very nice i like how much.");
}


    
    
}
