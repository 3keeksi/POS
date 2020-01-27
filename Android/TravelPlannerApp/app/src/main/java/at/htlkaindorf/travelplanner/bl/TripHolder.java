package at.htlkaindorf.travelplanner.bl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.htlkaindorf.travelplanner.CountryOverviewActivity;
import at.htlkaindorf.travelplanner.MainActivity;

public class TripHolder extends RecyclerView.ViewHolder {
    private TextView tvCountry, tvNumberOfTrips;
    private MainActivity main;
    private ArrayList<Trip> trips;

    public TripHolder(@NonNull View itemView, TextView tvCountry, TextView tvNumberOfTrips, MainActivity main) {
        super(itemView);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(main.getApplicationContext(), CountryOverviewActivity.class);
            intent.putExtra("trips", trips);
            main.startActivity(intent);
        });
        this.tvCountry = tvCountry;
        this.tvNumberOfTrips = tvNumberOfTrips;
        this.main = main;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public TextView getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(TextView tvCountry) {
        this.tvCountry = tvCountry;
    }

    public TextView getTvNumberOfTrips() {
        return tvNumberOfTrips;
    }

    public void setTvNumberOfTrips(TextView tvNumberOfTrips) {
        this.tvNumberOfTrips = tvNumberOfTrips;
    }
}
