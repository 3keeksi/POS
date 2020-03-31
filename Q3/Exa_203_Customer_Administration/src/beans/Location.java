/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author crether
 */
public class Location {
    
    private int id;
    private String country;
    private String city;
    
    public Location(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public int getId() {
        return id;
    }
    
}
