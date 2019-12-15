package at.htlkaindorf.exa_203_bankaccountapp.bl;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.htlkaindorf.exa_203_bankaccountapp.MainActivity;
import at.htlkaindorf.exa_203_bankaccountapp.Transaction;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;

public class AccountViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout item;
    private ImageView ivProfile;
    private TextView tvTitle;
    private TextView tvIBAN;
    private TextView tvMoney;
    private TextView tvAvMoney;
    private Account account;
    private ArrayList<String> ibans;

    public AccountViewHolder(@NonNull View itemView, MainActivity main, RelativeLayout item, ImageView ivProfile, TextView tvTitle, TextView tvIBAN, TextView tvMoney, TextView tvAvMoney, ArrayList<String> ibans) {
        super(itemView);
        this.item = item;
        this.ivProfile = ivProfile;
        this.tvTitle = tvTitle;
        this.tvIBAN = tvIBAN;
        this.tvMoney = tvMoney;
        this.tvAvMoney = tvAvMoney;

        item.setOnLongClickListener(v -> {
            Intent intent = new Intent(main, Transaction.class);

            intent.putExtra("account", account);
            intent.putExtra("ibans", ibans);

            main.startActivityForResult(intent, 2);
            return true;
        });
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<String> getIbans() {
        return ibans;
    }

    public void setIbans(ArrayList<String> ibans) {
        this.ibans = ibans;
    }

    public TextView getTvMoney() {
        return tvMoney;
    }

    public void setTvMoney(TextView tvMoney) {
        this.tvMoney = tvMoney;
    }

    public RelativeLayout getItem() {
        return item;
    }

    public void setItem(RelativeLayout item) {
        this.item = item;
    }

    public ImageView getIvProfile() {
        return ivProfile;
    }

    public void setIvProfile(ImageView ivProfile) {
        this.ivProfile = ivProfile;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvIBAN() {
        return tvIBAN;
    }

    public void setTvIBAN(TextView tvIBAN) {
        this.tvIBAN = tvIBAN;
    }

    public TextView getTvAvMoney() {
        return tvAvMoney;
    }

    public void setTvAvMoney(TextView tvAvMoney) {
        this.tvAvMoney = tvAvMoney;
    }
}
