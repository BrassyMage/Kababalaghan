package Characters.villains;

import Characters.Character;

public class Santelmo extends Character {
    public Santelmo(){super("Santelmo", "Villain", 175, 16, 20, 100);}

    @Override
    public void basicAttack(Character target){
        int damage = attack;
        System.out.println(name + " taps with a Spark!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target){
        int damage = attack + 15;
        System.out.println(name + " spits out BlAAAAAZzZEE!");
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target){
        int damage = attack + 25;
        System.out.println(name + " turns the world into a FIRE STORM!");
        target.takeDamage(damage);
    }

}
