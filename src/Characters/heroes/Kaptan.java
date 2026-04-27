package Characters.heroes;

import Characters.Character;

public class Kaptan extends Character {
    private int stormCharge = 0;
    public Kaptan() {
        super("Kaptan", "Hero", 180, 30, 100, 100,
                "KiDLAT",
                "BAGYOH!",
                "Hampas-Langit");

        // Burst/Combo stats - builds charges for massive damage
        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 60;
        this.staminaRegenMin = 20;
        this.staminaRegenMax = 35;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(15);
        stormCharge++;
        System.out.println("Umaatake ang kidlat!");
        System.out.println("Kaptan gathers static energy in the air! (Storm Charges: " + stormCharge + ")");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            System.out.println(name + " uses " + specialSkillName + "!");
            int baseDamage = attack * 2 + random.nextInt(20);

            System.out.println("Pinaulanan ka ng kidlat!");
            target.takeDamage(baseDamage);

            // chain lightning effect based on how much charge he built up
            if (stormCharge > 0) {
                System.out.println("⚡ CHAIN LIGHTNING triggers " + stormCharge + " extra times! ⚡");
                for (int i = 0; i < stormCharge; i++) {
                    int chainDamage = 10 + random.nextInt(15);
                    System.out.println("ZAP!");
                    target.takeDamage(chainDamage);
                }
                stormCharge = 0; // reset charges after discharging
            }
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            int damage = attack * 4 + random.nextInt(40);
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Bumagsak ang poot ng kalangitan! MATAPOS KA NA!");
            target.takeDamage(damage);

            // gives him instant setup for his next turn
            stormCharge += 3;
            System.out.println("The sky roars... Kaptan is overcharged! (+3 Storm Charges)");
        }
    }
}
