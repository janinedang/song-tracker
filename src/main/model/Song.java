package model;

public class Song {

    // REQUIRES: length of title > 0
    // EFFECTS: creates a song with given title, rating of 0, and an empty review
    public Song(String title) {
        // stub
    }

    // REQUIRES: length of title > 0
    // MODIFIES: this
    // EFFECTS: sets this song's title to given title
    public void setTitle(String title) {
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

    public int getRating() {
        return 0; // stub
    }

    public String getReview() {
        return null; // stub
    }
}
