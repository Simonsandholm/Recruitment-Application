package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competence_profile")
@Getter
@Setter
@NoArgsConstructor
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id", nullable = false, updatable = false)
    private Integer competenceProfileId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "competence_id", nullable = false)
    private Competence competence;

    @Column(name = "years_of_experience", nullable = false)
    private Double yearsOfExperience;

    public CompetenceProfile(Person person, Competence competence, Double yearsOfExperience) {
        this.person = person;
        this.competence = competence;
        this.yearsOfExperience = yearsOfExperience;
    }


}
