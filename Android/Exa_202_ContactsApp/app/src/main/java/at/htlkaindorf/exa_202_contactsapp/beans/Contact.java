package at.htlkaindorf.exa_202_contactsapp.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Contact implements Parcelable {
    private String firstname;
    private String lastname;
    private String language;
    private char gender;
    private Uri picture;
    private String phoneNumber;

    public Contact(String firstname, String lastname, String language, char gender, String picture, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.language = language;
        this.gender = gender;
        this.picture = Uri.parse(picture);
        this.phoneNumber = phoneNumber;
    }

    public Contact(String line) {
        String[] split = line.split(",");
        this.firstname = split[1];
        this.lastname = split[2];
        this.language = split[3];
        this.gender = split[4].charAt(0);
        this.picture = Uri.parse(split[5]);
        this.phoneNumber = split[6];
    }

    protected Contact(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        language = in.readString();
        gender = (char) in.readInt();
        picture = in.readParcelable(Uri.class.getClassLoader());
        phoneNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return getGender() == contact.getGender() &&
                getFirstname().equals(contact.getFirstname()) &&
                getLastname().equals(contact.getLastname()) &&
                getLanguage().equals(contact.getLanguage()) &&
                getPicture().equals(contact.getPicture()) &&
                Objects.equals(getPhoneNumber(), contact.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getLanguage(), getGender(), getPicture(), getPhoneNumber());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(language);
        dest.writeString(String.valueOf(gender));
        dest.writeParcelable(picture, flags);
        dest.writeString(phoneNumber);
    }
}
