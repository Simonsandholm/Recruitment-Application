package se.kth.iv1201.group4.recruitment.recruitmentapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a role in the system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
