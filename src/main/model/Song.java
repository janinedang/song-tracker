package model;

public class Song {

    // REQUIRES: length of title > 0, length of artist > 0, length of genre > 0
    // EFFECTS: creates a song with given title, given artist, given genre,
    //          rating of 1, and an empty review
    public Song(String title, String artist, String genre) {
        // stub
    }

    // REQUIRES: length of title > 0
    // MODIFIES: this
    // EFFECTS: sets this song's title to given title
    public void setTitle(String title) {
        // stub
    }

    // REQUIRES: length of artist > 0
    // MODIFIES: this
    // EFFECTS: sets this song's artist to given artist
    public void setArtist(String artist) {
        // stub
    }

    // REQUIRES: length of genre > 0
    // MODIFIES: this
    // EFFECTS: sets this song's genre to given genre
    public void setGenre(String genre) {
        // stub
    }

    // REQUIRES: 1 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets this song's rating to given rating
    public void setRating(int rating) {
        // stub
    }

    // REQUIRES: 0 < length of review <= 150
    // MODIFIES: this
    // EFFECTS: sets this song's review to given review
    public void setReview(String review) {
        // stub
    }

    public String getTitle() {
        return null; // stub
    }

    public String getArtist() {
        return null; // stub
    }

    public String getGenre() {
        return null; // stub
    }

    public int getRating() {
        return 0; // stub
    }

    public String getReview() {
        return null; // stub
    }
}
