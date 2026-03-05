package Characters.heroes;

import Characters.Character;

public class BaganiNgKape extends Character {

    public BaganiNgKape() {
        super("Ibong Adarna Jr.", "Hero", 115, 19,15,100);
    }

    @Override
    public void basicAttack(Character target) {
        if (spendStamina(10)) {
            int damage = attack;
            System.out.println(name + " swings a glowing bean! Crack!\"");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(20)) {
            int damage = attack + 15;
            System.out.println(name + " zips through the air! Whoosh!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(30)) {
            int damage = attack + 25;
            System.out.println(name + " pours a boiling wave! Splash!");
            target.takeDamage(damage);
        }
    }
}