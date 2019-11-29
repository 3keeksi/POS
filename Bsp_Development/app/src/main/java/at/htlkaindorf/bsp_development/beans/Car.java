package at.htlkaindorf.bsp_development.beans;

import android.graphics.Color;

import java.time.YearMonth;

public class Car extends Vehicle {
    private Color color;
    private double price;

    public Car(String brand, String model, YearMonth constructionDate, Color color, double price) {
        super(brand, model, constructionDate);
        this.color = color;
        this.price = price;
    }

    public Car(Color color, double price) {
        this("BMW", "X5", YearMonth.now());
        this.color = color;
        this.price = price;
    }

    public Car(String brand, String model, YearMonth constructionDate) {
        super(brand, model, constructionDate);
    }
}
