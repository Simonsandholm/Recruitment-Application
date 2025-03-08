package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;

import java.util.List;

/**
 * Repository for handling database operations related to availability periods.
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    /**
     * Retrieves all availability periods for a given person.
     *
     * @param personId The ID of the person.
     * @return A list of availability periods associated with the person.
     */
    List<Availability> findByPerson_Id(Integer personId);
}
