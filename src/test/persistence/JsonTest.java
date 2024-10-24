package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(String title, String artist, String genre, 
            int rating, String review, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(artist, song.getArtist());
        assertEquals(genre, song.getGenre());
        assertEquals(rating, song.getRating());
        assertEquals(review, song.getReview());
    }
}
