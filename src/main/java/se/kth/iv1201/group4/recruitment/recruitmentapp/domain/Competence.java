package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "competence")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Competence() {
    }

    public Competence(String name) {
        this.name = name;
    }

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
