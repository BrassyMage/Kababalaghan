package Characters.heroes;

import Characters.Character;

public class JuanTamad extends Character {

    public JuanTamad() {
        super("Juan Tamad", "Hero", 120, 20);
    }

    @Override
    public void basicAttack(Character target) {
        if (spendStamina(10)) {
            int damage = attack + 5;
            System.out.println(name + " throws a coconut!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(20)) {
            int damage = attack + 15;
            System.out.println(name + " attacks from his dream!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(35)) {
            int damage = attack + 30;
            System.out.println(name + " finally gets serious!");
            target.takeDamage(damage);
        }
    }
}