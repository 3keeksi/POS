/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author crether
 */
public class Product {
    
    private String name;
    private String details;
    private int quantity;
    private double price;
    private LocalDate expiration;

    public Product(String name, String details, int quantity, double price) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, String details, int quantity, double price, LocalDate expiration) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.price = price;
        this.expiration = expiration;
    }
    
    @Override
    public String toString() {
        return String.format("");
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
        final Product other = (Product) obj;
        if(!this.name.equals(other.getName())) {
            return false;
        }
        if(!this.details.equals(other.getDetails())) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
