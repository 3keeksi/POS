package at.htlkaindorf.exa_102_zodiacsign.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.exa_102_zodiacsign.R;
import at.htlkaindorf.exa_102_zodiacsign.beans.ZodiacSign;

public class ZodiacAdapter extends RecyclerView.Adapter<ZodiacHolder> {
    private List<ZodiacSign> signs = Arrays.asList(
            new ZodiacSign("Aquarius", MonthDay.of(1, 21), R.drawable.aquarius),
            new ZodiacSign("Pisces", MonthDay.of(2, 18), R.drawable.pisces),
            new ZodiacSign("Aries", MonthDay.of(3, 20), R.drawable.aries),
            new ZodiacSign("Taurus", MonthDay.of(4, 20), R.drawable.aries),
            new ZodiacSign("Gemini", MonthDay.of(5, 21), R.drawable.gemini),
            new ZodiacSign("Cancer", MonthDay.of(6, 21), R.drawable.cancer),
            new ZodiacSign("Leo", MonthDay.of(7, 23), R.drawable.leo),
            new ZodiacSign("Virgo", MonthDay.of(8, 23), R.drawable.aries),
            new ZodiacSign("Libra", MonthDay.of(9, 23), R.drawable.libra),
            new ZodiacSign("Scorpius", MonthDay.of(10, 23), R.drawable.aries),
            new ZodiacSign("Sagittarius", MonthDay.of(11, 22), R.drawable.aries),
            new ZodiacSign("Capricornus", MonthDay.of(12, 22), R.drawable.capricornus)
    );

    @NonNull
    @Override
    public ZodiacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_item, parent, false);
        RelativeLayout layout = view.findViewById(R.id.tvContainer);
        ImageView img = view.findViewById(R.id.img);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDates = view.findViewById(R.id.tvDates);
        return new ZodiacHolder(view, layout, img, tvName, tvDates);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacHolder holder, int position) {
        ZodiacSign sign = signs.get(position);
        holder.getImg().setImageResource(sign.getDrawableId());

        ZodiacSign other = signs.get((position + 1) % signs.size());

        holder.getTvDates().setText(sign.getDuration(other.getStartDate()));
        holder.getTvName().setText(sign.getName());
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }
}
