package com.employee.app.ui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Employee Management System");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a main panel with GridBagLayout for centering
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Vertical spacing between buttons
        gbc.weightx = 1.0;
        gbc.ipady = 10; // Button height padding

        // Title Label
        JLabel titleLabel = new JLabel("Employee Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0); // Extra space below title
        mainPanel.add(titleLabel, gbc);

        // Reset insets for buttons
        gbc.insets = new Insets(10, 50, 10, 50); // Side padding for buttons

        // Button 1: Get Employee By ID
        JButton btnGetById = new JButton("1. Get Employee By ID");
        btnGetById.setFocusPainted(false);
        btnGetById.addActionListener(e -> new GetEmployeeByIdFrame().setVisible(true));
        gbc.gridy++;
        mainPanel.add(btnGetById, gbc);

        // Button 2: Get All Employees
        JButton btnGetAll = new JButton("2. Get All Employees");
        btnGetAll.setFocusPainted(false);
        btnGetAll.addActionListener(e -> new GetAllEmployeesFrame().setVisible(true));
        gbc.gridy++;
        mainPanel.add(btnGetAll, gbc);

        // Button 3: Add Employee
        JButton btnAdd = new JButton("3. Add Employee");
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> new AddEmployeeFrame().setVisible(true));
        gbc.gridy++;
        mainPanel.add(btnAdd, gbc);

        // Button 4: Update Employee
        JButton btnUpdate = new JButton("4. Update Employee");
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(e -> new UpdateEmployeeFrame().setVisible(true));
        gbc.gridy++;
        mainPanel.add(btnUpdate, gbc);

        // Button 5: Delete Employee
        JButton btnDelete = new JButton("5. Delete Employee");
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(e -> new DeleteEmployeeFrame().setVisible(true));
        gbc.gridy++;
        mainPanel.add(btnDelete, gbc);

        // Button 6: Exit
        JButton btnExit = new JButton("6. Exit Application");
        btnExit.setFocusPainted(false);
        btnExit.setBackground(new Color(220, 53, 69)); // Reddish color for exit
        btnExit.setForeground(Color.WHITE);
        btnExit.addActionListener(e -> System.exit(0));
        gbc.gridy++;
        mainPanel.add(btnExit, gbc);

        setLocationRelativeTo(null); // Center on screen
    }
}
