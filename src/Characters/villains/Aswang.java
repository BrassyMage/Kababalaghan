package Characters.villains;

import Characters.Character;

public class Aswang extends Character {

    public Aswang() {
        super("Aswang", "The Hangry Shape-shifter", "Villain", 150, 18, 5);
        this.origin = "Visayas";
        this.personality = "Always hungry, shape-shifting trickster";

        // Set skills
        setS1("Claw Swipe", 20, 0);
        setS2("Shape Shift", 30, 35);
        setS3("Night Hunt", 45, 65);
        setS4("Snack Time", 25, 30); // Signature - heals by damaging
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(name + " swipes with sharp claws!");
        target.takeDamage(s1Damage + attack);
        regenStamina();
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(s2StaminaCost)) {
            System.out.println(name + " shifts into a terrifying form!");
            target.takeDamage(s2Damage + attack + 3);
//            target.defense -= 2; // Lower defense
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(s3StaminaCost)) {
            System.out.println(name + " hunts under the full moon!");
            target.takeDamage(s3Damage + attack + 8);
            if (Math.random() * 100 < luck) {
                target.stun(1); // Chance to stun
            }
        }
    }

    @Override
    public void signatureMove(Character target) {
        if (spendStamina(s4StaminaCost)) {
            System.out.println(name + " takes a SNACK BREAK!");
            int damage = s4Damage + attack;
            target.takeDamage(damage);
            heal(damage / 2); // Heals half the damage dealt
            System.out.println(name + " looks satisfied... for now.");
        }
    }
}