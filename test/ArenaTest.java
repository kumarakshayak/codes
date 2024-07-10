package test;

import arenafight.Arena;
import arenafight.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArenaTest {

    @Test
    public void testStartBattle() {
        Player player1 = new Player("Player1", 50, 10, 10);
        Player player2 = new Player("Player2", 30, 5, 5);

        Arena arena = new Arena(player1, player2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        arena.startBattle();

        String output = outContent.toString();
        assertTrue(output.contains("Player1 wins!") || output.contains("Player2 wins!"));
    }

    @Test
    public void testRollDice() {
        Player player1 = new Player("Player1", 50, 10, 10);
        Player player2 = new Player("Player2", 50, 10, 10);

        Arena arena = new Arena(player1, player2);

        for (int i = 0; i < 100; i++) {
            int roll = arena.rollDice();
            assertTrue(roll >= 1 && roll <= 6);
        }
    }

    @Test
    public void testAttack() {
        Player player1 = new Player("Player1", 50, 10, 10);
        Player player2 = new Player("Player2", 50, 10, 10);

        Arena arena = new Arena(player1, player2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        arena.attack(player1, player2);

        String output = outContent.toString();
        assertTrue(output.contains("Player1 attacks with"));
        assertTrue(output.contains("Player2 defends with"));
        assertTrue(output.contains("Player2 health is now"));
    }
}
