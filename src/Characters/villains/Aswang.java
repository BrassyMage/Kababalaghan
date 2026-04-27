package Characters.villains;

import Characters.Character;

public class Aswang extends Character {

    public Aswang() {
        super("Aswang", "Villain", 110, 25, 90, 90,
                "Kagat ng Aswang",
                "Pagbabaligtad",
                "Blood Moon Feast");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 15;
        this.staminaRegenMax = 30;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(12);
        System.out.println("Mabilis na kagat mula sa dilim!");
        target.takeDamage(damage);

    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(18);
            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("Nagbabagong anyo ang Aswang!");
            target.takeDamage(damage);


            // Life steal - recover HP from damage dealt
            int heal = damage / 3;
            this.hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " steals " + heal + " HP from the victim!");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(28);
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Dinudumog ng mga Aswang ang kalaban!");
            target.takeDamage(damage);


            // Big life steal
            int heal = damage / 2;
            this.hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " feasts on blood and recovers " + heal + " HP!");
        }
    }
}