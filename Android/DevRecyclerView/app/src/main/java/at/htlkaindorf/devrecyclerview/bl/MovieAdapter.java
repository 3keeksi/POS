package at.htlkaindorf.devrecyclerview.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.devrecyclerview.MovieHolder;
import at.htlkaindorf.devrecyclerview.R;
import at.htlkaindorf.devrecyclerview.beans.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
    private List<Movie> movieList = Arrays.asList(
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150),
            new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228),
            new Movie("Star Wars", YearMonth.of(1980, 9), 228),
            new Movie("Spectre", YearMonth.of(2015, 11), 150)
    );

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        TextView tvMoviename = view.findViewById(R.id.tvMoviename);
        TextView tvDuration = view.findViewById(R.id.tvDuration);
        TextView tvReleased = view.findViewById(R.id.tvReleased);
        return new MovieHolder(view, tvMoviename, tvDuration, tvReleased);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.getTvMoviename().setText(movie.getName());
        holder.getTvDuration().setText(movie.getDurationString());
        holder.getTvReleased().setText(movie.getRelasedString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
