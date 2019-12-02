package at.htlkaindorf.exa_203_bankaccountapp.bl;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_203_bankaccountapp.MainActivity;

public class AccountViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout lDetails;
    private ImageView ivProfile;
    private TextView tvTitle;
    private TextView tvIBAN;
    private TextView tvAvMoney;

    public AccountViewHolder(@NonNull View itemView, MainActivity main, RelativeLayout lDetails, ImageView ivProfile, TextView tvTitle, TextView tvIBAN, TextView tvAvMoney) {
        super(itemView);
        this.lDetails = lDetails;
        this.ivProfile = ivProfile;
        this.tvTitle = tvTitle;
        this.tvIBAN = tvIBAN;
        this.tvAvMoney = tvAvMoney;
    }

    public RelativeLayout getlDetails() {
        return lDetails;
    }

    public void setlDetails(RelativeLayout lDetails) {
        this.lDetails = lDetails;
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
