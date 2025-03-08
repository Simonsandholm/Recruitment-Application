package se.kth.iv1201.group4.recruitment.recruitmentapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

import java.util.Collections;

/**
 * Service class responsible for loading user details from the database.
 * Implements Spring Security's {@link UserDetailsService} to support authentication.
 */
@Service
public class PersonUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Loads a user by username from the database.
     *
     * @param username The username of the user.
     * @return User details for authentication.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(), // Stored as a BCrypt hash
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
