package at.htlkaindorf.exa_203_bankaccountapp.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_203_bankaccountapp.R;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);

        RelativeLayout rl = view.findViewById(R.id.lDetails);
        ImageView ivProfile = view.findViewById(R.id.ivProfile);
        TextView tvIBAN = view.findViewById(R.id.tvIBAN);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvAvMoney = view.findViewById(R.id.tvAvailableMoney);

        return new AccountViewHolder(view, rl, ivProfile, tvTitle, tvIBAN, tvAvMoney);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
