package com.student.sms.ui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Header
        JLabel lblHeader = new JLabel("Student Management System", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(70, 130, 180)); // Steel Blue
        lblHeader.setForeground(Color.WHITE);
        getContentPane().add(lblHeader, BorderLayout.NORTH);

        // Main Panel with Grid for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Buttons
        JButton btnAdd = createStyledButton("Add Student");
        JButton btnView = createStyledButton("Get Student By ID");
        JButton btnViewAll = createStyledButton("Get All Students");
        JButton btnUpdate = createStyledButton("Update Student");
        JButton btnDelete = createStyledButton("Delete Student");
        JButton btnExit = createStyledButton("Exit");

        // Layout Buttons (2 Columns)
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(btnAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(btnView, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(btnViewAll, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(btnUpdate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(btnDelete, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonPanel.add(btnExit, gbc);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners
        btnAdd.addActionListener(e -> new AddStudentFrame().setVisible(true));
        btnView.addActionListener(e -> new GetStudentFrame().setVisible(true));
        btnViewAll.addActionListener(e -> new GetAllStudentsFrame().setVisible(true));
        btnUpdate.addActionListener(e -> new UpdateStudentFrame().setVisible(true));
        btnDelete.addActionListener(e -> new DeleteStudentFrame().setVisible(true));
        btnExit.addActionListener(e -> System.exit(0));
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 255, 128));
        btn.setPreferredSize(new Dimension(200, 50));
        // Hover effect could be added with MouseListener if desired
        return btn;
    }

    public static void main(String[] args) {
        // Set Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to default
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
