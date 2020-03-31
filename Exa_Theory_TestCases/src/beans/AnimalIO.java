/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.Animal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class AnimalIO {

    private static String filename = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "res"
            + File.separator + "animals.csv";

    public static void saveAnimals(List<Animal> animals) throws IOException {
        FileWriter writer = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(writer);
        for (Animal animal : animals) {
            bw.write(animal.getName() + ","
                    + animal.getSpecies() + ","
                    + animal.getAge() + "\n"
            );
        }
        bw.close();
    }

    public static List<Animal> loadAnimals() throws FileNotFoundException, IOException {
        List<Animal> animals = new ArrayList<>();

        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            if (tokens.length != 3) {
                String species = tokens[1];
                String name = tokens[0];
                int age = Integer.parseInt(tokens[2]);

                animals.add(new Animal(name, species, age));
            }
        }
        return animals;
    }

    public static void main(String[] args) {
        try {
            List<Animal> lol = loadAnimals();
            System.out.println(lol.toString());
        } catch (IOException ex) {
            Logger.getLogger(ex.toString());
        }
    }

}
