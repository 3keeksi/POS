package at.htlkaindorf.exa_203_bankaccountapp.bl;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_203_bankaccountapp.MainActivity;
import at.htlkaindorf.exa_203_bankaccountapp.R;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    private List<Account> accounts = new ArrayList<>();
    private List<Account> filtered = new ArrayList<>();
    private ArrayList<String> ibans = new ArrayList<>();
    private MainActivity main;

    public AccountAdapter(List<Account> accounts, MainActivity main) {
        this.accounts.addAll(accounts);
        this.filtered.addAll(accounts);
        this.main = main;
        this.accounts.forEach(account -> ibans.add(account.getIban()));
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);

        RelativeLayout item = view.findViewById(R.id.item);
        ImageView ivProfile = view.findViewById(R.id.ivProfile);
        TextView tvIBAN = view.findViewById(R.id.tvIBAN);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvMoney = view.findViewById(R.id.tvMoney);
        TextView tvAvMoney = view.findViewById(R.id.tvAvailableMoney);

        return new AccountViewHolder(view, main, item, ivProfile, tvTitle, tvIBAN, tvMoney, tvAvMoney, ibans);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = filtered.get(position);

        holder.setAccount(account);
        holder.setIbans(ibans);

        setAccountItem(
                account,
                holder.getTvTitle(),
                holder.getTvAvMoney(),
                holder.getTvMoney(),
                holder.getIvProfile(),
                holder.getTvIBAN());
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }


    public void filterAccounts(String type) {
        filtered.clear();
        filtered.addAll(accounts);

        if (type.equalsIgnoreCase("student")) {
            filtered.removeIf(a -> !(a instanceof StudentAccount));
        } else if (type.equalsIgnoreCase("giro")) {
            filtered.removeIf(a -> !(a instanceof GiroAccount));
        }
        this.notifyDataSetChanged();
    }

    public void transfer(Account account, String iban, double amount) {
        Account realAcc = null;
        Account toTransfer = null;
        for (Account acc : accounts) {
            if (acc.equals(account)) {
                realAcc = acc;
            } else if (acc.getIban().equalsIgnoreCase(iban)) {
                toTransfer = acc;
                break;
            }
        }
        Log.d("main", toTransfer+"");
        Log.d("main", realAcc+"");
        if (toTransfer != null && realAcc != null) {
            realAcc.setBalance(realAcc.getBalance() - amount);
            toTransfer.setBalance(toTransfer.getBalance() + amount);
            this.notifyDataSetChanged();
        }
    }

    public static void setAccountItem(Account account, TextView tvTitle, TextView tvAvMoney, TextView tvMoney, ImageView ivProfile, TextView tvIBAN) {
        int id = 0;
        if (account instanceof StudentAccount) {
            tvTitle.setText("Student-Account");
            tvAvMoney.setText(String.format("€ %,10.2f", account.getAvailable()));
            id = R.drawable.book;

        } else {
            GiroAccount giro = (GiroAccount) account;
            tvTitle.setText("Giro-Account");
            tvAvMoney.setText(String.format("€ %,10.2f", account.getAvailable()));
            id = R.drawable.credit_card;
        }
        setMoney(tvMoney, account.getBalance());
        ivProfile.setImageResource(id);
        tvIBAN.setText(account.getIban());
    }

    public static void setMoney(TextView tv, double money) {
        tv.setText(String.format("€ %,10.2f", money));
        if (money < 0) {
            tv.setTextColor(Color.RED);
        } else {
            tv.setTextColor(Color.GREEN);
        }
    }
}
