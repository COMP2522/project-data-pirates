import org.example.Main.Preloader;
import org.example.Main.Window;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreloaderTest {
    private Window scene;

    private Preloader preloader;

    @Before
    public void setup() {
        scene = new Window();
        preloader = new Preloader(scene);
    }

    @Test
    void testIsFinished() {
        assertFalse(preloader.isFinished());

        while (!preloader.isFinished()) {
            // wait for the preloader to finish
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(preloader.isFinished());
    }

    @Test
    void testIsNotFinished() {
        assertFalse(preloader.isFinished());
    }
}
