package at.htlkaindorf.travelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.bl.Trip;

public class CountryOverviewActivity extends AppCompatActivity {
    private TextView tvCountry,
        tvOveriew;
    private SearchView svCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_overview);

        tvCountry = findViewById(R.id.tvCountry);
        tvOveriew = findViewById(R.id.tvOverview);
        svCity = findViewById(R.id.svCity);

        Intent intent = getIntent();
        ArrayList<Trip> trips = (ArrayList<Trip>) intent.getSerializableExtra("trips");

        trips.sort(Comparator.comparing(Trip::getStartDate));

        tvCountry.setText(trips.get(0).getCountry());

        setOverview(trips);

        svCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Trip> filtered = trips.stream()
                        .filter(t -> t.getCity().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList());
                setOverview(filtered);
                return true;
            }
        });

    }
        private void setOverview(List<Trip> list) {
            String overview = list
                    .stream()
                    .map(t -> t.getCity() +
                            ": "+
                            t.getStartDate() +
                            " - " +
                            t.getStartDate().plusDays(t.getDuration()) +
                            "\n")
            .collect(Collectors.joining());
            tvOveriew.setText(overview);
        }
}
