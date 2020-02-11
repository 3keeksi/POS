/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

/**
 *
 * @author crether
 */
public class TimerLevel extends JLabel implements Runnable {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            LocalTime time = LocalTime.now();
            setText(DTF.format(time));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                break;
            }
        }
        setText("00:00:00");
    }

}
