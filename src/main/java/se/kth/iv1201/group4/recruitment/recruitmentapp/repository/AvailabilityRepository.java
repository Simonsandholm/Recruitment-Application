package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    // Add custom query methods if needed
}
