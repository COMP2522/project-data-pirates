import org.example.Main.Window;
import org.example.spriteClasses.GifManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class GifManagerTest {
    private Window scene;
    private GifManager gifManager;

    private String fileNames;

    private int numberOfSprites;

    @BeforeEach
    void setup() {
        scene = new Window();
        fileNames = "test\\frame";
        numberOfSprites = 5;
        gifManager = new GifManager(fileNames, numberOfSprites, scene);
    }

    @Test
    void testGifManagerCreation() {
        assertNotNull(gifManager);
    }
}
