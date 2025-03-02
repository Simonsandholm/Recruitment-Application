package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;
import jakarta.persistence.Tuple;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.PersonService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.RecruiterService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.PersonStatusDTO;

import java.util.List;
import java.util.Map;

//import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;
@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    private final PersonService personService;




    public RecruiterController(RecruiterService recruiterService, PersonService personService) {
        this.recruiterService = recruiterService;
        this.personService = personService;
    }

    @GetMapping("")
    public String recruiterPage(Model model/*, @SessionAttribute("username") String username*/) {
        List<Person> persons = recruiterService.getAllCompetencePersons();
        List<Availability> availabilities = recruiterService.getAllAvailabilities();
        for (int i = 0; i< availabilities.size(); i++){
            System.out.println("availability list item" +availabilities.get(i));
        }
        List<CompetenceProfile> profiles = recruiterService.getAllCompetenceProfiles();

        model.addAttribute("availabilities", availabilities);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  // Get current logged-in username

        model.addAttribute("username", username);
        model.addAttribute("competenceProfiles", profiles);
        model.addAttribute("persons", persons);

        //Map<Integer, String> personStatus = recruiterService.getAllPersonStatus();
        List<PersonStatusDTO> personStatus = recruiterService.getAllPersonStatus();
        for (int i = 0; i< personStatus.size(); i++){
            System.out.println("personstatus list item" +personStatus.get(i));
        }
        model.addAttribute("personStatus", personStatus);
        return "/recruiter";
    }
    @GetMapping("/profile/{personID}")
    public String profilePage(Model model, @PathVariable int personID){
        Person person = personService.getPersonById(personID);
        model.addAttribute("person", person);
        return "/profile";
    }
}
