package model;

import java.util.ArrayList;

public class Playlist {

    // REQUIRES: length of name > 0
    // EFFECTS: creates an empty playlist with the given name
    public Playlist(String name) {
        // stub
    }

    // EFFECTS: returns list of songs in this playlist
    public ArrayList<Song> viewSongs() {
        return null; // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0, length of genre > 0
    // MODIFIES: this
    // EFFECTS: creates song with given title, given artist, and given genre and adds it to 
    //          next empty slot in this playlist only if there are no other songs with the same title && artist
    public void addSong(String title, String artist, String genre) {
        // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0
    // MODIFIES: this
    // EFFECTS: removes song with given title and given artist from this playlist,
    //          if there is no song with the given title && given artist then list stays the same
    public void removeSong(String title, String artist) {
        // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0, 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets song with given title and given artist in this playlist to have the given rating,
    //          if there is no song with the given title && given artist then its rating stays the same
    public void rateSong(String title, String artist, int rating) {
        // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0, 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets the song with the given title and given artist in this playlist to have the given review,
    //          if there is no song with the given title && given artist then its review stays the same
    public void reviewSong(String title, String artist, String review) {
        // stub
    }

    // REQUIRES: length of title > 0, length of artist > 0
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
}
