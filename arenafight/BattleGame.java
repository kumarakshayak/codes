import java.util.Random;
import java.util.*;

class Player {
    private int health;
    private int strength;
    private int attack;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public void reduceHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}

class Arena {
    private Player player1;
    private Player player2;
    private Random dice;

    public Arena(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.dice = new Random();
    }

    private int rollDice() {
        return dice.nextInt(6) + 1;
    }

    private void attack(Player attacker, Player defender) {
        int attackRoll = rollDice();
        int defendRoll = rollDice();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defense = defender.getStrength() * defendRoll;

        int damageDealt = attackDamage - defense;
        if (damageDealt > 0) {
            defender.reduceHealth(damageDealt);
        }

        System.out.printf("%s attacks with %d damage. %s defends with %d. %s health is now %d.\n",
                attacker.getClass().getSimpleName(), attackDamage, defender.getClass().getSimpleName(), defense, defender.getClass().getSimpleName(), defender.getHealth());
    }

    public void startBattle() {
        while (player1.isAlive() && player2.isAlive()) {
            if (player1.getHealth() <= player2.getHealth()) {
                attack(player1, player2);
                if (!player2.isAlive()) {
                    System.out.println("Player 1 wins!");
                    break;
                }
                attack(player2, player1);
                if (!player1.isAlive()) {
                    System.out.println("Player 2 wins!");
                }
            } else {
                attack(player2, player1);
                if (!player1.isAlive()) {
                    System.out.println("Player 2 wins!");
                    break;
                }
                attack(player1, player2);
                if (!player2.isAlive()) {
                    System.out.println("Player 1 wins!");
                }
            }
        }
    }
}

public class BattleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the PlayerA Details in this format: Health(int) Strength(int)  Attact(int) ");
        int health_of_A=scanner.nextInt();
        int strength_of_A=scanner.nextInt();
        int attack_of_A=scanner.nextInt();
        Player playerA = new Player(health_of_A, strength_of_A, attack_of_A);
        System.out.println("Enter the PlayerB Details in this format: Health(int) Strength(int)  Attact(int) ");

        int health_of_B=scanner.nextInt();
        int strength_of_B=scanner.nextInt();
        int attack_of_B=scanner.nextInt();
        Player playerB = new Player(health_of_B, strength_of_B, attack_of_B);
        Arena arena = new Arena(playerA, playerB);
        arena.startBattle();
    }
}
