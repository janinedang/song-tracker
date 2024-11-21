package ui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Playlist;
import model.Song;

// Graphical panel displaying list of songs in playlist and a button panel 
// Referenced from the ListDemo
// https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
public class PlaylistGUI extends JPanel implements ListSelectionListener {
    private Playlist playlist;
    private JList<Song> playlistJList;
    private DefaultListModel<Song> playlistModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String rateString = "Rate";
    private static final String reviewString = "Review";
    private static final String quitString = "Quit";

    private JButton addButton;
    private JButton removeButton;
    private JButton rateButton;
    private JButton reviewButton;
    private JButton quitButton;
    
    // EFFECTS: constructs a vertically scrollable panel displaying list of songs
    // from a given playlist and a button panel with add, remove, rate, and review buttons
    public PlaylistGUI(Playlist playlist) {
        super(new BorderLayout());

        this.playlist = playlist;
        initializePlaylistPanel();
        initializeButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates a panel displaying all of the songs in the playlist
    private void initializePlaylistPanel() {
        playlistModel = new DefaultListModel<>();
        addSongsToPlaylistModel();

        playlistJList = new JList(playlistModel);
        playlistJList.setCellRenderer(new PlaylistCellRenderer());
        playlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistJList.setSelectedIndex(0);
        playlistJList.addListSelectionListener(this);

        JScrollPane playlistScrollPane = new JScrollPane(playlistJList);
        add(playlistScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel displaying the add, remove, rate, and review song buttons
    private void initializeButtonPanel() {
        addButton = new JButton(addString);
        addButton.addActionListener(new AddAction());

        removeButton = new JButton(removeString);
        rateButton = new JButton(rateString);
        reviewButton = new JButton(reviewString);
        quitButton = new JButton(quitString);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(rateButton);
        buttonPane.add(reviewButton);
        buttonPane.add(quitButton);

        buttonPane.setBorder(BorderFactory.createLineBorder(Color.black));
        add(buttonPane, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds all songs in playlist in order of first song added
    // to a given playlistModel
    private void addSongsToPlaylistModel() {
        for (Song song : playlist.getPlaylist()) {
            playlistModel.addElement(song);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears playlistModel
    private void clearPlaylist() {
        playlistJList.clearSelection();
        playlistModel.clear();
    }

    // MODIFIES: this
    // EFFECTS: clears playlistModel, then updates
    // playlistModel to the current state of playlist
    private void updatePlaylist() {
        clearPlaylist();
        addSongsToPlaylistModel();
    }

    // EFFECTS: creates an error panel displaying the given message
    // Referenced from AlarmSystem
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
    private void errorPanel(String message) {
        JOptionPane.showMessageDialog(null, message, "System Error",
                        JOptionPane.ERROR_MESSAGE);
    }
    
    // MODIFIES: this
    // EFFECTS: updates add, rate, review, and remove buttons when 
    // selection of list cell changes
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (playlistJList.getSelectedIndex() == -1) {
                addButton.setEnabled(false);
                removeButton.setEnabled(false);
                rateButton.setEnabled(false);
                reviewButton.setEnabled(false);
            } else {
                addButton.setEnabled(true);
                removeButton.setEnabled(true);
                rateButton.setEnabled(true);
                reviewButton.setEnabled(true);
            }
        }
    }
    
    // UI to add songs to the playlist
    private class AddAction implements ActionListener {
        private JTextField titleField;
        private JTextField artistField;
        private JTextField genreField;
        
        // EFFECTS: constructs an AddAction object
        public AddAction() {
            // blank
        }
        
        // MODIFIES: this
        // EFFECTS: asks user for song title, artist, and genre and
        // adds it to the playlist, but:
        // if the song title and artist are already in the playlist
        // or if any text fields are left blank, a system error pops up.
        // Referenced from https://stackoverflow.com/a/6555051
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel addPanel = createAddPanel();
            int result = JOptionPane.showConfirmDialog(null, addPanel,
                    "Please enter the song title, artist, and genre.", JOptionPane.OK_CANCEL_OPTION);
    
            if (result == JOptionPane.OK_OPTION) {
                String title = titleField.getText();
                String artist = artistField.getText();
                String genre = genreField.getText();
    
                if (title.length() != 0 && artist.length() != 0 && genre.length() != 0) {
                    if (!playlist.inPlaylist(title, artist)) {
                        playlist.addSong(title, artist, genre);
                        updatePlaylist();
                    } else {
                        errorPanel("This song is already in your playlist.");
                    }
                } else {
                    errorPanel("Please fill all blanks.");
                }
            }
            
        }
        
        // MODIFIES: this
        // EFFECTS: creates a pop up panel with text fields for
        // the user to input a song's title, artist, and genre
        // Referenced from https://stackoverflow.com/a/6555051 
        private JPanel createAddPanel() {
            titleField = new JTextField(5);
            artistField = new JTextField(5);
            genreField = new JTextField(5);
    
            JPanel addPanel = new JPanel();
            addPanel.setLayout(new GridLayout(6, 1, 5, 0));
    
            addPanel.add(new JLabel("Title:"));
            addPanel.add(titleField);
    
            addPanel.add(new JLabel("Artist:"));
            addPanel.add(artistField);
    
            addPanel.add(new JLabel("Genre:"));
            addPanel.add(genreField);
    
            return addPanel;
        }

        private void addPanelInput(JPanel addPanel) {
            int result = JOptionPane.showConfirmDialog(null, addPanel,
                    "Please enter the song title, artist, and genre.", JOptionPane.OK_CANCEL_OPTION);
    
            if (result == JOptionPane.OK_OPTION) {
                String title = titleField.getText();
                String artist = artistField.getText();
                String genre = genreField.getText();
    
                if (title.length() != 0 && artist.length() != 0 && genre.length() != 0) {
                    if (!playlist.inPlaylist(title, artist)) {

                    } else {
                        errorPanel("This song is already in your playlist.");
                    }
                } else {
                    errorPanel("Please fill all blanks.");
                }
            }
        }
    }
}
