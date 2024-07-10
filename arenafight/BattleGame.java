import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return name + "(health=" + health + ", strength=" + strength + ", attack=" + attack + ")";
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
        System.out.printf("%s get %d on rolling attacking dice\n",attacker.getName(),attackRoll);
        System.out.printf("%s get %d on rolling defending dice\n",defender.getName(),defendRoll);
        int attackDamage = attacker.getAttack() * attackRoll;
        int defense = defender.getStrength() * defendRoll;
        
        int damageDealt = attackDamage - defense;
        if (damageDealt > 0) {
            defender.reduceHealth(damageDealt);
        }

        System.out.printf("%s attacks with %d damage. %s defends with %d. %s health is now %d.\n\n",
                attacker.getName(), attackDamage, defender.getName(), defense, defender.getName(), defender.getHealth());
    }

    public void startBattle() {
        while (player1.isAlive() && player2.isAlive()) {
            if (player1.getHealth() <= player2.getHealth()) {
                System.out.println(player1.getName() + "'s turn:");
                attack(player1, player2);
                if (!player2.isAlive()) {
                    System.out.println(player1.getName() + " wins!");
                    break;
                }
                System.out.println(player2.getName() + "'s turn:");
                attack(player2, player1);
                if (!player1.isAlive()) {
                    System.out.println(player2.getName() + " wins!");
                }
            } else {
                System.out.println(player2.getName() + "'s turn:");
                attack(player2, player1);
                if (!player1.isAlive()) {
                    System.out.println(player2.getName() + " wins!");
                    break;
                }
                System.out.println(player1.getName() + "'s turn:");
                attack(player1, player2);
                if (!player2.isAlive()) {
                    System.out.println(player1.getName() + " wins!");
                }
            }
        }
    }
}

public class BattleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Player A's name:");
        String nameA = scanner.next();
        System.out.println("Enter Player A's health, strength, and attack (space-separated integers):");
        int healthA = scanner.nextInt();
        int strengthA = scanner.nextInt();
        int attackA = scanner.nextInt();
        Player playerA = new Player(nameA, healthA, strengthA, attackA);

        System.out.println("Enter Player B's name:");
        String nameB = scanner.next();
        System.out.println("Enter Player B's health, strength, and attack (space-separated integers):");
        int healthB = scanner.nextInt();
        int strengthB = scanner.nextInt();
        int attackB = scanner.nextInt();
        Player playerB = new Player(nameB, healthB, strengthB, attackB);

        Arena arena = new Arena(playerA, playerB);
        arena.startBattle();

        scanner.close();
    }
}
