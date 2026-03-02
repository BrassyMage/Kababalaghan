package Characters.heroes;

import Characters.Character;

public class JuanTamad extends Character {

    public JuanTamad() {
        // Call parent constructor: (name, title, type, maxHp, attack, defense)
        super("Juan Tamad", "The Lazy Genius", "Hero", 120, 12, 8);
        this.origin = "Visayas";
        this.personality = "Surprisingly lazy but accidentally brilliant";

        setS1("Tamad Punch", 15, 0);
        setS2("Accidental Wisdom", 25, 30);
        setS3("Strategic Nap", 40, 60);
        setS4("Power Nap", 0, 20);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(name + " lazily swings an arm... and hits!");
        target.takeDamage(s1Damage + getAttack());
        regenStamina();
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(s2StaminaCost)) {
            System.out.println(name + " accidentally says something brilliant!");
            target.takeDamage(s2Damage + getAttack() + 5);
            target.stun(1);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(s3StaminaCost)) {
            System.out.println(name + " takes a nap in enemy territory!");
            target.takeDamage(s3Damage + getAttack() + 10);
            setDefense(getDefense() + 3); // Using setter
        }
    }

    @Override
    public void signatureMove(Character target) {
        if (spendStamina(s4StaminaCost)) {
            System.out.println(name + " takes a POWER NAP!");
            heal(30);
            stamina = Math.min(maxStamina, stamina + 40);
        }
    }
}