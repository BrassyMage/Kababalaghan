package Characters.heroes;

import Characters.Character;

public class DaragangMagayonHeir extends Character {

    public DaragangMagayonHeir() {
        super("Daragang Magayon's Heir", "Hero", 200, 4, 15, 100);
    }

    @Override
    public void basicAttack(Character target) {
        if (spendStamina(10)) {
            int damage = attack + 12;
            System.out.println(name + " releases Ash Flare from the volcano!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(19)) {
            int damage = attack + 20;
            System.out.println(name + " erupts with Cinder Burst!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(38)) {
            int damage = attack + 42;
            System.out.println(name + " unleashes Wrath of the Peak!");
            target.takeDamage(damage);
        }
    }
}