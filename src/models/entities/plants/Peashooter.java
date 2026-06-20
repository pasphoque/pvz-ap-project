package models.entities.plants;

import models.entities.Plant;
import models.entities.PlantCategory;
import models.entities.PlantTag;
import models.entities.Projectile;
import models.GameEngine;

public class Peashooter extends Plant {
    private int ticksSinceLastShot = 0;
    private final int fireRate = 15; // Shoots every 1.5 seconds (15 ticks)
    private GameEngine engine;

    public Peashooter(int x, int y, GameEngine engine) {
        super(x, y, "Peashooter", 100, 300, 20, 50);
        this.category = PlantCategory.SHOOTER;
        this.tags.add(PlantTag.PEA);
        this.engine = engine;
    }

    @Override
    public void performAction() {
        ticksSinceLastShot++;
        if (ticksSinceLastShot >= fireRate) {
            shoot();
            ticksSinceLastShot = 0;
        }
    }

    private void shoot() {
        // Spawn a projectile just slightly ahead of the plant (x + 0.5)
        Projectile pea = new Projectile(this.x + 0.5f, this.y, this.damage, 0.2f, PlantTag.PEA);
        engine.addProjectile(pea);
    }
}