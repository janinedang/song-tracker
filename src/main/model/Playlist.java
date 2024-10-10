package model;

import java.util.ArrayList;

// Represents a playlist with a name and a list of songs in the order in which they were added
public class Playlist {
    private ArrayList<Song> playlist;
    private String name;

    // REQUIRES: length of name > 0
    // EFFECTS: creates an empty playlist with the given name
    public Playlist(String name) {
        this.playlist = new ArrayList<>();
        this.name = name;
    }

    // REQUIRES: song with given title && given artist not in playlist,
    // length of title > 0, length of artist > 0, length of genre > 0
    // MODIFIES: this
    // EFFECTS: creates song with given title, given artist, and given genre and
    // adds it to
    // next empty slot in this playlist
    public void addSong(String title, String artist, String genre) {
        Song newSong = new Song(title, artist, genre);
        playlist.add(newSong);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0
    // MODIFIES: this
    // EFFECTS: removes song with given title and given artist from this playlist
    public void removeSong(String title, String artist) {
        Song scrapSong = getSong(title, artist);
        playlist.remove(scrapSong);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0, 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets song with given title and given artist in this playlist to have
    // the given rating
    public void rateSong(String title, String artist, int rating) {
        Song songRate = getSong(title, artist);
        songRate.setRating(rating);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0, 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets the song with the given title and given artist in this playlist
    // to have the given review
    public void reviewSong(String title, String artist, String review) {
        Song songReview = getSong(title, artist);
        songReview.setReview(review);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0
    // EFFECTS: returns the song with the given title and given artist
    public Song getSong(String title, String artist) {
        Song targetSong = null;

        for (Song nextSong : playlist) {
            if (nextSong.getTitle().equals(title) && nextSong.getArtist().equals(artist)) {
                targetSong = nextSong;
                break;
            }
        }

        return targetSong;
    }

    // REQUIRES: length of title > 0, length of artist > 0
    // EFFECTS: returns true if song with given title and given artist is in this
    // playlist,
    // false if song with given title and given artist is not in this playlist
    public boolean inPlaylist(String title, String artist) {
        boolean songInPlaylist = false;

        for (Song nextSong : playlist) {
            if (nextSong.getTitle().equals(title) && nextSong.getArtist().equals(artist)) {
                songInPlaylist = true;
                break;
            }
        }

        return songInPlaylist;
    }

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public String getName() {
        return name;
    }
}
