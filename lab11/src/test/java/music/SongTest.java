package music;

import laboratoria.database.DatabaseConnection;
import laboratoria.music.Song;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongTest {

    /*
    @Test
    public void readSongTest() {
        DatabaseConnection.connect("src/main/resources/songs.db", "songs");
        try {
            Optional<Song> song = Song.Persistence.read(1);
            Song correctSong = new Song("The Beatles", "Hey Jude", 431);
            assertTrue(song.isPresent(), "Song should be present in the database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect("songs");
        }
    }*/

    @BeforeAll
    static void connectDB() {
        DatabaseConnection.connect("src/main/resources/songs.db", "songs");
    }

    @Test
    void readIsCorrectTest() {
        try {
            Optional<Song> song = Song.Persistence.read(1);
            Song correctSong = new Song("The Beatles", "Hey Jude", 431);
            assertTrue(song.isPresent(), "Song should be present in the database");
            assertEquals(song.get(), correctSong);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/songs.csv", numLinesToSkip = 1)
    void songCheckerFromFile(int index, String artist, String title, int length) {
        try {
            Song expectedSong = new Song(artist, title, length);
            Optional<Song> song = Song.Persistence.read(index);

            assertTrue(song.isPresent(), "Song should be present in the database");
            assertEquals(expectedSong, song.get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    static void disconnectDB() {
        DatabaseConnection.disconnect("songs");
    }
}
