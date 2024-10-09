package model;

import java.util.ArrayList;

public class Playlist {

    // REQUIRES: length of name > 0
    // EFFECTS: creates an empty playlist with the given name
    public Playlist(String name) {
        // stub
    }

    // REQUIRES: song with given title && given artist not in playlist, 
    //           length of title > 0, length of artist > 0, length of genre > 0
    // MODIFIES: this
    // EFFECTS: creates song with given title, given artist, and given genre and adds it to 
    //          next empty slot in this playlist
    public void addSong(String title, String artist, String genre) {
        // stub
    }

    // REQUIRES: song with the given title && given artist in playlist,
    //           length of title > 0, length of artist > 0
    // MODIFIES: this
    // EFFECTS: removes song with given title and given artist from this playlist
    public void removeSong(String title, String artist) {
        // stub
    }

    // REQUIRES: song with the given title && given artist in playlist,
    //           length of title > 0, length of artist > 0, 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets song with given title and given artist in this playlist to have the given rating
    public void rateSong(String title, String artist, int rating) {
        // stub
    }

    // REQUIRES: song with the given title && given artist in playlist,
    //           length of title > 0, length of artist > 0, 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets the song with the given title and given artist in this playlist to have the given review
    public void reviewSong(String title, String artist, String review) {
        // stub
    }

    // REQUIRES: song with the given title && given artist in playlist,
    //           length of title > 0, length of artist > 0
    // EFFECTS: returns the song with the given title and given artist
    public Song getSong(String title, String artist) {
        return null; // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0
    // EFFECTS: returns true if song with given title and given artist is in this playlist, 
    //          false if song with given title and given artist is not in this playlist
    public boolean inPlaylist(String title, String artist) {
        return false; // stub
    }

    public ArrayList<Song> getPlaylist() {
        return null; // stub
    }

    public String getName() {
        return null; // stub
    }
}
