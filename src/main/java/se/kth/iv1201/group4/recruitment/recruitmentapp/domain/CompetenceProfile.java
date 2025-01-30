package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "competence_profile")
public class CompetenceProfile {
    @Id
    @Column(name = "competence_profile_id")
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
