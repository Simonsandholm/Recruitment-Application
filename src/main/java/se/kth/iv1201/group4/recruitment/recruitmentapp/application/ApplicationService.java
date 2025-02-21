package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Application;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Competence;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.ApplicationRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.AvailabilityRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceProfileRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceRepository;

/**
 * Service class containing business logic for handling Applications.
 */
@Service
@Transactional
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * IMPORTANT:
     * You need a separate repository for CompetenceProfile (NOT CompetenceRepository).
     */
    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    /**
     * Creates and saves a new Application for the given Person.
     *
     * @param applicant The person who is applying.
     * @return The created and saved Application.
     */
    public Application createNewApplication(Person applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null");
        }
        Application application = new Application(applicant);
        return applicationRepository.save(application);
    }

    /**
     * Adds a CompetenceProfile (with yearsOfExperience) to an existing Application.
     *
     * @param application       The existing application to link the CompetenceProfile to.
     * @param competence        The Competence to associate with this profile.
     * @param yearsOfExperience The number of years of experience for this competence.
     */
    public void addCompetenceToApplication(Application application,
                                           Competence competence,
                                           Double yearsOfExperience) {
        if (application == null || competence == null) {
            throw new IllegalArgumentException("Application and Competence must not be null");
        }

        // Make sure both the Application and Competence are persisted
        // (have non-null IDs) before creating a CompetenceProfile.
        if (application.getId() == null) {
            application = applicationRepository.save(application);
        }
        if (competence.getId() == null) {
            competence = competenceRepository.save(competence);
        }

        CompetenceProfile competenceProfile =
                new CompetenceProfile(application, competence, yearsOfExperience);

        // IMPORTANT: Use CompetenceProfileRepository, not CompetenceRepository
        competenceProfileRepository.save(competenceProfile);
    }

    /**
     * Adds an Availability record (fromDate, toDate) to an existing Application.
     *
     * @param application The application to which this availability should be added.
     * @param fromDate    The start date of availability.
     * @param toDate      The end date of availability.
     */
    public void addAvailabilityToApplication(Application application,
                                             String fromDate,
                                             String toDate) {
        if (application == null) {
            throw new IllegalArgumentException("Application must not be null");
        }
        if (application.getId() == null) {
            application = applicationRepository.save(application);
        }
        Availability availability = new Availability(application, fromDate, toDate);
        availabilityRepository.save(availability);
    }

    /**
     * Marks an Application as submitted and updates it in the database.
     *
     * @param application The application to be marked as submitted.
     */
    public void submitApplication(Application application) {
        if (application == null || application.getId() == null) {
            throw new IllegalArgumentException("Application must be persisted before submission");
        }
        application.setSubmitted(true);
        applicationRepository.save(application);
    }
}
