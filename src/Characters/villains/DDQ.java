package Characters.villains;

import Characters.Character;

public class Diwata extends Character {
    public class Diwata(){
        super("Diwata Drama Queen", "Villain", 140, 20);
    }

    @Override
    public void basicAttack(Character target){
        if(spendStamina(10)) {
            int damage = attack;
            System.out.println(name + "commands you to hit yourself!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target){
            if(spendStamina(11)) {
                int damage = attack + 13;
                System.out.println(name + "shape shift into a jaguar and bites you!");
                target.takeDamage(damage);
            }
    }

    @Override
    public void ultSkill(Character target){
                if(spendStamina(11)) {
                    int damage = attack * 3;
                    System.out.println(name + "lifts 25 tons and throws it at you!");
                    target.takeDamage(damage);
                }
    }
}
