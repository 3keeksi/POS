package at.htlkaindorf.travelplanner.bl;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.MainActivity;
import at.htlkaindorf.travelplanner.R;

public class TripAdapter extends RecyclerView.Adapter<TripHolder> {
    private Map<String, List<Trip>> countryTrips;
    private Map<Integer, String> mapper = new HashMap<>();
    private MainActivity main;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TripAdapter(Map<String, List<Trip>> countryTrips, MainActivity main) {
        this.countryTrips = countryTrips;
        int index = 0;
        List<String> list = countryTrips.keySet().stream().sorted().collect(Collectors.toList());
        for (String s : list) {
            mapper.put(index, s);
            index++;
        }
        this.main = main;
    }

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);

        TextView tvCountry = view.findViewById(R.id.tvCountry);
        TextView tvNumberOfTrips = view.findViewById(R.id.tvNumberOfTrips);

        return new TripHolder(view, tvCountry, tvNumberOfTrips, main);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        ArrayList<Trip> trips = (ArrayList<Trip>) countryTrips.get(mapper.get(position));
        holder.setTrips(trips);

        String country = trips.get(0).getCountry() + " (" + trips.get(0).getCountryCode() + ")";
        holder.getTvCountry().setText(country);

        String bottom = trips.size() + " trip - ";
        bottom += trips.stream().mapToInt(Trip::getDuration).sum() + " days (";

        List<LocalDate> startDates = new ArrayList<>();
        List<LocalDate> endDates = new ArrayList<>();
        trips.forEach(t -> {
            startDates.add(t.getStartDate());
            endDates.add(t.getStartDate().plusDays(t.getDuration()));
        });
        startDates.sort(LocalDate::compareTo);
        endDates.sort(LocalDate::compareTo);
        bottom += startDates.get(0).format(dtf) + " - " + endDates.get(endDates.size()-1).format(dtf) + ")";

        holder.getTvNumberOfTrips().setText(bottom);
    }

    @Override
    public int getItemCount() {
        return mapper.size();
    }
}
