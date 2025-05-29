package music;

import laboratoria.music.Playlist;
import laboratoria.music.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaylistTest {

    @Test
    public void isEmptyTest() {
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty(), "Playlist should be empty initially");
    }

    @Test
    public void addingTest() {
        Playlist playlist = new Playlist();
        Song song = new Song("Test Song", "Test Artist", 100);
        playlist.add(song);
        assertEquals(1, playlist.size(), "Playlist should contain the added song");
    }

    @Test
    public void atSecondTest() {
        Playlist playlist = new Playlist();
        Song song1 = new Song("Song 1", "Artist 1", 10);
        Song song2 = new Song("Song 2", "Artist 2", 20);
        playlist.add(song1);
        playlist.add(song2);
        assertEquals(song2, playlist.atSecond(11));
    }

    @Test
    public void atSecondExceptionTest() {
        Playlist playlist = new Playlist();
        Song song1 = new Song("Song 1", "Artist 1", 10);
        Song song2 = new Song("Song 2", "Artist 2", 20);
        playlist.add(song1);
        playlist.add(song2);

        try {
            playlist.atSecond(31);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Provided second exceeds total length of the playlist", e.getMessage());
        }
    }
}
