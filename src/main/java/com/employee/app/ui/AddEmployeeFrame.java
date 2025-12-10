package com.employee.app.ui;

import com.employee.app.dao.EmployeeDAO;
import com.employee.app.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AddEmployeeFrame extends JFrame {

    private JTextField nameField, emailField, salaryField;
    private EmployeeDAO employeeDAO;

    public AddEmployeeFrame() {
        employeeDAO = new EmployeeDAO();
        setTitle("Add New Employee");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Name:"), gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Email:"), gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Salary
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Salary:"), gbc);

        salaryField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(salaryField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Add Employee");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        setLocationRelativeTo(null);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        String salary = salaryField.getText();

        if (name.isEmpty() || email.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        Employee emp = new Employee(name, email, salary);
        boolean success = employeeDAO.addEmployee(emp);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            nameField.setText("");
            emailField.setText("");
            salaryField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add employee.");
        }
    }
}
