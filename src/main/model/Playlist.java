package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a playlist with a name and a list of songs in the order in which they were added
public class Playlist implements Writable {
    private ArrayList<Song> playlist;
    private String name;

    // REQUIRES: 0 < length of name <= 20
    // EFFECTS: creates an empty playlist with the given name
    public Playlist(String name) {
        this.playlist = new ArrayList<>();
        this.name = name;
    }

    // REQUIRES: song with given title && given artist not in playlist,
    // length of title > 0, length of artist > 0, length of genre > 0
    // MODIFIES: this
    // EFFECTS: creates song with given title, given artist, and given genre and
    // adds it to next empty slot in this playlist
    public void addSong(String title, String artist, String genre) {
        Song newSong = new Song(title, artist, genre);
        playlist.add(newSong);
        EventLog.getInstance().logEvent(new Event(title + " by " + artist + " was added to the playlist."));
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0
    // MODIFIES: this
    // EFFECTS: removes song with given title and given artist from this playlist
    public void removeSong(String title, String artist) {
        Song scrapSong = getSong(title, artist);
        playlist.remove(scrapSong);
        EventLog.getInstance().logEvent(new Event(title + " by " + artist + " was removed from the playlist."));
    }

    // REQUIRES: playlist.size() > 0, 0 <= index <= playlist.size() - 1
    // MODIFIES: this
    // EFFECTS: removes song in playlist at the given index
    public void removeSong(int index) {
        Song song = playlist.get(index);
        String title = song.getTitle();
        String artist = song.getArtist();

        removeSong(title, artist);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0, 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets song with given title and given artist in this playlist to have
    // the given rating
    public void rateSong(String title, String artist, int rating) {
        Song song = getSong(title, artist);
        song.setRating(rating);

        EventLog.getInstance().logEvent(new Event(title + " by " + artist + " was rated " + rating + "."));
    }

    // REQUIRES: playlist.size() > 0, 0 <= index <= playlist.size() - 1,
    // 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets the song at the given index in this playlist
    // to have the given rating
    public void rateSong(int index, int rating) {
        Song song = playlist.get(index);
        String title = song.getTitle();
        String artist = song.getArtist();

        rateSong(title, artist, rating);
    }

    // REQUIRES: song with the given title && given artist in playlist,
    // length of title > 0, length of artist > 0, 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets the song with the given title and given artist in this playlist
    // to have the given review
    public void reviewSong(String title, String artist, String review) {
        Song song = getSong(title, artist);
        song.setReview(review);

        EventLog.getInstance().logEvent(new Event(title + " by " + artist + " was given the review: '" + review + "'."));
    }

    // REQUIRES: playlist.size() > 0, 0 <= index <= playlist.size() - 1,
    // review.length() > 0
    // MODIFIES: this
    // EFFECTS: sets the song at the given index in this playlist
    // to have the given review
    public void reviewSong(int index, String review) {
        Song song = playlist.get(index);
        String title = song.getTitle();
        String artist = song.getArtist();

        reviewSong(title, artist, review);
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
    // EFFECTS: returns true if song with given title and given artist
    // is in this playlist, false if song with given title and given artist
    // is not in this playlist
    public boolean inPlaylist(String title, String artist) {
        for (Song nextSong : playlist) {
            if (nextSong.getTitle().equals(title) && nextSong.getArtist().equals(artist)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public String getName() {
        return name;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates JSON object of the playlist
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        return json;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : playlist) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }
}
