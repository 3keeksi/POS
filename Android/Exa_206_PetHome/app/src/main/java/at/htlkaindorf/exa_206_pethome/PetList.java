package at.htlkaindorf.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.bl.PetAdapter;

public class PetList extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rvPets;
    private ImageButton btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
        tvTitle = findViewById(R.id.tvTitle);
        rvPets = findViewById(R.id.rvPets);
        btBack = findViewById(R.id.btBack);

        Intent intent = getIntent();
        ArrayList<Pet> pets = (ArrayList<Pet>) intent.getSerializableExtra("pets");

        String type = intent.getStringExtra("type");
        if (type.equalsIgnoreCase("dogs")) {
            tvTitle.setText("Dog list");
        } else {
            tvTitle.setText("Cat list");
        }

        PetAdapter pa = new PetAdapter(pets);
        rvPets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvPets.setAdapter(pa);

        btBack.setOnClickListener(v -> finish());
    }
}
