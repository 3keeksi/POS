package at.htlkaindorf.exa_203_bankaccountapp.beans;

public class StudentAccount extends Account{
    private boolean debitCard;

    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }
}
