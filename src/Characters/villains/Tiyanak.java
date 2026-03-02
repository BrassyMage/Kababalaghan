package Characters.villains;

import Characters.Character;

public class Tiyanak extends Character {

    public Tiyanak() {
        super("Tiyanak", "Villain", 120, 22);
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack;
        System.out.println(name + " cries like a baby then attacks!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 15;
        System.out.println(name + " lets out a piercing scream!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 25;
        System.out.println(name + " transforms into its true demonic form!");
        target.takeDamage(damage);
    }
}