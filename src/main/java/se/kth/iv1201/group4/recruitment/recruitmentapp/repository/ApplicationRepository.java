package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Application;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    // Find all applications submitted by a specific user (if needed)
    List<Application> findByPersonId(Long personId);
}
