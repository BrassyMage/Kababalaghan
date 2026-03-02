package Characters.heroes;

import Characters.Character;

public class IbongAdarnaJr extends Character {

    public IbongAdarnaJr() {
        super("Ibong Adarna Jr.", "Hero", 100, 25);
    }

    @Override
    public void basicAttack(Character target) {
        if (spendStamina(10)) {
            int damage = attack;
            System.out.println(name + " sings softly!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(20)) {
            int damage = attack + 15;
            System.out.println(name + " sings a magical song!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(30)) {
            int damage = attack + 25;
            System.out.println(name + " sings the legendary song!");
            target.takeDamage(damage);
        }
    }
}