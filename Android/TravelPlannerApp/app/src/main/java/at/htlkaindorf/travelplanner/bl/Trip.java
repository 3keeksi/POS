package at.htlkaindorf.travelplanner.bl;

import android.util.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trip implements Serializable {
    private String city;
    private String country;
    private String countryCode;
    private LocalDate startDate;
    private int duration;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");

    // city - country - country_code - startDate - duration in days
    public Trip(String line) {
        String[] tokens = line.split(" - ");
        this.city = tokens[0];
        this.country = tokens[1];
        this.countryCode = tokens[2];
        this.startDate = LocalDate.parse(tokens[3], dtf);
        this.duration = Integer.parseInt(tokens[4]);
    }

    public Trip(String city, String country, String countryCode, LocalDate startDate, int duration) {
        this.city = city;
        this.country = country;
        this.countryCode = countryCode;
        this.startDate = startDate;
        this.duration = duration;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
