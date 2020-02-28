/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Dimension2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author crether
 */
public class TimePanel extends JPanel implements Runnable {

    private ZoneId zone;
    private LocalTime time;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DigitLabel[] labels = new DigitLabel[8];
    private static final int TIMEZONE_WIDTH = 200;
    TimePanel panel = this;

    public TimePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (int i = 0; i < labels.length; i++) {
            DigitLabel label = new DigitLabel();
            labels[i] = label;
            this.add(label);
        }
//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent ce) {
//                ((TimePanel)ce.getComponent()).resize();
//            }
//        });
    }

    public void init(ZoneId zone) {
        this.zone = zone;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            char[] chars;
            if (zone == null) {
                chars = new char[8];
                Arrays.fill(chars, '0');
            } else {
                time = LocalTime.now(zone);
                chars = DTF.format(time).toCharArray();
            }
            for (int i = 0; i < labels.length; i++) {
                labels[i].setValue(chars[i]);
                labels[i].repaint();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void resize(int width) {
        Component[] components = this.getComponents();
        if (components.length < 1) {
            return;
        }
        int w = width - components[0].getBounds().width;

        width /= 8;
        int height = width / 11 * 18;

        for (DigitLabel label : labels) {
            label.setPreferredSize(new Dimension(width, height));
            label.setMaximumSize(new Dimension(width, height));
            label.setMinimumSize(new Dimension(width, height));
        }
        this.revalidate();
        this.repaint();
    }
}
