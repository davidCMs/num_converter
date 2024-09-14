package org.davidCMs;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main class for the desktop ui
 */

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        Base[] bases = Base.getBases().toArray(new Base[0]);

        JTextField input = new JTextField("0");
        input.setMaximumSize(new Dimension(99999999,20));
        mainPanel.add(input);

        JPanel convertFromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel convertFromText = new JLabel("Convert from ");
        JComboBox<Base> convertFromComboBox = new JComboBox<>(bases);
        convertFromPanel.add(convertFromText);
        convertFromPanel.add(convertFromComboBox);
        convertFromPanel.setMaximumSize(convertFromPanel.getPreferredSize());
        mainPanel.add(convertFromPanel);

        JPanel convertToPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel convertToText = new JLabel("Convert to ");
        JComboBox<Base> convertToComboBox = new JComboBox<>(bases);
        convertToPanel.add(convertToText);
        convertToPanel.add(convertToComboBox);
        convertToPanel.setMaximumSize(convertToPanel.getPreferredSize());
        mainPanel.add(convertToPanel);

        JTextArea output = new JTextArea("0");
        output.setLineWrap(true);
        output.setEditable(false);
        mainPanel.add(output, Component.LEFT_ALIGNMENT);

        ActionListener baseChangeListener = e -> output.setText(ConversionUtils.convert((Base) convertFromComboBox.getSelectedItem(), (Base) convertToComboBox.getSelectedItem(), input.getText()));
        convertFromComboBox.addActionListener(baseChangeListener);
        convertToComboBox.addActionListener(baseChangeListener);
        input.addActionListener(baseChangeListener);
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                output.setText(ConversionUtils.convert((Base) convertFromComboBox.getSelectedItem(), (Base) convertToComboBox.getSelectedItem(), input.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                output.setText(ConversionUtils.convert((Base) convertFromComboBox.getSelectedItem(), (Base) convertToComboBox.getSelectedItem(), input.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                output.setText(ConversionUtils.convert((Base) convertFromComboBox.getSelectedItem(), (Base) convertToComboBox.getSelectedItem(), input.getText()));
            }
        });


        frame.add(mainPanel);
        frame.setVisible(true);
    }
}