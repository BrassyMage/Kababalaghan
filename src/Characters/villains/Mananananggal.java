package Characters.villains;

import Characters.Character;

public class Mananananggal extends Character {

    public Mananananggal() {
        super("Manananggal", "Villain", 100, 20, 150, 150,
                "Kalas ng Laman",
                "Hapdi ng Paglipad",
                "Pagtipon ng Hating Katawan!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 10;
        this.staminaRegenMax = 25;
    }

    // stance(if flying do more dmg,take more dmg)
    boolean isFlying = false;
    int flyVulnerability = 0;

    @Override
    public void basicAttack(Character target) {

        int damage = attack + random.nextInt(10);

        int cost = isFlying ? 20 : 0;
        if (!spendStamina(cost)) return;

        if (isFlying) {

            int followUp = attack / 2;

            damage += 8;
            damage += flyVulnerability;

            System.out.println("Sumisirit ang katawan sa hangin... may gutom na bumabalot sa dilim.");
            System.out.println("Ang hiwalay na laman ay sumusunod na parang aninong kumakagat.");

            target.takeDamage(damage);


            target.takeDamage(followUp);


        } else {

            System.out.println("🧍 Ang mabigat na katawan ay humahagis sa lupa na parang sumpa.");


            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {

        if (spendStamina(specialSkillStaminaCost)) {

            isFlying = !isFlying;

            if (isFlying) {
                flyVulnerability = 20;

                System.out.println(name + " uses " + specialSkillName + "!");
                System.out.println("Nagkakalas ang laman... ang dilim ay bumubukas.");
                System.out.println("Ang itaas na katawan ay lumulutang, naghahanap ng biktima...");
                System.out.println("Ang katawan ay nagiging marupok sa himpapawid!");
            } else {
                flyVulnerability = 0;

                System.out.println(name + " uses " + specialSkillName + "!");
                System.out.println("Bumabalik ang laman sa lupa...");
                System.out.println("Ang katawang hinati ay muling nagiging isa... ngunit hindi ganap.");
            }
        }
    }

    @Override
    public void ultimateSkill(Character target) {

        if (spendStamina(ultimateSkillStaminaCost)) {

            int damage = attack * 3 + random.nextInt(35);

            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("Ang hiwalay na laman ay nagtatagpo sa isang pagputok ng dilim.");

            if (isFlying) {
                System.out.println("Mula sa himpapawid, bumabagsak ang gutom na anino.");
                damage += attack;
            } else {
                System.out.println("Mula sa lupa, sumisigaw ang katawan na hindi dapat nabubuo.");
            }


            target.takeDamage(damage);
        }
    }
}