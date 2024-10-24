package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a song with a title, artist, genre, rating, and a review
public class Song implements Writable {
    private String title;
    private String artist;
    private String genre;
    private int rating;
    private String review;

    // REQUIRES: length of title > 0, length of artist > 0, length of genre > 0
    // EFFECTS: creates a song with given title, given artist, given genre,
    // rating of 1, and an empty review
    public Song(String title, String artist, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.rating = 1;
        this.review = "";
    }

    // REQUIRES: length of title > 0
    // MODIFIES: this
    // EFFECTS: sets this song's title to given title
    public void setTitle(String title) {
        this.title = title;
    }

    // REQUIRES: length of artist > 0
    // MODIFIES: this
    // EFFECTS: sets this song's artist to given artist
    public void setArtist(String artist) {
        this.artist = artist;
    }

    // REQUIRES: length of genre > 0
    // MODIFIES: this
    // EFFECTS: sets this song's genre to given genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // REQUIRES: 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets this song's rating to given rating
    public void setRating(int rating) {
        this.rating = rating;
    }

    // REQUIRES: 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets this song's review to given review
    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a JSON object from this song
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("artist", artist);
        json.put("genre", genre);
        json.put("rating", rating);
        json.put("review", review);

        return json;
    }
}
