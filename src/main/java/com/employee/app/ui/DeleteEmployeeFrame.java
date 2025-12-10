package com.employee.app.ui;

import com.employee.app.dao.EmployeeDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class DeleteEmployeeFrame extends JFrame {

    private JTextField idField;
    private EmployeeDAO employeeDAO;

    public DeleteEmployeeFrame() {
        employeeDAO = new EmployeeDAO();
        setTitle("Delete Employee");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Enter Employee ID:"), gbc);

        // ID Field
        idField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(idField, gbc);

        // Delete Button
        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.setBackground(new Color(220, 53, 69)); // Reddish color caution
        deleteButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(deleteButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        setLocationRelativeTo(null);
    }

    private void deleteEmployee() {
        String idText = idField.getText();

        // Validation
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete employee with ID: " + id + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = employeeDAO.deleteEmployee(id);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete employee. ID might not exist.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric value.");
        }
    }
}
