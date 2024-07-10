import java.util.Random;

public class Arena {
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
