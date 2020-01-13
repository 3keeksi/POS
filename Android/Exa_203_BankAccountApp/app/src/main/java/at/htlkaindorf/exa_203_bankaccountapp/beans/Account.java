package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Account implements Parcelable {
    protected String iban;
    protected double balance;
    protected float interest;

    public Account(String iban, double balance, float interest) {
        this.iban = iban;
        this.balance = balance;
        this.interest = interest;
    }

    protected Account(Parcel in) {
        iban = in.readString();
        balance = in.readDouble();
        interest = in.readFloat();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iban);
        dest.writeDouble(balance);
        dest.writeFloat(interest);
    }

    public double getAvailable() {
        if (this instanceof StudentAccount) {
            return this.getBalance();
        } else if (this instanceof GiroAccount) {
            GiroAccount giro = (GiroAccount) this;
            return this.getBalance() + giro.getOverdraft();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getIban().equals(account.getIban());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIban());
    }
}
