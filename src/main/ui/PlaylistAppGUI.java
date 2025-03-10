package ui;

import javax.swing.*;

import model.Event;
import model.EventLog;
import model.Playlist;
import persistence.JsonReader;

import java.awt.*;
import java.io.IOException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// Playlist Application Graphical User Interface
// Referenced from https://youtu.be/4BRUmU-ETRk?si=dMzs0I5Nene-i4Sl
public class PlaylistAppGUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/playlist.json";

    private JsonReader jsonReader;

    private ErrorPanel error;
    private Playlist playlist;

    // EFFECTS: initializes JFrame and asks user to load save
    public PlaylistAppGUI() {
        initialize();
        loadSave();
        addPlaylistPanel();
        setVisible(true);
        setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
                dispose();
                System.exit(0);
            }
        });
    }

    // EFFECTS: prints out each event recorded in the event log
    private void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString() + "\n");
        }
    }

    // EFFECTS: initializes error panel, json reader, and json writer
    private void initialize() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        

        error = new ErrorPanel();

        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: adds playlist panel to the frame
    private void addPlaylistPanel() {
        JComponent playlistContentPane = new PlaylistGUI(this, playlist);
        playlistContentPane.setOpaque(true);

        add(playlistContentPane, BorderLayout.CENTER);
        
        playlistContentPane.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: creates a pop up asking user if they want to load their save,
    // if user clicks yes then if there is a save file, it will be loaded.
    // if user clicks no, then no save file is loaded.
    private void loadSave() {
        boolean validInput = false;

        while (!validInput) {
            int result = JOptionPane.showConfirmDialog(this, "Would you like to load your save?", "Load Save?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                validInput = true;
                boolean loaded = loadPlaylist();

                if (!loaded) {
                    createPlaylist();
                }

            } else if (result == JOptionPane.NO_OPTION) {
                validInput = true;
                createPlaylist();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: asks user to create a name for the playlist, if playlist name does
    // not meet the requirements a system error will pop up until a playlist name that
    // does meet the requirement is entered.
    private void createPlaylist() {
        boolean validName = false;

        while (!validName) {
            String playlistName = JOptionPane.showInputDialog(null,
                    "Please create a name for your playlist within 1-20 characters",
                    "Name: ",
                    JOptionPane.QUESTION_MESSAGE);
            if (playlistName != null) {
                if (playlistName.length() != 0) {
                    if (playlistName.length() >= 1 && playlistName.length() <= 20) {
                        validName = true;
                        playlist = new Playlist(playlistName);
                    } else {
                        error.errorMessage("Your playlist name is not within the character limit.");
                    }
                } else {
                    error.errorMessage("No name was entered, try again.");
                }
            }
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads playlist from file and returns true if a playlist was loaded,
    // if unable to load file, a system error pops up and returns false
    private boolean loadPlaylist() {
        try {
            playlist = jsonReader.read();
            return true;
        } catch (IOException e) {
            error.errorMessage("Unable to read from file: " + JSON_STORE);
            return false;
        }
    }
}
