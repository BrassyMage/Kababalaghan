// Gamemode/Multiplayer.java
package Gamemode;

import Characters.Character;
import java.util.*;

public class Multiplayer {
    private Scanner scanner;
    private BattleSystem battleSystem;
    private int player1Wins = 0;
    private int player2Wins = 0;

    public Multiplayer(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public void start() {
        System.out.println();
        System.out.println();
        System.out.println("===========================================");
        System.out.println("            MULTIPLAYER MODE");
        System.out.println("===========================================");

        boolean playing = true;

        while (playing) {
            // Display current score
            displayScore();

            // Player 1 chooses character
            System.out.println("\n[PLAYER 1'S TURN]");
            Character player1 = chooseCharacter("Player 1");

            // Player 2 chooses character
            System.out.println("\n[PLAYER 2'S TURN]");
            Character player2 = chooseCharacter("Player 2");

            // Display battle intro
            System.out.println("\n===========================================");
            System.out.println("              FINAL BATTLE");
            System.out.println("===========================================");
            System.out.println(player1.getName() + " VS " + player2.getName());
            System.out.println("Press Enter to start the battle!");
            scanner.nextLine();

            // Start PVP battle
            Character winner = battleSystem.startPVPBattle(player1, player2);

            // Update score
            if (winner == player1) {
                player1Wins++;
                System.out.println("\n[PLAYER 1 WINS THE MATCH!]");
            } else {
                player2Wins++;
                System.out.println("\n[PLAYER 2 WINS THE MATCH!]");
            }

            // Reset characters for next match
            player1.resetAll();
            player2.resetAll();

            // Ask to play again
            playing = playAgain();
        }

        displayFinalScore();
    }

    private Character chooseCharacter(String playerName) {
        System.out.println("\n" + playerName + ", choose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if (side == 1) {
            ArrayList<Character> heroes = Character.getAllHeroes();
            return selectCharacter(heroes, playerName);
        } else if (side == 2) {
            ArrayList<Character> villains = Character.getAllVillains();
            return selectCharacter(villains, playerName);
        } else {
            System.out.println("Invalid choice! Defaulting to Hero.");
            return Character.getAllHeroes().get(0);
        }
    }

    private Character selectCharacter(ArrayList<Character> characters, String playerName) {
        System.out.println("\n" + playerName + ", choose your character:");
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

        Character selected = characters.get(choice - 1);
        System.out.println("\n[" + playerName + " chose: " + selected.getName() + "]");
        return selected;
    }

    private void displayScore() {
        System.out.println("\n======================================");
        System.out.printf("SCORE:  Player 1 [%d]  -  [%d] Player 2%n", player1Wins, player2Wins);
        System.out.println("======================================");
    }

    private boolean playAgain() {
        System.out.println("\nPlay another match?");
        System.out.println("1. Yes");
        System.out.println("2. No (Return to menu)");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice == 1;
    }

    private void displayFinalScore() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n===========================================");
        System.out.println("              FINAL SCORE");
        System.out.println("===========================================");
        System.out.println("Player 1: " + player1Wins + " wins");
        System.out.println("Player 2: " + player2Wins + " wins");

        if (player1Wins > player2Wins) {
            System.out.println("\n[PLAYER 1 IS THE CHAMPION!]");
        } else if (player2Wins > player1Wins) {
            System.out.println("\n[PLAYER 2 IS THE CHAMPION!]");
        } else {
            System.out.println("\n[IT'S A TIE!]");
        }
        System.out.println("===========================================");
    }
}