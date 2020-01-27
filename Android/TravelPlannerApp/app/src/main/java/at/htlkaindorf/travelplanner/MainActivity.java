package at.htlkaindorf.travelplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.travelplanner.bl.TripAdapter;
import at.htlkaindorf.travelplanner.io.IO_Helper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTrips = findViewById(R.id.rvTrips);
        rvTrips.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTrips.setAdapter(new TripAdapter(IO_Helper.loadTrips(getAssets()), this));
    }
}
