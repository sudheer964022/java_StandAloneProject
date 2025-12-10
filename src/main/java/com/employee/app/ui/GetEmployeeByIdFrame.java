package com.employee.app.ui;

import com.employee.app.dao.EmployeeDAO;
import com.employee.app.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GetEmployeeByIdFrame extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private EmployeeDAO employeeDAO;

    public GetEmployeeByIdFrame() {
        employeeDAO = new EmployeeDAO();
        setTitle("Get Employee By ID");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Enter Employee ID:"), gbc);

        idField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        JButton fetchButton = new JButton("Fetch Details");
        gbc.gridx = 2;
        inputPanel.add(fetchButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setMargin(new Insets(10, 10, 10, 10)); // Add padding inside text area
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchEmployee();
            }
        });

        setLocationRelativeTo(null); // Center on screen
    }

    private void fetchEmployee() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Employee emp = employeeDAO.getEmployeeById(id);

            if (emp != null) {
                resultArea.setText("ID: " + emp.getId() + "\n" +
                        "Name: " + emp.getName() + "\n" +
                        "Email: " + emp.getEmail() + "\n" +
                        "Salary: " + emp.getSalary());
            } else {
                resultArea.setText("Employee not found with ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
        }
    }
}
