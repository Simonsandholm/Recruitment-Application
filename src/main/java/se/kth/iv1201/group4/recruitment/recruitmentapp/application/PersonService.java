package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;


import java.util.Collections;


@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterDTO dto) {
        if (personRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use bitchass.");
        }

        Person person = new Person();
        personRepository.save(person);
    }

    public boolean validateUser(LoginDTO dto){
        Person person = personRepository.findByUsername(dto.getUsername());
        if (person != null){
            if (person.getPassword().equals(dto.getPassword())){

                return true;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(),

                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Set user role
        );
    }




    
}
