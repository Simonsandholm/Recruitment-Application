package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Represents the availability of a person for a specific period. A person can have multiple availabilities.
 * This class is mapped to the "availability" table in the database.
 */

@Entity
@Table(name = "availability", schema = "public") // Ensure Hibernate looks in the correct schema
@Data
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    @Column(name = "availability_id")
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    // Default constructor
    public Availability() {}

    // Parameterized constructor
    public Availability(Integer personId, Date fromDate, Date toDate) {
        this.personId = personId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
