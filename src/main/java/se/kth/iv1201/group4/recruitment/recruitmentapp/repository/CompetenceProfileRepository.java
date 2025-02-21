package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;

@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long> {
    // Add custom queries if needed
}
