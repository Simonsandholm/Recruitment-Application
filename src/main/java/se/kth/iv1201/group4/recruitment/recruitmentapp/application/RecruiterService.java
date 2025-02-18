package se.kth.iv1201.group4.recruitment.recruitmentapp.application;

import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.AvailabilityRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceProfileRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.CompetenceRepository;
import se.kth.iv1201.group4.recruitment.recruitmentapp.repository.PersonRepository;

public class RecruiterService {
    private final AvailabilityRepository availabilityRepository;
    private final CompetenceRepository competenceRepository;
    private final CompetenceProfileRepository competenceProfileRepository;

    public RecruiterService(AvailabilityRepository availabilityRepository, CompetenceRepository competenceRepository, CompetenceProfileRepository competenceProfileRepository) {
        this.availabilityRepository = availabilityRepository;
        this.competenceRepository = competenceRepository;
        this.competenceProfileRepository = competenceProfileRepository;
    }

}
