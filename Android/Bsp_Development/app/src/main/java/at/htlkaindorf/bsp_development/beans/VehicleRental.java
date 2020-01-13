package at.htlkaindorf.bsp_development.beans;

import android.graphics.Color;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class VehicleRental {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void printVehicles(String type) {
        List<Vehicle> filtered = new ArrayList<>(vehicles);
        if(type.equalsIgnoreCase("truck")) {
            filtered.removeIf(v -> !(v instanceof Truck));
        } else {
            filtered.removeIf(v -> !(v instanceof Car));
        }
        System.out.println(type);
        filtered.forEach(System.out::println);
    }

    public static void main(String[] args) {
        VehicleRental vr = new VehicleRental();
        Vehicle vehicle = new Vehicle("nix", "unknown", YearMonth.now());
        vr.addVehicle(vehicle);
        Car car1 = new Car("VW", "Golf", YearMonth.now(), null, 19999.99);
        vr.addVehicle(car1);
        Vehicle truck1 = new Truck("Volvo", "XY", YearMonth.now(), 200f);
        vr.addVehicle(truck1);
        ((Truck) truck1).setMaxWeight(3000f);
        vr.printVehicles("truck");
    }
}
