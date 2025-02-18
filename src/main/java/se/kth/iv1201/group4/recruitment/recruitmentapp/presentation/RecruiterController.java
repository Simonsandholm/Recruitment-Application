package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.PersonService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;

//import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
@Controller
@RequestMapping("/recruiter")
public class RecruiterController {


    @GetMapping("")
    public String recruiterPage(Model model/*, @SessionAttribute("username") String username*/) {
        // Get current logged-in username
         // Add username to the model
        return "/recruiter";
    }
}
