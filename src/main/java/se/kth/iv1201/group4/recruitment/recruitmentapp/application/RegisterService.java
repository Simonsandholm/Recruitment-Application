package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Role;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.RegisterRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Handles business logic related to user registration.
 * This service interacts with the repository and encodes passwords before saving users.
 */
@Service
public class RegisterService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;

    /**
     * Constructor for RegisterService.
     *
     * @param personRepository The repository handling database operations for users.
     * @param roleRepository The repository for retrieving roles.
     * @param passwordEncoder The encoder used to hash passwords before saving.
     */
    public RegisterService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     * Checks if the email and username are already in use before saving the user.
     * If either is taken, an exception is thrown.
     *
     * @param dto The data transfer object containing user registration details.
     * @throws RuntimeException if the email or username is already in use.
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void registerUser(RegisterDTO dto) {
        if (personRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }

        if (personRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username is already taken.");
        }

        // Fetch applicant role with role_id = 2
        Role applicantRole = roleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Default role applicant not found"));

        // Create person with assigned role
        Person person = new Person(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPersonNumber(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getUsername()
        );

        person.setRole(applicantRole);

        System.out.println("service " + person.getUsername());

        personRepository.save(person);
    }
}
