package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import jakarta.persistence.Tuple;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Availability;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.CompetenceProfile;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.AvailabilityRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceProfileRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling business logic related to recruiters,
 * such as fetching availability, competence profiles, person status, and more.
 */

@Service
public class RecruiterService {
    private final AvailabilityRepository availabilityRepository;
    private final CompetenceRepository competenceRepository;
    private final CompetenceProfileRepository competenceProfileRepository;
    private final PersonRepository personRepository;

    /**
     * Constructor for injecting necessary repositories.
     *
     * @param availabilityRepository    Repository for availability data
     * @param competenceRepository      Repository for competence data
     * @param competenceProfileRepository Repository for competence profiles
     * @param personRepository          Repository for person data
     */

    public RecruiterService(AvailabilityRepository availabilityRepository, CompetenceRepository competenceRepository, CompetenceProfileRepository competenceProfileRepository, PersonRepository personRepository) {
        this.availabilityRepository = availabilityRepository;
        this.competenceRepository = competenceRepository;
        this.competenceProfileRepository = competenceProfileRepository;
        this.personRepository = personRepository;
    }

    /**
     * Retrieves all availability records, sorted by personId in ascending order.
     *
     * @return A list of all availability records
     */

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll(Sort.by(Sort.Direction.ASC, "personId"));
    }

    /**
     * Retrieves all competence profiles, sorted by personId in ascending order.
     *
     * @return A list of all competence profiles
     */

    public List<CompetenceProfile> getAllCompetenceProfiles(){
        return competenceProfileRepository.findAll(Sort.by(Sort.Direction.ASC, "personId"));
    }

    /**
     * Retrieves a map of person IDs to their status.
     * The status is obtained from the competence profiles.
     *
     * @return A map with person IDs as keys and their statuses as values
     */

    public Map<Integer, String> getAllPersonStatus() {
        //List<Tuple> personStatus = competenceProfileRepository.findPersonIdsAndStatuses();
        List<Tuple> personStatus = competenceProfileRepository.findPersonIdsAndStatuses();
        Map<Integer, String> personStatusMap = personStatus.stream()
                .collect(Collectors.toMap(tuple -> (Integer) tuple.get(0), tuple -> (String) tuple.get(1)));
        //personStatus = personStatus.stream().distinct().collect(Collectors.toList());

        return personStatusMap;
    }

    /**
     * Retrieves all persons who have competence profiles associated with them.
     *
     * @return A list of persons who have competence profiles
     */

    public List<Person> getAllCompetencePersons(){
        return personRepository.findPersonsWithCompetenceProfiles();
    }



}
