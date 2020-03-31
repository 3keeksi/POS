/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class CalenderBL {

    public static DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMMM yyyy");
    public static DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm");
    public static String table = "";
    public static Random rand = new Random();

    public static String generateDaysOfMonth(String month) {
        if (month == "Mrz") {
            month = "Mär";
        }

        DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("MMM", Locale.GERMAN);
        TemporalAccessor acc = dtfMonth.parse(month);

        LocalDate date = LocalDate.of(2019, acc.get(ChronoField.MONTH_OF_YEAR), 1);

        int daysMonth = date.getMonth().maxLength();
        if (daysMonth == 29 && !date.isLeapYear()) {
            daysMonth = 28;
        }

        String[] days = new String[daysMonth];
        String text = "";
        for (int i = 1; i <= daysMonth; i++) {
            LocalDate temp = LocalDate.of(2019, acc.get(ChronoField.MONTH_OF_YEAR), i);
            days[i-1] = dtfDate.format(temp) + " - <br/>";
            text+=days[i-1];
        }
        
        return text;
    }

    public static String generateEvents(String month, String epContent) {
        String temp = epContent.substring(epContent.indexOf("<body>")+6, epContent.indexOf("</body>"));
        String[] days = temp.split("<br>");
        
        for(int i = 0; i<days.length-1;i++){
            if(rand.nextBoolean()){
                int hour = rand.nextInt(13)+6;
                int minute = Integer.parseInt("0 15 30 45".split(" ")[rand.nextInt(4)]);
                
                LocalTime time = LocalTime.of(hour, minute);
                
                String add = "Test Meeting Party".split(" ")[rand.nextInt(3)];
                
                days[i] += " <i>" + dtfTime.format(time) + String.format("</i> <span style=\"color: red;font-weight: bold\">%s</span> <br>", add);
            }else {
                days[i] += "<br/>";
            }
        }
        String text = "";
        for (String day : days) {
            text+=day;
        }
        return text;
    }
    
    public static String removeEvent(int day, String epContent) {
        if(epContent.isEmpty()) {
            throw new NumberFormatException();
        }
        String temp = epContent.substring(epContent.indexOf("<body>")+6, epContent.indexOf("</body>"));
        
        String[] split = temp.split("<br>");
        boolean bool = false;
        for (int i = 0; i < split.length-1; i++) {
            if(i == (day-1)){
                split[i] = split[i].split("-")[0] + "- <br>";
                bool = true;
                break;
            }
            split[i]+="<br>";
            bool = false;
        }
        if(!bool) {
            throw new NumberFormatException();
        }
        
        String text = "";
        for (String string : split) {
            text+=string;
        }
        return text;
    }
    
    public static void main(String[] args) {
        LocalDate lol = LocalDate.of(1, Month.MARCH, 1);
        DateTimeFormatter lel = DateTimeFormatter.ofPattern("dd. MM yyyy");
        System.out.println(lel.format(lol));
    }

}
