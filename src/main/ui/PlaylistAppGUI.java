package ui;

import javax.swing.*;
import java.awt.*;

// Playlist Application Graphical User Interface
// Referenced from https://youtu.be/4BRUmU-ETRk?si=dMzs0I5Nene-i4Sl
public class PlaylistAppGUI {
    private JFrame frame;
    public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;


    public PlaylistAppGUI() {
        initialize();
    }

    // EFFECTS: initializes frame
    private void initialize() {
        frame = new JFrame("Playlist App");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
