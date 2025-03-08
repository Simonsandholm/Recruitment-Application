package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Application;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;

import java.util.List;
import java.util.Optional;

/**
 * Repository for handling database operations related to job applications.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    /**
     * Retrieves the latest draft application for a given person that has not yet been submitted.
     *
     * @param person The applicant.
     * @return An optional containing the draft application if found.
     */
    Optional<Application> findFirstByPersonAndSubmittedFalseOrderByIdDesc(Person person);

    /**
     * Finds a submitted application for a specific user.
     *
     * @param person The applicant.
     * @return An optional containing the application if found.
     */
    Optional<Application> findByPerson(Person person);

    /**
     * Retrieves all applications associated with a specific person.
     *
     * @param id The ID of the person.
     * @return A list of applications for the person.
     */
    List<Application> findByPerson_Id(Integer id);

    /**
     * Finds a submitted application for a specific user.
     *
     * @param person The applicant.
     * @return An optional containing the submitted application if found.
     */
    Optional<Application> findByPersonAndSubmittedTrue(Person person);

    /**
     * Retrieves the most recent submitted application for a specific user.
     *
     * @param person The applicant.
     * @return An optional containing the latest submitted application if found.
     */
    Optional<Application> findFirstByPersonAndSubmittedTrueOrderByIdDesc(Person person);
}
