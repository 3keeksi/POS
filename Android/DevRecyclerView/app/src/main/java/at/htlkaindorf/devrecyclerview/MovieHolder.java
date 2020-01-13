package at.htlkaindorf.devrecyclerview;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieHolder extends RecyclerView.ViewHolder {
    private TextView tvMoviename;
    private TextView tvDuration;
    private TextView tvReleased;

    public MovieHolder(@NonNull View itemView, TextView tvMoviename, TextView tvDuration, TextView tvReleased) {
        super(itemView);
        this.tvMoviename = tvMoviename;
        this.tvDuration = tvDuration;
        this.tvReleased = tvReleased;
    }

    public TextView getTvMoviename() {
        return tvMoviename;
    }

    public void setTvMoviename(TextView tvMoviename) {
        this.tvMoviename = tvMoviename;
    }

    public TextView getTvDuration() {
        return tvDuration;
    }

    public void setTvDuration(TextView tvDuration) {
        this.tvDuration = tvDuration;
    }

    public TextView getTvReleased() {
        return tvReleased;
    }

    public void setTvReleased(TextView tvReleased) {
        this.tvReleased = tvReleased;
    }
}
