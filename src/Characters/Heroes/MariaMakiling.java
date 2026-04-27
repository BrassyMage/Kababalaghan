package Characters.heroes;

import Characters.Character;

public class MariaMakiling extends Character {

    public MariaMakiling() {
        super("Maria Makiling", "Hero", 130, 22, 110, 110,
                "Suntok ng Diwata",
                "Yakap ng Kagubatan",
                "Bangon, Kalikasan!");

        // Guardian stats - balanced with healing
        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 18;
        this.ultimateSkillStaminaCost = 45;
        this.staminaRegenMin = 20;
        this.staminaRegenMax = 35;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(12);
        System.out.println("Tinatawag ang mga ugat mula sa lupa!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(15);
            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("Yumayakap ang mga puno sa kalaban!");
            target.takeDamage(damage);

            // Heal self
            int heal = 25;
            this.hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " recovers " + heal + " HP from nature's blessing!");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(30);
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Gumagalaw ang buong kagubatan! Tumutulong ang mga hayop at puno!");
            target.takeDamage(damage);
            System.out.println(target.getName()+" took " + damage);

            // Big heal
            int heal = 45;
            this.hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " fully restores " + heal + " HP from nature's power!");
        }
    }
}