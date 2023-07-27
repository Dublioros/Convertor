package com.alura.conversorchallenge.latam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.Objects;

public class DistWindow extends JFrame implements ActionListener, ItemListener {
    JFrame frameDist = new JFrame();
    private JButton buttonConvertDist;
    private JButton buttonBack;
    private JComboBox<String> comboDistance;
    private JLabel jLabelResult;
    private JTextField textFieldDistance;
    private static final double kilometres = 0.621371;
    private static final double miles = 1.60934;

    public DistWindow() {
        initComponents();
        frameDist.setTitle("Oracle One - Distances");
        frameDist.setIconImage(new ImageIcon("C:\\Users\\joseg\\Documents\\WorkSpace - Software Developer\\Conversor\\Images\\ExAppIcon.png").getImage());
        frameDist.setBounds(0, 0, 550, 350);
        frameDist.setLayout(null);
        frameDist.setResizable(false);
        frameDist.setVisible(true);
        frameDist.setLocationRelativeTo(null);
        frameDist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        JLabel labelTitle = new JLabel("Distance Converter");
        labelTitle.setBounds(200, 40, 150, 20);
        frameDist.add(labelTitle);

        JLabel labelAmount = new JLabel("Length");
        labelAmount.setBounds(235, 80, 150, 20);
        frameDist.add(labelAmount);

        textFieldDistance = new JTextField();
        textFieldDistance.setBounds(210, 100, 100, 20);
        frameDist.add(textFieldDistance);

        JLabel labelUnits = new JLabel("Units");
        labelUnits.setBounds(240, 120, 150, 20);
        frameDist.add(labelUnits);

        String[] distanceUnit = {"Kilometer to Miles", "Miles to Kilometer"};
        comboDistance = new JComboBox<>(distanceUnit);
        comboDistance.setBounds(160, 140, 210, 20);
        frameDist.add(comboDistance);

        buttonConvertDist = new JButton("Convert");
        buttonConvertDist.setBounds(220, 170, 80, 25);
        frameDist.add(buttonConvertDist);
        buttonConvertDist.addActionListener((ActionListener) this);

        jLabelResult = new JLabel();
        jLabelResult.setBounds(245, 190, 80, 20);
        frameDist.add(jLabelResult);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(220, 220, 80, 25);
        frameDist.add(buttonBack);
        buttonBack.addActionListener((ActionListener) this);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboDistance && e.getStateChange() == ItemEvent.SELECTED) {
            String selection = Objects.requireNonNull(comboDistance.getSelectedItem()).toString();
            setTitle(selection);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            frameDist.setVisible(false);
        }
        if (e.getSource() == buttonConvertDist) {
            String inputText = textFieldDistance.getText();
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a distance to convert.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            try {
                double distance = Double.parseDouble((inputText));
                String convert = Objects.requireNonNull(comboDistance.getSelectedItem()).toString();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                double resultDist = 0;
                switch (convert) {
                    case "Kilometer to Miles" -> {
                        resultDist = distance * 0.621371;
                        jLabelResult.setText(decimalFormat.format(resultDist) + " miles");
                    }
                    case "Miles to Kilometer" -> {
                        resultDist = distance * 1.60934;
                        jLabelResult.setText(decimalFormat.format(resultDist)+ " kilometers");
                    }
                    default -> jLabelResult.setText("");
                };
                jLabelResult.setText(decimalFormat.format(resultDist));
                JOptionPane.showMessageDialog(this, "Distance: " + decimalFormat.format(resultDist), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid distance entered", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        DistWindow distWindow = new DistWindow();
        distWindow.setLocationRelativeTo(null);
    }
}
