package Character;

import Characters.heroes.*;
import Characters.villains.*;

import java.util.*;

public abstract class Character {
    protected String name;
    protected String type;  // "Hero" or "Villain"
    protected int hp;
    protected int maxHp;
    protected int stamina;
    protected int maxStamina;
    protected int attack;
    protected boolean isAlive = true;
    protected Random random = new Random();

    // Attack names
    protected String basicAttackName;
    protected String specialSkillName;
    protected String ultimateSkillName;

    // Stamina costs (editable per character)
    protected int basicAttackStaminaCost = 0;
    protected int specialSkillStaminaCost = 20;
    protected int ultimateSkillStaminaCost = 50;

    // Stamina regeneration settings (editable per character)
    protected int staminaRegenMin = 15;  // Minimum regen amount
    protected int staminaRegenMax = 25;  // Maximum regen amount

    public Character(String name, String type, int maxHp, int attack, int stamina, int maxStamina,
                     String basicAttackName, String specialSkillName, String ultimateSkillName) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.stamina = maxStamina;
        this.maxStamina = maxStamina;
        this.basicAttackName = basicAttackName;
        this.specialSkillName = specialSkillName;
        this.ultimateSkillName = ultimateSkillName;
    }

    public abstract void basicAttack(Character target);
    public abstract void specialSkill(Character target);
    public abstract void ultimateSkill(Character target);

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;

        System.out.println(name + " takes " + damage + " damage! HP: " + hp + "/" + maxHp);

        if (hp <= 0) {
            isAlive = false;
            System.out.println(name + " defeated!");
        }
    }

    public boolean spendStamina(int cost) {
        if (stamina >= cost) {
            stamina -= cost;
            return true;
        } else {
            System.out.println("Not enough stamina! (Need " + cost + ", have " + stamina + ")");
            return false;
        }
    }

    public void regenStamina() {
        // Randomize stamina regeneration between min and max values
        int regenAmount = staminaRegenMin + random.nextInt(staminaRegenMax - staminaRegenMin + 1);
        int oldStamina = stamina;
        stamina = Math.min(maxStamina, stamina + regenAmount);
        int actualRegen = stamina - oldStamina;

        System.out.println(name + " regenerates " + actualRegen + " stamina! (Stamina: " + stamina + "/" + maxStamina + ")");
    }

    // Overloaded method for custom regen range
    public void regenStamina(int min, int max) {
        int regenAmount = min + random.nextInt(max - min + 1);
        int oldStamina = stamina;
        stamina = Math.min(maxStamina, stamina + regenAmount);
        int actualRegen = stamina - oldStamina;

        System.out.println(name + " regenerates " + actualRegen + " stamina! (Stamina: " + stamina + "/" + maxStamina + ")");
    }

    // Set randomized stamina regen range
    public void setStaminaRegenRange(int min, int max) {
        this.staminaRegenMin = min;
        this.staminaRegenMax = max;
    }

    // Set fixed stamina regen (no randomness)
    public void setFixedStaminaRegen(int amount) {
        this.staminaRegenMin = amount;
        this.staminaRegenMax = amount;
    }

    // Get current regen range
    public int getStaminaRegenMin() { return staminaRegenMin; }
    public int getStaminaRegenMax() { return staminaRegenMax; }

    // Stamina management methods
    public void setStamina(int stamina) {
        this.stamina = Math.min(stamina, maxStamina);
        if (this.stamina < 0) this.stamina = 0;
    }

    public void addStamina(int amount) {
        this.stamina = Math.min(maxStamina, this.stamina + amount);
        System.out.println(name + " recovers " + amount + " stamina! Stamina: " + this.stamina + "/" + maxStamina);
    }

    public void reduceStamina(int amount) {
        this.stamina = Math.max(0, this.stamina - amount);
        System.out.println(name + " loses " + amount + " stamina! Stamina: " + this.stamina + "/" + maxStamina);
    }

    // Custom stamina cost setters
    public void setBasicAttackStaminaCost(int cost) {
        this.basicAttackStaminaCost = cost;
    }

    public void setSpecialSkillStaminaCost(int cost) {
        this.specialSkillStaminaCost = cost;
    }

    public void setUltimateSkillStaminaCost(int cost) {
        this.ultimateSkillStaminaCost = cost;
    }

    // Getters for stamina costs
    public int getBasicAttackStaminaCost() { return basicAttackStaminaCost; }
    public int getSpecialSkillStaminaCost() { return specialSkillStaminaCost; }
    public int getUltimateSkillStaminaCost() { return ultimateSkillStaminaCost; }

    // Getters for attack names
    public String getBasicAttackName() { return basicAttackName; }
    public String getSpecialSkillName() { return specialSkillName; }
    public String getUltimateSkillName() { return ultimateSkillName; }

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMaxStamina() { return maxStamina; }
    public int getStamina() { return stamina; }
    public int getAttack() { return attack; }
    public boolean isAlive() { return isAlive; }

    // Reset methods for new battles
    public void resetHp() {
        this.hp = this.maxHp;
        this.isAlive = true;
    }

    public void resetStamina() {
        this.stamina = this.maxStamina;
    }

    public void resetAll() {
        resetHp();
        resetStamina();
    }

    public static ArrayList<Character> getAllHeroes() {
        ArrayList<Character> heroes = new ArrayList<>();
        heroes.add(new MariaMakiling());
        heroes.add(new Apolaki());
        heroes.add(new Magwayen());
        heroes.add(new Kaptan());
        //to add more

        Collections.shuffle(heroes);
        return heroes;
    }

    public static ArrayList<Character> getAllVillains() {
        ArrayList<Character> villains = new ArrayList<>();
        villains.add(new Aswang());
        villains.add(new Mananananggal());
        villains.add(new Kapre());
        villains.add(new Santelmo());
        //to add more

        Collections.shuffle(villains);
        return villains;
    }

    // ADD THIS METHOD - Display all characters with their skills
    public static void showAllCharacters() {
        System.out.println("\n===========================================");
        System.out.println("              CHARACTERS");
        System.out.println("===========================================");

        System.out.println("\n[ HEROES ]");
        System.out.println("-------------------------------------------");
        ArrayList<Character> heroes = getAllHeroes();
        for (int i = 0; i < heroes.size(); i++) {
            Character hero = heroes.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i+1), hero.getName(), hero.getMaxHp(), hero.getAttack(), hero.getMaxStamina());
            System.out.printf("   Skills: %s, %s, %s%n",
                    hero.getBasicAttackName(),
                    hero.getSpecialSkillName(),
                    hero.getUltimateSkillName());
            System.out.printf("   Stamina Regen: %d-%d per round%n",
                    hero.getStaminaRegenMin(),
                    hero.getStaminaRegenMax());
        }

        System.out.println("\n[ VILLAINS ]");
        System.out.println("-------------------------------------------");
        ArrayList<Character> villains = getAllVillains();
        for (int i = 0; i < villains.size(); i++) {
            Character villain = villains.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i+1), villain.getName(), villain.getMaxHp(), villain.getAttack(), villain.getMaxStamina());
            System.out.printf("   Skills: %s, %s, %s%n",
                    villain.getBasicAttackName(),
                    villain.getSpecialSkillName(),
                    villain.getUltimateSkillName());
            System.out.printf("   Stamina Regen: %d-%d per round%n",
                    villain.getStaminaRegenMin(),
                    villain.getStaminaRegenMax());
        }
        System.out.println("===========================================");
    }
}