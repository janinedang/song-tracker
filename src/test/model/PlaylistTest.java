package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistTest {
    private Playlist testPlaylist;

    @BeforeEach
    void runBefore() {
        testPlaylist = new Playlist("Studying");
    }

    @Test
    void testConstructor() {
        ArrayList<Song> songs = testPlaylist.getPlaylist();
        assertEquals("Studying", testPlaylist.getName());
        assertEquals(0, songs.size());
    }

    @Test
    void testAddOneSong() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        ArrayList<Song> songs = testPlaylist.getPlaylist();

        assertEquals(1, songs.size());
        assertTrue(testPlaylist.inPlaylist("Butter", "BTS"));

        assertEquals("Butter", songs.get(0).getTitle());
        assertEquals("BTS", songs.get(0).getArtist());
        assertEquals("KPop", songs.get(0).getGenre());
        assertEquals(1, songs.get(0).getRating());
        assertEquals("", songs.get(0).getReview());
    }

    @Test
    void testAddTwoSongs() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.addSong("Clementine", "grentperez", "Indie Pop");
        ArrayList<Song> songs = testPlaylist.getPlaylist();

        assertEquals(2, songs.size());
        assertTrue(testPlaylist.inPlaylist("Butter", "BTS"));
        assertTrue(testPlaylist.inPlaylist("Clementine", "grentperez"));

        assertEquals("Butter", songs.get(0).getTitle());
        assertEquals("BTS", songs.get(0).getArtist());
        assertEquals("KPop", songs.get(0).getGenre());
        assertEquals(1, songs.get(0).getRating());
        assertEquals("", songs.get(0).getReview());

        assertEquals("Clementine", songs.get(1).getTitle());
        assertEquals("grentperez", songs.get(1).getArtist());
        assertEquals("Indie Pop", songs.get(1).getGenre());
        assertEquals(1, songs.get(1).getRating());
        assertEquals("", songs.get(1).getReview());
    }

    @Test
    void testRemoveSongItemInList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        ArrayList<Song> songs = testPlaylist.getPlaylist();
        assertEquals(1, songs.size());
        assertTrue(testPlaylist.inPlaylist("Butter", "BTS"));
        testPlaylist.removeSong("Butter", "BTS");

        assertEquals(0, songs.size());
        assertFalse(testPlaylist.inPlaylist("Butter", "BTS"));
    }

    @Test
    void testRemoveFirstItemLongList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.addSong("Clementine", "grentperez", "Indie Pop");
        testPlaylist.addSong("Get You", "Daniel Caesar", "R&B");
        testPlaylist.removeSong("Butter", "BTS");
        ArrayList<Song> songs = testPlaylist.getPlaylist();

        assertEquals(2, songs.size());
        assertFalse(testPlaylist.inPlaylist("Butter", "BTS"));

        assertEquals("Clementine", songs.get(0).getTitle());
        assertEquals("grentperez", songs.get(0).getArtist());
        assertEquals("Indie Pop", songs.get(0).getGenre());
        assertEquals(1, songs.get(0).getRating());
        assertEquals("", songs.get(0).getReview());

        assertEquals("Get You", songs.get(1).getTitle());
        assertEquals("Daniel Caesar", songs.get(1).getArtist());
        assertEquals("R&B", songs.get(1).getGenre());
        assertEquals(1, songs.get(1).getRating());
        assertEquals("", songs.get(1).getReview());
    }

    @Test
    void testRemoveMiddleItemLongList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.addSong("Clementine", "grentperez", "Indie Pop");
        testPlaylist.addSong("Get You", "Daniel Caesar", "R&B");
        testPlaylist.removeSong("Clementine", "grentperez");
        ArrayList<Song> songs = testPlaylist.getPlaylist();

        assertEquals(2, songs.size());
        assertFalse(testPlaylist.inPlaylist("Clementine", "grentperez"));

        assertEquals("Butter", songs.get(0).getTitle());
        assertEquals("BTS", songs.get(0).getArtist());
        assertEquals("KPop", songs.get(0).getGenre());
        assertEquals(1, songs.get(0).getRating());
        assertEquals("", songs.get(0).getReview());

        assertEquals("Get You", songs.get(1).getTitle());
        assertEquals("Daniel Caesar", songs.get(1).getArtist());
        assertEquals("R&B", songs.get(1).getGenre());
        assertEquals(1, songs.get(1).getRating());
        assertEquals("", songs.get(1).getReview());
    }

    @Test
    void testRateSongInList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.rateSong("Butter", "BTS", 3);
        assertEquals(3, testPlaylist.getSong("Butter", "BTS").getRating());
    }

    @Test
    void testRateSongSameRating() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.rateSong("Butter", "BTS", 2);
        testPlaylist.rateSong("Butter", "BTS", 2);
        assertEquals(2, testPlaylist.getSong("Butter", "BTS").getRating());
    }

    @Test
    void testRateSongMiddleItemLongList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.addSong("Clementine", "grentperez", "Indie Pop");
        testPlaylist.addSong("Get You", "Daniel Caesar", "R&B");
        testPlaylist.rateSong("Clementine", "grentperez", 5);
        assertEquals(5, testPlaylist.getSong("Clementine", "grentperez").getRating());
    }

    @Test
    void testReviewSongInList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.reviewSong("Butter", "BTS", "This song is slippery like butter!");
        assertEquals("This song is slippery like butter!", testPlaylist.getSong("Butter", "BTS").getReview());
    }

    @Test
    void testReviewSongSameReview() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.reviewSong("Butter", "BTS", "I LOVE BTS");
        testPlaylist.reviewSong("Butter", "BTS", "I LOVE BTS");
        assertEquals("I LOVE BTS", testPlaylist.getSong("Butter", "BTS").getReview());
    }

    @Test
    void testReviewSongMiddleItemLongList() {
        testPlaylist.addSong("Butter", "BTS", "KPop");
        testPlaylist.addSong("Clementine", "grentperez", "Indie Pop");
        testPlaylist.addSong("Get You", "Daniel Caesar", "R&B");
        testPlaylist.reviewSong("Clementine", "grentperez", "I love this song!");
        assertEquals("I love this song!", testPlaylist.getSong("Clementine", "grentperez").getReview());
    }

    @Test
    void testGetSongInList() {
        testPlaylist.addSong("TT", "TWICE", "KPop");
        assertEquals("TT", testPlaylist.getSong("TT", "TWICE").getTitle());
        assertEquals("TWICE", testPlaylist.getSong("TT", "TWICE").getArtist());
        assertEquals("TWICE", testPlaylist.getSong("TT", "TWICE").getArtist());
    }

    @Test
    void testGetSongSameTitleDiffArtist() {
        testPlaylist.addSong("Baby", "TWICE", "KPop");
        assertEquals(null, testPlaylist.getSong("Baby", "Justin Bieber"));
    }

    @Test
    void testGetSongDiffTitleSameArtist() {
        testPlaylist.addSong("TT", "TWICE", "KPop");
        assertEquals(null, testPlaylist.getSong("FANCY", "TWICE"));
    }

    @Test
    void testInPlaylistFalseSameTitleDiffArtist() {
        testPlaylist.addSong("TT", "TWICE", "KPop");
        assertFalse(testPlaylist.inPlaylist("TT", "Justin Bieber"));
    }

    @Test
    void testInPlaylistFalseDiffTitleSameArtist() {
        testPlaylist.addSong("TT", "TWICE", "KPop");
        assertFalse(testPlaylist.inPlaylist("FANCY", "TWICE"));
    }
}