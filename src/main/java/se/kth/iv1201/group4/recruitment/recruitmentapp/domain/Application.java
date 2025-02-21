package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person person;  // The applicant who submitted this application

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetenceProfile> competenceProfiles;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Availability> availabilityPeriods;

    private boolean submitted;  // Tracks if the application has been finalized

    public Application() {}

    public Application(Person person) {
        this.person = person;
        this.submitted = false;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<CompetenceProfile> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(List<CompetenceProfile> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }

    public List<Availability> getAvailabilityPeriods() {
        return availabilityPeriods;
    }

    public void setAvailabilityPeriods(List<Availability> availabilityPeriods) {
        this.availabilityPeriods = availabilityPeriods;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
