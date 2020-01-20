package at.htlkaindorf.exa_206_pethome.beans;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.time.LocalDate;

import at.htlkaindorf.exa_206_pethome.enums.CatColor;
import at.htlkaindorf.exa_206_pethome.enums.Gender;

public class Cat extends Pet {
    private CatColor color;
    private transient Uri pictureUri;

    public Cat() {
        super();
    }

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatColor color, Uri pictureUri) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureUri = pictureUri;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public Uri getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(Uri pictureUri) {
        this.pictureUri = pictureUri;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(pictureUri.toString());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        pictureUri = Uri.parse((String) in.readObject());
    }
}
