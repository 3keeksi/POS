package at.htlkaindorf.bsp_development.beans;

import java.time.YearMonth;

public class Vehicle {
    private String brand;
    private String model;
    private YearMonth constructionDate;

    public Vehicle() {

    }

    public Vehicle(String brand, String model, YearMonth constructionDate) {
        this.brand = brand;
        this.model = model;
        this.constructionDate = constructionDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public YearMonth getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(YearMonth constructionDate) {
        this.constructionDate = constructionDate;
    }
}
