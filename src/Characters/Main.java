package Characters;

import Characters.heroes.*;
import Characters.villains.*;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random(); {

        System.out.println("======================");
        System.out.println("    KABABALAGHAN");
        System.out.println("======================");

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Start Game");
            System.out.println("2. View Characters");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    startGame(scanner);
                    break;
                case 2:
                    viewCharacters();
                    break;
                case 3:
                    System.out.println("Thanks for playing!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    public static void startGame(Scanner scanner) {
        System.out.println("\n--- CHOOSE HERO ---");

        // Get all heroes dynamically
        ArrayList<Character> heroes = getAllHeroes();
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println((i+1) + ". " + heroes.get(i).getName());
        }
        System.out.print("Choose hero: ");

        int heroChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (heroChoice < 0 || heroChoice >= heroes.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Character player = heroes.get(heroChoice);
        System.out.println("\nYou chose: " + player.getName());

        // RANDOM VILLAIN SELECTION
        ArrayList<Character> villains = getAllVillains();
        Random rand = new Random();
        int randomIndex = rand.nextInt(villains.size());
        Character villain = villains.get(randomIndex);

        System.out.println("\n--- RANDOM ENCOUNTER ---");
        System.out.println("A " + villain.getName() + " appears!");
        System.out.println("\n" + player.getName() + " vs " + villain.getName());
        System.out.println("Press Enter to fight!");
        scanner.nextLine();

        boolean won = fight(scanner, player, villain);

        System.out.println("\n=== GAME OVER ===");
        if (won) {
            System.out.println("YOU WIN! " + player.getName() + " defeated " + villain.getName() + "!");
        } else {
            System.out.println("YOU LOSE! " + villain.getName() + " won!");
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static boolean fight(Scanner scanner, Character player, Character villain) {

        while (player.isAlive() && villain.isAlive()) {
            // Player turn
            System.out.println("\n" + player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("Stamina: " + player.getStamina());
            System.out.println("1. Basic Attack");
            System.out.println("2. Special Skill");
            System.out.println("3. Ultimate");
            System.out.print("Action: ");

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
                default:
                    System.out.println("Invalid action!");
            }

           /* if (!villain.isAlive()) {
                return true;
            }*/

            // Villain turn
            if (villain.isAlive()) {
                int enemyAction = (int)(Math.random() * 3) + 1;
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
                }
            }

            // Regen stamina
            if (player.isAlive()) player.regenStamina();
            if (villain.isAlive()) villain.regenStamina();
        }

        return player.isAlive();
    }

    public static void viewCharacters() {
        System.out.println("\n--- HEROES ---");
        ArrayList<Character> heroes = getAllHeroes();
        for (Character hero : heroes) {
            System.out.println("- " + hero.getName() + " (HP: " + hero.getMaxHp() + ", ATK: " + hero.getAttack() + ")");
        }

        System.out.println("\n--- VILLAINS ---");
        ArrayList<Character> villains = getAllVillains();
        for (Character villain : villains) {
            System.out.println("- " + villain.getName() + " (HP: " + villain.getMaxHp() + ", ATK: " + villain.getAttack() + ")");
        }
    }

    public static ArrayList<Character> getAllHeroes() {
        ArrayList<Character> heroes = new ArrayList<>();
        int randh = random.nextInt(heroes.size());
        heroes.add(new JuanTamad());
        heroes.add(new IbongAdarnaJr());
        heroes.add(new LapuLapu());
        // Add more heroes here in the future
        return heroes;
    }

    public static ArrayList<Character> getAllVillains() {
        ArrayList <Character> villains = new ArrayList<>();
        int randv = random.nextInt(villains.size()) + 1;
        switch (randv) {
            case 1:villains.add(new Aswang());
            case 2:villains.add(new Tiyanak());
            case 3:villains.add(new Manananggal());
            case 4:villains.add(new Bakunawa());

            default:  villains.add(new Aswang());
        }
        // Add more villains here in the future
        return villains;
    }
}