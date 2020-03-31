/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package address.bl;

/**
 *
 * @author Denis Imeri
 */
public class Address {
    private String street;
    private int number;
    private String city;
    private int zipCode;
    
    public Address(String street, int number, String city, int zipCode){
        this.street = street;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    
    
    @Override
    public String toString(){
        String txt = String.format("Address: %s %d - %d %s", street, number, zipCode, city);
        return txt;
    }
}
