package at.htlkaindorf.exa_202_contactsapp.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Student implements Parcelable {
    private String name;
    private int catNr;
    private LocalDate dateOfBirth;
    private Uri uri;

    protected Student(Parcel in) {
        name = in.readString();
        catNr = in.readInt();
        uri = in.readParcelable(Uri.class.getClassLoader());
        dateOfBirth = LocalDate.ofEpochDay(in.readLong());
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(catNr);
        dest.writeParcelable(uri, flags);
        dest.writeLong(dateOfBirth.toEpochDay());
    }
}
