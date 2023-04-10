import org.example.Main.Window;
import org.example.spriteClasses.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    private Enemy enemy;

    @BeforeEach
    public void setUp() {
        Window window = new Window();
        PVector position = new PVector(0, 0);
        PVector direction = new PVector(0, 0);
        float size = 10;
        float speed = 5;
        Color color = Color.RED;
        String level = "level1";
        enemy = new Enemy(position, direction, size, speed, color, window, level);
    }

    @Test
    public void testEnemyCreation() {
        assertNotNull(enemy, "Enemy should be created");
    }

    @Test
    public void testEnemyUpdate() {
        PVector initialPosition = enemy.getPosition().copy();
        enemy.update();
        PVector expectedPosition = initialPosition.add(enemy.getDirection().copy().mult(enemy.getSpeed()));
        assertEquals(expectedPosition, enemy.getPosition(), "Enemy position should be updated");
    }

    @Test
    public void testEnemyStats() {
        assertNotNull(enemy.getEnemyStat(), "Enemy stats should be set");
    }
}
