package models.entities;

public class Projectile {
    private float x;
    private final int y; // The row it's traveling in
    private final int damage;
    private final float speed;
    private final PlantTag effectTag; // e.g., ICE for slowing, PEA for normal

    public Projectile(float x, int y, int damage, float speed, PlantTag effectTag) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.speed = speed;
        this.effectTag = effectTag;
    }

    public void move() {
        this.x += speed; // Projectiles move right
    }

    public float getX() { return x; }
    public int getY() { return y; }
    public int getDamage() { return damage; }
    public PlantTag getEffectTag() { return effectTag; }
}