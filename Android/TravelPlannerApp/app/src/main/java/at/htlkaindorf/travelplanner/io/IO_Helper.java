package at.htlkaindorf.travelplanner.io;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.htlkaindorf.travelplanner.bl.Trip;

public class IO_Helper {
    // city - country - country_code - startDate - duration in days
    public static Map<String, List<Trip>> loadTrips(AssetManager am) {
        List<String> countries = new ArrayList<>();
        try {
            HashMap<String, List<Trip>> map = new HashMap<>();
            InputStream is = am.open("travel_data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            br.lines()
                    .skip(4)
                    .forEach(l -> {
                Trip trip = new Trip(l);
                if(!countries.contains(trip.getCountry())) {
                    map.put(trip.getCountry(), new ArrayList<>());
                    countries.add(trip.getCountry());
                }
                map.get(trip.getCountry()).add(trip);
            });
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
