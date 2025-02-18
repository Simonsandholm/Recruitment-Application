package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.*;

import java.util.List;

// JpaRepository<User, Integer> means this repository is managing User entities
// and the data type for the primary key is Integer


@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,  Integer> {
    List<Availability> findByPersonId(Integer personId);
}
