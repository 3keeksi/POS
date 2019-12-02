package at.htlkaindorf.exa_203_bankaccountapp.bl;

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
    private MainActivity main;

    public AccountAdapter(List<Account> accounts, MainActivity main) {
        this.accounts.addAll(accounts);
        this.filtered.addAll(accounts);
        this.main = main;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);

        RelativeLayout rl = view.findViewById(R.id.lDetails);
        ImageView ivProfile = view.findViewById(R.id.ivProfile);
        TextView tvIBAN = view.findViewById(R.id.tvIBAN);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvAvMoney = view.findViewById(R.id.tvAvailableMoney);

        return new AccountViewHolder(view, main, rl, ivProfile, tvTitle, tvIBAN, tvAvMoney);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = filtered.get(position);

        int id = 0;
        if (account instanceof StudentAccount) {
            id = R.drawable.book;
        } else {
            id = R.drawable.credit_card;
        }
        holder.getIvProfile().setImageResource(id);

        holder.getTvIBAN().setText(account.getIban());
        // TODO rest
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void filterAccounts(String type) {
        filtered.clear();
        filtered.addAll(accounts);

        if(type.equalsIgnoreCase("student")) {
            filtered.removeIf(a -> !(a instanceof StudentAccount));
        } else if (type.equalsIgnoreCase("giro")){
            filtered.removeIf(a -> !(a instanceof GiroAccount));
        }
        this.notifyDataSetChanged();
    }
}
