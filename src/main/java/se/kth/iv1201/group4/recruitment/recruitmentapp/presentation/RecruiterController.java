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
import java.util.stream.Collectors;

//import se.kth.iv1201.group4.recruitment.recruitmentapp.application.UserService;

/**
 * Controller for handling recruiter to list and change the status of the applications.
 */

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    private final PersonService personService;


    /**
     * Constructor to initialize the RecruiterController with the required services.
     *
     * @param recruiterService The service that provides recruiter-specific business logic
     * @param personService The service that provides person-specific business logic
     */

    public RecruiterController(RecruiterService recruiterService, PersonService personService) {
        this.recruiterService = recruiterService;
        this.personService = personService;
    }

    /**
     * Displays the  list of applications and their statuses.
     * @param model The model to hold attributes for the view
     * @return The name of the view template to render
     */

    @GetMapping("")
    public String recruiterPage(Model model/*, @SessionAttribute("username") String username*/) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        List<Person> persons = recruiterService.getAllCompetencePersons();
        Map<Integer, String> personStatusMap = recruiterService.getAllPersonStatus();

        model.addAttribute("persons", persons);
        model.addAttribute("personStatusMap", personStatusMap);

        return "recruiter";
    }

    /**
     * Displays the application page of a specific person based on their personID.
     * @param model The model to hold attributes for the view
     * @param personID The ID of the person whose profile is to be displayed
     * @return The name of the view template to render
     */

    @GetMapping("/profile/{personID}")
    public String profilePage(Model model, @PathVariable int personID){
        Person person = personService.getPersonById(personID);
        Map<Integer, String> personStatusMap = recruiterService.getAllPersonStatus();


        model.addAttribute("personStatusMap", personStatusMap);
        model.addAttribute("person", person);
        return "/profile";
    }
}
