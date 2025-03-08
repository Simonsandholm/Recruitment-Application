package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;

import java.util.List;

/**
 * Repository for handling database operations related to competence profiles.
 */
@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long> {

    /**
     * Retrieves all competence profiles associated with a specific person.
     *
     * @param personId The ID of the person.
     * @return A list of competence profiles for the person.
     */
    List<CompetenceProfile> findByPerson_Id(Integer personId);

    /**
     * Retrieves all competence profiles linked to a specific Person entity.
     *
     * @param person The person entity.
     * @return A list of competence profiles for the given person.
     */
    List<CompetenceProfile> findByPerson(Person person);
}
