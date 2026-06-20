package models.entities;

import java.util.Stack;

public abstract class Zombie {
    protected float x; // Float because they move continuously
    protected float y;
    protected String name;
    protected float speed; // Movement per tick
    protected int baseDamage;
    protected int baseHp;
    protected int currentHp;
    protected int waveCost;
    protected boolean isGlowing = false;
    protected Stack<Armor> armors;
    protected AttackStrategy attackStrategy;

    public Zombie(float x, float y, String name, float speed, int baseDamage, int baseHp, int waveCost) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.speed = speed;
        this.baseDamage = baseDamage;
        this.baseHp = baseHp;
        this.currentHp = baseHp;
        this.waveCost = waveCost;
        this.armors = new Stack<>();
    }

    public void move() {
        this.x -= speed; // Zombies move left
    }

    // Called every tick
    public abstract void performAction();

    public void takeDamage(int amount) {
        if (!armors.isEmpty()) {
            Armor topArmor = armors.peek();
            topArmor.takeDamage(amount);
            if (topArmor.isDestroyed()) {
                armors.pop(); // Remove destroyed armor
            }
        } else {
            this.currentHp -= amount;
            if (this.currentHp < 0) this.currentHp = 0;
        }
    }

    public boolean isDead() {
        return this.currentHp <= 0 && armors.isEmpty();
    }

    // Getters
    public String getName() { return name; }
    public float getX() { return x; }
    public int getCurrentHp() { return currentHp; }
}