package Gamemode;

import Characters.Character;
import java.util.*;

public class BattleSystem {
    private Scanner scanner;

    public BattleSystem(Scanner scanner) {
        this.scanner = scanner;
    }

    // For PVE (Player vs AI) - Singleplayer
    public boolean startPVEBattle(Character player, Character enemy) {
        System.out.println("\n[ BATTLE STARTED ]");
        System.out.println(player.getName() + " VS " + enemy.getName());
        System.out.println("Press Enter to fight!");
        scanner.nextLine();

        while (player.isAlive() && enemy.isAlive()) {
            // Display both HP bars
            displayHP(player, enemy);

            // Player turn
            playerTurn(player, enemy);

            if (!enemy.isAlive()) {
                System.out.println("\n*** " + enemy.getName() + " has been defeated! ***");
                break;
            }

            // Enemy turn (AI)
            System.out.println("\n[ " + enemy.getName() + "'s turn ]");
            aiTurn(enemy, player);

            // Regenerate stamina at end of round with random amounts
            if (player.isAlive()) {
                System.out.println("\n[Stamina Regeneration]");
                player.regenStamina();
            }
            if (enemy.isAlive()) {
                enemy.regenStamina();
            }

            System.out.println("\nPress Enter to continue to next round...");
            scanner.nextLine();
        }

        return player.isAlive();
    }

    // For PVP (Player vs Player) - Multiplayer
    public Character startPVPBattle(Character player1, Character player2) {
        System.out.println("\n[ PLAYER VS PLAYER BATTLE ]");
        System.out.println(player1.getName() + " VS " + player2.getName());
        System.out.println("Press Enter to start!");
        scanner.nextLine();

        boolean player1Turn = true;

        while (player1.isAlive() && player2.isAlive()) {
            // Display both HP bars
            displayHP(player1, player2);

            if (player1Turn) {
                System.out.println("\n======================================");
                System.out.println("PLAYER 1'S TURN (" + player1.getName() + ")");
                System.out.println("======================================");
                playerTurn(player1, player2);
            } else {
                System.out.println("\n======================================");
                System.out.println("PLAYER 2'S TURN (" + player2.getName() + ")");
                System.out.println("======================================");
                playerTurn(player2, player1);
            }

            player1Turn = !player1Turn;

            // Regenerate stamina for both with random amounts
            if (player1.isAlive()) {
                System.out.println("\n[Stamina Regeneration]");
                player1.regenStamina();
            }
            if (player2.isAlive()) {
                player2.regenStamina();
            }

            System.out.println("\nPress Enter to continue to next round...");
            scanner.nextLine();
        }

        // Return the winner
        if (player1.isAlive()) {
            System.out.println("\n[ PLAYER 1 WINS! ]");
            System.out.println(player1.getName() + " defeated " + player2.getName() + "!");
            return player1;
        } else {
            System.out.println("\n[ PLAYER 2 WINS! ]");
            System.out.println(player2.getName() + " defeated " + player1.getName() + "!");
            return player2;
        }
    }

    // Player turn logic (for both PVE and PVP)
    private void playerTurn(Character attacker, Character defender) {
        // Display attacker stats
        System.out.println("\n" + attacker.getName() + " - HP: " + attacker.getHp() + "/" + attacker.getMaxHp());
        System.out.println("Stamina: " + attacker.getStamina() + "/" + attacker.getMaxStamina());

        // Display available actions with custom stamina costs
        System.out.println("\nAvailable Actions:");
        System.out.println("1. " + attacker.getBasicAttackName() + " (Cost: " + attacker.getBasicAttackStaminaCost() + " Stamina)");
        System.out.println("2. " + attacker.getSpecialSkillName() + " (Cost: " + attacker.getSpecialSkillStaminaCost() + " Stamina)");
        System.out.println("3. " + attacker.getUltimateSkillName() + " (Cost: " + attacker.getUltimateSkillStaminaCost() + " Stamina)");
        System.out.print("\nChoose action: ");

        int action = getValidAction();

        switch(action) {
            case 1:
                if (attacker.spendStamina(attacker.getBasicAttackStaminaCost())) {
                    System.out.println("\n" + attacker.getName() + " uses " + attacker.getBasicAttackName() + "!");
                    attacker.basicAttack(defender);
                } else {
                    System.out.println("\n[WARNING] Not enough stamina! Turn skipped!");
                }
                break;
            case 2:
                if (attacker.spendStamina(attacker.getSpecialSkillStaminaCost())) {
                    System.out.println("\n" + attacker.getName() + " uses " + attacker.getSpecialSkillName() + "!");
                    attacker.specialSkill(defender);
                } else {
                    System.out.println("\n[WARNING] Not enough stamina! Need " + attacker.getSpecialSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");
                    attacker.basicAttack(defender);
                }
                break;
            case 3:
                if (attacker.spendStamina(attacker.getUltimateSkillStaminaCost())) {
                    System.out.println("\n" + attacker.getName() + " uses " + attacker.getUltimateSkillName() + "!");
                    attacker.ultimateSkill(defender);
                } else {
                    System.out.println("\n[WARNING] Not enough stamina! Need " + attacker.getUltimateSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");
                    attacker.basicAttack(defender);
                }
                break;
        }

        System.out.println("\n" + defender.getName() + " HP: " + defender.getHp() + "/" + defender.getMaxHp());
    }

    // AI turn logic (for PVE)
    private void aiTurn(Character ai, Character player) {
        int action = decideAIAction(ai);

        switch(action) {
            case 1:
                if (ai.spendStamina(ai.getBasicAttackStaminaCost())) {
                    System.out.println(ai.getName() + " uses " + ai.getBasicAttackName() + "!");
                    ai.basicAttack(player);
                } else {
                    System.out.println(ai.getName() + " has no stamina and cannot attack!");
                }
                break;
            case 2:
                if (ai.spendStamina(ai.getSpecialSkillStaminaCost())) {
                    System.out.println(ai.getName() + " uses " + ai.getSpecialSkillName() + "!");
                    ai.specialSkill(player);
                } else {
                    System.out.println(ai.getName() + " uses " + ai.getBasicAttackName() + "!");
                    ai.basicAttack(player);
                }
                break;
            case 3:
                if (ai.spendStamina(ai.getUltimateSkillStaminaCost())) {
                    System.out.println(ai.getName() + " uses " + ai.getUltimateSkillName() + "!");
                    ai.ultimateSkill(player);
                } else {
                    System.out.println(ai.getName() + " uses " + ai.getBasicAttackName() + "!");
                    ai.basicAttack(player);
                }
                break;
        }

        System.out.println("\n" + player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
    }

    // AI decision-making logic (considering custom stamina costs)
    private int decideAIAction(Character ai) {
        int aiHPPercentage = (ai.getHp() * 100) / ai.getMaxHp();
        int stamina = ai.getStamina();
        int ultimateCost = ai.getUltimateSkillStaminaCost();
        int specialCost = ai.getSpecialSkillStaminaCost();

        // If low on HP and has enough stamina for ultimate, use it
        if (aiHPPercentage < 30 && stamina >= ultimateCost && Math.random() < 0.5) {
            return 3;
        }

        // If have enough stamina for ultimate, 30% chance
        if (stamina >= ultimateCost && Math.random() < 0.3) {
            return 3;
        }

        // If have enough stamina for special, 40% chance
        if (stamina >= specialCost && Math.random() < 0.4) {
            return 2;
        }

        // Default to basic attack
        return 1;
    }

    // Display HP and Stamina bars for both characters
    private void displayHP(Character c1, Character c2) {
        System.out.println("\n======================================");
        System.out.printf("%-20s %20s%n", c1.getName(), c2.getName());
        System.out.printf("HP: %-3d/%-3d              HP: %-3d/%-3d%n",
                c1.getHp(), c1.getMaxHp(),
                c2.getHp(), c2.getMaxHp());
        System.out.printf("STA: %-3d/%-3d             STA: %-3d/%-3d%n",
                c1.getStamina(), c1.getMaxStamina(),
                c2.getStamina(), c2.getMaxStamina());

        // Visual HP bars
        String hpBar1 = createBar(c1.getHp(), c1.getMaxHp(), '#', '.');
        String hpBar2 = createBar(c2.getHp(), c2.getMaxHp(), '#', '.');

        // Visual Stamina bars
        String staBar1 = createBar(c1.getStamina(), c1.getMaxStamina(), '=', '-');
        String staBar2 = createBar(c2.getStamina(), c2.getMaxStamina(), '=', '-');

        System.out.printf("HP:  %-20s HP:  %20s%n", hpBar1, hpBar2);
        System.out.printf("STA: %-20s STA: %20s%n", staBar1, staBar2);
        System.out.println("======================================");
    }

    // Create visual bar
    private String createBar(int current, int max, char filledChar, char emptyChar) {
        int barLength = 20;
        int filledLength = (current * barLength) / max;

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append(filledChar);
            } else {
                bar.append(emptyChar);
            }
        }
        bar.append("]");

        return bar.toString();
    }

    // Validate player action input
    private int getValidAction() {
        while (true) {
            try {
                int action = scanner.nextInt();
                scanner.nextLine();
                if (action >= 1 && action <= 3) {
                    return action;
                } else {
                    System.out.print("Invalid choice! Please enter 1, 2, or 3: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid number (1-3): ");
                scanner.nextLine();
            }
        }
    }

    // Display battle intro
    public void displayBattleIntro(Character player, Character enemy) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n===========================================");
        System.out.println("              BATTLE ARENA");
        System.out.println("===========================================");
        System.out.printf("%-20s VS %-20s%n", player.getName(), enemy.getName());

        // Show character types
        System.out.printf("%-20s   %-20s%n",
                "[" + player.getType() + "]",
                "[" + enemy.getType() + "]");

        System.out.println("===========================================");

        // Show attack names and stamina costs
        System.out.println("\n" + player.getName() + "'s Arsenal:");
        System.out.println("  - " + player.getBasicAttackName() + " (Cost: " + player.getBasicAttackStaminaCost() + " Stamina)");
        System.out.println("  - " + player.getSpecialSkillName() + " (Cost: " + player.getSpecialSkillStaminaCost() + " Stamina)");
        System.out.println("  - " + player.getUltimateSkillName() + " (Cost: " + player.getUltimateSkillStaminaCost() + " Stamina)");
        System.out.println("  - Stamina Regen: " + player.getStaminaRegenMin() + "-" + player.getStaminaRegenMax() + " per round");

        System.out.println("\n" + enemy.getName() + "'s Arsenal:");
        System.out.println("  - " + enemy.getBasicAttackName() + " (Cost: " + enemy.getBasicAttackStaminaCost() + " Stamina)");
        System.out.println("  - " + enemy.getSpecialSkillName() + " (Cost: " + enemy.getSpecialSkillStaminaCost() + " Stamina)");
        System.out.println("  - " + enemy.getUltimateSkillName() + " (Cost: " + enemy.getUltimateSkillStaminaCost() + " Stamina)");
        System.out.println("  - Stamina Regen: " + enemy.getStaminaRegenMin() + "-" + enemy.getStaminaRegenMax() + " per round");

        System.out.println("\nPress Enter to start the battle!");
        scanner.nextLine();
    }

    // Display battle result
    public void displayBattleResult(boolean won, Character player, Character enemy) {
        System.out.println("\n===========================================");
        if (won) {
            System.out.println("              VICTORY!");
            System.out.println("    " + player.getName() + " defeated " + enemy.getName() + "!");
            System.out.println("    " + player.getName() + " is victorious!");
        } else {
            System.out.println("              DEFEAT!");
            System.out.println("    " + enemy.getName() + " defeated " + player.getName() + "!");
            System.out.println("    Better luck next time!");
        }
        System.out.println("===========================================");
    }

    // Quick battle mode (no delays, faster gameplay)
    public boolean startQuickBattle(Character player, Character enemy) {
        System.out.println("\n[ QUICK BATTLE ]");
        System.out.println(player.getName() + " VS " + enemy.getName());

        int round = 1;
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n--- Round " + round + " ---");

            // Player attacks
            player.basicAttack(enemy);
            if (!enemy.isAlive()) break;

            // Enemy attacks
            enemy.basicAttack(player);

            // Regen stamina
            player.regenStamina();
            enemy.regenStamina();

            round++;
        }

        return player.isAlive();
    }
}