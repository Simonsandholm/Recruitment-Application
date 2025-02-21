package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Competence;
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {  // Changed Integer to Long

    // Optional: Find a competence by name if needed
    Competence findByName(String name);
}
