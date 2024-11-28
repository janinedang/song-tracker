package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import model.Playlist;
import model.Song;
import persistence.JsonWriter;

// Graphical panel displaying list of songs in playlist and a button panel 
// Referenced from the ListDemo
// https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
public class PlaylistGUI extends JPanel implements ListSelectionListener {
    private Playlist playlist;
    private JList<Song> playlistJList;
    private DefaultListModel<Song> playlistModel;
    private ErrorPanel error;

    private static final int FACTOR = 3;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String rateString = "Rate";
    private static final String reviewString = "Review";
    private static final String quitString = "Quit";
    private static final Border paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    private static final String JSON_STORE = "./data/playlist.json";

    private JButton addButton;
    private JButton removeButton;
    private JButton rateButton;
    private JButton reviewButton;
    private JButton quitButton;
    private ImageIcon logoIcon;
    private ImageIcon backgroundIcon;

    private JsonWriter jsonWriter;

    // EFFECTS: constructs a vertically scrollable panel displaying list of songs
    // from a given playlist and a button panel with add, remove, rate, and review
    // buttons
    public PlaylistGUI(Playlist playlist) {
        super(new BorderLayout());

        this.playlist = playlist;
        error = new ErrorPanel();
        jsonWriter = new JsonWriter(JSON_STORE);

        initializeTopPanel();
        initializePlaylistPanel();
        initializeButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates a panel on the top of the screen with the playlist name on
    // the left and
    // the application logo on the right
    private void initializeTopPanel() {
        // Referenced from BorderDemo
        // https://docs.oracle.com/javase/tutorial/uiswing/components/border.html

        loadImage();
        JLabel logo = new JLabel(logoIcon);
        JLabel background = new JLabel(backgroundIcon);

        JLabel playlistName = new JLabel(playlist.getName());
        playlistName.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        playlistName.setForeground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(playlistName, BorderLayout.WEST);
        topPanel.add(background, BorderLayout.CENTER);
        topPanel.add(logo, BorderLayout.EAST);
        topPanel.setBorder(paneEdge);
        topPanel.setBackground(new Color(194, 225, 237));

        add(topPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: loads and resizes logoIcon
    // Referenced from https://stackoverflow.com/a/18335435
    private void loadImage() {
        String sep = System.getProperty("file.separator");

        logoIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "music_tracker_logo_white.png");

        Image logoImage = logoIcon.getImage();
        Image logoImageNew = logoImage.getScaledInstance(479 / FACTOR, 70 / FACTOR, java.awt.Image.SCALE_SMOOTH);

        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "blue_bg.png");
        Image background = img.getImage();
        background = background.getScaledInstance(600, 50, java.awt.Image.SCALE_SMOOTH);

        logoIcon = new ImageIcon(logoImageNew);
        backgroundIcon = new ImageIcon(background);
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
    // EFFECTS: creates a panel displaying the add, remove, rate, and review song
    // buttons
    private void initializeButtonPanel() {
        initializeButtons();

        if (playlist.getPlaylist().size() == 0) {
            removeButton.setEnabled(false);
            rateButton.setEnabled(false);
            reviewButton.setEnabled(false);
        }

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

    private void initializeButtons() {
        addButton = new JButton(addString);
        addButton.addActionListener(new AddAction());

        removeButton = new JButton(removeString);
        removeButton.addActionListener(new RemoveAction());

        rateButton = new JButton(rateString);
        rateButton.addActionListener(new RateAction());

        reviewButton = new JButton(reviewString);
        reviewButton.addActionListener(new ReviewAction());

        quitButton = new JButton(quitString);
        quitButton.addActionListener(new QuitAction());
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

    // MODIFIES: this
    // EFFECTS: updates add, rate, review, and remove buttons when
    // selection of list cell changes
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (playlistJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
                rateButton.setEnabled(false);
                reviewButton.setEnabled(false);
            } else {
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
                        error.errorMessage("This song is already in your playlist.");
                    }
                } else {
                    error.errorMessage("Please fill all blanks.");
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
    }

    // UI to remove songs from the playlist
    private class RemoveAction implements ActionListener {

        // EFFECTS: constructs a RemoveAction object
        public RemoveAction() {
            // blank
        }

        // REQUIRES: an element in playlistJList must be selected
        // MODIFIES: this
        // EFFECTS: removes currently selected list item from playlist
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = playlistJList.getSelectedIndex();
            playlist.removeSong(index);
            updatePlaylist();
        }
    }

    // UI to rate songs from the playlist
    private class RateAction implements ActionListener {

        // EFFECTS: constructs a RateAction object
        public RateAction() {
            // blank
        }

        // REQUIRES: an element in playlistJList must be selected, user input must be a
        // number
        // MODIFIES: this
        // EFFECTS: rates currently selected list item from playlist
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = playlistJList.getSelectedIndex();
            String userRating = JOptionPane.showInputDialog(null,
                    "Please enter a rating from 1-5.",
                    "Rating: ",
                    JOptionPane.QUESTION_MESSAGE);

            int rating = Integer.parseInt(userRating);

            if (userRating.length() != 0) {
                if (rating >= 1 && rating <= 5) {
                    playlist.rateSong(index, rating);
                    updatePlaylist();
                } else {
                    error.errorMessage(userRating + " is not an accepted rating.");
                }
            } else {
                error.errorMessage("Please fill all blanks.");
            }
        }
    }

    // UI to review songs from the playlist
    private class ReviewAction implements ActionListener {

        // EFFECTS: constructs a ReviewAction object
        public ReviewAction() {
            // blank
        }

        // REQUIRES: an element in playlistJList must be selected, user input must be
        // within 1-150 characters
        // MODIFIES: this
        // EFFECTS: reviews currently selected list item from playlist
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = playlistJList.getSelectedIndex();
            String review = JOptionPane.showInputDialog(null,
                    "Please enter a review within 1-150 characters",
                    "Review: ",
                    JOptionPane.QUESTION_MESSAGE);

            if (review.length() != 0) {
                if (review.length() >= 1 && review.length() <= 150) {
                    playlist.reviewSong(index, review);
                    updatePlaylist();
                } else {
                    error.errorMessage("Your review is not within the character limit.");
                }
            } else {
                error.errorMessage("Please fill all blanks.");
            }
        }
    }

    // UI to quit the application
    private class QuitAction implements ActionListener {

        // EFFECTS: constructs a QuitAction object
        public QuitAction() {
            // blank
        }

        // MODIFIES: this
        // EFFECTS: asks user if they want to save their playlist and quits the
        // application
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean validInput = false;

            while (!validInput) {
                int result = JOptionPane.showConfirmDialog(null, "Would you like to save your playlist?",
                        "Save Playlist?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    validInput = true;
                    boolean saved = savePlaylist();

                    if (!saved) {
                        error.errorMessage("File was not able to save.");
                    }

                    System.exit(0);

                } else if (result == JOptionPane.NO_OPTION) {
                    validInput = true;
                    System.exit(0);
                }
            }
        }

        // Referenced from the JsonSerialization Demo
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
        // EFFECTS: saves the playlist to file
        private boolean savePlaylist() {
            try {
                jsonWriter.open();
                jsonWriter.write(playlist);
                jsonWriter.close();
                return true;
            } catch (FileNotFoundException e) {
                return false;
            }
        }
    }
}
