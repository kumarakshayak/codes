public class main {
    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);

        Arena arena = new Arena(playerA, playerB);
        arena.startBattle();
    }
}
