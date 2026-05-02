# KABABALAGHAN

A console-based RPG game featuring Filipino mythological characters in turn-based battles.

## Project Overview

KABABALAGHAN is a Java-based game that brings Filipino folklore to life through interactive battles between heroes and villains from Philippine mythology. Players can engage in single-player mode against AI opponents or multiplayer battles with friends.

## Features

- **Character Selection**: Choose from various Filipino mythological heroes and villains
- **Battle System**: Turn-based combat with unique skills and stamina management
- **Game Modes**:
  - Singleplayer (vs AI)
  - Multiplayer (PVP)
  - Story Mode (planned)
- **Character Classes**:
  - Heroes: Support-focused with healing abilities
  - Villains: Aggressive with life-stealing mechanics

## Architecture

### Core Components

- `src/Main.java`: Entry point with menu system
- `src/Characters/Character.java`: Abstract base class for all characters
- `src/Gamemode/BattleSystem.java`: Core battle logic and AI
- `src/Gamemode/Singleplayer.java`: PVE mode implementation
- `src/Gamemode/Multiplayer.java`: PVP mode with scoring

### Character System

All characters extend the `Character` abstract class and implement three skills:
- **Basic Attack**: Low-cost or free attack
- **Special Skill**: Moderate damage/cost with unique effects
- **Ultimate Skill**: High damage/cost with powerful abilities

Characters have HP, Stamina, and randomized stamina regeneration per round.

## Getting Started

### Prerequisites
- Java 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE

### Building the Project
```bash
# Compile all Java files
javac -d out src/**/*.java

# Run the game
java -cp out Main
```

### Development Setup
1. Clone repo
2. IDE: IntelliJ IDEA
3. Build & run `Main.java`

## Adding New Characters

1. Create a new class in the appropriate subpackage:
   - `src/Characters/heroes/` for heroes
   - `src/Characters/villains/` for villains

2. Extend the `Character` class with:
   - Stats (HP, attack, stamina)
   - Skill names (in Filipino for thematic consistency)
   - Stamina costs and regeneration settings

3. Implement the three abstract methods:
   - `basicAttack(Character target)`
   - `specialSkill(Character target)`
   - `ultimateSkill(Character target)`

4. Add the character to the appropriate list in `Character.java`:
   - `getAllHeroes()` for heroes
   - `getAllVillains()` for villains

### Sample Character Implementation
```java
public class NewHero extends Character {
    public NewHero() {
        super("New Hero", "Hero", 120, 20, 100, 100,
              "Basic Attack Name", "Special Skill Name", "Ultimate Skill Name");
        // Customize stamina costs and regen
        this.specialSkillStaminaCost = 25;
        this.ultimateSkillStaminaCost = 50;
        this.staminaRegenMin = 18;
        this.staminaRegenMax = 28;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(10);
        System.out.println("Attack description");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            // Special ability logic
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            // Ultimate ability logic
        }
    }
}
```

## Battle Mechanics

### Turn Structure
1. Display current HP/Stamina bars
2. Player selects action (1-3)
3. Execute action or fallback to basic attack if insufficient stamina
4. Enemy turn (AI or human)
5. Stamina regeneration for both characters
6. Repeat until one character is defeated

### AI Behavior
- Prioritizes ultimate skills when HP < 30% and stamina available
- 30% chance to use ultimate when possible
- 40% chance to use special when possible
- Defaults to basic attack otherwise

## Contributing

When adding new features:
- Follow the existing code style and naming conventions
- Use Filipino language for skill names and descriptions
- Test in both singleplayer and multiplayer modes
- Ensure characters reset properly between battles

## Characters (as of May 2, 2026)

### Heroes
- **Maria Makiling**: Nature guardian with healing abilities and forest control
- **Apolaki**: Sun god with fire-based attacks and solar empowerment
- **Magwayen**: Spirit of the underworld with soul-draining attacks and healing
- **Kaptan**: Lightning deity with storm powers and thunder strikes
- **Mayari**: Moon goddess with counter mechanics and celestial damage reduction

### Villains
- **Aswang**: Shape-shifting creature with life steal and blood magic
- **Mananananggal**: Winged monster with blood magic and self-severing abilities
- **Kapre**: Giant tree guardian with smoke abilities and earth-shaking attacks
- **Santelmo**: Ghostly entity with ethereal attacks and possession
- **Tikbalang**: Trickster spirit with evasion, confusion, and supernatural speed

## Future Plans

- Story Mode campaign
- Enhance AI difficulty levels
- Additional characters from Filipino mythology

[Use Case Diagram]
<img width="600" height="400" alt="use-case-diagram" src="https://github.com/user-attachments/assets/56167d73-46e2-4b90-a5b2-c1acc1d7be89" />

[class Diagram]
<img width="600" height="400" alt="class-diagram" src="https://github.com/user-attachments/assets/901b17ae-1aa8-47a8-9289-3b67ffbe0e57" />
