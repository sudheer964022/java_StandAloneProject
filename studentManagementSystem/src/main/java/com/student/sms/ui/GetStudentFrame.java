package com.student.sms.ui;

import javax.swing.*;
import java.awt.*;
import com.student.sms.dao.StudentDao;
import com.student.sms.dao.StudentDaoImpl;
import com.student.sms.model.Student;

@SuppressWarnings("serial")
public class GetStudentFrame extends JFrame {

    private JTextField txtId;
    private JTextArea displayArea;
    private StudentDao studentDao;

    public GetStudentFrame() {
        studentDao = new StudentDaoImpl();

        setTitle("Get Student By ID");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Student ID:"));
        txtId = new JTextField(10);
        topPanel.add(txtId);
        JButton btnSearch = new JButton("Search");
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        btnSearch.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        String idStr = txtId.getText(); // Fixed variable name from txtName to txtId
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Student student = studentDao.getStudentById(id);

            if (student != null) {
                displayArea.setText("ID: " + student.getId() + "\n" +
                        "Name: " + student.getName() + "\n" +
                        "Email: " + student.getEmail() + "\n" +
                        "Marks: " + student.getMarks());
            } else {
                displayArea.setText("");
                JOptionPane.showMessageDialog(this, "Student not found with ID: " + id, "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching student: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
