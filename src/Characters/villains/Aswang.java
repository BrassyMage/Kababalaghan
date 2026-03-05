package Characters.villains;

import Characters.Character;

public class Aswang extends Character {

    public Aswang() {
        super("Aswang", "Villain", 150, 18,15,100);
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack;
        System.out.println(name + " claws at you!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 12;
        System.out.println(name + " transforms and attacks!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 20;
        System.out.println(name + " uses its deadly bite!");
        target.takeDamage(damage);
    }
}