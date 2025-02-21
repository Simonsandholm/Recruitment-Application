package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "competence")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // Constructors
    public Competence() {}

    public Competence(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
