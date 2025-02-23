package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_person_id")  // Matches DB schema
    private Person person;

    @Column(name = "submitted", nullable = false)
    private boolean submitted;

    // Constructors
    public Application() {}

    public Application(Person person, boolean submitted) {
        this.person = person;
        this.submitted = submitted;
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

    /*public List<CompetenceProfile> getCompetenceProfiles() {
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
    }*/

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
