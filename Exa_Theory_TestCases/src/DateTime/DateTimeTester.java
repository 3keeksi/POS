/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class DateTimeTester {
    
    private static final DateTimeFormatter dtf = 
            DateTimeFormatter.ofPattern("EEEE dd.MMMM.yyyy",
                    Locale.GERMAN);
    
    private static final DateTimeFormatter dtfTime = 
            DateTimeFormatter.ofPattern("HH:mm:ss",
                    Locale.GERMAN);
    
    private static final DateTimeFormatter dtfDateTime = 
            DateTimeFormatter.ofPattern("EEEE dd.MMMM.yyyy HH:mm:ss",
                    Locale.GERMAN);
    
    /*
    alte Date/Time APIs:
        Date, Calender, GregorianCalender
    neue Date/Time APIs:
        LocalDate, LocalTime, LocalDateTime
    Formatter für die neuen: DateTimeFormatter
    */
    
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Random rand = new Random();
        
        today = today.minusDays(rand.nextInt(1000));
        System.out.println(dtf.format(today));
        
        LocalDate birthday = LocalDate.of(1999, Month.FEBRUARY, 20);
        if(birthday.isBefore(LocalDate.of(2020,Month.FEBRUARY, 29))){
            System.out.println("born in se last centuryyyyy");
        }
        
        System.out.println(dtf.format(birthday));
        
        LocalTime time = LocalTime.now();
        System.out.println(dtfTime.format((time)));
        
        LocalTime time2 = LocalTime.of(12, 30, 0);
        LocalTime minus = time2.minusHours(123);
        
        
        LocalDateTime datetime = LocalDateTime.now();
        LocalDateTime datetimeOF = LocalDateTime.of(20002, 6, 23, 6, 0, 0);
        
    }
    
}
