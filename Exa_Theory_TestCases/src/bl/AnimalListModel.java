/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Animal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Denis Imeri
 */
public class AnimalListModel 
        extends AbstractListModel
        implements AnimalList {

    private List<Animal> allAnimals = new ArrayList<>();
    private List<Animal> filteredAnimals = new ArrayList<>();

    public AnimalListModel() {
        for(String[] anim : VALUES) {
            addAnimal(new Animal(anim[0], anim[1], Integer.valueOf(anim[2])));
        }
        //filteredAnimals = allAnimals; falsch => beide zeigen aufs gleiche
        filter("None");
    }
    
    public AnimalListModel(List<Animal> animals) {
        for(Animal anim : animals) {
            addAnimal(anim);
        }
        
        filter("None");
    }

    @Override
    public int getSize() {
        return filteredAnimals.size();
    }

    @Override
    public Object getElementAt(int index) {
        return filteredAnimals.get(index);
    }

    public void addAnimal(Animal animal) {
        allAnimals.add(animal);
        Comparator<Animal> compSpecies = new SortBySpecies();
        Comparator<Animal> compName = new SortByName();
        //comp = comp.reversed(); // absteigend jetzt
        compSpecies = compSpecies.thenComparing(compName); //.reversed() => verkehrte Reihenfolge
        Collections.sort(allAnimals, compSpecies);
        filter("none");
        //this.fireIntervalAdded(this, animals.size()-1, animals.size());
    }

    public void removeAnimal(int index) {
        allAnimals.remove(index);
        //this.fireIntervalAdded(this, 0, animals.size());
    }

    public void removeAnimals(List<Animal> toDelete) {
        allAnimals.removeAll(toDelete);
        //this.fireIntervalRemoved(this, 0, animals.size());
    }

    public void filter(String type) {
        filteredAnimals.clear();
        if (type.equalsIgnoreCase("none")) {
            filteredAnimals.addAll(allAnimals);
        } else {
            for (Animal animal : allAnimals) {
                if (animal.getSpecies().equalsIgnoreCase(type)) {
                    filteredAnimals.add(animal);
                    System.out.println("lo");
                }
            }
        }
        this.fireContentsChanged(filteredAnimals, 0, filteredAnimals.size());
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }
}
