package Characters;

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
    Random random = new Random();
    public Character(String name, String type, int maxHp, int attack, int stamina, int maxStamina) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.stamina = maxStamina;
        this.maxStamina = maxStamina;
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
            System.out.println("Not enough stamina!");
            return false;
        }
    }

    public void regenStamina() {
        stamina = Math.min(maxStamina, stamina + 20);
    }

    // Getters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getStamina() { return stamina; }
    public int getAttack() { return attack; }
    public boolean isAlive() { return isAlive; }
}