package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Shows a persons competence profile and years of experience for that specific competence.
 * This class is mapped to the "competence_profile" table in the database.
 */

@Entity
@Data
@Table(name = "competence_profile")
public class CompetenceProfile {
    @Id
    @Column(name = "competence_profile_id")
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "years_of_experience")
    private Float yearsOfExperience;

    @Column(name = "status")
    private String status;
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
