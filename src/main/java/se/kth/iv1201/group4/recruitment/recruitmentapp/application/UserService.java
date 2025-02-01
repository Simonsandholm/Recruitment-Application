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
            throw new RuntimeException("Email already in use bitchass.");
        }

        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                             dto.getPersonNumber(), dto.getUsername(),
                             passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }




    
}
