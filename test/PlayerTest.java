import arenafight.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testReduceHealth() {
        Player player = new Player("TestPlayer", 100, 10, 10);
        player.reduceHealth(30);
        assertEquals(70, player.getHealth());
    }

    @Test
    public void testReduceHealthBelowZero() {
        Player player = new Player("TestPlayer", 50, 10, 10);
        player.reduceHealth(60);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        Player player = new Player("TestPlayer", 50, 10, 10);
        assertTrue(player.isAlive());
        player.reduceHealth(50);
        assertFalse(player.isAlive());
    }

    @Test
    public void testToString() {
        Player player = new Player("TestPlayer", 50, 10, 10);
        assertEquals("TestPlayer(health=50, strength=10, attack=10)", player.toString());
    }
}
