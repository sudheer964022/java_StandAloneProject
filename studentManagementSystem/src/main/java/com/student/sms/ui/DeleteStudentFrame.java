package com.student.sms.ui;

import javax.swing.*;
import java.awt.*;
import com.student.sms.dao.StudentDao;
import com.student.sms.dao.StudentDaoImpl;
import com.student.sms.model.Student;

@SuppressWarnings("serial")
public class DeleteStudentFrame extends JFrame {

    private JTextField txtId;
    private StudentDao studentDao;

    public DeleteStudentFrame() {
        studentDao = new StudentDaoImpl();

        setTitle("Delete Student");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblHeader = new JLabel("Delete Student", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setForeground(Color.RED);
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        centerPanel.add(new JLabel("Enter Student ID:"), gbc);
        txtId = new JTextField(15);
        centerPanel.add(txtId, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnDelete = new JButton("Delete");
        JButton btnCancel = new JButton("Cancel");

        // Style
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);

        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnCancel);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        add(buttonPanel, BorderLayout.SOUTH);

        btnDelete.addActionListener(e -> deleteStudent());
        btnCancel.addActionListener(e -> dispose());
    }

    private void deleteStudent() {
        String idStr = txtId.getText();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            // Confirm
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete student with ID: " + id + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Check if exists first (optional but good UI)
                Student s = studentDao.getStudentById(id);
                if (s == null) {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                    return;
                }

                studentDao.deleteStudent(id);
                JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
                dispose();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + ex.getMessage());
        }
    }
}
