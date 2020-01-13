package at.htlkaindorf.devrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.htlkaindorf.devrecyclerview.bl.MovieAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recView.setAdapter(new MovieAdapter());
    }
}
