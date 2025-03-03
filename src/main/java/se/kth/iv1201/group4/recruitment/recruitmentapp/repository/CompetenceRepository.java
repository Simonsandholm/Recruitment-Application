package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.*;

// JpaRepository<User, Integer> means this repository is managing User entities
// and the data type for the primary key is Integer

/**
 * The repository that is mapped to the competence table.
 */

@Repository
public interface CompetenceRepository extends JpaRepository<Competence,  Integer> {
    
}

