package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.RegisterRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Handles business logic related to user registration.
 * This service interacts with the repository and encodes passwords before saving users.
 */
@Service
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for RegisterService.
     *
     * @param registerRepository  The repository handling database operations for users.
     * @param passwordEncoder The encoder used to hash passwords before saving.
     */
    public RegisterService(RegisterRepository registerRepository, PasswordEncoder passwordEncoder) {
        this.registerRepository = registerRepository;
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
    @Transactional
    public void registerUser(RegisterDTO dto) {
        if (registerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }

        if (registerRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username is already taken.");
        }

        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.getPersonNumber(), dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()));

        System.out.println("service " + user.getUsername());

        registerRepository.save(user);
    }
}
