package at.htlkaindorf.exa_206_pethome.bl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetHolder extends RecyclerView.ViewHolder {
    private ImageView ivPic;
    private TextView tvName;
    private TextView tvDate;
    private TextView tvOpt;
    private ImageView ivGender;

    public PetHolder(@NonNull View itemView, ImageView ivPic, TextView tvName, TextView tvDate, TextView tvOpt, ImageView ivGender) {
        super(itemView);
        this.ivPic = ivPic;
        this.tvName = tvName;
        this.tvDate = tvDate;
        this.tvOpt = tvOpt;
        this.ivGender = ivGender;
    }

    public ImageView getIvPic() {
        return ivPic;
    }

    public void setIvPic(ImageView ivPic) {
        this.ivPic = ivPic;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvOpt() {
        return tvOpt;
    }

    public void setTvOpt(TextView tvOpt) {
        this.tvOpt = tvOpt;
    }

    public ImageView getIvGender() {
        return ivGender;
    }

    public void setIvGender(ImageView ivGender) {
        this.ivGender = ivGender;
    }
}
