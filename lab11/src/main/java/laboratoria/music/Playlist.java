package laboratoria.music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

    public Song atSecond(int seconds) {
        int totalSeconds = 0;
        for (Song song : this) {
            totalSeconds += song.length();
            if (totalSeconds > seconds) {
                return song;
            }
        }
        throw new IndexOutOfBoundsException("Provided second exceeds total length of the playlist");
    }
}
