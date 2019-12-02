package at.htlkaindorf.exa_203_bankaccountapp.beans;

public class GiroAccount extends Account {
    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }
}
