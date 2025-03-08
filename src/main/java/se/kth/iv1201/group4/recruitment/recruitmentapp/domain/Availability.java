package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "availability")
// This allows us to skip implementing getters and setters
@Getter
@Setter
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id", nullable = false, updatable = false)
    private Integer availabilityId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "from_date", nullable = false)
    private String fromDate;

    @Column(name = "to_date", nullable = false)
    private String toDate;

    /**
     * Convenience constructor
     */
    public Availability(Person person, String fromDate, String toDate) {
        this.person = person;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
