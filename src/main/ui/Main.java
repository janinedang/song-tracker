package ui;

import javax.swing.SwingUtilities;

// Calls PlaylistAppGUI class
// Referenced from https://youtu.be/4BRUmU-ETRk?si=Y89UBQdTkuJx4Ip4
public class Main {
    public static void main(String[] args) {
        // new PlaylistApp();
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new PlaylistAppGUI();
            }
        });
    }
}
