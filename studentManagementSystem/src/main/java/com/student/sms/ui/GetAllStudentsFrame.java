package com.student.sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.student.sms.dao.StudentDao;
import com.student.sms.dao.StudentDaoImpl;
import com.student.sms.model.Student;

@SuppressWarnings("serial")
public class GetAllStudentsFrame extends JFrame {

    private JTable table;
    private StudentDao studentDao;

    public GetAllStudentsFrame() {
        studentDao = new StudentDaoImpl();

        setTitle("All Students");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Column Names
        String[] columnNames = { "ID", "Name", "Email", "Marks" };

        // Data
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData(model);
    }

    private void loadData(DefaultTableModel model) {
        try {
            List<Student> students = studentDao.getAllStudents();
            for (Student s : students) {
                model.addRow(new Object[] { s.getId(), s.getName(), s.getEmail(), s.getMarks() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
