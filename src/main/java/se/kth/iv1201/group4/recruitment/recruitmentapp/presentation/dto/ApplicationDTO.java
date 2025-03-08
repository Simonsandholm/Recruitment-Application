package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Application;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Competence;
import se.kth.iv1201.group4.recruitment.recruitmentapp.domain.Person;

import java.util.List;

/**
 * DTO that encapsulates data needed for the application form.
 */
@Getter
@Setter
@AllArgsConstructor
public class ApplicationDTO {
    private Person applicant;
    private List<Competence> competences;
    private Application application;
}
