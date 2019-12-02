package at.htlkaindorf.exa_203_bankaccountapp.beans;

import androidx.annotation.NonNull;

public class Account {
    protected String iban;
    protected double balance;
    protected float interest;

    public Account(String iban, double balance, float interest) {
        this.iban = iban;
        this.balance = balance;
        this.interest = interest;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
