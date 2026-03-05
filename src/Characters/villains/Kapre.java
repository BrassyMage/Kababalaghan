package Characters.villains;

import Characters.Character;

public class Kapre extends Character {

    public Kapre() {
        super("Kapre", "Villain", 190, 22,15,100);
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack;
        System.out.println(name + " flicks his cigar. Cling.");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 15;
        System.out.println(name + " vanishes into a puff of smoke. Whoosh!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 25;
        System.out.println(name + " releases a massive cloud. Cough!");
        target.takeDamage(damage);
    }
}