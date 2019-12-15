package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.annotation.SuppressLint;
import android.os.Parcel;

public class GiroAccount extends Account {
    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }

    @SuppressLint("NewApi")
    protected GiroAccount(Parcel in) {
        super(in);
        overdraft = in.readDouble();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new GiroAccount(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new GiroAccount[size];
        }
    };

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @SuppressLint("NewApi")
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(overdraft);
    }
}
