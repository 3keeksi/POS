package at.htlkaindorf.exa_206_pethome.beans;

import java.time.LocalDate;

import at.htlkaindorf.exa_206_pethome.enums.Gender;
import at.htlkaindorf.exa_206_pethome.enums.Size;

public class Dog extends Pet {
    private Size size;

    public Dog(String name, LocalDate dateOfBirth, Gender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }
}
