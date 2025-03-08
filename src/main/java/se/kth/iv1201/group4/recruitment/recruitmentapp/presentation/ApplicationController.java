package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.application.ApplicationService;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Application;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Competence;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling application-related requests.
 * Manages form display, submission, and cancellation.
 */
@Controller
@RequestMapping("/apply")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final CompetenceRepository competenceRepository;
    private final PersonRepository personRepository;

    /**
     * Constructs an ApplicationController with necessary dependencies.
     *
     * @param applicationService   Service handling application logic.
     * @param competenceRepository Repository for retrieving competence data.
     * @param personRepository     Repository for retrieving person data.
     */
    public ApplicationController(ApplicationService applicationService,
                                 CompetenceRepository competenceRepository,
                                 PersonRepository personRepository) {
        this.applicationService = applicationService;
        this.competenceRepository = competenceRepository;
        this.personRepository = personRepository;
    }

    /**
     * Handles GET requests to /apply.
     * Displays the application form if the user has no submitted application.
     * If an application has already been submitted, redirects to a confirmation page.
     *
     * @param model     Spring Model to pass data to the view.
     * @param principal Authenticated user details.
     * @return The name of the view to render.
     */
    @GetMapping
    public String showApplicationForm(Model model, Principal principal) {
        String username = principal.getName();
        Person applicant = personRepository.findByUsername(username);

        // Check if user already has an application (draft or submitted)
        Optional<Application> existing = applicationService.findApplication(username);
        if (existing.isPresent()) {
            Application app = existing.get();
            if (app.isSubmitted()) {
                // If already submitted, show confirmation page
                return "application-confirmation";
            }
            // Otherwise, it's a draft, proceed to show the form.
        }

        // No application or a draft exists – show the application form
        model.addAttribute("applicant", applicant);
        List<Competence> competences = competenceRepository.findAll();
        model.addAttribute("competences", competences);

        return "application-form";
    }

    /**
     * Handles POST requests to /apply/submit-all.
     * Processes a single-form application submission, including competence, availability, and finalization.
     * If the user already has a submitted application, this prevents duplicate entries.
     *
     * @param competenceId      ID of the selected competence.
     * @param yearsOfExperience User's experience in years.
     * @param fromDate          Availability start date.
     * @param toDate            Availability end date.
     * @param principal         Authenticated user details.
     * @return The confirmation page view.
     */
    @PostMapping("/submit-all")
    public String submitAllApplication(
            @RequestParam Integer competenceId,
            @RequestParam Double yearsOfExperience,
            @RequestParam String fromDate,
            @RequestParam String toDate,
            Principal principal
    ) {
        String username = principal.getName();

        // Call the service to handle application submission
        applicationService.submitAll(username, competenceId, yearsOfExperience, fromDate, toDate);

        // Redirect to confirmation page
        return "application-confirmation";
    }

    /**
     * Handles POST requests to /apply/cancel.
     * Cancels the application process and redirects to the home page.
     *
     * @return Redirects the user to the home page.
     */
    @PostMapping("/cancel")
    public String cancelApplication() {
        return "redirect:/home";
    }
}
