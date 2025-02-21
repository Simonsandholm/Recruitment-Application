package se.kth.iv1201.group4.recruitment.recruitmentapp.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

import java.util.Collections;

@Service
public class PersonUserDetailsService implements UserDetailsService {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(), // stored as a BCrypt hash
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
