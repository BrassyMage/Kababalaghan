package Characters.heroes;

import Characters.Character;

import java.util.*;

public class LapuLapu extends Character {
    public LapuLapu() {
        super("Lapu Lapu 2.0", "Hero", 120, 21,15,100);
    }
    Random random = new Random();
    @Override
    public void basicAttack(Character target) {
        if (spendStamina(12)) {
            int damage = random.nextInt(15) + 7;
            System.out.println(name + " swings his kampilan!");
            target.takeDamage(damage);
        }
    }
    @Override
    public void specialSkill(Character target) {
        if (spendStamina(21)) {
            int damage = attack + 15;
            System.out.println(name + " charges with Mactan's might!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(35)) {
            int damage = attack + 30;
            System.out.println(name + " strikes with the courage of Mactan!");
            target.takeDamage(damage);
        }
    }
}