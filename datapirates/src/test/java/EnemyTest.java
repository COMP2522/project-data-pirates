import org.example.Main.Window;
import org.example.spriteClasses.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnemyTest {
    private Window scene;
    private Enemy enemy;
    private int damage;
    private float size;
    private float speed;
    private String level;
    private int numSprites;

    @BeforeEach
    void setUp() {
        scene = new Window();
        damage = 10;
        size = 50;
        speed = 2;
        level = "LVL_2";
        numSprites = 4;
        enemy = new Enemy(damage, true, size, speed, scene, level, numSprites);
    }

    @Test
    void testEnemyCreation() {
        assertNotNull(enemy, "Enemy should not be null after instantiation");
    }
}
