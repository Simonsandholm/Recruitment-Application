package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;

import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        userRepository.save(user); // .save is inherited from JpaRepository
    }



    
}
