package ui;
import java.awt.*;
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

    private JButton addButton;
    private JButton removeButton;
    private JButton rateButton;
    private JButton reviewButton;
    
    // EFFECTS: constructs a vertically scrollable panel displaying list of songs
    // from a given playlist and a button panel with add, remove, rate, and review buttons
    public PlaylistGUI(Playlist playlist) {
        super(new BorderLayout());

        this.playlist = playlist;
        initializePlaylistPanel();
        initializeButtonPanel();
    }

    // EFFECTS: creates a panel displaying all of the songs in the playlist
    private void initializePlaylistPanel() {
        playlistModel = new DefaultListModel<>();
        addSongsToPlaylistModel(playlistModel);

        playlistJList = new JList(playlistModel);
        playlistJList.setCellRenderer(new PlaylistCellRenderer());
        playlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistJList.setSelectedIndex(0);
        playlistJList.addListSelectionListener(this);

        JScrollPane playlistScrollPane = new JScrollPane(playlistJList);
        add(playlistScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: creates a panel displaying the add, remove, rate, and review song buttons
    private void initializeButtonPanel() {
        addButton = new JButton(addString);
        removeButton = new JButton(removeString);
        rateButton = new JButton(rateString);
        reviewButton = new JButton(reviewString);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(rateButton);
        buttonPane.add(reviewButton);

        buttonPane.setBorder(BorderFactory.createLineBorder(Color.black));
        add(buttonPane, BorderLayout.SOUTH);
    }

    // EFFECTS: adds all songs in playlist in order of first song added
    // to a given playlistModel
    private void addSongsToPlaylistModel(DefaultListModel<Song> playlistModel) {
        for (Song song : playlist.getPlaylist()) {
            playlistModel.addElement(song);
        }
    }

    // EFFECTS: updates add, rate, review, and remove buttons when 
    // selection of list cell changes
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (playlistJList.getSelectedIndex() == -1) {
        
                addButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                addButton.setEnabled(true);
            }
        }
    }
}
