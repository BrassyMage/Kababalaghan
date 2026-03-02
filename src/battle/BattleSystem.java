package battle;

import Characters.Character;

public class BattleSystem {
    private Character player;
    private Character enemy;

    // Constructor with two parameters
    public BattleSystem(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        System.out.println("\n========================================");
        System.out.println("Battle begins: " + player.getName() + " vs " + enemy.getName());
        System.out.println("========================================");
    }

    public void startBattle() {
        // This method can be empty or contain battle start logic
    }

    public boolean isBattleOver() {
        return !player.isAlive() || !enemy.isAlive();
    }

    public Character getWinner() {
        if (!player.isAlive()) return enemy;
        if (!enemy.isAlive()) return player;
        return null;
    }
}