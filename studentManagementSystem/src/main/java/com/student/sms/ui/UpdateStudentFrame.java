package com.student.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.student.sms.dao.StudentDao;
import com.student.sms.dao.StudentDaoImpl;
import com.student.sms.model.Student;

@SuppressWarnings("serial")
public class UpdateStudentFrame extends JFrame {

    private JComboBox<String> cmbStudentIds;
    private JTextField txtName, txtEmail, txtMarks;
    private StudentDao studentDao;
    private List<Student> students;

    public UpdateStudentFrame() {
        studentDao = new StudentDaoImpl();

        setTitle("Update Student");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblHeader = new JLabel("Update Student Details", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Center Panel (Form)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Select ID Row
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Select Student ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        cmbStudentIds = new JComboBox<>();
        centerPanel.add(cmbStudentIds, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtName = new JTextField(20);
        centerPanel.add(txtName, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtEmail = new JTextField(20);
        centerPanel.add(txtEmail, gbc);

        // Marks
        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(new JLabel("Marks:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        txtMarks = new JTextField(20);
        centerPanel.add(txtMarks, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton btnLoad = new JButton("Load Details");
        JButton btnUpdate = new JButton("Update");
        JButton btnCancel = new JButton("Cancel");

        // Style
        btnUpdate.setBackground(new Color(60, 179, 113));
        btnUpdate.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(220, 20, 60));
        btnCancel.setForeground(Color.WHITE);
        btnLoad.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        btnLoad.setForeground(Color.WHITE);

        bottomPanel.add(btnLoad);
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnCancel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        add(bottomPanel, BorderLayout.SOUTH);

        // Load IDs
        loadStudentIds();

        // Listeners
        btnLoad.addActionListener(e -> loadStudentDetails());
        cmbStudentIds.addActionListener(e -> loadStudentDetails());
        btnUpdate.addActionListener(e -> updateStudent());
        btnCancel.addActionListener(e -> dispose());
    }

    private void loadStudentIds() {
        try {
            students = studentDao.getAllStudents();
            cmbStudentIds.removeAllItems();
            cmbStudentIds.addItem("Select ID");
            for (Student s : students) {
                cmbStudentIds.addItem(String.valueOf(s.getId()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
    }

    private void loadStudentDetails() {
        String selectedId = (String) cmbStudentIds.getSelectedItem();
        if (selectedId == null || selectedId.equals("Select ID")) {
            clearFields();
            return;
        }

        int id = Integer.parseInt(selectedId);
        // Find in local list or query DB? Querying DB is safer for fresh data
        Student s = studentDao.getStudentById(id);
        if (s != null) {
            txtName.setText(s.getName());
            txtEmail.setText(s.getEmail());
            txtMarks.setText(String.valueOf(s.getMarks()));
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtMarks.setText("");
    }

    private void updateStudent() {
        String selectedId = (String) cmbStudentIds.getSelectedItem();
        if (selectedId == null || selectedId.equals("Select ID")) {
            JOptionPane.showMessageDialog(this, "Please select a student ID");
            return;
        }

        try {
            int id = Integer.parseInt(selectedId);
            String name = txtName.getText();
            String email = txtEmail.getText();
            String marksStr = txtMarks.getText();

            // Validation
            if (name.isEmpty() || email.isEmpty() || marksStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            double marks = Double.parseDouble(marksStr);
            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100");
                return;
            }

            Student s = new Student();
            s.setId(id);
            s.setName(name);
            s.setEmail(email);
            s.setMarks(marks);

            studentDao.updateStudent(s);
            JOptionPane.showMessageDialog(this, "Student Updated Successfully!");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Marks format");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + ex.getMessage());
        }
    }
}
