package at.htlkaindorf.exa_206_pethome.beans;

import java.io.Serializable;
import java.time.LocalDate;

import at.htlkaindorf.exa_206_pethome.enums.Gender;

public class Pet implements Serializable {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Pet() {
    }

    public Pet(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
