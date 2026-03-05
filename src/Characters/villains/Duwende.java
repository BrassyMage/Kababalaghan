package Characters.villains;

import Characters.Character;

public class Duwende extends Character {
    public class Duwende(){
        super("Black Duwende", "Creature", 120, 30);
    }

    @Override
    public void basicAttack(Character target){
        if(spendStamina(10)) {
            int damage = attack;
            System.out.println(name + "scratches you!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target){
        if(spendStamina(15)) {
            int damage = attack + 13;
            System.out.println(name + "bites ankles!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultSkill(Character target){
        if(spendStamina(26)) {
            int damage = attack * 3;
            System.out.println(name + "hits you with powerful poison!");
            target.takeDamage(damage);
        }
    }

}
