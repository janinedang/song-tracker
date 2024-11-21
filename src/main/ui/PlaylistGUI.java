package ui;
import java.awt.*;
import javax.swing.*;

import model.Playlist;
import model.Song;

// Graphical panel displaying list of songs in playlist
// Referenced from the ListDemo
// https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
public class PlaylistGUI extends JPanel {
    private Playlist playlist;
    private JList<Song> playlistJList;
    private DefaultListModel<Song> playlistModel;
    
    // EFFECTS: constructs a vertically scrollable panel displaying list of songs
    // from a given playlist
    public PlaylistGUI(Playlist playlist) {
        super(new BorderLayout());

        this.playlist = playlist;

        playlistModel = new DefaultListModel<>();
        addSongsToPlaylistModel(playlistModel);

        playlistJList = new JList(playlistModel);
        playlistJList.setCellRenderer(new PlaylistCellRenderer());
        playlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistJList.setSelectedIndex(0);

        JScrollPane playlistScrollPane = new JScrollPane(playlistJList);
        add(playlistScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: adds all songs in playlist in order of first song added
    // to a given playlistModel
    private void addSongsToPlaylistModel(DefaultListModel<Song> playlistModel) {
        for (Song song : playlist.getPlaylist()) {
            playlistModel.addElement(song);
        }
    }
}
