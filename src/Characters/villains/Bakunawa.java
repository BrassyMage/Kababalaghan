package Characters.villains;

import Characters.Character;

public class Bakunawa extends Character {
        public Bakunawa() {
            super("Bakunawa", "Villian", 210, 10);
        }

    @Override
    public void basicAttack(Character target) {
        int damage = attack+3;
        System.out.println(name + " lashes with tidal fury!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + 13;
        System.out.println(name + " bites at the moon's glow!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack + 30;

        System.out.println(name + " devours the moon!");
        target.takeDamage(damage);
    }
}


