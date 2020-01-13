package at.htlkaindorf.exa_102_zodiacsign.beans;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZodiacSign {
    private String name;
    private MonthDay startDate;
    private int drawableId;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM", Locale.GERMANY);

    public ZodiacSign(String name, MonthDay startDate, int drawableId) {
        this.name = name;
        this.startDate = startDate;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonthDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getDuration(MonthDay other) {
        return dtf.format(startDate) + " bis " + dtf.format(other.withDayOfMonth(other.getDayOfMonth()-1));
    }
}
