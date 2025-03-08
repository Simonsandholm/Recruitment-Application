package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.*;

import java.util.Optional;

/**
 * Service class responsible for handling business logic related to job applications.
 * Provides methods to manage applications, including retrieval, creation, and submission.
 */
@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CompetenceRepository competenceRepository;
    private final AvailabilityRepository availabilityRepository;
    private final CompetenceProfileRepository competenceProfileRepository;
    private final PersonRepository personRepository;

    /**
     * Constructs an ApplicationService with the required repositories.
     *
     * @param applicationRepository     Repository for storing applications.
     * @param competenceRepository      Repository for retrieving competence data.
     * @param availabilityRepository    Repository for handling availability data.
     * @param competenceProfileRepository Repository for competence profiles.
     * @param personRepository          Repository for retrieving user details.
     */
    public ApplicationService(ApplicationRepository applicationRepository,
                              CompetenceRepository competenceRepository,
                              AvailabilityRepository availabilityRepository,
                              CompetenceProfileRepository competenceProfileRepository,
                              PersonRepository personRepository) {
        this.applicationRepository = applicationRepository;
        this.competenceRepository = competenceRepository;
        this.availabilityRepository = availabilityRepository;
        this.competenceProfileRepository = competenceProfileRepository;
        this.personRepository = personRepository;
    }

    /**
     * Finds an existing application for the given user (either draft or submitted).
     *
     * @param username The username of the applicant.
     * @return An optional containing the application if found, otherwise empty.
     * @throws IllegalArgumentException If the applicant is not found.
     */
    public Optional<Application> findApplication(String username) {
        Person applicant = personRepository.findByUsername(username);
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant not found for username: " + username);
        }
        return applicationRepository.findByPerson(applicant);
    }

    /**
     * Retrieves an existing draft application for the user or creates a new one if none exists.
     *
     * @param username The username of the applicant.
     * @return A draft application for the applicant.
     * @throws IllegalArgumentException If the applicant is not found.
     */
    public Application getDraftApplication(String username) {
        Person applicant = personRepository.findByUsername(username);
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant not found.");
        }

        // Check if the user already has a draft application
        Optional<Application> existingApp = applicationRepository.findByPerson(applicant);

        // Return an existing draft if found, otherwise create and save a new one
        return existingApp.stream()
                .filter(app -> !app.isSubmitted())
                .findFirst()
                .orElseGet(() -> applicationRepository.save(new Application(applicant)));
    }

    /**
     * Processes the application submission in a single transaction.
     * Adds competence, availability, and finalizes the application.
     * If an application is already submitted, it does nothing.
     *
     * @param username           The username of the applicant.
     * @param competenceId       The ID of the selected competence.
     * @param yearsOfExperience  The applicant's years of experience in the selected competence.
     * @param fromDate           The availability start date.
     * @param toDate             The availability end date.
     * @throws RuntimeException If the specified competence is not found.
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitAll(String username, Integer competenceId, Double yearsOfExperience,
                          String fromDate, String toDate) {
        // Retrieve or create a draft application
        Application draft = getDraftApplication(username);

        // If the draft is already submitted, do nothing
        if (draft.isSubmitted()) {
            return;
        }

        // Retrieve the applicant
        Person applicant = draft.getPerson();

        // Find the selected competence
        Competence competence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new RuntimeException("Competence not found with ID: " + competenceId));

        // Create and save a competence profile
        CompetenceProfile cp = new CompetenceProfile(applicant, competence, yearsOfExperience);
        competenceProfileRepository.save(cp);

        // Create and save an availability entry
        Availability availability = new Availability(applicant, fromDate, toDate);
        availabilityRepository.save(availability);

        // Mark the application as submitted and save it
        draft.setSubmitted(true);
        applicationRepository.save(draft);
    }
}
