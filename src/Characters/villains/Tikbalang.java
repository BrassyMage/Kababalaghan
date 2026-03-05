package Characters.villains;

import Characters.Character;

public class Tikbalang extends Character {
    public Tikbalang() {
        super("Tikbalang", "Villain", 200, 6, 15, 100);
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + 6;
        System.out.println(name + " stomps with Hoof Smash!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 15;
        System.out.println(name + " delivers a powerful Labyrinthine Kick!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 35;
        System.out.println(name + " summons Balete's Shadow!");
        target.takeDamage(damage);
    }
}