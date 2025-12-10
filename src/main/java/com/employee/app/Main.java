package com.employee.app;

import com.employee.app.ui.MainMenuFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run UI in Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame().setVisible(true);
        });
    }
}
