package Characters.heroes;

import Characters.Character;

public class IbongAdarnaJr extends Character {

    public IbongAdarnaJr() {
        super("Ibong Adarna Jr.", "The Melodramatic Bird", "Hero", 100, 10, 10);
        this.origin = "Mount Makiling";
        this.personality = "Dramatic, colorful, and sings to heal or annoy";

        setS1("Feather Flick", 12, 0);
        setS2("Healing Song", 20, 25);
        setS3("Rainbow Sneezes", 35, 50);
        setS4("Melodramatic Aria", 30, 40);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(name + " flicks rainbow feathers!");
        target.takeDamage(s1Damage + getAttack());
        regenStamina();
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(s2StaminaCost)) {
            System.out.println(name + " sings a healing melody!");
            heal(20);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(s3StaminaCost)) {
            System.out.println(name + " sneezes rainbow colors everywhere!");
            target.takeDamage(s3Damage + getAttack() + 5);
            if (Math.random() > 0.5) {
                target.stun(1);
            }
        }
    }

    @Override
    public void signatureMove(Character target) {
        if (spendStamina(s4StaminaCost)) {
            System.out.println(name + " sings a DRAMATIC aria!");
            target.takeDamage(s4Damage + getAttack() + 8);
            heal(10);
        }
    }
}