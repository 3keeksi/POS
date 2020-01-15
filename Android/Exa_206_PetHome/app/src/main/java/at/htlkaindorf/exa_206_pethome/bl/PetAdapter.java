package at.htlkaindorf.exa_206_pethome.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_206_pethome.R;
import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.enums.Gender;

public class PetAdapter extends RecyclerView.Adapter<PetHolder> {
    private List<Pet> pets;
    private List<Pet> filtered = new ArrayList<>();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
        filtered.addAll(pets);
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);

        ImageView ivPic = view.findViewById(R.id.ivPic);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvOpt = view.findViewById(R.id.tvOpt);
        ImageView ivGender = view.findViewById(R.id.ivGender);

        return new PetHolder(view, ivPic, tvName, tvDate, tvOpt, ivGender);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = filtered.get(position);
        String opt;

        if (pet instanceof Dog) {
            Dog dog = (Dog) pet;
            opt = "Size: " + dog.getSize().toString().toLowerCase();
        } else {
            Cat cat = (Cat) pet;
            opt = "Color: " + cat.getColor().toString().toLowerCase();
            Picasso.get().load(cat.getPictureUri()).into(holder.getIvPic());
        }

        holder.getTvName().setText(pet.getName());
        holder.getTvDate().setText(dtf.format(pet.getDateOfBirth()));
        holder.getTvOpt().setText(opt);

        if(pet.getGender() == Gender.FEMALE) {
            holder.getIvGender().setBackgroundResource(R.drawable.female);
        } else {
            holder.getIvGender().setBackgroundResource(R.drawable.male);
        }
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }
}
