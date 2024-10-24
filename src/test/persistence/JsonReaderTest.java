package persistence;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist playlist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("My playlist", playlist.getName());
            assertEquals(0, playlist.getPlaylist().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("My playlist", playlist.getName());
            List<Song> songs = playlist.getPlaylist();
            assertEquals(3, songs.size());
            checkSong("FANCY", "TWICE", "K-Pop", 4, "I love this song!", songs.get(0));
            checkSong("Finesse", "Bruno Mars", "Pop", 5, "Amazing song, so catchy!", songs.get(1));
            checkSong("Creep", "Radiohead", "Alternative", 3, "I'm a creep...", songs.get(2));
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}