package com.employee.app.ui;

import com.employee.app.dao.EmployeeDAO;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class UpdateEmployeeFrame extends JFrame {

    private JTextField idField, updateDataField;
    private JComboBox<String> fieldDropdown;
    private EmployeeDAO employeeDAO;

    public UpdateEmployeeFrame() {
        employeeDAO = new EmployeeDAO();
        setTitle("Update Employee Details");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Employee ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Employee ID:"), gbc);

        idField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(idField, gbc);

        // Field Dropdown
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Select Field to Update:"), gbc);

        String[] fields = { "name", "email", "salary" };
        fieldDropdown = new JComboBox<>(fields);
        gbc.gridx = 1;
        mainPanel.add(fieldDropdown, gbc);

        // New Value
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("New Value:"), gbc);

        updateDataField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(updateDataField, gbc);

        // Update Button
        JButton updateButton = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(updateButton, gbc);

        updateButton.addActionListener(e -> updateEmployee());

        setLocationRelativeTo(null);
    }

    private void updateEmployee() {
        try {
            String idText = idField.getText();
            String newValue = updateDataField.getText();

            if (idText.isEmpty() || newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Updated Data are required.");
                return;
            }

            int id = Integer.parseInt(idText);
            String selectedField = (String) fieldDropdown.getSelectedItem();

            boolean success = employeeDAO.updateEmployeeField(id, selectedField, newValue);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                idField.setText("");
                updateDataField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee. Check ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID format.");
        }
    }
}
