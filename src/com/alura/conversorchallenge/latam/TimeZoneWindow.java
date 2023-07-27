package com.alura.conversorchallenge.latam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TimeZoneWindow extends JFrame implements ActionListener {

    private final JComboBox<String> timeZoneComboBox;
    private final JLabel timeLabel;
    private final JButton buttonBack;

    public TimeZoneWindow() {
        setTitle("Time Zone Converter");
        setIconImage(new ImageIcon("C:\\Users\\joseg\\Documents\\WorkSpace - Software Developer\\Conversor\\Images\\ExAppIcon.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 550, 350);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelTitle = new JLabel("Time Zone Real Time");
        labelTitle.setBounds(200, 40, 150, 20);
        add(labelTitle);

        timeLabel = new JLabel();
        timeLabel.setBounds(230, 100, 100, 20);
        add(timeLabel);

        List<String> timeZoneIds = new ArrayList<>(ZoneId.getAvailableZoneIds());
        timeZoneIds.sort(String::compareTo);
        timeZoneComboBox = new JComboBox<>(timeZoneIds.toArray(new String[0]));
        timeZoneComboBox.setBounds(190, 140, 150, 20);
        add(timeZoneComboBox);

        timeZoneComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });

        buttonBack = new JButton("Back");
        buttonBack.setBounds(220, 220, 80, 25);
        add(buttonBack);
        buttonBack.addActionListener(this);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void updateTime() {
        String selectedTimeZoneId = (String) timeZoneComboBox.getSelectedItem();

        if (selectedTimeZoneId != null) {
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(selectedTimeZoneId));
            String output = String.format(
                    "Time: %02d:%02d", localDateTime.getHour(), localDateTime.getMinute()
            );
            timeLabel.setText(output);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        TimeZoneWindow app = new TimeZoneWindow();
        app.setVisible(true);
    }
}
