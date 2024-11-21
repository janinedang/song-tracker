package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import model.Song;

// Renders cells in PlaylistGUI to have line breaks
// Referenced from https://stackoverflow.com/a/21502062
public class PlaylistCellRenderer extends DefaultListCellRenderer {

    // EFFECTS: renders the cells of a given playlist to have line breaks
    // in between each song's title, artist, genre, rating, and review
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        Song song = (Song) value;
        String title = song.getTitle();
        String artist = song.getArtist();
        String genre = song.getGenre();
        int rating = song.getRating();
        String review = song.getReview();

        String labelText = "<html>Title: " + title + "<br/>Artist: " + artist + "<br/>Genre: " + genre 
                + "<br/>Rating: " + rating + "<br/>Review: " + review;
        setText(labelText);
        
        Border paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        setBorder(paneEdge);

        return this;
    }
}
