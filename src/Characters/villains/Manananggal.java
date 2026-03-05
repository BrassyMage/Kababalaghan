package Characters.villains;

import Characters.Character;

public class Manananggal extends Character {
    public Manananggal() {
        super("Manananggal", "Villian", 180, 15,15,100);
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack+5;
        System.out.println(name + " slices through the air!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 10;
        System.out.println(name + " detaches and strikes from above!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 25;
        System.out.println(name + " swoops down for a deadly feast!");
        target.takeDamage(damage);
    }
}