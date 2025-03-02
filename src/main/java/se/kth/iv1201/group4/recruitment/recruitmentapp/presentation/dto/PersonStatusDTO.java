package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation.dto;

public class PersonStatusDTO {
    private Integer personId;
    private String status;

    public PersonStatusDTO(Integer personId, String status) {
        this.personId = personId;
        this.status = status;
    }

    // Getters & Setters

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
