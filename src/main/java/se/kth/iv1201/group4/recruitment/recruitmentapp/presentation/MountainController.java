package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/mountain")
public class MountainController {

    @GetMapping("/bizimdir BRATHA WHO GIVE BLACK BELT")
    public String getMountain() {
        return "index"; // This is the html found in resources
    }
}
