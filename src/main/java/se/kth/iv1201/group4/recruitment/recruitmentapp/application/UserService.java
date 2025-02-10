package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Handles business logic related to user registration.
 * This service interacts with the repository and encodes passwords before saving users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService.
     *
     * @param userRepository  The repository handling database operations for users.
     * @param passwordEncoder The encoder used to hash passwords before saving.
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username is already taken.");
        }

        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.getPersonNumber(), dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()));

        System.out.println("service " + user.getUsername());

        userRepository.save(user);
    }
}
