package at.htlkaindorf.exa_102_zodiacsign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.htlkaindorf.exa_102_zodiacsign.bl.ZodiacAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recView;

    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recView.setAdapter(new ZodiacAdapter());
    }
}
