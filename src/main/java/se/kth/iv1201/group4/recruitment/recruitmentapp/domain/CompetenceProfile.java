package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "competence_profile")
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)  // Foreign key reference to Application
    private Application application;

    @ManyToOne
    @JoinColumn(name = "competence_id", nullable = false)  // Foreign key reference to Competence
    private Competence competence;

    @Column(name = "years_of_experience", nullable = false)
    private Double yearsOfExperience;

    // Constructors
    public CompetenceProfile() {}

    public CompetenceProfile(Application application, Competence competence, Double yearsOfExperience) {
        this.application = application;
        this.competence = competence;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
