package Gamemode;

import Characters.Character;
import java.util.*;

public class Singleplayer {
    private Scanner scanner;
    private BattleSystem battleSystem;

    public Singleplayer(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public void start() {
        System.out.println();
        System.out.println();
        System.out.println("===========================================");
        System.out.println("            SINGLEPLAYER MODE");
        System.out.println("===========================================");

        // Choose your side
        Character player = chooseCharacter();

        // Generate random enemy (opposite side)
        Character enemy = generateRandomEnemy(player);

        // Display battle intro
        battleSystem.displayBattleIntro(player, enemy);

        // Start the battle
        boolean won = battleSystem.startPVEBattle(player, enemy);

        // Display result
        battleSystem.displayBattleResult(won, player, enemy);

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    private Character chooseCharacter() {
        System.out.println("\nChoose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if (side == 1) {
            ArrayList<Character> heroes = Character.getAllHeroes();
            return selectCharacter(heroes, "Hero");
        } else if (side == 2) {
            ArrayList<Character> villains = Character.getAllVillains();
            return selectCharacter(villains, "Villain");
        } else {
            System.out.println("Invalid choice! Defaulting to Hero.");
            return Character.getAllHeroes().get(0);
        }
    }

    private Character selectCharacter(ArrayList<Character> characters, String type) {
        System.out.println("\nChoose your " + type + ":");
        System.out.println("--------------------------------------");
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i + 1), c.getName(), c.getMaxHp(), c.getAttack(), c.getMaxStamina());
            System.out.printf("   Skills: %s, %s, %s%n",
                    c.getBasicAttackName(),
                    c.getSpecialSkillName(),
                    c.getUltimateSkillName());
        }

        System.out.print("\nChoice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice! Defaulting to first character.");
            return characters.get(0);
        }

        // FIXED: Changed from getFirst() to get()
        Character selected = characters.get(choice - 1);
        System.out.println("\n[You chose: " + selected.getName() + "]");
        return selected;
    }

    private Character generateRandomEnemy(Character player) {
        ArrayList<Character> enemies;

        // If player is hero, enemy is villain, and vice versa
        if (player.getType().equals("Hero")) {
            enemies = Character.getAllVillains();
            System.out.println("\n[As a Hero, you will fight a Villain!]");
        } else {
            enemies = Character.getAllHeroes();
            System.out.println("\n[As a Villain, you will fight a Hero!]");
        }

        Random rand = new Random();
        Character enemy = enemies.get(rand.nextInt(enemies.size()));
        System.out.println("[Your enemy is: " + enemy.getName() + "]");

        return enemy;
    }
}