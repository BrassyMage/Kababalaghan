package Characters.heroes;

import Characters.Character;

public class Tikoy extends Character {

    public Tikoy() {
        super("Tikoy", "Hero", 100, 2, 15, 100);
    }

    @Override
    public void basicAttack(Character target) {
        if (spendStamina(5)) {
            int damage = attack + 3;
            System.out.println(name + " uses Glutinous Slap! Squish!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(8)) {
            int damage = attack + 5;
            System.out.println(name + " throws a Sticky Trap! The enemy struggles!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(12)) {
            int damage = attack + 15;
            System.out.println(name + " unleashes Lunar Feast! Sweet destruction!");
            target.takeDamage(damage);
        }
    }
}