package bl;

import beans.Event;

import java.time.LocalDate;
import java.util.*;

public class EventBL {
    private List<Event> events = new ArrayList<>();
    private List<String> classes = new ArrayList<>();
    private List<String> activities = new ArrayList<>();
    Random rand = new Random();

    public EventBL() {
        classes.add("TINF");
        classes.add("NVS");
        classes.add("DBI");
        classes.add("POS");

        activities.add("PLF");
        activities.add("Übung");
        activities.add("Aufgabe");
    }

    public static void main(String[] args) {
        EventBL bl = new EventBL();
        bl.initEvents();
        bl.sortEvents(false);
        bl.printEvents();

        System.out.println("=========after filtering=========");

        bl.filterEvents("Aufgabe");
        bl.printEvents();
    }

    public void initEvents() {
        int day = 9, month = 9;
        for (int i = 0; i < 25; i++) {
            month = rand.nextInt(4) + 9;
            switch(month) {
                case 9:
                    day = rand.nextInt(23)+9;
                    break;
                case 10:
                    day = rand.nextInt(31)+1;
                    break;
                case 11:
                    day = rand.nextInt(30)+1;
                    break;
                case 12:
                    day = rand.nextInt(20)+1;
                    break;
            }
            LocalDate date = LocalDate.of(2019, month, day);
            String text = "";

            text += classes.get(rand.nextInt(classes.size())) + "-";
            text += activities.get(rand.nextInt(activities.size()));

            Event event = new Event(text, date);
            events.add(event);
        }
    }

    public void sortEvents(boolean upwards) {
//        events.sort((e1,e2) -> {
//            if(e1.getDate().equals(e2.getDate())) {
//                return  e1.getText().compareTo(e2.getText());
//            }
//            return e1.getDate().compareTo(e2.getDate());
//        });

        if (upwards) {
            events.sort(Comparator.comparing(Event::getDate).thenComparing(Event::getText));
        } else {
            events.sort(Comparator.comparing(Event::getDate).reversed().thenComparing(Event::getText));
        }
    }

    public void filterEvents(String activity) {
        events.removeIf((e1) -> {
            String[] pivots = e1.getText().split("-");
            return pivots[1].equals(activity);
        });
    }

    public void printEvents() {
//        events.forEach((event) -> {
//            System.out.println(event.toString());
//        });
        events.forEach(System.out::println);
    }
}
