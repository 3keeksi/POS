package at.htlkaindorf.bsp_development.beans;

import java.time.YearMonth;

public class Truck extends Vehicle {
    private float maxWeight;

    public Truck(String brand, String model, YearMonth constructionDate, float maxWeight) {
        super(brand, model, constructionDate);
        this.maxWeight = maxWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getBrand(), getModel());
    }
}
