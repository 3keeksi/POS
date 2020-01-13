package at.htlkaindorf.exa_206_pethome.beans;

import android.net.Uri;

import java.time.LocalDate;

import at.htlkaindorf.exa_206_pethome.enums.CatColor;
import at.htlkaindorf.exa_206_pethome.enums.Gender;

public class Cat extends Pet {
    private CatColor color;
    private transient Uri pictureUri;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatColor color, Uri pictureUri) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureUri = pictureUri;
    }
}
