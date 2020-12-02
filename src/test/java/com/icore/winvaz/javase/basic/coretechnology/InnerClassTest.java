package com.icore.winvaz.javase.basic.coretechnology;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/11/4 15:29
 * @Version 1.0.0
 */
public class InnerClassTest {
    public static void main(String[] args) {
        // TalkingClock clock = new TalkingClock(1000, true);
        // clock.start();
        // Quit Program
        JOptionPane.showMessageDialog(null, "Quit Program !");
        System.exit(0);
    }
}

class TalkingClock {
    /*private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }*/

    public void start(int interval, boolean beep) {
        // 局部内部类
         class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
                // 外部类的正规引用语法
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(interval, timePrinter);
        timer.start();
    }

    /*
    // 成员内部类
    private class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
            // 外部类的正规引用语法
            if (TalkingClock.this.beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    */
}