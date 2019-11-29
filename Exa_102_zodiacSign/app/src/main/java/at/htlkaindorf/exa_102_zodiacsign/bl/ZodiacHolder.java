package at.htlkaindorf.exa_102_zodiacsign.bl;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_102_zodiacsign.MainActivity;
import at.htlkaindorf.exa_102_zodiacsign.R;

public class ZodiacHolder extends RecyclerView.ViewHolder {

    private RelativeLayout tvContainer;
    private ImageView img;
    private TextView tvName;
    private TextView tvDates;

    public ZodiacHolder(@NonNull View itemView, RelativeLayout tvContainer, ImageView img, TextView tvName, TextView tvDates) {
        super(itemView);
        this.tvContainer = tvContainer;
        this.tvContainer.setOnClickListener(this::onClick);

        this.img = img;
        this.tvName = tvName;
        this.tvDates = tvDates;
    }

    public void onClick(View v) {
        String url = MainActivity.main.getString(R.string.wikipedia_url, tvName.getText());
        Intent viewIntent = new Intent("android.intent.action.VIEW",
                Uri.parse(url));
        MainActivity.main.startActivity(viewIntent);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDates() {
        return tvDates;
    }

    public void setTvDates(TextView tvDates) {
        this.tvDates = tvDates;
    }

    public RelativeLayout getTvContainer() {
        return tvContainer;
    }

    public void setTvContainer(RelativeLayout tvContainer) {
        this.tvContainer = tvContainer;
    }
}