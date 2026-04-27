package Characters.heroes;

import Characters.Character;

public class Apolaki extends Character {

    public Apolaki() {
        super("Apolaki", "Hero", 150, 25, 100, 100,
                "Banal ng Digmaan",
                "Bagsik ng Araw",
                "Paghampas ng Bathalang Mandirigma!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 15;
        this.staminaRegenMax = 30;
    }

    // burst mechanic
    int burstStacks = 0;

    @Override
    public void basicAttack(Character target) {

        int damage = attack + random.nextInt(10);

        System.out.println("Umiinit ang hangin sa bawat galaw ni Apolaki!");

        System.out.println(target.getName()+" took " + damage);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {

        if (spendStamina(specialSkillStaminaCost)) {

            int damage = attack + random.nextInt(20);

            burstStacks++;
            if (burstStacks > 5) burstStacks = 5;

            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("Lumalakas ang init ng digmaan! [" + (burstStacks * 10) + "% charge]");

            System.out.println(target.getName()+" took " + damage);
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {

        if (spendStamina(ultimateSkillStaminaCost)) {

            int damage = attack * 3 + random.nextInt(35);

            int chance = burstStacks * 10;

            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Ang mundo ay yayanig sa ilalim ng hatol ng walang hanggang sinag!");

            if (random.nextInt(100) < chance) {

                damage *= 4;
                System.out.println("🔥 BURST x4! PAGHUKOM NI APOLAKI!");

            }



            burstStacks = 0;

            target.takeDamage(damage);
        }
    }
}