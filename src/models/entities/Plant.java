package models.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Plant {
    protected int x;
    protected int y;
    protected String name;
    protected PlantCategory category;
    protected List<PlantTag> tags;
    protected int sunCost;
    protected int baseHp;
    protected int currentHp;
    protected int damage;
    protected int rechargeTime; // in ticks
    protected int currentLevel = 1;
    protected boolean isBoosted = false;
    protected AttackStrategy attackStrategy;

    public Plant(int x, int y, String name, int sunCost, int baseHp, int damage, int rechargeTime) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.sunCost = sunCost;
        this.baseHp = baseHp;
        this.currentHp = baseHp;
        this.damage = damage;
        this.rechargeTime = rechargeTime;
        this.tags = new ArrayList<>();
    }

    // Called every tick by the GameEngine
    public abstract void performAction();

    public void applyPlantFood() {
        this.isBoosted = true;
        // Specific boost logic will be handled in subclasses
    }

    public void takeDamage(int amount) {
        this.currentHp -= amount;
        if (this.currentHp < 0) this.currentHp = 0;
    }

    public boolean isDead() {
        return this.currentHp <= 0;
    }

    // Getters
    public String getName() { return name; }
    public int getSunCost() { return sunCost; }
    public int getCurrentHp() { return currentHp; }
}