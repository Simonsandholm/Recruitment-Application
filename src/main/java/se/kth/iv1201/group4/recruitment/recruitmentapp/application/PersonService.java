package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Role;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.LoginDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto.RegisterDTO;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

import java.util.List;

/**
 * Service for managing users, including registration, validation, and user details loading.
 */
@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     *
     * @param dto The registration details.
     * @throws RuntimeException if the email is already in use.
     */
    public void registerUser(RegisterDTO dto) {
        if (personRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use.");
        }

        Person person = new Person();
        personRepository.save(person);
    }

    /**
     * Validates a user's credentials during login.
     *
     * @param dto The login details.
     * @return true if the user is valid, false otherwise.
     */
    public boolean validateUser(LoginDTO dto) {
        Person person = personRepository.findByUsername(dto.getUsername());
        if (person != null && person.getPassword().equals(dto.getPassword())) {
            return true;
        }
        return false;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Person getPersonById(Integer id){
        return personRepository.findById(id).orElse(null);
    }


    /**
     * Loads user details by username.
     *
     * @param username The username of the user.
     * @return The UserDetails object for the given username.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        Role role = person.getRole();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role.getName()));
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(),
                authorities
        );
    }
}
