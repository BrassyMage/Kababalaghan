public class Character{
    private String name;
    private String type;
    private int health;
    private int mana;

    public Character(String name, String type, int health, int mana){
        this.name = name;
        this.type = type;
        this.health = health;
        this.mana = mana;
    }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setType(String type){ this.type = type; }
    public String getType(){ return type; }

    public void setHealth(int health){ this.health = health;}
    public int getHealth(){ return health; }

    public void setMana(int mana){ this.mana = mana; }
    public int getMana(){return mana; }
}