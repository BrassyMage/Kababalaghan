import Characters.Character;
import Characters.heroes.*;
import Characters.villains.*;
import battle.BattleSystem;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ========================================
        // WELCOME SCREEN
        // ========================================
        System.out.println("========================================");
        System.out.println("  FILIPINO MYTHOLOGY: TURN-BASED RPG");
        System.out.println("========================================");
        System.out.println("Heroes vs Villains of Philippine Legend");
        System.out.println("========================================");

        // ========================================
        // MAIN MENU
        // ========================================
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("             MAIN MENU");
            System.out.println("========================================");
            System.out.println("1. Start New Game");
            System.out.println("2. View Heroes");
            System.out.println("3. View Villains");
            System.out.println("4. Exit");
            System.out.println("========================================");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    startNewGame(scanner);
                    break;
                case 2:
                    displayHeroes();
                    break;
                case 3:
                    displayVillains();
                    break;
                case 4:
                    System.out.println("Thanks for playing! Paalam!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }

    // ========================================
    // START NEW GAME
    // ========================================
    public static void startNewGame(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("           START NEW GAME");
        System.out.println("========================================");

        // Choose hero
        System.out.println("Choose your hero:");
        System.out.println("1. Juan Tamad - The Lazy Genius");
        System.out.println("2. Ibong Adarna Jr. - The Melodramatic Bird");
        System.out.println("3. Maria Makiling's Cousin - The Sarcastic Guardian");
        System.out.println("4. Tikoy - The Sticky Rice Warrior");
        System.out.println("5. Lapu-Lapu 2.0 - The Legendary Warrior");
        System.out.println("6. Daragang Magayon's Heir - The Graceful Fighter");
        System.out.println("7. Koboy Kalabaw - The Mystical Cowboy");
        System.out.println("8. Bagani ng Kape - The Heroic Barista");
        System.out.println("========================================");
        System.out.print("Choose your hero (1-8): ");

        int heroChoice = scanner.nextInt();
        scanner.nextLine();

        Character player = createHero(heroChoice);

        if (player == null) {
            System.out.println("Invalid hero choice!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("You chose: " + player.getName() + " - " + player.getTitle());
        System.out.println("========================================");
        player.displayInfo();

        // ========================================
        // BATTLE ARENA - 3 Rounds
        // ========================================
        System.out.println("\n========================================");
        System.out.println("           BATTLE ARENA");
        System.out.println("========================================");
        System.out.println("You will face 3 villains!");
        System.out.println("Press Enter to start...");
        scanner.nextLine();

        int wins = 0;
        int losses = 0;

        // Round 1 - Easy
        Character villain1 = createVillain(1); // Aswang
        if (runBattle(scanner, player, villain1, 1)) {
            wins++;
        } else {
            losses++;
        }

        if (player.isAlive() && losses == 0) {
            // Round 2 - Medium
            player.regenStamina();
            player.heal(30); // Partial heal between rounds
            Character villain2 = createVillain(3); // Kapre
            if (runBattle(scanner, player, villain2, 2)) {
                wins++;
            } else {
                losses++;
            }
        }

        if (player.isAlive() && losses == 0) {
            // Round 3 - Boss
            // You need to add a restoreStats() method to Character.java
            // For now, just heal fully
            player.heal(player.getMaxHp()); // Full heal
            Character villain3 = createVillain(6); // Bakunawa
            if (runBattle(scanner, player, villain3, 3)) {
                wins++;
            } else {
                losses++;
            }
        }

        // ========================================
        // GAME RESULT
        // ========================================
        System.out.println("\n========================================");
        System.out.println("           GAME OVER");
        System.out.println("========================================");
        System.out.println("Hero: " + player.getName());
        System.out.println("Wins: " + wins + " | Losses: " + losses);

        if (wins == 3) {
            System.out.println("\n========================================");
            System.out.println("  CONGRATULATIONS! YOU ARE A LEGEND!");
            System.out.println("  You defeated all villains!");
            System.out.println("========================================");
        } else if (wins == 2) {
            System.out.println("\n========================================");
            System.out.println("  WELL DONE! You fought bravely!");
            System.out.println("========================================");
        } else if (wins == 1) {
            System.out.println("\n========================================");
            System.out.println("  GOOD TRY! Practice makes perfect.");
            System.out.println("========================================");
        } else {
            System.out.println("\n========================================");
            System.out.println("  BETTER LUCK NEXT TIME!");
            System.out.println("========================================");
        }

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    // ========================================
    // RUN BATTLE
    // ========================================
    public static boolean runBattle(Scanner scanner, Character player, Character villain, int round) {
        System.out.println("\n========================================");
        System.out.println("           ROUND " + round);
        System.out.println("========================================");
        System.out.println("VS " + villain.getName() + " - " + villain.getTitle());
        System.out.println("========================================");

        BattleSystem battle = new BattleSystem(player, villain);

        while (player.isAlive() && villain.isAlive()) {
            // Player turn
            System.out.println("\n----------------------------------------");
            System.out.println(player.getName() + "'s Turn");
            System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp() +
                    " | Stamina: " + player.getStamina() + "/100");
            System.out.println("----------------------------------------");

            player.displaySkills();
            System.out.println("5. Defend");
            System.out.println("----------------------------------------");
            System.out.print("Choose action: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 1:
                    player.basicAttack(villain);
                    break;
                case 2:
                    player.specialSkill(villain);
                    break;
                case 3:
                    player.ultimateSkill(villain);
                    break;
                case 4:
                    player.signatureMove(villain);
                    break;
                case 5:
                    player.defend();
                    break;
                default:
                    System.out.println("Invalid action! Lost turn.");
            }

            if (!villain.isAlive()) {
                System.out.println("\n" + villain.getName() + " is defeated!");
                break;
            }

            // Enemy turn
            if (villain.isAlive()) {
                System.out.println("\n----------------------------------------");
                System.out.println(villain.getName() + "'s Turn");
                System.out.println("----------------------------------------");

                // Simple AI for villain
                int enemyAction = (int)(Math.random() * 4) + 1;

                switch(enemyAction) {
                    case 1:
                        villain.basicAttack(player);
                        break;
                    case 2:
                        villain.specialSkill(player);
                        break;
                    case 3:
                        villain.ultimateSkill(player);
                        break;
                    case 4:
                        villain.signatureMove(player);
                        break;
                }
            }

            // Regen stamina for both after turn
            if (player.isAlive()) player.regenStamina();
            if (villain.isAlive()) villain.regenStamina();
        }

        return player.isAlive();
    }

    // ========================================
    // CREATE HERO
    // ========================================
    public static Character createHero(int choice) {
        switch(choice) {
            case 1: return new JuanTamad();
            case 2: return new IbongAdarnaJr();
//            case 3: return new MariaMakilingCousin();
//            case 4: return new Tikoy();
//            case 5: return new LapuLapu2();
//            case 6: return new DaragangMagayonHeir();
//            case 7: return new KoboyKalabaw();
//            case 8: return new BaganiNgKape();
            default: return null;
        }
    }

    // ========================================
    // CREATE VILLAIN
    // ========================================
    public static Character createVillain(int choice) {
        switch(choice) {
            case 1: return new Aswang();
//            case 2: return new Manananggal();
//            case 3: return new Kapre();
//            case 4: return new Tiyanak();
//            case 5: return new Tikbalang();
//            case 6: return new Bakunawa();
//            case 7: return new DiwataDramaQueen();
//            case 8: return new Dwende();
            default: return new Aswang(); // Default
        }
    }

    // ========================================
    // DISPLAY HEROES
    // ========================================
    public static void displayHeroes() {
        System.out.println("\n========================================");
        System.out.println("              HEROES");
        System.out.println("========================================");

        Character[] heroes = {
                new JuanTamad(),
                new IbongAdarnaJr(),
//                new MariaMakilingCousin(),
//                new Tikoy(),
//                new LapuLapu2(),
//                new DaragangMagayonHeir(),
//                new KoboyKalabaw(),
//                new BaganiNgKape()
        };

        for (int i = 0; i < heroes.length; i++) {
            System.out.println("\n" + (i+1) + ". " + heroes[i].getName());
            System.out.println("   Title: " + heroes[i].getTitle());
            System.out.println("   Origin: " + heroes[i].getOrigin());        // FIXED: using getter
            System.out.println("   Personality: " + heroes[i].getPersonality()); // FIXED: using getter
            System.out.println("   HP: " + heroes[i].getMaxHp() + " | Attack: " + heroes[i].getAttack());
            System.out.println("   ----------------------");
        }
        System.out.println("========================================");
    }

    // ========================================
    // DISPLAY VILLAINS
    // ========================================
    public static void displayVillains() {
        System.out.println("\n========================================");
        System.out.println("             VILLAINS");
        System.out.println("========================================");

        Character[] villains = {
                new Aswang(),
//                new Manananggal(),
//                new Kapre(),
//                new Tiyanak(),
//                new Tikbalang(),
//                new Bakunawa(),
//                new DiwataDramaQueen(),
//                new Dwende()
        };

        for (int i = 0; i < villains.length; i++) {
            System.out.println("\n" + (i+1) + ". " + villains[i].getName());
            System.out.println("   Title: " + villains[i].getTitle());
            System.out.println("   Origin: " + villains[i].getOrigin());        // FIXED: using getter
            System.out.println("   Personality: " + villains[i].getPersonality()); // FIXED: using getter
            System.out.println("   HP: " + villains[i].getMaxHp() + " | Attack: " + villains[i].getAttack());
            System.out.println("   ----------------------");
        }
        System.out.println("========================================");
    }
}