package Characters;

public abstract class Character {

    // ============== CORE STATS ==============
    protected String name;
    protected String title;              // e.g., "The Shape-shifter", "Moon-eater"
    protected String type;                // "Hero" or "Villain"
    protected String origin;              // Where they come from
    protected String personality;         // Short personality description

    // Health System
    protected int hp;
    protected int maxHp;

    // Energy System
    protected int stamina;
    protected int maxStamina = 100;

    // Combat Stats
    protected int attack;
    private int defense;
    protected int speed;                  // Who attacks first
    protected int luck;                    // For critical hits and random events

    // Level System
    protected int level = 1;
    protected int exp = 0;
    protected int expToNextLevel = 100;

    // ============== SKILLS ==============
    protected String s1Name;    // Basic Attack
    protected String s2Name;    // Special Skill
    protected String s3Name;    // Ultimate Skill
    protected String s4Name;    // Signature Move

    protected int s1Damage;
    protected int s2Damage;
    protected int s3Damage;
    protected int s4Damage;

    protected int s1StaminaCost;
    protected int s2StaminaCost;
    protected int s3StaminaCost;
    protected int s4StaminaCost;

    // ============== STATUS ==============
    protected boolean isAlive = true;
    protected boolean isDefending = false;
    protected boolean isStunned = false;
    protected int stunTurns = 0;

    // ============== CONSTRUCTOR ==============

    public Character(String name, String title, String type, int maxHp, int attack, int defense) {
        this.name = name;
        this.title = title;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;

        this.stamina = 50;
        this.speed = 10;
        this.luck = 5;
        this.origin = "Philippines";
        this.personality = "Mysterious";

        this.s1Name = "Basic Attack";
        this.s2Name = "Special Move";
        this.s3Name = "Ultimate";
        this.s4Name = "Signature";
    }

    // ============== ABSTRACT METHODS ==============

    public abstract void basicAttack(Character target);

    public abstract void specialSkill(Character target);

    public abstract void ultimateSkill(Character target);

    public abstract void signatureMove(Character target);

    // ============== COMMON METHODS ==============

    public void takeDamage(int damage) {
        // If defending, take half damage
        if (isDefending) {
            damage = damage / 2;
            System.out.println(name + " blocks! Damage reduced!");
            isDefending = false;
        }

        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;

        System.out.println(name + " took " + damage + " damage! HP: " + hp + "/" + maxHp);

        // Check if defeated
        if (this.hp <= 0) {
            this.isAlive = false;
            System.out.println(name + " has been defeated!");
        }
    }

    public void heal(int amount) {
        int oldHp = hp;
        hp = Math.min(maxHp, hp + amount);
        int healed = hp - oldHp;
        System.out.println(name + " healed " + healed + " HP!");
    }

    public boolean spendStamina(int cost) {
        if (stamina >= cost) {
            stamina -= cost;
            System.out.println(name + " used " + cost + " stamina. Remaining: " + stamina);
            return true;
        } else {
            System.out.println("Not enough stamina! Need " + cost + ", have " + stamina);
            return false;
        }
    }

    public void regenStamina() {
        int regen = 15 + (level * 2);
        stamina = Math.min(maxStamina, stamina + regen);
        System.out.println(name + " regained " + regen + " stamina");
    }

    public void defend() {
        isDefending = true;
        System.out.println(name + " takes a defensive stance!");
    }

    public boolean canAct() {
        if (isStunned) {
            System.out.println(name + " is stunned and cannot move!");
            stunTurns--;
            if (stunTurns <= 0) {
                isStunned = false;
                System.out.println(name + " recovers from stun!");
            }
            return false;
        }
        return true;
    }

    public void stun(int turns) {
        this.isStunned = true;
        this.stunTurns = turns;
        System.out.println(name + " is stunned for " + turns + " turns!");
    }

    public void displayInfo() {
        System.out.println("========================================");
        System.out.println("Name: " + name);
        System.out.println("Title: " + title);
        System.out.println("Type: " + type);
        System.out.println("Origin: " + origin);
        System.out.println("Personality: " + personality);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Stamina: " + stamina + "/" + maxStamina);
        System.out.println("Attack: " + attack + " | Defense: " + defense);
        System.out.println("Speed: " + speed + " | Luck: " + luck);
        System.out.println("Level: " + level + " | EXP: " + exp + "/" + expToNextLevel);
        System.out.println("========================================");
    }

    public void displaySkills() {
        System.out.println("========================================");
        System.out.println(name + "'s Skills:");
        System.out.println("1. " + s1Name + " - Damage: " + s1Damage + " | Stamina: " + s1StaminaCost);
        System.out.println("2. " + s2Name + " - Damage: " + s2Damage + " | Stamina: " + s2StaminaCost);
        System.out.println("3. " + s3Name + " - Damage: " + s3Damage + " | Stamina: " + s3StaminaCost);
        System.out.println("4. " + s4Name + " - Damage: " + s4Damage + " | Stamina: " + s4StaminaCost);
        System.out.println("========================================");
    }

    // ============== GETTERS & SETTERS ==============

    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getOrigin() { return origin; }
    public String getPersonality() { return personality; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getStamina() { return stamina; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public boolean isAlive() { return isAlive; }

    // ============== STAT MODIFICATION METHODS ==============


    public void setDefense(int defense) {
        this.defense = Math.max(0, defense);
    }

    public void setAttack(int attack) {
        this.attack = Math.max(0, attack);
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(0, speed);
    }
    public void setS1(String name, int damage, int cost) {
        this.s1Name = name;
        this.s1Damage = damage;
        this.s1StaminaCost = cost;
    }

    public void setS2(String name, int damage, int cost) {
        this.s2Name = name;
        this.s2Damage = damage;
        this.s2StaminaCost = cost;
    }

    public void setS3(String name, int damage, int cost) {
        this.s3Name = name;
        this.s3Damage = damage;
        this.s3StaminaCost = cost;
    }

    public void setS4(String name, int damage, int cost) {
        this.s4Name = name;
        this.s4Damage = damage;
        this.s4StaminaCost = cost;
    }
}