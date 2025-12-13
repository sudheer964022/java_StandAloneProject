package com.student.sms.ui;

import javax.swing.*;
import java.awt.*;
import com.student.sms.dao.StudentDao;
import com.student.sms.dao.StudentDaoImpl;
import com.student.sms.model.Student;

@SuppressWarnings("serial")
public class AddStudentFrame extends JFrame {

    private JTextField txtName, txtEmail, txtMarks;
    private StudentDao studentDao;

    public AddStudentFrame() {
        studentDao = new StudentDaoImpl();

        setTitle("Add Student");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblHeader = new JLabel("Add New Student", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        // Marks
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Marks:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtMarks = new JTextField(20);
        formPanel.add(txtMarks, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        // Style Buttons slightly
        btnSave.setBackground(new Color(60, 179, 113)); // Medium Sea Green
        btnSave.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(220, 20, 60)); // Crimson
        btnCancel.setForeground(Color.WHITE);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveStudent());
        btnCancel.addActionListener(e -> dispose());
    }

    private void saveStudent() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String marksStr = txtMarks.getText();

        // Validation
        if (name.isEmpty() || email.isEmpty() || marksStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Invalid Email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double marks;
        try {
            marks = Double.parseDouble(marksStr);
            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setMarks(marks);

        try {
            studentDao.saveStudent(student);
            JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving student: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
