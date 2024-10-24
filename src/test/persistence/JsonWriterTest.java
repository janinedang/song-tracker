package persistence;

import model.Playlist;
import model.Song;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist playlist = new Playlist("My playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() {
        try {
            Playlist playlist = new Playlist("My playlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            playlist = reader.read();
            assertEquals("My playlist", playlist.getName());
            assertEquals(0, playlist.getPlaylist().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlaylist() {
        try {
            Playlist playlist = new Playlist("My playlist");

            playlist.addSong("FANCY", "TWICE", "K-Pop");
            playlist.rateSong("FANCY", "TWICE", 4);
            playlist.reviewSong("FANCY", "TWICE", "I love this song!");

            playlist.addSong("Finesse", "Bruno Mars", "Pop");
            playlist.rateSong("Finesse", "Bruno Mars", 5);
            playlist.reviewSong("Finesse", "Bruno Mars", "Amazing song, so catchy!");

            playlist.addSong("Creep", "Radiohead", "Alternative");
            playlist.rateSong("Creep", "Radiohead", 3);
            playlist.reviewSong("Creep", "Radiohead", "I'm a creep...");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            playlist = reader.read();
            assertEquals("My playlist", playlist.getName());
            List<Song> songs = playlist.getPlaylist();
            assertEquals(3, songs.size());
            checkSong("FANCY", "TWICE", "K-Pop", 4, "I love this song!", songs.get(0));
            checkSong("Finesse", "Bruno Mars", "Pop", 5, "Amazing song, so catchy!", songs.get(1));
            checkSong("Creep", "Radiohead", "Alternative", 3, "I'm a creep...", songs.get(2));
            

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}