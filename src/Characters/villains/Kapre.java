package Characters.villains;

import Characters.Character;

public class Kapre extends Character {
    public Kapre() {
        super("Kapre", "Villain", 170, 17,100, 100,
                "Suntok ng Higante",
                "Usok ng Sigarilyo",
                "Galit ng Bantay-Puno");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 18;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 8;
        this.staminaRegenMax = 20;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(8);
        System.out.println("Umuuga ang lupa sa lakas ng suntok ng Kapre!");

        target.takeDamage(damage);
        System.out.println(target.getName() + " took " + damage + " damage.");
    }

    @Override
    public void specialSkill(Character target) {

        if (spendStamina(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(12);
            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("Bumabalot ang makapal na usok... naliligaw ang diwa ng kalaban!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");

            int heal = 12;
            this.hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " restores " + heal + " HP.");
        }
    }

    @Override
    public void ultimateSkill(Character target) {

        if (spendStamina(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(25) + 10;
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Nagngangalit ang Kapre sa sinumang lumalapastangan sa kanyang punong binabantayan!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }
}