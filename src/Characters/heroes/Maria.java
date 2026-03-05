package Characters.heroes;

import Characters.Character;

public class Maria extends Character {
    public Maria(){
        super("Maria Makiling's Cousin", "Hero", 135, 25, 10, 40);
    }

    @Override
    public void basicAttack(Character target){
        if(spendStamina(11)) {
            int damage = attack + 6;
            System.out.println(name + "uses vines to hit you!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target){
        if (spendStamina(24)) {
            int damage = attack + 13;
            System.out.println(name + "throws boulders!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target){
        if(spendStamina(35)) {
            int damage = attack * 3;
            System.out.println(name + "conjures a tornado!");
            target.takeDamage(damage);
        }
    }
}
