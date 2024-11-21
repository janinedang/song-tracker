package ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorPanel extends JPanel {
    // EFFECTS: creates an error panel displaying the given message
    // Referenced from AlarmSystem
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

    public ErrorPanel() {
        // blank
    }

    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "System Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
