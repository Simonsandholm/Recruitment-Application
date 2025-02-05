package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }


    /**
     * The initial method that is called, resulting in register.html getting rendered.
     * @param model is where Thymeleaf can get data from the backend.
     * @return the register.html file that is rendered.
     */
    @GetMapping("/register")
    public String register(Model model) {
        // Add to the model so thymeleaf can access the object
        // Sends an empty registerDTO object to thymeleaf
        System.out.println(model);
        model.addAttribute("registerDTO", new RegisterDTO());
        System.out.println("first register, user entered the endpoint");
        return "register";

    }

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") @Valid RegisterDTO dto, BindingResult result, Model model) {
        System.out.println("second " + dto.getEmail());
        System.out.println(model);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            return "register";
        }

        userService.registerUser(dto);

        System.out.println("second register" + dto.getUsername());
        return "redirect:/user/register-success";
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "dashboard";
    }



    /*@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("User regged very nice i like how much.");
    }*/




    
    
}
