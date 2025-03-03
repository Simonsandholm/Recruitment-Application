package se.kth.iv1201.group4.recruitment.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Role;

/**
 * The repository that is mapped to the role table.
 */

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
