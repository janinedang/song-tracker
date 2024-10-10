package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongTest {
    private Song testSong;

    @BeforeEach
    void runBefore() {
        testSong = new Song("TT", "Twice", "KPop");
    }

    @Test
    void testConstructor() {
        assertEquals("TT", testSong.getTitle());
        assertEquals("Twice", testSong.getArtist());
        assertEquals("KPop", testSong.getGenre());
        assertEquals(1, testSong.getRating());
        assertEquals("", testSong.getReview());
    }

    @Test
    void testSetTitle() {
        testSong.setTitle("FANCY");
        assertEquals("FANCY", testSong.getTitle());
    }

    @Test
    void testSetArtist() {
        testSong.setArtist("New Jeans");
        assertEquals("New Jeans", testSong.getArtist());
    }

    @Test
    void testSetGenre() {
        testSong.setGenre("R&B");
        assertEquals("R&B", testSong.getGenre());
    }

    @Test
    void testSetRating() {
        testSong.setRating(4);
        assertEquals(4, testSong.getRating());
    }

    @Test
    void testSetReview() {
        testSong.setReview("I love this song!");
        assertEquals("I love this song!", testSong.getReview());
    }
}
