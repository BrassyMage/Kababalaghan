package Characters.villains;

import Characters.Character;

public class Santelmo extends Character {
    public Santelmo(){
        super("Santelmo", "Villain", 120, 22);

    }

    @Override
    public void basicAttack(Character target){
        int damage = attack;
        System.out.println(name + " taps with a Spark!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target){
        int damage = attack + 15;
        System.out.println(name + " spits out a B")
    }

}
