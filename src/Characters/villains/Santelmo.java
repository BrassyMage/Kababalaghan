package Characters.villains;

import Characters.Character;

public class Santelmo extends Character{
    private int heatLevel = 0;

    public Santelmo(){
        super("Santelmo", "Villain", 150, 25, 100, 100, "SpARK!", "BlaZe", "FiREBALL!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 15;
        this.staminaRegenMax = 30;
    }

    //spark
    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(12);
        heatLevel++;
        System.out.println("Nagbabaga! Santelmo's heat rises to " + heatLevel + "!");
        target.takeDamage(damage);
    }


    //blaze
    @Override
    public void specialSkill(Character target){
        if (spendStamina(specialSkillStaminaCost)){
            int bonusDamage = heatLevel * 5;
            int damage = attack * 2 + random.nextInt(18) + bonusDamage;
            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("Damhin mo ang init! (Bonus Heat Damage: " + bonusDamage + ")");
            target.takeDamage(damage);
            heatLevel += 2;
        }
    }

    // fireball
    @Override
    public void ultimateSkill(Character target){
        if (spendStamina(ultimateSkillStaminaCost)){
            int explosionDamage = heatLevel * 15;
            int damage = attack * 3 + random.nextInt(28) + explosionDamage;
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Sundan mo ako... patungo sa kabilang buhay! SUNOG!");

            if (heatLevel > 0) {
                System.out.println("MASSIVE EXPLOSION! Consumed " + heatLevel + " heat for " + explosionDamage + " extra damage!");
            }

            target.takeDamage(damage);
            heatLevel = 0;
        }
    }
}

