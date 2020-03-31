/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Objects;

/**
 *
 * @author Denis Imeri
 */
public class Animal // implements //Comparable<Animal>
{
    private String name;
    private String species;
    private int age;

    public Animal() {
    }

    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s, (%s) - age: %d", name, species, age);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.species, other.species)) {
            return false;
        }
        return true;
    }

//    @Override
//    public int compareTo(Animal o) {
//        //return this.age - o.getAge();
//        if (species.compareTo(o.getSpecies()) == 0) {
//            return name.compareTo(o.getName());
//        }
//        return this.species.compareTo(o.getSpecies());
//    }
}
