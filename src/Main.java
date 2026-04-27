import Characters.Character;
import Gamemode.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("              KABABALAGHAN");
        System.out.println("        A Filipino Mythology");
        System.out.println("===========================================");

        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getIntInput("Choose: ");

            switch (choice) {
                case 1:
                    startGameMode();
                    break;
                case 2:
                    Character.showAllCharacters();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\nThanks for playing KABABALAGHAN!");
                    System.out.println("Until next time, brave warrior!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1-3.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n===========================================");
        System.out.println("                 MENU");
        System.out.println("===========================================");
        System.out.println("1. Start Game");
        System.out.println("2. View Characters");
        System.out.println("3. Exit");
        System.out.println("===========================================");
    }

    private static void startGameMode() {
        boolean inGameModeMenu = true;

        while (inGameModeMenu) {
            System.out.println("\n===========================================");
            System.out.println("              GAME MODES");
            System.out.println("===========================================");
            System.out.println("1. Singleplayer (VS Computer)");
            System.out.println("2. Multiplayer (PVP)");
            System.out.println("3. Story Mode (Campaign)");
            System.out.println("0. Back to Menu");
            System.out.println("===========================================");

            int choice = getIntInput("Choose mode: ");

            switch (choice) {
                case 1:
                    Singleplayer singleplayer = new Singleplayer(scanner);
                    singleplayer.start();
                    break;
                case 2:
                    Multiplayer multiplayer = new Multiplayer(scanner);
                    multiplayer.start();
                    break;
                //          case 3:
                //              StoryMode storyMode = new StoryMode(scanner);
                //              storyMode.start();
                //                  break;
                case 0:
                    System.out.println("Returning to main menu...");
                    inGameModeMenu = false; //exits loop
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    //stays in loop
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }
    }
}