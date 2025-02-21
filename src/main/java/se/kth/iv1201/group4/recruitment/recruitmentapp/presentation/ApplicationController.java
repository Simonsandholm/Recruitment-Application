package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/apply")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    /**
     * Helper method to get or create a draft application for the applicant.
     * In a real app, you'd check if there's an existing, unsubmitted draft
     * instead of always creating a new one.
     */
    private Application getDraftApplication(Person applicant) {
        // For now, we create a new draft every time:
        return applicationService.createNewApplication(applicant);
    }

    /**
     * GET /apply
     *
     * Shows the application form (application-form.html).
     * Expects that you're authenticated (so Principal is not null).
     */
    @GetMapping
    public String showApplicationForm(Model model, Principal principal) {
        // 1. Retrieve the currently logged-in user (by username)
        Person applicant = personRepository.findByUsername(principal.getName());

        // 2. Get all available competences
        List<Competence> competences = competenceRepository.findAll();

        // 3. Retrieve or create the draft application
        Application draft = getDraftApplication(applicant);

        // 4. Add attributes for the view
        model.addAttribute("applicant", applicant);
        model.addAttribute("competences", competences);
        model.addAttribute("application", draft);

        // 5. Return the Thymeleaf template name (application-form.html in /templates)
        return "application-form";
    }

    /**
     * POST /apply/add-competence
     *
     * Handles adding a competence to the user's application draft.
     * Redirects back to /apply to show the updated form.
     */
    @PostMapping("/add-competence")
    public String addCompetence(@RequestParam Integer competenceId,
                                @RequestParam Double yearsOfExperience,
                                Principal principal) {
        Person applicant = personRepository.findByUsername(principal.getName());
        Application draft = getDraftApplication(applicant);

        // Find the selected Competence from DB
        Competence competence = competenceRepository
                .findById(competenceId)
                .orElse(null);

        if (competence != null) {
            applicationService.addCompetenceToApplication(draft, competence, yearsOfExperience);
        }

        // Redirect back to /apply to see the updated draft
        return "redirect:/apply";
    }

    /**
     * POST /apply/add-availability
     *
     * Handles adding an availability (fromDate, toDate) to the application draft.
     */
    @PostMapping("/add-availability")
    public String addAvailability(@RequestParam String fromDate,
                                  @RequestParam String toDate,
                                  Principal principal) {
        Person applicant = personRepository.findByUsername(principal.getName());
        Application draft = getDraftApplication(applicant);

        applicationService.addAvailabilityToApplication(draft, fromDate, toDate);
        return "redirect:/apply";
    }

    /**
     * POST /apply/submit
     *
     * Marks the draft application as submitted and shows confirmation.
     */
    @PostMapping("/submit")
    public String submitApplication(Principal principal) {
        Person applicant = personRepository.findByUsername(principal.getName());
        Application draft = getDraftApplication(applicant);

        applicationService.submitApplication(draft);

        // Render application-confirmation.html (make sure this template exists)
        return "application-confirmation";
    }

    /**
     * POST /apply/cancel
     *
     * Cancels the application process and redirects to /home (or anywhere else).
     */
    @PostMapping("/cancel")
    public String cancelApplication() {
        return "redirect:/home";
    }
}
