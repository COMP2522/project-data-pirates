import org.example.music.MusicManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicManagerTest {

    private MusicManager musicManager;

    @BeforeEach
    public void setUp() {
        musicManager = MusicManager.getInstance();
    }

    @Test
    public void testPlayBackgroundMusic() {
        musicManager.play(0); // Play music for worldID 0
        assertTrue(musicManager.isPlaying(), "Background music should be playing");
    }

    @Test
    public void testStopMusic() {
        musicManager.play(0); // Play music for worldID 0
        musicManager.stop();
        assertFalse(musicManager.isPlaying(), "Music should be stopped");
    }

}