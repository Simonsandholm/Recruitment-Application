package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;


import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.*;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.PersonStatusDTO;

import java.util.List;

// JpaRepository<User, Integer> means this repository is managing User entities
// and the data type for the primary key is Integer
/**
 * Repository interface for managing {@link CompetenceProfile} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries.
 */

@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile,  Integer> {

    /**
     * Finds a list of {@link CompetenceProfile} entities by the given person ID.
     *
     * @param personId The ID of the person whose competence profile records are to be retrieved.
     * @return A list of {@link CompetenceProfile} entities associated with the specified person ID
     */

    List<CompetenceProfile> findByPersonId(Integer personId);


    /**
     * Retrieves a list of tuples containing distinct person IDs and their associated status from the {@link CompetenceProfile} entity.
     * @return A list of tuples, each containing a person ID and their status
     */

    @Query("SELECT DISTINCT cp.personId, cp.status FROM CompetenceProfile cp ORDER BY cp.personId ASC")
    //@Query("SELECT DISTINCT new src/main/java/se/kth/iv1201/group4/recruitment/recruitmentapp/presentation/dto/PersonStatusDTO(cp.personId, cp.status) FROM CompetenceProfile cp")
    List<Tuple> findPersonIdsAndStatuses();
}
