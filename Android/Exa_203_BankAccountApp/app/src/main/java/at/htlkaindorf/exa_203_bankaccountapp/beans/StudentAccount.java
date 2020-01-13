package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class StudentAccount extends Account implements Parcelable {
    private boolean debitCard;

    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    @SuppressLint("NewApi")
    protected StudentAccount(Parcel in) {
        super(in);
        debitCard = in.readByte() != 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new StudentAccount(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new StudentAccount[size];
        }
    };

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }

    @SuppressLint("NewApi")
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (debitCard ? 1 : 0));
    }
}
