package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.User;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.UserRepository;


@Service
public class PersonService {

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




    
}
