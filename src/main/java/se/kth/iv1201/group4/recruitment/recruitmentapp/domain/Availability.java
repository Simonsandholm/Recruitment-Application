package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)  // Foreign key reference to Application
    private Application application;

    @Column(name = "from_date", nullable = false)
    private String fromDate;

    @Column(name = "to_date", nullable = false)
    private String toDate;

    // Constructors
    public Availability() {}

    public Availability(Application application, String fromDate, String toDate) {
        this.application = application;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
