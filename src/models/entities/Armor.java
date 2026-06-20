package models.entities;

public class Armor {
    private final String armorName;
    private int health;
    private final boolean isMetallic;

    public Armor(String armorName, int health, boolean isMetallic) {
        this.armorName = armorName;
        this.health = health;
        this.isMetallic = isMetallic;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public boolean isDestroyed() {
        return this.health <= 0;
    }

    public String getArmorName() { return armorName; }
    public int getHealth() { return health; }
    public boolean isMetallic() { return isMetallic; }
}