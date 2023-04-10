import org.example.Main.Weapon;
import org.example.Main.Window;
import org.example.spriteClasses.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private Window window;

    @BeforeEach
    public void setUp() {
        window = new Window();
        PVector position = new PVector(0, 0);
        PVector direction = new PVector(0, 0);
        float size = 10;
        float speed = 5;
        Color color = Color.BLUE;
        player = Player.getInstance(position, direction, size, speed, color, window);
    }

    @Test
    public void testMove() {
        player.move();
        PVector expectedDirection = new PVector(5, 5).normalize();
        assertEquals(expectedDirection, player.getDirection(), "Player direction should be updated");
    }

    @Test
    public void testWeapon() {
        // The player object now initializes with a random weapon, so it shouldn't be null initially
        assertNotNull(player.getWeapon(), "Player weapon should not be null initially");

        // Create a new weapon with the required arguments
        String model = "Test Weapon";
        Color bulletColor = Color.RED;
        int maxAmmo = 50;
        int dmg = 20;
        Weapon weapon = new Weapon(model, bulletColor, maxAmmo, dmg);
        player.setWeapon(weapon);
        assertEquals(weapon, player.getWeapon(), "Player weapon should be updated");
    }
}
