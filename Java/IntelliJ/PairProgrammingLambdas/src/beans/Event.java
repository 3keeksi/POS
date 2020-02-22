package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String text;
    private LocalDate date;
    private final DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("E dd.MM.uuuu");

    public Event(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s - %s",text,dtf.format(date));
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }
}
