import org.example.Main.Window;
import org.example.gui.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class MenuTest {
    private Menu menu;
    private Window scene;

    @BeforeEach
    void setup() {
        menu = new Menu(scene);
        scene = new Window();
    }

    @Test
    void testMenuCreation() {
        assertNotNull(menu);
    }
}
