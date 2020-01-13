package at.htlkaindorf.exa_206_pethome.bl;

import android.content.res.AssetManager;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.enums.CatColor;
import at.htlkaindorf.exa_206_pethome.enums.Gender;

public class IO_Helper {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");

    // pet_type,name,gender,birthdate,size,color,avatar
    public static List<Pet> loadPets(AssetManager am) {
        try {
            InputStream is = am.open("pets.csv");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            return br.lines().skip(1).map(line -> {
                String[] split = line.split(",");
                String name = split[1];
                Gender gender = split[2].charAt(0) == 'M' ? Gender.MALE : Gender.FEMALE;
                LocalDate date = (LocalDate) dtf.parse(split[3]);
                if(split[0].equalsIgnoreCase("cat")) {
                    CatColor color = CatColor.valueOf(split[4].toUpperCase());
                    Uri picture = Uri.parse(split[5]);
                    return new Cat(name, date, gender, color, picture);
                } else {
                }
                return null;
            }).collect(Collectors.toList());
        } catch (IOException e) {

        }
        return null;
    }
}
