import java.util.Random;
 
public class BattleGame {
 
    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);
 
        BattleGame game = new BattleGame();
        game.startGame(playerA, playerB);
    }
 
    public void startGame(Player playerA, Player playerB) {
        Player attacker, defender;
        
        while (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
            if (playerA.getHealth() < playerB.getHealth()) {
                attacker = playerA;
                defender = playerB;
            } else {
                attacker = playerB;
                defender = playerA;
            }
 
            simulateTurn(attacker, defender);
 
            if (defender.getHealth() <= 0) {
                System.out.println(attacker.getName() + " wins!");
                break;
            }
        }
    }
 
    private void simulateTurn(Player attacker, Player defender) {
        int attackRoll = rollDice();
        int defenseRoll = rollDice();
 
        int attackDamage = attacker.getAttack() * attackRoll;
        int defenseStrength = defender.getStrength() * defenseRoll;
 
        int damageInflicted = attackDamage - defenseStrength;
        if (damageInflicted > 0) {
            defender.reduceHealth(damageInflicted);
        }
 
        System.out.printf("%s attacks with a roll of %d: Attack damage = %d\n", attacker.getName(), attackRoll, attackDamage);
        System.out.printf("%s defends with a roll of %d: Defense strength = %d\n", defender.getName(), defenseRoll, defenseStrength);
        System.out.printf("%s's health is now %d\n", defender.getName(), defender.getHealth());
        System.out.println();
    }
 
    private int rollDice() {
        return new Random().nextInt(6) + 1;
    }
 
    static class Player {
        private int health;
        private int strength;
        private int attack;
        private String name;
 
        public Player(int health, int strength, int attack) {
            this.health = health;
            this.strength = strength;
            this.attack = attack;
this.name = "Player " + (attack > strength ? "A" : "B");
        }
 
        public int getHealth() {
            return health;
        }
 
        public void reduceHealth(int damage) {
            this.health -= damage;
        }
 
        public int getStrength() {
            return strength;
        }
 
        public int getAttack() {
            return attack;
        }
 
        public String getName() {
            return name;
        }
    }
}