package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.RegisterService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;


@Controller
@RequestMapping("/user")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
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
     * The second method that is called once the user fills in the form.
     * @param dto the object holding the form information.
     * @return the dashboard view if successful.
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") @Valid RegisterDTO dto,
                           BindingResult result,
                           Model model) {

        // If validation errors exist, return to the registration form with values submitted before.
        if (result.hasErrors()) {
            return "register";
        }

        try {
            registerService.registerUser(dto);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register"; // Stay on the same page and display error
        }

        return "redirect:/user/register-success";
    }

    /**
     * A get call that is made if registering is successful.
     * @return to the dashboard
     */
    @GetMapping("/register-success")
    public String registerSuccess() {
        return "dashboard";
    }

    
}