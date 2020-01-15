package at.htlkaindorf.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.bl.IO_Helper;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rlDogs;
    private RelativeLayout rlCats;
    private List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlDogs = findViewById(R.id.rlDogs);
        rlCats = findViewById(R.id.rlCats);

        pets = IO_Helper.loadPets(getAssets());

        ArrayList<Dog> dogs = new ArrayList<>();
        pets.forEach(p -> {
            if (p instanceof Dog) dogs.add((Dog) p);
        });

        ArrayList<Cat> cats = new ArrayList<>();
        pets.forEach(p -> {
            if (p instanceof Cat) cats.add((Cat) p);
        });

        rlDogs.setOnClickListener(v -> {
            Intent intent = new Intent(this, PetList.class);
            intent.putExtra("pets", dogs);
            intent.putExtra("type", "dogs");
            startActivity(intent);
        });

        rlCats.setOnClickListener(v -> {
            Intent intent = new Intent(this, PetList.class);
            intent.putExtra("pets", cats);
            intent.putExtra("type", "cats");
            startActivity(intent);
        });
    }
}
