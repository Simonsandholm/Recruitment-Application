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


@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile,  Integer> {
    List<CompetenceProfile> findByPersonId(Integer personId);

    //@Query("SELECT DISTINCT cp.personId, cp.status FROM CompetenceProfile cp ORDER BY cp.personId ASC")
    @Query("SELECT DISTINCT new src/main/java/se/kth/iv1201/group4/recruitment/recruitmentapp/presentation/dto/PersonStatusDTO(cp.personId, cp.status) FROM CompetenceProfile cp")
    List<PersonStatusDTO> findPersonIdsAndStatuses();
}
