package com.employee.app.ui;

import com.employee.app.dao.EmployeeDAO;
import com.employee.app.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class GetAllEmployeesFrame extends JFrame {

    private JTable employeeTable;
    private EmployeeDAO employeeDAO;

    public GetAllEmployeesFrame() {
        employeeDAO = new EmployeeDAO();
        setTitle("All Employees");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = { "ID", "Name", "Email", "Salary" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);

        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.addActionListener(e -> loadData(tableModel));
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadData(tableModel);
        setLocationRelativeTo(null);
    }

    private void loadData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing data
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp : employees) {
            Object[] row = { emp.getId(), emp.getName(), emp.getEmail(), emp.getSalary() };
            tableModel.addRow(row);
        }
    }
}
